package com.hitotek.workflow.constant;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @Description 保存接口回调的键值对常量
 * @date Create at 2018/7/21
 */
public interface Messages {
    /**
     * 接口回调键 msg 保存传给前端的消息
     */
    public final static String KEY_MSG = "msg";
    /**
     * 保存当前接口请求状态
     */
    public final static String KEY_CODE = "code";
    /**
     * 保存当前请求数据内容
     */
    public final static String KEY_DATA = "data";
    /**
     * 当前时间戳
     */
    public final static String KEY_TIME = "timestamp";

    /**
     * 服务名称
     */
    public final static String KEY_WEBSOCKET_SERVICE_TYPE = "serviceType";
    /**
     * 当前分页的总数
     */
    public final static String KEY_PAGE_SIZE = "pageSize";
    /**
     * 下一页
     */
    public final static String KEY_PAGE_NEXT = "nextPage";
    /**
     * 请求成功
     */
    public final static int CODE_SUCCESS = 200;
    /**
     * 请求失败
     */
    public final static int CODE_FAILED = 400;
    /**
     * 服务端错误
     */
    public final static int CODE_ERROR = 500;
    /**
     * 角色未认证错误
     */
    public final static int CODE_ERROR_AUTHORIZATION = 520;
    /**
     * 权限未认证错误
     */
    public final static int CODE_ERROR_AUTHENTICATED = 521;
    /**
     * 会话超时错误
     */
    public final static int CODE_ERROR_SESSION_TIMEOUT = 601;
    /**
     * 空消息
     */
    public final static int CODE_EMPTY = 402;
    /**
     * 请求成功消息回调
     */
    public final static String MSG_SUCCESS = "请求成功";
    /**
     * 请求后数据空的回调
     */
    public final static String MSG_EMPTY_DATA = "找不到记录...";

    public final static String MSG_SERVICE_NOT_REGISTER = "当前服务未注册";

    public final static String MSG_SERVICE_NO_RESPONSE = "请求服务未得到响应";

    /**
     * 请求失败消息回调
     */
    public final static String MSG_FAILED = "请求失败，请检查参数是否正确";

    /**
     * 服务端错误消息回调
     */
    public final static String MSG_ERROR = "哎呀，服务器开小差了";
    /**
     * 角色未认证错误回调
     */
    public final static String MSG_ERROR_AUTHORIZATION = "抱歉，您的角色权限不够";
    /**
     * 权限未认证错误回调
     */
    public final static String MSG_ERROR_AUTHENTICATED = "抱歉，您的权限不够";
    /**
     * 会话超时
     */
    public final static String MSG_ERROR_SESSION_TIMEOUT = "会话超时，请重新登录";

}
