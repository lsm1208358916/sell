package com.mcit.sell.utils;

import com.mcit.sell.VO.ResultVO;

/**
 * 描述:
 *
 * @author LSM
 * @create 2018-06-11 11:48
 */
public class ResultVOUtil {
    /**
     * 成功
     *
     * @param object
     * @return
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }

    /**
     * 成功数据为空的情况
     *
     * @return
     */
    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }
}
