package com.hitotek.workflow.model.listener;

import java.util.List;

/**
 * @author Qicheng Peng
 * @Description 提供接口返回经过计算的limit函数起点和限制数并最终返回集合数据
 */
public interface PageDataListener<T> {
    /**
     * 提供接口返回经过计算的limit函数起点和限制数并最终返回集合数据
     *
     * @param index 起点
     * @param limit 限制数量
     * @return 集合数据
     */
    public List<T> data(int index, int limit);
}