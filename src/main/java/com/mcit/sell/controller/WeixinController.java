package com.mcit.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 微信
 *
 * @author lsm12
 * @create 2018-07-03 11:15
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    @RequestMapping("/auth")
    public String auth(@RequestParam("Token") String Token) {
        log.error("进入auth方法。。。。");
        return Token;
    }
}
