package com.hitotek.workflow.util;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @Description 字符串工具类
 * @date Create at 2018/7/21
 */
public class Strings {
    public static boolean isNullOrEmpty(String... strings) {
        for (String string : strings) {
            if (string == null || string.length() == 0) {
                return true;
            }
        }
        return false;
    }
}
