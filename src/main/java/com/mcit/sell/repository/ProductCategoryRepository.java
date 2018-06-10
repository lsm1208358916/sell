package com.mcit.sell.repository;

import com.mcit.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 * 类目表实体类的映射接口
 *
 * @author LSM
 * @create 2018-06-08 17:50
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    /**
     * 根据类目编号来查询
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}
