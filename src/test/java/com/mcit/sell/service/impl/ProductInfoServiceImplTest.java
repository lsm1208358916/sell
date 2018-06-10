package com.mcit.sell.service.impl;

import com.mcit.sell.dataobject.ProductInfo;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 描述:
 *
 * @author lsm12
 * @create 2018-06-10 8:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Log4j
public class ProductInfoServiceImplTest {
    @Autowired
    ProductInfoServiceImpl productInfoService;

    @Test
    public void findOneByIdTest() {
        ProductInfo productInfo = productInfoService.findOne("1");
        Assert.assertEquals("1", productInfo.getProductId());
    }

    @Test
    public void findAllTest() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> productInfoList = productInfoService.findAll(pageRequest);
    }

    @Test
    public void findByProductStatusTest() {
        List<ProductInfo> productInfoList = productInfoService.findByProductStatus(0);
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findUpAllTest() {
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }


    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("2");
        productInfo.setProductName("扬州炒饭");
        productInfo.setProductPrice(new BigDecimal(3.23));
        productInfo.setProductStock(10);
        productInfo.setProductDescription("你以为就是扬州的炒饭？");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        ProductInfo result = productInfoService.save(productInfo);
    }
}