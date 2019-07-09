package com.hitotek.workflow.util;

/**
 * @author ZTrue
 * @version V1.0
 * @Description 浮点型工具类
 * @date Create at 2019/3/11
 */
public class Floats {
    public static boolean isNullOrEmpty(Float... floats) {
        for (Float f : floats) {
            if (f == null || f == 0) {
                return true;
            }
        }
        return false;
    }
}
