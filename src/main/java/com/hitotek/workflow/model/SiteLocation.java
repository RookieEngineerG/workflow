package com.hitotek.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description 站点信息模型
 * @date 2018/8/27
 */
@Data
public class SiteLocation {
    /**
     * 省份
     */
    @JsonProperty(value = "Province")
    String province;
    /**
     * 城市
     */
    @JsonProperty(value = "City")
    String city;
    /**
     *
     */
    @JsonProperty(value = "Srovince")
    String srovince;
}
