package com.hitotek.workflow.util;

import java.util.UUID;

/**
 * @author: ztrue
 * @date: 2019-04-28
 */
public class Ids {

    public static String getId () {
        return UUID.randomUUID().toString();
    }

}
