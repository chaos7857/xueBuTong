package com.cc.xuebutongbackend.common.model.dto;

import com.cc.xuebutongbackend.common.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMsg(), null);
    }
}
