package com.hitotek.workflow;

import com.hitotek.workflow.model.Data;
import com.hitotek.workflow.model.multipart.MultipartData;
import com.hitotek.workflow.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ztrue
 * @date: 2019-07-09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BaseServiceTest {

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private BaseService baseService;

    @Test
    public void testGetProcessDefinition () {
        Data processDefinitions = baseService.getProcessDefinitions(null);
        log.info("processDefinition = {}", processDefinitions);
    }

    @Test
    public void testStartProcessInstanceByKey () {
        String definitionKey = "my-process";
        MultipartData multipartData = new MultipartData();
        Data data = baseService.startProcessInstanceByKey(definitionKey, multipartData);
    }

}
