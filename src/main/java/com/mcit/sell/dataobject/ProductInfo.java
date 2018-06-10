package com.mcit.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 描述:
 * 商品
 *
 * @author lsm12
 * @create 2018-06-10 1:27
 */
@Entity
@Data//省去写getter和setter，toString的方法
public class ProductInfo {
    @Id//主键
    /*@GeneratedValue//自增*/
    private String productId;//因为商品会有很多，故类型选择用string，可以提高查询效率
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品的价格
     */
    private BigDecimal productPrice;//金钱用高精度的类
    /**
     * 库存
     */
    private Integer productStock;
    /**
     * 描述
     */
    private String productDescription;
    /**
     * 小图
     */
    private String productIcon;
    /**
     * 商品状态，0正常，1下架
     */
    private Integer productStatus;
    /**
     * 类目编号
     */
    private Integer categoryType;

}
