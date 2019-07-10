package com.hitotek.workflow.service;

import com.hitotek.workflow.model.Data;

/**
 * @author: ztrue
 * @date: 2019-07-09
 */
public interface ProcessDefinitionService {
    Data deployMyProcess();

    Data removeProcessDefinitions(String deploymentId);

    Data getProcessDefinitions(String deploymentId);

    Data getLatestProcessDefinitions(String definitionKey);
}
