package com.mcit.sell.service.impl;

import com.mcit.sell.dataobject.OrderDetail;
import com.mcit.sell.dto.CartDTO;
import com.mcit.sell.dto.OrderMasterDTO;
import com.mcit.sell.enums.OrderStatusEnum;
import com.mcit.sell.enums.PayStatusEnum;
import com.mcit.sell.service.OrderMasterService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 描述:
 * 订单业务实现测试
 *
 * @author LSM
 * @create 2018-06-12 14:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceImplTest {
    @Autowired
    OrderMasterService orderMasterService;

    @Test
    public void create() {
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        orderMasterDTO.setBuyerName("小李");
        orderMasterDTO.setBuyerPhone("18179147179");
        orderMasterDTO.setBuyerAddress("上地三街");
        orderMasterDTO.setBuyerOpenid("123123");
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("1");
        orderDetail1.setProductQuantity(1);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("2");
        orderDetail2.setProductQuantity(3);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(orderDetail1);
        orderDetailList.add(orderDetail2);
        orderMasterDTO.setOrderDetailList(orderDetailList);
        OrderMasterDTO result = orderMasterService.create(orderMasterDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAllList() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderMasterDTO> orderMasterDTOPage = orderMasterService.findAllList("123123", pageRequest);
        Assert.assertNotEquals(0, orderMasterDTOPage.getTotalElements());
    }

    @Test
    public void findOne() {
        OrderMasterDTO orderMasterDTO = orderMasterService.findOne("2");
        Assert.assertNotNull(orderMasterDTO);
    }

    @Test
    public void cancel() {
        OrderMasterDTO orderMasterDTO = orderMasterService.findOne("1528785854157234328");
        OrderMasterDTO result = orderMasterService.cancel(orderMasterDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}