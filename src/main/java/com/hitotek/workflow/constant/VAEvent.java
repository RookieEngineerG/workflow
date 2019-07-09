package com.hitotek.workflow.constant;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description VA事件字符串约定，便于调用
 * @date 2019-04-16
 */
public interface VAEvent {
    /**
     * 视频处理开始
     */
    public static final String STARTED = "htva:started";

    /**
     * 视频处理结束
     */
    public static final String STOPPED = "htva:stopped";

    /**
     * 签到
     */
    public static final String SIGN_IN = "htva:signin";

    /**
     * 行为分析结果
     */
    public static final String RESULT = "htva:result";

    /**
     * 告警事件
     */
    public static final String ALARM = "htva:alarm";
}
