package com.hitotek.workflow.model.multipart;

import lombok.Data;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description
 * @date 2019/1/11
 */
@Data
public class ConditionFilter<T> extends MultipartData{
    private T filter;
}
