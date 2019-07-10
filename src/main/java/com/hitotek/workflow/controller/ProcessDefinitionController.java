package com.hitotek.workflow.controller;

import com.hitotek.workflow.model.Data;
import com.hitotek.workflow.service.ProcessDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流程定义controller
 *
 * @author: ztrue
 * @date: 2019-07-10
 */
@RestController
@RequestMapping("/definition")
public class ProcessDefinitionController {

    @Autowired
    private ProcessDefinitionService processDefinitionService;

    /**
     * 部署我的流程
     *
     * @return
     */
    @GetMapping("/deploy/myProcess")
    public Data deployMyProcess() {
        return processDefinitionService.deployMyProcess();
    }

    /**
     * 删除已部署的流程
     *
     * @param deploymentId 流程部署id
     * @return
     */
    @GetMapping("/remove")
    public Data removeProcessDefinitions(@RequestParam String deploymentId) {
        return processDefinitionService.removeProcessDefinitions(deploymentId);
    }

    /**
     * 获取已部署的流程
     *
     * @param deploymentId 若传入 deploymentId 则精确查找
     */
    @GetMapping("/cat")
    public Data getProcessDefinitions(String deploymentId) {
        return processDefinitionService.getProcessDefinitions(deploymentId);
    }

    /**
     * 获取最新部署的流程
     *
     * @param definitionKey 流程定义key
     */
    @GetMapping("/latest/cat")
    public Data getLatestProcessDefinitions(@RequestParam String definitionKey) {
        return processDefinitionService.getLatestProcessDefinitions(definitionKey);
    }


}
