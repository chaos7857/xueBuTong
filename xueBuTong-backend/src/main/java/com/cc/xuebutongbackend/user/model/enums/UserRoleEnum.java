package com.cc.xuebutongbackend.user.model.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

@Getter
public enum UserRoleEnum {
    USER("用户","user"),
    ADMIN("管理员","admin");

    private final String zh;
    private final String value;
    /*
    * TODO：可以再加一个数字，用来比较权限，方便更多权限用户扩展*/

    UserRoleEnum(String zh, String value) {
        this.zh = zh;
        this.value = value;
    }

    public static UserRoleEnum getEnumByValue(String value){
        if(ObjUtil.isEmpty(value)){
            return null;
        }
        for(UserRoleEnum e : UserRoleEnum.values()){
            if(e.value.equals(value)){
                return e;
            }
        }

        return null;
    }
}
