package com.hitotek.workflow.controller;

import com.hitotek.workflow.model.Data;
import com.hitotek.workflow.service.ProcessHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ztrue
 * @date: 2019-07-10
 */
@RestController
@RequestMapping
public class ProcessHistoryController {
    @Autowired
    private ProcessHistoryService processHistoryService;

}
