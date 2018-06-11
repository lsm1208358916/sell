package com.mcit.sell.repository;

import com.mcit.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 描述:
 *
 * @author LSM
 * @create 2018-06-11 15:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    OrderMasterRepository repository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("2");
        orderMaster.setBuyerName("李世金");
        orderMaster.setBuyerPhone("18179147178");
        orderMaster.setBuyerAddress("福建晋江");
        orderMaster.setBuyerOpenid("110111");
        orderMaster.setOrderAmount(new BigDecimal(1002));
        OrderMaster orderMasters = repository.save(orderMaster);
        Assert.assertNotNull(orderMasters);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0, 3);
        Page<OrderMaster> orderMasterList = repository.findByBuyerOpenid("110111", pageRequest);
        Assert.assertNotEquals(0, orderMasterList.getTotalElements());
    }
}