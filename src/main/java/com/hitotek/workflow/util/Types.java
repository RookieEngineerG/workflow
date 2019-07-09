package com.hitotek.workflow.util;

import java.util.List;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description
 * @date 2019/1/22
 */
public class Types {
    public static <T> List<T> getListPart(List list, Class<T> tClass) {
        if (list.size() > 0) {
            Object o = list.get(0);
            if (tClass.isAssignableFrom(o.getClass())) {
                return (List<T>) list;
            }
        }
        return null;
    }
}
