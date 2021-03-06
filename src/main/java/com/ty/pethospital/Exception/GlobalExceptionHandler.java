package com.ty.pethospital.Exception;

import com.ty.pethospital.ApiCommon.ApiResponse;
import com.ty.pethospital.Common.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

/**
 * 全局统一异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse handlerException(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            log.error("【全局异常拦截】MethodArgumentNotValidException", e);
            return ApiResponse.status(Status.BAD_REQUEST);
        } else if (e instanceof ConstraintViolationException) {
            log.error("【全局异常拦截】ConstraintViolationException", e);
            return ApiResponse.status(Status.BAD_REQUEST);
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            log.error("【全局异常拦截】MethodArgumentTypeMismatchException: 参数名 {}, 异常信息 {}", ((MethodArgumentTypeMismatchException) e).getName(), ((MethodArgumentTypeMismatchException) e).getMessage());
            return ApiResponse.status(Status.PARAM_NOT_MATCH);
        } else if (e instanceof HttpMessageNotReadableException) {
            log.error("【全局异常拦截】HttpMessageNotReadableException: 错误信息 {}", ((HttpMessageNotReadableException) e).getMessage());
            return ApiResponse.status(Status.PARAM_NOT_NULL);
        } else if (e instanceof BadCredentialsException) {
            log.error("【全局异常拦截】BadCredentialsException: 错误信息 {}", e.getMessage());
            return ApiResponse.status(Status.USERNAME_PASSWORD_ERROR);
        } else if (e instanceof DisabledException) {
            log.error("【全局异常拦截】BadCredentialsException: 错误信息 {}", e.getMessage());
            return ApiResponse.status(Status.USER_DISABLED);
        } else if (e instanceof AccessDeniedException) {
            log.error("【全局异常拦截】BadCredentialsException: 错误信息 {}", e.getMessage());
            return ApiResponse.status(Status.ACCESS_DENIED);
        } else if (e instanceof DisabledException) {
            log.error("【全局异常拦截】BadCredentialsException: 错误信息 {}", e.getMessage());
            return ApiResponse.status(Status.USER_DISABLED);
        }

        log.error("【全局异常拦截】: 异常信息 {} ", e.getMessage());
        return ApiResponse.status(Status.ERROR);
    }
}
