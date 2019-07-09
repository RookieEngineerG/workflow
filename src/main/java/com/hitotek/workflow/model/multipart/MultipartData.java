package com.hitotek.workflow.model.multipart;


import com.alibaba.fastjson.JSON;
import com.hitotek.workflow.model.Data;

import java.lang.reflect.Field;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description 拼装多个PO的多部件实体
 * @date 2019/1/9
 */
public class MultipartData extends Data {

    public interface ConditionCallback<T> {
        T resolve(MultipartData store);
    }

    /**
     * 解析对象并放入到Map中
     *
     * @param target 目标
     */
    private void dissect(Object target) {
        Field[] declaredFields = target.getClass().getDeclaredFields();
        Object current;
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                // 获得当前值
                current = declaredField.get(target);
                this.include(declaredField.getName(), current);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            declaredField.setAccessible(false);
        }
    }

    /**
     * 加入并合并
     *
     * @param targets 目标对象
     * @return 返回自己供链式调用
     */
    public MultipartData parties(Object... targets) {
        for (Object target : targets) {
            this.dissect(target);
        }
        return this;
    }


    @Override
    public MultipartData exclude(String key) {
        super.exclude(key);
        return this;
    }

    /**
     * 多个条件
     *
     * @param conditions 目标条件key
     * @return HASH
     */
    public Integer conditionHash(String... conditions) {
        StringBuilder builder = new StringBuilder();
        for (String key : conditions) {
            builder.append(key).append(":").append(get(key));
        }
        return builder.toString().hashCode();
    }

    public <T> T condition(ConditionCallback<T> conditionCallback) {
        return conditionCallback.resolve(this);
    }


    @Override
    public MultipartData include(String key, Object value) {
        super.include(key, value);
        return this;
    }

    /**
     * 转化所有下划线命名法 -> 驼峰命名法
     *
     * @return 当前类
     */
    public MultipartData translate2Camel() {
        MultipartData data = new MultipartData();
        KeyConverter converter = new KeyConverter();
        for (String key : this.keySet()) {
            Object o = get(key);
            String targetKey = converter.convert(key);
            data.put(targetKey, o);
        }
        return data;
    }

    public String buildJson () {
        return JSON.toJSONString(this);
    }
}
