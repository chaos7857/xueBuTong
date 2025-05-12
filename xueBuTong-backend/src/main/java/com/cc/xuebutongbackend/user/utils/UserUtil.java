package com.cc.xuebutongbackend.user.utils;

import org.springframework.util.DigestUtils;

public class UserUtil {
    public static String getEncodedPassword(String password) {
        final String SALT = "vagrant";
        return DigestUtils.md5DigestAsHex((SALT + password).getBytes());
    }
}
