package com.mcit.sell.repository;

import com.mcit.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 类目表实体类的映射接口
 *
 * @author LSM
 * @create 2018-06-08 17:50
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
