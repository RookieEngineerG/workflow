package com.hitotek.workflow.constant;

/**
 * 图数仪/数采仪/数据采集器分析仪/物联网关
 * @author: ztrue
 * @description SIP事件字符串约定，便于调用
 * @date: 2019-04-24
 */
public interface IotdEvent {

    /**
     * 设备上线
     */
    public static final String ONLINE = "iotd:online";
    /**
     * 设备离线
     */
    public static final String OFFLINE = "iotd:offline";
    /**
     * 收到设备数据
     */
    public static final String DATA = "iotd:data";
    /**
     * TBD
     */
    public static final String ALARM = "iotd:alarm";


}
