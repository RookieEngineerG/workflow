package com.hitotek.workflow.service.impl;

import com.hitotek.workflow.factory.DataFactory;
import com.hitotek.workflow.model.Data;
import com.hitotek.workflow.service.BaseService;
import org.activiti.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基础服务
 *
 * @author: ztrue
 * @date: 2019-07-08
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    /**
     * 二级审批部署流程
     *
     * @return ProcessResp 返回参数
     * @throws Exception 异常
     */
    @Override
    public Data deployMyProcess() {
        try {
            repositoryService.createDeployment()
                    //创建一个部署对象
                    .addClasspathResource("processes/my-process.bpmn20.xml")
                    //添加名称
                    .name("部署流程")
                    //完成部署
                    .deploy();
            return DataFactory.createSuccess().message("我的流程，部署成功! ");
        } catch (Exception e) {
            e.printStackTrace();
            return DataFactory.createError().message("我的流程，部署失败! ");
        }
    }

}
