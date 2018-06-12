package com.mcit.sell.service.impl;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<OrderMasterDTO> findAllList(String buyerOpendId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderMasterDTO findOne(String buyerOpendId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(buyerOpendId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDERMASTER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        return null;
    }

    @Override
    public OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO) {
        return null;
    }

    @Override
    public OrderMasterDTO finish(OrderMasterDTO orderMasterDTO) {
        return null;
    }

    @Override
    public OrderMasterDTO paid(OrderMasterDTO orderMasterDTO) {
        return null;
    }
}
