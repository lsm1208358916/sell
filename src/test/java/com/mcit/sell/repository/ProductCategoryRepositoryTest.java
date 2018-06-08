package com.mcit.sell.repository;

import com.mcit.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        ProductCategory productCategory = repository.getOne(1);
        System.out.println(productCategory.toString());
    }
}