package com.hitotek.workflow.model.multipart.callback;

import com.hitotek.workflow.model.multipart.MultipartData;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description
 * @date 2019-03-02
 */
public interface ConvertCallback {
    /**
     * 转化过程
     *
     * @param data 数据
     */
    public void process(MultipartData data);
}
