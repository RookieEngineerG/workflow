package com.hitotek.workflow.service.impl;

import com.hitotek.workflow.service.ProcessHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ztrue
 * @date: 2019-07-10
 */
@Service
@Slf4j
public class ProcessHistoryServiceImpl implements ProcessHistoryService {

    @Autowired
    private HistoryService historyService;

    /**
     * 历史任务实例查询
     * @param assigneeId 执行人id
     * @param definitionKey
     * @return
     */
    @Override
    public List<HistoricTaskInstance> queryTaskByNodeName(String assigneeId, String definitionKey) {
        // 创建历史任务实例查询
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(assigneeId)
                .taskDefinitionKey(definitionKey)
                .list();
        return list;
    }
}
