package com.ty.pethospital.Common;

import lombok.Getter;

/**
 * <p>
 * 通用状态码
 * </p>
 */
@Getter
public enum Status {
    /**
     * 操作成功！
     */
    SUCCESS(200, "操作成功！"),
    /**
     * 操作异常！
     */
    ERROR(500, "操作异常！"),
    /**
     * 暂无权限访问！
     */
    ACCESS_DENIED(403, "权限不足！"),
    /**
     * 暂无权限访问！
     */
    ACCESS_JWT_DENIED(403, "认证失败！"),
    /**
     * 请求异常！
     */
    BAD_REQUEST(400, "请求异常！"),
    /**
     * 参数不匹配！
     */
    PARAM_NOT_MATCH(400, "参数不匹配！"),
    /**
     * 参数不能为空！
     */
    PARAM_NOT_NULL(400, "参数不能为空！"),
    /**
     * 当前用户已被锁定，请联系管理员解锁！
     */
    USER_DISABLED(403, "当前用户已被锁定，请联系管理员解锁！"),
    /**
     * 用户名或密码错误！
     */
    USERNAME_PASSWORD_ERROR(5001, "用户名或密码错误！"),
    /**
     * token 已过期，请重新登录！
     */
    TOKEN_EXPIRED(5002, "token 已过期，请重新登录！");
    /**
     * 状态码
     */
    private Integer status;
    /**
     * 返回信息
     */
    private String message;

    Status(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static Status fromStatusCode(Integer statusCode) {
        Status[] statuses = Status.values();
        for (Status status : statuses) {
            if (status.getStatus()
                    .equals(statusCode)) {
                return status;
            }
        }
        return SUCCESS;
    }

    @Override
    public String toString() {
        return String.format(" Status:{status=%s, message=%s} ", getStatus(), getMessage());
    }
}
