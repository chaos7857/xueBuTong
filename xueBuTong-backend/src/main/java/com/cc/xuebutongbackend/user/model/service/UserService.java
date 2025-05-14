package com.cc.xuebutongbackend.user.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.xuebutongbackend.user.model.entity.User;
import com.cc.xuebutongbackend.user.model.vo.LoginUserVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service
* @createDate 2025-05-12 08:21:33
*/
public interface UserService extends IService<User> {
    public Long userRegister(String username, String password, String confirmPassword);
    public LoginUserVO userLogin(String username, String password, HttpServletRequest request);
    public User getLoginUser(HttpServletRequest request);
    public String userLogout(HttpServletRequest request);

    public Long addUser(String userAccount, String userName);
}
