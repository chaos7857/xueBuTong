package com.cc.xuebutongbackend.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.cc.xuebutongbackend.common.annotation.AuthCheck;
import com.cc.xuebutongbackend.common.exception.ErrorCode;
import com.cc.xuebutongbackend.common.model.dto.BaseResponse;
import com.cc.xuebutongbackend.common.utils.ResultUtils;
import com.cc.xuebutongbackend.common.utils.ThrowUtils;
import com.cc.xuebutongbackend.user.constant.UserRole;
import com.cc.xuebutongbackend.user.model.dto.UserAddRequest;
import com.cc.xuebutongbackend.user.model.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/admin")
@RestController
public class AdminController {
    @Resource
    UserServiceImpl userService;

    @AuthCheck(mustRole = UserRole.ROLE_ADMIN)
    @PostMapping("/add")
    public BaseResponse<Long> add(@RequestBody UserAddRequest user) {
        ThrowUtils.throwIf(BeanUtil.isEmpty(user), ErrorCode.PARAMS_ERROR);
        String userAccount = user.getUserAccount();
        String userName = user.getUserName();
        Long l = userService.addUser(userAccount, userName);
        return ResultUtils.success(l);
    }
}
