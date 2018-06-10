package com.mcit.sell.service;

import com.mcit.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * 描述:
 * 类目
 *
 * @author lsm12
 * @create 2018-06-10 1:03
 */
public interface CategoryService {
    ProductCategory findOneById(Integer productId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

    ProductCategory save(ProductCategory productCategory);
}
