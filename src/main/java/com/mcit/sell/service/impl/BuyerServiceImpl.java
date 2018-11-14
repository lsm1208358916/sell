package com.mcit.sell.service.impl;

import com.mcit.sell.dto.OrderMasterDTO;
import com.mcit.sell.enums.ResultEnum;
import com.mcit.sell.exception.SellException;
import com.mcit.sell.service.BuyerService;
import com.mcit.sell.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * 买家服务实现
 *
 * @author lsm12
 * @create 2018-07-02 9:39
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public OrderMasterDTO findOrderMasterOne(String openid, String orderId) {
        return checkAuthor(openid, orderId);
    }

    @Override
    public OrderMasterDTO cancelOrderMaster(String openid, String orderId) {
        OrderMasterDTO orderMasterDTO = checkAuthor(openid, orderId);
        if (orderMasterDTO == null) {
            log.info("【查询订单】 订单不存在");
            return null;
        }
        OrderMasterDTO result = orderMasterService.cancel(orderMasterDTO);
        return result;
    }

    //定义一个共有的用户验证
    private OrderMasterDTO checkAuthor(String openid, String orderId) {

        OrderMasterDTO orderMasterDTO = orderMasterService.findOne(orderId);
        if (orderMasterDTO == null) {
            return null;
        }
        //验证用户
        if (!orderMasterDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【用户认证】 不是同一用户。 openid={}，orderMasterDTO={}", openid, orderMasterDTO);
            throw new SellException(ResultEnum.CHECK_AUTHOR_ERROR);
        }
        return orderMasterDTO;
    }
}
