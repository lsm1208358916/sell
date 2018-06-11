package com.mcit.sell.dto;

import com.mcit.sell.dataobject.OrderDetail;
import com.mcit.sell.enums.OrderStatusEnum;
import com.mcit.sell.enums.PayStatusEnum;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 订单传输对象
 *
 * @author LSM
 * @create 2018-06-11 16:35
 */
public class OrderMasterDTO {

    private String orderId;//订单id

    private String buyerName;//买家姓名

    private String buyerPhone;//买家电话

    private String buyerAddress;//买家地址

    private String buyerOpenid;//买家微信id

    private BigDecimal orderAmount;//总金额

    private Integer orderStatus;//订单状态，默认0新下单

    private Integer payStatus;//支付状态，默认0未支付

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    private List<OrderDetail> orderDetailList;
}
