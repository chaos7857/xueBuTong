package com.cc.xuebutongbackend.common.exception;

import com.cc.xuebutongbackend.common.model.dto.BaseResponse;
import com.cc.xuebutongbackend.common.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e){
        log.error("businessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e){
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }
}
