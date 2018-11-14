package com.mcit.sell.exception;

import com.mcit.sell.enums.ResultEnum;

import javax.management.RuntimeOperationsException;
import javax.validation.constraints.NotNull;

/**
 * 描述:
 * sell项目异常
 *
 * @author LSM
 * @create 2018-06-12 10:06
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
