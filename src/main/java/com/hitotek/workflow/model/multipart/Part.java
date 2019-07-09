package com.hitotek.workflow.model.multipart;

import lombok.Data;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description
 * @date 2019/1/18
 */
@Data
public class Part {
    private String key;
    private MultipartData partValue;
    private MultipartData parent;
}
