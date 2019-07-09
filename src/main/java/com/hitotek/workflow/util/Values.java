package com.hitotek.workflow.util;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description 用于判断对象是否存在具体值
 * @date Create at 2018/7/30
 */
public class Values {
    public interface ParameterCallback<T> {
        /**
         * 判断是否空的具体回调
         *
         * @param target 目标对象
         * @return 是否空
         */
        public boolean judge(T target);
    }

    /**
     * 目标对象是否空
     *
     * @param target 目标对象
     * @return null -> true or -> false
     */
    public static boolean isNull(Object target) {
        return null == target;
    }

    /**
     * @param callback 判断回调
     * @param targets  目标参数列表
     * @param <T>      目标类型
     * @return 参数是否完整
     */
    @SafeVarargs
    public static <T> boolean isParameterComplete(ParameterCallback<T> callback, T... targets) {
        for (T target : targets) {
            if (!callback.judge(target)) {
                return false;
            }
        }
        return true;
    }
}
