package com.hitotek.workflow.controller;

import com.hitotek.workflow.factory.DataFactory;
import com.hitotek.workflow.model.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: ztrue
 * @date: 2019-07-08
 */
@RestController
@RequestMapping("/base")
public class BaseController {

    @GetMapping("/deploy/myProcess")
    public Data helloWorld () {
        return DataFactory.createSuccess().message("helloworld!");
    }

}
