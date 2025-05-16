package com.cc.xuebutongbackend.user.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/*
* 脱敏用户数据*/
@Data
public class LoginUserVO implements Serializable {
    public Long id;
    public String userAccount;
    public String userName;
    public String userAvatar;
    public String userProfile;
    public String userRole;
    public Date createTime;
    public Date updateTime;
}
