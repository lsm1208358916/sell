package com.mcit.sell.repository;

import com.mcit.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 描述:
 * 类目表dao层测试
 *
 * @author LSM
 * @create 2018-06-08 17:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    ProductCategoryRepository repository;

    /**
     * 查询测试
     */
    @Test
    public void findOneTest() {
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory.toString());
    }

    /**
     * 存储测试
     */
    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(1);
        repository.save(productCategory);
    }

    /**
     * 更新数据
     */
    @Test
    @Transactional//测试方法中添加事务的注解，可以使测试数据回滚
    public void updateTest() {
        ProductCategory productCategory = repository.findOne(1);
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(4);
        repository.save(productCategory);
    }

    /**
     * 根据类目编号来查询
     */
    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result);
        
    }
}