package com.mcit.sell.controller;

import com.mcit.sell.VO.ResultVO;
import com.mcit.sell.converter.OrderForm2OrderMasterDTO;
import com.mcit.sell.converter.OrderMaster2OrderMasterDTO;
import com.mcit.sell.dto.OrderMasterDTO;
import com.mcit.sell.enums.ResultEnum;
import com.mcit.sell.exception.SellException;
import com.mcit.sell.form.OrderForm;
import com.mcit.sell.service.BuyerService;
import com.mcit.sell.service.OrderMasterService;
import com.mcit.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 * 订单控制器
 * 提醒：当表单提交上数据时，需要先验证参数,当数据出现异常或者程序报错都要写日志
 *
 * @author lsm12
 * @create 2018-07-01 17:25
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private BuyerService buyerService;

    //创建订单
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        //验证参数
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderMasterDTO orderMasterDTO = OrderForm2OrderMasterDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderMasterDTO.getOrderDetailList())) {
            log.error("【创建订单】 购物车为空 orderDetailList={}", orderMasterDTO.getOrderDetailList());
            throw new SellException(ResultEnum.CAR_EMPTY);
        }
        OrderMasterDTO result = orderMasterService.create(orderMasterDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {

        buyerService.cancelOrderMaster(openid, orderId);
        return ResultVOUtil.success();
    }    //查询订单列

    @GetMapping("/list")
    public ResultVO<List<OrderMasterDTO>> list(@RequestParam("openid") String openid,
                                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】 openid为空 openid={}", openid);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderMasterDTO> orderMasterDTOPage = orderMasterService.findAllList(openid, pageRequest);
        //返回时间要求秒，时间拿到时间为毫秒，需要转化时间，并且要求null的属性不返回
        return ResultVOUtil.success(orderMasterDTOPage.getContent());
    }

    //查询订单详情
    @GetMapping("/detail")
    public ResultVO<OrderMasterDTO> detail(@RequestParam("openid") String openid,
                                           @RequestParam("orderId") String orderId) {
        //TODO 不安全，需要改进
        OrderMasterDTO orderMasterDTO = buyerService.findOrderMasterOne(openid, orderId);
        return ResultVOUtil.success(orderMasterDTO);
    }
}
