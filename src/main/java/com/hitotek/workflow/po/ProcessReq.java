package com.hitotek.workflow.po;

import lombok.Data;

import java.util.List;

/**
 * @author: ztrue
 * @date: 2019-07-09
 */
@Data
public class ProcessReq {

    /**
     * 流程部署id
     */
    private String deploymentId;
    /**
     * 流程key
     */
    private String definitionKey;

    /**
     * 流程实例id ,可用来查询流程已部署的流程等
     */
    private String instanceId;

    /**
     * 流程定义id
     */
    private String definitionId;

    /**
     * 用户id ,查询自己的任务
     */
    private String userId;

    /**
     * 组用户id
     */
    private List<String> listUserId;

    /**
     * 任务id ,用来执行任务 可在任务查询里面得到自己的任务
     */
    private String taskId;

    /**
     * 任务id ,用来执行任务 可在任务查询里面得到自己的任务
     */
    private String taskName;

    /**
     * 审核  通过：true  不通过：false
     */
    private Boolean examine;

    /**
     * 备注 可填写审核通过原因或者不通过原因
     */
    private String remark;

}
