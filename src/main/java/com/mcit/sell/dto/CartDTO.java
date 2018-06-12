package com.mcit.sell.dto;

import lombok.Data;

/**
 * 描述:
 * 购物车中的单件商品
 *
 * @author LSM
 * @create 2018-06-12 11:43
 */
@Data
public class CartDTO {
    private String productId;
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
