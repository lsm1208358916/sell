package com.mcit.sell.service.impl;

import com.mcit.sell.converter.OrderMaster2OrderMasterDTO;
import com.mcit.sell.dataobject.OrderDetail;
import com.mcit.sell.dataobject.OrderMaster;
import com.mcit.sell.dataobject.ProductInfo;
import com.mcit.sell.dto.CartDTO;
import com.mcit.sell.dto.OrderMasterDTO;
import com.mcit.sell.enums.OrderStatusEnum;
import com.mcit.sell.enums.PayStatusEnum;
import com.mcit.sell.enums.ResultEnum;
import com.mcit.sell.exception.SellException;
import com.mcit.sell.repository.OrderDetailRepository;
import com.mcit.sell.repository.OrderMasterRepository;
import com.mcit.sell.service.OrderMasterService;
import com.mcit.sell.service.ProductInfoService;
import com.mcit.sell.utils.KeyUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:
 * 订单业务实现
 *
 * @author LSM
 * @create 2018-06-12 9:40
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderMasterDTO create(OrderMasterDTO orderMasterDTO) {
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.genUniqueKey();
        /*List<CartDTO> cartDTOList = new ArrayList<>();*/
        //查询单价，数量（单价不能从前台获取，必须从数据库，安全）
        for (OrderDetail orderDetail : orderMasterDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());//设置订单详情的id
            orderDetail.setOrderId(orderId);//设置统一的订单id
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);

            /*CartDTO cartDTO = new CartDTO(productInfo.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);*/
        }

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //扣库存
        List<CartDTO> cartDTOList = orderMasterDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);
        return orderMasterDTO;


    }

    @Override
    public Page<OrderMasterDTO> findAllList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderMasterDTO> orderMasterDTOList = OrderMaster2OrderMasterDTO.converter(orderMasterPage.getContent());
        Page<OrderMasterDTO> OrderMasterDTO = new PageImpl<>(orderMasterDTOList, pageable, orderMasterPage.getTotalElements());
        return OrderMasterDTO;
    }

    @Override
    public OrderMasterDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDERMASTER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties(orderMaster, orderMasterDTO);
        orderMasterDTO.setOrderDetailList(orderDetailList);
        return orderMasterDTO;
    }

    @Override
    @Transactional
    public OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO) {
        //判断订单状态
        if (!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确  orderMasterDTO={}", orderMasterDTO);
            throw new SellException(ResultEnum.ORDERMASTER_STATUS_ERROR);
        }

        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderMasterDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null) {
            log.error("【取消订单】 订单状态更新失败  orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDERMASTERSTATUS_UPDATE_ERROR);
        }
        //返还库存
        List<CartDTO> cartDTOList = orderMasterDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        //如果用户已经支付，退款
        if (orderMasterDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)) {
            //TODO
        }
        return orderMasterDTO;
    }

    @Override
    @Transactional
    public OrderMasterDTO finish(OrderMasterDTO orderMasterDTO) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderMasterDTO.getOrderId());
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());//订单状态改为完结
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());//支付状态改为支付
        OrderMaster result = orderMasterRepository.save(orderMaster);

        //返回数据设置订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        BeanUtils.copyProperties(orderMaster, orderMasterDTO);
        orderMasterDTO.setOrderDetailList(orderDetailList);
        return orderMasterDTO;
    }

    @Override
    @Transactional
    public OrderMasterDTO paid(OrderMasterDTO orderMasterDTO) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderMasterDTO.getOrderId());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());//支付状态改为支付
        OrderMaster result = orderMasterRepository.save(orderMaster);

        //返回数据设置订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        BeanUtils.copyProperties(orderMaster, orderMasterDTO);
        orderMasterDTO.setOrderDetailList(orderDetailList);
        return orderMasterDTO;
    }
}
