package com.hitotek.workflow.constant;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description SIP事件字符串约定，便于调用
 * @date 2019-04-16
 */
public interface SipEvent {
    /**
     * (IPC)注册
     */
    public static final String REGISTER = "sip:register";

    /**
     * (IPC)注销
     */
    public static final String DEREGISTER = "sip:deregister";

    /**
     * 闯入
     */
    public static final String INTRUSION = "sip:intrusion";

    /**
     * 离线
     */
    public static final String OFFLINE = "sip:offline";

    /**
     * 告警
     */
    public static final String ALARM = "sip:alarm";
}
