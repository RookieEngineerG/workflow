package com.hitotek.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description 告警信息
 * @date 2018/8/27
 */

@Data
public class AlarmMessage {
    /**
     * 行为所属站点名称
     */
    @NotNull(message = "站点名称不能空")
    @JsonProperty(value = "SiteName")
    private String siteName;
    /**
     * 站点位置
     */
    @JsonProperty(value = "SiteLocation")
    private SiteLocation siteLocation;
    /**
     * 告警类别
     * 0：运维类告警
     * 1：因子数据类告警
     * 2：设备类告警
     * 4：摄像头智能检测类告警
     */
    @NotNull
    @JsonProperty(value = "AlarmCategory")
    private int alarmCategory;
    /**
     * 一．运维类告警
     * 0：未识别人员
     * 1：人员非法闯入
     * 2：运维行为违规
     * 3：运维行为漏做
     * 二．因子数据类告警
     * 0: 数据读取失败
     * 1: 数据超量程
     * 2: 数据超警戒值
     * 3: 数据变化异常
     * 三．设备类告警
     * 0: 设备未连接
     * 1: 站点视频未连接
     * 摄像头智能检测类告警
     * 0：区域入侵
     * 1：越界侦测
     * 2：移动侦测
     * 3：摄像头遮挡
     */
    @NotNull
    @JsonProperty(value = "AlarmType")
    private String alarmType;
    /**
     * 告警级别
     * 0: 紧急
     * 1: 重要
     * 2: 次要
     * 3: 一般
     */
    @NotNull
    @JsonProperty(value = "AlarmLevel")
    private String alarmLevel;
    /**
     * 产生告警的摄像头编号
     * 运维类告警或摄像头智能检测类告警时需携带
     */
    @JsonProperty(value = "CameraDevId")
    private String cameraDevId;
    /**
     * 格式
     * 2018-08-23 12:01:59
     */
    @NotNull
    @JsonProperty(value = "AlarmRaiseTime")
    private String alarmRaiseTime;
}
