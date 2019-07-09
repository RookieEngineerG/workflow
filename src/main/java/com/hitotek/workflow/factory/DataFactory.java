package com.hitotek.workflow.factory;


import com.hitotek.workflow.constant.Messages;
import com.hitotek.workflow.model.Data;

import java.time.LocalDateTime;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description 消息模型工厂
 * @date Create at 2018/7/26
 */
public class  DataFactory {
    /**
     * 默认的消息模型
     * 只带有时间戳
     * 作为基础的消息模型，后续的模型都从默认这里取
     *
     * @return 消息模型
     */
    public static Data createDefault() {
        return new Data()
                .include(Messages.KEY_TIME, LocalDateTime.now());
    }

    /**
     * 请求成功的消息模型
     *
     * @return 消息模型
     */
    public static Data createSuccess() {
        return createDefault()
                .include(Messages.KEY_MSG, Messages.MSG_SUCCESS)
                .include(Messages.KEY_CODE, Messages.CODE_SUCCESS);
    }

    /**
     * 请求失败的消息模型
     *
     * @return 消息模型
     */
    public static Data createFail() {
        return createDefault()
                .include(Messages.KEY_MSG, Messages.MSG_FAILED)
                .include(Messages.KEY_CODE, Messages.CODE_FAILED);
    }

    /**
     * 请求后数据为空的消息模型
     *
     * @return 消息模型
     */
    public static Data createEmptyData() {
        return createDefault()
                .include(Messages.KEY_MSG, Messages.MSG_EMPTY_DATA)
                .include(Messages.KEY_CODE, Messages.CODE_EMPTY);
    }

    /**
     * 请求后数据为空的消息模型
     *
     * @return 消息模型
     */
    public static Data createServiceNotRegister() {
        return createDefault()
                .include(Messages.KEY_MSG, Messages.MSG_SERVICE_NOT_REGISTER)
                .include(Messages.KEY_CODE, Messages.CODE_ERROR);
    }

    public static Data createServiceNoResponse() {
        return createDefault()
                .include(Messages.KEY_MSG, Messages.MSG_SERVICE_NO_RESPONSE)
                .include(Messages.KEY_CODE, Messages.CODE_ERROR);
    }

    /**
     * 请求失败的消息模型
     *
     * @return 消息模型
     */
    public static Data createError() {
        return createDefault()
                .include(Messages.KEY_MSG, Messages.MSG_ERROR)
                .include(Messages.KEY_CODE, Messages.CODE_ERROR);
    }

    /**
     * 请求角色权限不够的消息模型
     *
     * @return 消息模型
     */
    public static Data createAuthorizationError() {
        return createError()
                .include(Messages.KEY_MSG, Messages.MSG_ERROR_AUTHORIZATION)
                .include(Messages.KEY_CODE, Messages.CODE_ERROR_AUTHORIZATION);
    }

    /**
     * 请求角色限不够的消息模型
     *
     * @return 消息模型
     */
    public static Data createAuthenticatedError() {
        return createError()
                .include(Messages.KEY_MSG, Messages.MSG_ERROR_AUTHENTICATED)
                .include(Messages.KEY_CODE, Messages.CODE_ERROR_AUTHENTICATED);
    }

    public static Data createSocketMessage(String key) {
        return DataFactory.createSuccess()
                .include(Messages.KEY_WEBSOCKET_SERVICE_TYPE, key);
    }

    /**
     * 请求会话超时的消息模型
     *
     * @return 消息模型
     */
    public static Data createSessionTimeoutError() {
        return createDefault()
                .include(Messages.KEY_MSG, Messages.MSG_ERROR_SESSION_TIMEOUT)
                .include(Messages.KEY_CODE, Messages.CODE_ERROR_SESSION_TIMEOUT);
    }
}
