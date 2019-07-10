package com.hitotek.workflow;

import com.hitotek.workflow.model.Data;
import com.hitotek.workflow.model.multipart.MultipartData;
import com.hitotek.workflow.service.ProcessDefinitionService;
import com.hitotek.workflow.service.ProcessInstanceService;
import com.hitotek.workflow.util.Values;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: ztrue
 * @date: 2019-07-09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BaseServiceTest {

    @Autowired
    private ProcessDefinitionService processDefinitionService;
    @Autowired
    private ProcessInstanceService processInstanceService;
    @Autowired
    private RuntimeService runtimeService;

    @Test
    public void testGetProcessDefinition () {
        Data processDefinitions = processDefinitionService.getProcessDefinitions(null);
        log.info("processDefinition = {}", processDefinitions);
    }

    @Test
    public void testStartProcessInstanceByKey () {
        String definitionKey = "my-process";
        MultipartData multipartData = new MultipartData();
        Data data = processInstanceService.startProcessInstanceByKey(definitionKey, multipartData);
    }

}
