package com.mcit.sell.service;

import com.mcit.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 描述:
 * 商品
 *
 * @author lsm12
 * @create 2018-06-10 8:01
 */
public interface ProductInfoService {
    ProductInfo findOne(String productInfoId);

    /**
     * 查询全部在售的商品
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    List<ProductInfo> findByProductStatus(Integer status);

    ProductInfo save(ProductInfo productInfo);

//    加库存
//    减库存
}
