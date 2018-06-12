package com.mcit.sell.service.impl;

import com.mcit.sell.dataobject.ProductInfo;
import com.mcit.sell.dto.CartDTO;
import com.mcit.sell.enums.ProductStatusEnum;
import com.mcit.sell.enums.ResultEnum;
import com.mcit.sell.exception.SellException;
import com.mcit.sell.repository.ProductInfoRepository;
import com.mcit.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.channels.SeekableByteChannel;
import java.util.List;

/**
 * 描述:
 *商品信息实现
 * @author lsm12
 * @create 2018-06-10 8:04
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productInfoId) {
        return repository.findOne(productInfoId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findByProductStatus(Integer status) {
        return repository.findByProductStatus(status);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartList) {
        for (CartDTO cartDTO : cartList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer resultStock = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (resultStock < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(resultStock);
            repository.save(productInfo);
        }
    }
}
