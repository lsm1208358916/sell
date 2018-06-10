package com.mcit.sell.controller;

import com.mcit.sell.VO.ProductInfoVO;
import com.mcit.sell.VO.ProductVO;
import com.mcit.sell.VO.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 描述:
 *
 * @author lsm12
 * @create 2018-06-10 9:23
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired


    @GetMapping("/list")
    public ResultVO list() {
        //查询数据

        //


        //数据封装
        ResultVO resultVO = new ResultVO();
        ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();
        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
        resultVO.setData(Arrays.asList(productVO));
        return resultVO;
    }
}
