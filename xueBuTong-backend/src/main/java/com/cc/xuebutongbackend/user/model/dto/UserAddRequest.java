package com.cc.xuebutongbackend.user.model.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserAddRequest implements Serializable {
    public String userAccount;
    public String userName;
//    public String email;
}
