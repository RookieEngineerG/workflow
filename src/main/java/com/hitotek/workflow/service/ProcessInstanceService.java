package com.hitotek.workflow.service;

import com.hitotek.workflow.model.Data;
import com.hitotek.workflow.model.multipart.MultipartData;

/**
 * @author: ztrue
 * @date: 2019-07-10
 */
public interface ProcessInstanceService {

    Data deleteProcessInstance(String instanceId);

    Data startProcessInstanceByKey(String definitionKey, MultipartData variables);

}
