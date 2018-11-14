package com.mcit.sell.service.impl;

import com.mcit.sell.dataobject.ProductInfo;
import com.mcit.sell.dto.CartDTO;
import com.mcit.sell.enums.ProductStatusEnum;
import com.mcit.sell.enums.ResultEnum;
import com.mcit.sell.exception.SellException;
import com.mcit.sell.repository.ProductInfoRepository;
import com.mcit.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.channels.SeekableByteChannel;
import java.util.List;

/**
 * 描述:
 * 商品信息实现
 *
 * @author lsm12
 * @create 2018-06-10 8:04
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository repository;

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
    @Transactional
    public void increaseStock(List<CartDTO> cartList) {
        for (CartDTO cartDTO : cartList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer resultStock = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(resultStock);
            ProductInfo result = repository.save(productInfo);
            if (result == null) {
                throw new SellException(ResultEnum.STOCK_UPDATE_ERROR);
            }
        }
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
            ProductInfo result = repository.save(productInfo);
            if (result == null) {
                throw new SellException(ResultEnum.STOCK_UPDATE_ERROR);
            }
        }
    }
}
