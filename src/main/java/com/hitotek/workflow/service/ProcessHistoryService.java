package com.hitotek.workflow.service;

import org.activiti.engine.history.HistoricTaskInstance;

import java.util.List;

/**
 * @author: ztrue
 * @date: 2019-07-10
 */
public interface ProcessHistoryService {

    List<HistoricTaskInstance> queryTaskByNodeName(String assigneeId, String definitionKey);

}
