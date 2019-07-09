package com.hitotek.workflow.util;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @Description 字符串工具类
 * @date Create at 2018/7/21
 */
public class Integers {
    public static boolean isNullOrEmpty(Integer... integers) {
        for (Integer integer : integers) {
            if (integer == null || integer == 0) {
                return true;
            }
        }
        return false;
    }
}
