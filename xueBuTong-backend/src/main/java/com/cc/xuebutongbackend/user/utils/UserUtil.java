package com.cc.xuebutongbackend.user.utils;

import cn.hutool.core.bean.BeanUtil;
import com.cc.xuebutongbackend.user.model.entity.User;
import com.cc.xuebutongbackend.user.model.vo.LoginUserVO;
import org.springframework.util.DigestUtils;

public class UserUtil {
    public static String getEncodedPassword(String password) {
        final String SALT = "vagrant";
        return DigestUtils.md5DigestAsHex((SALT + password).getBytes());
    }

    public static LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtil.copyProperties(user, loginUserVO);
        return loginUserVO;
    }
}
