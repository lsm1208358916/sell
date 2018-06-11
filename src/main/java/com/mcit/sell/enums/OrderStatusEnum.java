package com.mcit.sell.enums;

import lombok.Data;
import lombok.Getter;

/**
 * 描述:
 * 订单状态
 *
 * @author LSM
 * @create 2018-06-11 15:27
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新下单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消");

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
