package com.hitotek.workflow.constant;

//当前处理状态: /2区县审批通过/3市级审批通过/4省级审批通过
public interface AlarmProcessState {


    /**
     * 待处理
     */
    public static int ALARM_PROCESS_STATE_WAIT_PROCESS = 0;

    /**
     * 待审核
     */
    public static int ALARM_PROCESS_STATE_WAIT_REVIEW = 1;

    /**
     * 审批结束
     */
    public static int ALARM_PROCESS_STATE_FINISH = 100;

}
