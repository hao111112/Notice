package com.hystrix.notice.common;

/**
 * 错误码
 *
 *
 */
public enum ErrorCode {

    SUCCESS(0, "ok"),
    PARAMS_ERROR(40000, "请求参数错误"),
    NOT_LOGIN_ERROR(40100, "未登录"),
    NO_AUTH_ERROR(40101, "无权限"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    FORBIDDEN_ERROR(40300, "黑名单用户禁止访问"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败"),
    USERUPDATE_ERROR(50002,"用户更新失败"),
    NOT_NULL(50003,"请求参数不能为空"),
    REG_ERROR(50004,"注册失败"),
    USERNULL_ERROR(50005,"找不到该用户"),
    UN_REGISTER(50006,"未注册"),
    TOKENISNULL(50007,"token不能为空"),
    TOKEN_BADVALUE(50008,"token的解析异常");

    /**
     * 状态码
     */
    private final int code;


    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
