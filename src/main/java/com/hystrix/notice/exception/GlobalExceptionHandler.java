package com.hystrix.notice.exception;


import com.hystrix.notice.common.BaseResponse;
import com.hystrix.notice.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * 全局异常处理器
 *
 * @author yupi
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public BaseResponse< LinkedHashMap> businessExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        LinkedHashMap errors = new LinkedHashMap<>();
        for (FieldError fieldError : fieldErrors) {
          errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return new BaseResponse<>(100,errors);
    }

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        System.out.println("hello");
        log.error("businessException: " + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }


}
