package com.mcit.sell.utils;

import java.util.Random;

/**
 * 描述:
 * 主键唯一标识生成工具
 * synchronized防止多线程可能生成一样的key
 *
 * @author LSM
 * @create 2018-06-12 11:18
 */
public class KeyUtil {
    /**
     * 时间+随机数
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        int num = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
