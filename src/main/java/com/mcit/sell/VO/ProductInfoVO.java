package com.mcit.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述:
 * 商品api内层
 *
 * @author lsm12
 * @create 2018-06-10 10:57
 */
@Data
public class ProductInfoVO {
    @JsonProperty("id")
    private String productInfoId;
    @JsonProperty("name")
    private String productInfoName;
    @JsonProperty("price")
    private BigDecimal productInfoPrice;
    @JsonProperty("description")
    private String productInfoDescription;
    @JsonProperty("icon")
    private String productInfoIcon;
}
