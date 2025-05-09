package com.cc.xuebutongbackend.common.controller;

import com.cc.xuebutongbackend.common.model.dto.BaseResponse;
import com.cc.xuebutongbackend.common.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {
    @GetMapping("/health")
    public BaseResponse<String> healthCheck(){
        return ResultUtils.success("OK");
    }
}
