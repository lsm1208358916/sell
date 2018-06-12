package com.mcit.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述:
 * 商品api外层
 *
 * @author lsm12
 * @create 2018-06-10 10:47
 */
@Data
public class ProductVO {
    @JsonProperty("name")//对应返回的属性字段
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    List<ProductInfoVO> productInfoVOList;
}
