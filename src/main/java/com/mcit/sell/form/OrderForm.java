package com.mcit.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 描述:
 * 表单提交订单实体
 *
 * @author lsm12
 * @create 2018-07-01 17:31
 */
@Data
public class OrderForm {
    //姓名
    @NotEmpty(message = "姓名不能为空")
    private String name;
    //电话
    @NotEmpty(message = "电话不能为空")
    private String phone;
    // 地址
    @NotEmpty(message = "地址不能为空")
    private String address;
    //微信id
    @NotEmpty(message = "openid不能为空")
    private String openid;
    //购物车
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
