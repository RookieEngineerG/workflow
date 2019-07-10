package com.hitotek.workflow.controller;

import com.hitotek.workflow.model.Data;
import com.hitotek.workflow.model.multipart.MultipartData;
import com.hitotek.workflow.service.ProcessInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ztrue
 * @date: 2019-07-10
 */
@RestController
@RequestMapping("/instance")
public class ProcessInstanceController {

    @Autowired
    private ProcessInstanceService processInstanceService;

    /**
     * 删除流程实例
     *
     * @param instanceId 流程实例id
     */
    @GetMapping("/delete")
    public Data deleteProcessInstance(@RequestParam String instanceId) {
        return processInstanceService.deleteProcessInstance(instanceId);
    }

    /**
     * 开启流程实例
     *
     * @param definitionKey 流程定义key
     * @param requestBody   流程开始时需要的属性
     */
    @PostMapping("/start")
    public Data startProcessInstanceByKey(@RequestParam String definitionKey,
                                          @RequestBody MultipartData requestBody) {
        return processInstanceService.startProcessInstanceByKey(definitionKey, requestBody);
    }

}
