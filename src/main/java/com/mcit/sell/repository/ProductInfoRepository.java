package com.mcit.sell.repository;

import com.mcit.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述:
 *
 * @author lsm12
 * @create 2018-06-10 1:46
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    /**
     * 根据商品状态来查找商品
     *
     * @param status
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer status);
}
