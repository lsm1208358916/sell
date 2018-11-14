package com.mcit.sell.service;

import com.mcit.sell.dto.OrderMasterDTO;

/**
 * 描述:
 * 买家
 *
 * @author lsm12
 * @create 2018-07-02 9:34
 */
public interface BuyerService {
    //根据openid，订单orderId查询订单详情
    OrderMasterDTO findOrderMasterOne(String openid, String orderId);

    //根据openid，订单orderId取消订单
    OrderMasterDTO cancelOrderMaster(String openid, String orderId);
}
