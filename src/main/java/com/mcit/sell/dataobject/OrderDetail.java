package com.mcit.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:
 * 订单商品
 *
 * @author LSM
 * @create 2018-06-11 15:41
 */
@Entity
@Data
public class OrderDetail {
    @Id//主键
    private String detailId;//订单商品id

    private String orderId;//订单id

    private String productId;//商品id

    private String productName;//商品名称

    private BigDecimal productPrice;//商品价格

    private Integer productQuantity;//商品数量

    private String productIcon;//商品小图

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
}
