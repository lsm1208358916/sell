package com.mcit.sell.dataobject;

import com.mcit.sell.enums.OrderStatusEnum;
import com.mcit.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:
 * 订单
 *
 * @author LSM
 * @create 2018-06-11 15:21
 */
@Entity
@Data
@DynamicUpdate//动态更新updateTime字段
public class OrderMaster {
    @Id
    private String orderId;//订单id

    private String buyerName;//买家姓名

    private String buyerPhone;//买家电话

    private String buyerAddress;//买家地址

    private String buyerOpenid;//买家微信id

    private BigDecimal orderAmount;//总金额

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();//订单状态，默认0新下单

    private Integer payStatus = PayStatusEnum.WAIT.getCode();//支付状态，默认0未支付

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
}
