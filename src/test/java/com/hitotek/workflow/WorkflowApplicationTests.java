package com.hitotek.workflow;

import com.hitotek.workflow.model.Data;
import com.hitotek.workflow.model.multipart.MultipartData;
import com.hitotek.workflow.po.ProcessReq;
import com.hitotek.workflow.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WorkflowApplicationTests {
//    private static final Logger log = LoggerFactory.getLogger(WorkflowApplicationTests.class);


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
    public void contextLoads() {
        // 部署流程
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
//                .addClasspathResource("processes/my-process.bpmn20.xml")
                .addClasspathResource("processes/leave-request-process.bpmn20.xml")
//                .addClasspathResource("processes/second-approve.bpmn20.xml")
                .name("请假审批")
                .deploy();

//        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
//        List<Deployment> list = deploymentQuery.list();
//        log.info("deploymentQuery = {}", JSON.toJSONString(list));



        // 查看任务
//        TaskService taskService = processEngine.getTaskService();
//        TaskQuery taskQuery = taskService.createTaskQuery();
//        List<Task> taskList = taskQuery.list();
//        log.info("taskList.size() = {}", taskList.size());


    }

    @Test
    public void repositoryServiceTest () {
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        long count = deploymentQuery.count();
        log.info("count = {}", count);


    }

    @Test
    public void deleteRepositoryServiceTest () {

        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        List<Deployment> deployments = deploymentQuery.list();
        for (Deployment deployment : deployments) {
            repositoryService.deleteDeployment(deployment.getId());
        }

        long count = deploymentQuery.count();
        log.info("count = {}", count);
    }

    @Test
    public void selectRepositoryServiceTest () {
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        List<Deployment> deployments = deploymentQuery.list();
        for (Deployment deployment : deployments) {
            log.info("deployment = {}", deployment);
        }
    }

    @Test
    public void startProcessInstanceTest () {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("second-approve");
        log.info("processInstance = {}", processInstance);
    }

    @Test
    public void catProcessInstanceTest () {
        log.info("启动我们的程序");
        log.info("流程引擎名称{}, 版本{}", processEngine.getName(), ProcessEngine.VERSION);

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId("second-approve:2:32504")
                .singleResult();
        log.info("流程定义文件 {}, ID {}", processDefinition.getName(), processDefinition.getId());

        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
        log.info("启动流程 {}", processInstance.getProcessDefinitionKey());

        List<Task> tasks = taskService.createTaskQuery().list();
        for (Task task : tasks) {
            log.info("待处理任务 {}", task.getName());
        }
        log.info("待处理任务总数 = {}", tasks.size());

        log.info("结束我们的程序");
    }



}
