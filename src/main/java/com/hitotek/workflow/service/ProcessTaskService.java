package com.hitotek.workflow.service;

import com.hitotek.workflow.model.Data;
import com.hitotek.workflow.model.multipart.MultipartData;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @author: ztrue
 * @date: 2019-07-08
 */
public interface ProcessTaskService {

    Data getVariableValues(String taskId, String key);


    Data completeTaskProcess(String taskId, MultipartData variables);

    Task findTaskById(String taskId);

    List<Task> findTaskListByKey(String processInstanceId, String key);

    ProcessInstance findProcessInstanceByTaskId(String taskId);

    ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(String taskId);

}
