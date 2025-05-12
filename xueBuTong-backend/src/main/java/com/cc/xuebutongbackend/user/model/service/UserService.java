package com.cc.xuebutongbackend.user.model.service;

import com.cc.xuebutongbackend.user.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service
* @createDate 2025-05-12 08:21:33
*/
public interface UserService extends IService<User> {
    public Long UserRegister(String username, String password, String confirmPassword);
}
