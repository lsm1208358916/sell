package com.mcit.sell.enums;

import lombok.Getter;

/**
 * 描述:
 * 商品状态枚举
 *
 * @author lsm12
 * @create 2018-06-10 8:29
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "在售"),
    DOWN(1, "下架");
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
