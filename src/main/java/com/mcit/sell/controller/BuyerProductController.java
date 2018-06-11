package com.mcit.sell.controller;

import com.mcit.sell.VO.ProductInfoVO;
import com.mcit.sell.VO.ProductVO;
import com.mcit.sell.VO.ResultVO;
import com.mcit.sell.dataobject.ProductCategory;
import com.mcit.sell.dataobject.ProductInfo;
import com.mcit.sell.service.CategoryService;
import com.mcit.sell.service.ProductInfoService;
import com.mcit.sell.utils.ResultVOUtil;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:
 * 商品控制器
 *
 * @author lsm12
 * @create 2018-06-10 9:23
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {

        //查询所有上架的商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //寻找数据中有多少个类目类型

        //传统方法
       /* List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
        //精简方法 (java8新特性，收集指定属性)
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        //查询上架商品中的类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //数据拼接
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            for (ProductInfo productInfo : productInfoList) {
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    productInfoVO.setProductInfoId(productInfo.getProductId());
                    productInfoVO.setProductInfoName(productInfo.getProductName());
                    productInfoVO.setProductInfoPrice(productInfo.getProductPrice());
                    productInfoVO.setProductInfoDescription(productInfo.getProductDescription());
                    productInfoVO.setProductInfoIcon(productInfo.getProductIcon());
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVOList.add(productVO);
            productVO.setProductInfoVOList(productInfoVOList);
        }
        return ResultVOUtil.success(productVOList);
    }
}
