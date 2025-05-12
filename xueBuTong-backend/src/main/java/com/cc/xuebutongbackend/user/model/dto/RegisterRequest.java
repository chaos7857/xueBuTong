package com.cc.xuebutongbackend.user.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRequest implements Serializable {
    public String userAccount;
    public String userPassword;
    public String passwordConfirm;
//    public String email;
}
