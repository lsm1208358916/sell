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

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存异常"),
    ORDERMASTER_NOT_EXIST(12, "订单不存在");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
