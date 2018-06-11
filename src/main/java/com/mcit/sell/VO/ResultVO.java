package com.mcit.sell.VO;

import lombok.Data;

/**
 * 描述:
 * http请求结果返回集
 * vo=view object 就是全部前台数据映射返回的对象
 *
 * @author lsm12
 * @create 2018-06-10 9:36
 */
@Data
public class ResultVO<T> {

    private Integer code;//状态码

    private String message;//返回信息

    private T data;
}
