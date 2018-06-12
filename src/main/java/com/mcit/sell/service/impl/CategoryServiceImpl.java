package com.mcit.sell.service.impl;

import com.mcit.sell.dataobject.ProductCategory;
import com.mcit.sell.repository.ProductCategoryRepository;
import com.mcit.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 *类目服务实现
 * @author lsm12
 * @create 2018-06-10 1:09
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ProductCategoryRepository repository;

    @Override
    public ProductCategory findOneById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list) {
        return repository.findByCategoryTypeIn(list);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
