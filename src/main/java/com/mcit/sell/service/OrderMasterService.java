package com.mcit.sell.service;

import com.mcit.sell.dto.OrderMasterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 描述:
 * 订单服务
 * 由于订单中对象跟数据库对象不一致，所以要重新建订单中的对象，就是dto
 *
 * @author LSM
 * @create 2018-06-11 16:33
 */
public interface OrderMasterService {
    //创建订单
    OrderMasterDTO create(OrderMasterDTO orderMasterDTO);

    //查询所有订单列表
    Page<OrderMasterDTO> findAllList(String orderId, Pageable pageable);

    //查询单个订单
    OrderMasterDTO findOne(String orderId);

    //取消订单
    OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO);

    //完结订单
    OrderMasterDTO finish(OrderMasterDTO orderMasterDTO);

    //支付订单
    OrderMasterDTO paid(OrderMasterDTO orderMasterDTO);
}
