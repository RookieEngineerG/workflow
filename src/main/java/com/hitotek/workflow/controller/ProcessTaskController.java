package com.hitotek.workflow.controller;

import com.hitotek.workflow.factory.DataFactory;
import com.hitotek.workflow.model.Data;
import com.hitotek.workflow.model.multipart.MultipartData;
import com.hitotek.workflow.service.ProcessTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ztrue
 * @date: 2019-07-10
 */
@RestController
@RequestMapping("/task")
@Slf4j
public class ProcessTaskController {

    @Autowired
    private ProcessTaskService processTaskService;

    /**
     * 获取流程任务变量
     *
     * @param taskId 任务id
     * @param key    变量名
     */
    @GetMapping("/variable/cat")
    public Data getVariableValues(@RequestParam String taskId,
                                  @RequestParam String key) {
        return processTaskService.getVariableValues(taskId, key);
    }

    /**
     * 完成任务
     *
     * @param taskId 任务id
     * @param variables 任务对应的form表单
     * @return
     */
    @PostMapping("/complete")
    public Data completeTaskProcess(@RequestParam String taskId,
                                    @RequestBody MultipartData variables) {
        return processTaskService.completeTaskProcess(taskId, variables);
    }

}
