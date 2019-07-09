package com.hitotek.workflow.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description 封装分页请求需要的基本参数
 * @date Create at 2018/7/30
 */
@Data
public class PageRequest {
    /**
     * 站点ID
     */
    private Integer siteId;

    /**
     * 开始坐标
     */
    private Integer index = 1;

    /**
     * 单个分页返回的记录数量
     */
    private Integer limit = 10;
    /**
     * 开始时间
     */
    private long start;
    private Date startTime;
    /**
     * 结束时间
     */
    private long end;
    private Date endTime;

    /**
     * 请求来自哪个页面
     */
    private String origin;
}
