package com.mcit.sell.enums;

import lombok.Getter;

/**
 * 描述:
 * 返回结果异常枚举
 *
 * @author LSM
 * @create 2018-06-12 10:10
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存异常"),
    ORDERMASTER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDERMASTER_STATUS_ERROR(14, "订单状态不正确"),
    ORDERMASTERSTATUS_UPDATE_ERROR(15, "订单状态更新失败"),
    STOCK_UPDATE_ERROR(16, "商品库存更新异常"),
    PAY_STATUS_ERROR(17, "支付状态更新失败"),
    CAR_EMPTY(18, "购物车为空"),
    CHECK_AUTHOR_ERROR(19, "不是同一用户");


    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
