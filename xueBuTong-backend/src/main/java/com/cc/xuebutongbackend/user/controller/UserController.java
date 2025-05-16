package com.cc.xuebutongbackend.user.controller;

import com.cc.xuebutongbackend.common.exception.ErrorCode;
import com.cc.xuebutongbackend.common.model.dto.BaseResponse;
import com.cc.xuebutongbackend.common.utils.ResultUtils;
import com.cc.xuebutongbackend.common.utils.ThrowUtils;
import com.cc.xuebutongbackend.user.model.dto.LoginRequest;
import com.cc.xuebutongbackend.user.model.dto.RegisterRequest;
import com.cc.xuebutongbackend.user.model.entity.User;
import com.cc.xuebutongbackend.user.model.service.impl.UserServiceImpl;
import com.cc.xuebutongbackend.user.model.vo.LoginUserVO;
import com.cc.xuebutongbackend.user.utils.UserUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserServiceImpl userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody RegisterRequest registerRequest) {
        ThrowUtils.throwIf(registerRequest==null, ErrorCode.PARAMS_ERROR);
        String userAccount = registerRequest.getUserAccount();
        String userPassword = registerRequest.getUserPassword();
        String passwordConfirm = registerRequest.getPasswordConfirm();
        Long result = userService.userRegister(userAccount, userPassword, passwordConfirm);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(loginRequest==null, ErrorCode.PARAMS_ERROR);
        String userAccount = loginRequest.getUserAccount();
        String userPassword = loginRequest.getPassword();
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(loginUserVO);
    }

    @GetMapping("/logout")
    public BaseResponse<String> userLogout(HttpServletRequest httpServletRequest){
        String result = userService.userLogout(httpServletRequest);
        return ResultUtils.success(result);
    }

    @GetMapping("/me")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest httpServletRequest){
        User user = userService.getLoginUser(httpServletRequest);
        LoginUserVO loginUserVO = UserUtil.getLoginUserVO(user);
        return ResultUtils.success(loginUserVO);
    }

    /*
    * TODO：缺一个个人信息修改，其中的头像上传先缓一下，考虑下是存本地还是cos还是其他的*/
}
