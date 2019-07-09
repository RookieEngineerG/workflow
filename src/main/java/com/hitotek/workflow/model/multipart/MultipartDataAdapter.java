package com.hitotek.workflow.model.multipart;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description 适配器
 * @date 2019/1/21
 */
public class MultipartDataAdapter extends ArrayList<String> {

    public MultipartDataAdapter() {

    }

    public MultipartDataAdapter create(Class... classes) {
        for (Class aClass : classes) {
            this.dissect(aClass);
        }
        return this;
    }


    /**
     * 解析对象并放入到Map中
     *
     * @param target 目标
     */
    private void dissect(Class target) {
        Field[] declaredFields = target.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!contains(declaredField.getName())) {
                this.add(declaredField.getName());
            }
        }
    }
}
