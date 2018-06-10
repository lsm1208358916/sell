package com.mcit.sell.service.impl;

import com.mcit.sell.dataobject.ProductCategory;
import com.mcit.sell.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 描述:
 * 类目测试
 *
 * @author lsm12
 * @create 2018-06-10 1:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional//在测试类中可以测试数据回滚
public class CategoryServiceImplTest {
    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    public void findOneById() {
        ProductCategory productCategory = categoryService.findOneById(1);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("女生专享", 4);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}