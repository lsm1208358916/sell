package com.mcit.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mcit.sell.dataobject.OrderDetail;
import com.mcit.sell.dataobject.OrderMaster;
import com.mcit.sell.dto.CartDTO;
import com.mcit.sell.dto.OrderMasterDTO;
import com.mcit.sell.enums.ResultEnum;
import com.mcit.sell.exception.SellException;
import com.mcit.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * OrderForm对象转化为OrderMasterDTO对象
 *
 * @author lsm12
 * @create 2018-07-02 0:27
 */
@Slf4j
public class OrderForm2OrderMasterDTO {
    public static OrderMasterDTO convert(OrderForm orderForm) {
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        orderMasterDTO.setBuyerName(orderForm.getName());
        orderMasterDTO.setBuyerPhone(orderForm.getPhone());
        orderMasterDTO.setBuyerAddress(orderForm.getAddress());
        orderMasterDTO.setBuyerOpenid(orderForm.getOpenid());
        Gson gson = new Gson();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            //有可能string转json异常
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【类型转换】 类转换异常 orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderMasterDTO.setOrderDetailList(orderDetailList);
        return orderMasterDTO;
    }
}
