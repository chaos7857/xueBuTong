package com.cc.xuebutongbackend.user.model.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class LoginRequest implements Serializable {
    private String userAccount;
    private String password;
}
