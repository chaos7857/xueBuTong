package com.cc.xuebutongbackend.user.model.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.xuebutongbackend.common.exception.BusinessException;
import com.cc.xuebutongbackend.common.exception.ErrorCode;
import com.cc.xuebutongbackend.common.utils.ThrowUtils;
import com.cc.xuebutongbackend.user.constant.UserDefault;
import com.cc.xuebutongbackend.user.constant.UserRole;
import com.cc.xuebutongbackend.user.constant.UserStatus;
import com.cc.xuebutongbackend.user.model.entity.User;
import com.cc.xuebutongbackend.user.model.mapper.UserMapper;
import com.cc.xuebutongbackend.user.model.service.UserService;
import com.cc.xuebutongbackend.user.model.vo.LoginUserVO;
import com.cc.xuebutongbackend.user.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-05-12 08:21:33
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public Long userRegister(String userAccount, String userPassword, String confirmPassword) {
        // 1. 校验参数
        ThrowUtils.throwIf(
                StrUtil.hasBlank(userAccount, userPassword, confirmPassword),
                ErrorCode.PARAMS_ERROR,"参数为空"
        );
        ThrowUtils.throwIf(
                userAccount.length()<4,
                ErrorCode.PARAMS_ERROR,"账号过短"
        );
        ThrowUtils.throwIf(
                userPassword.length()<8,
                ErrorCode.PARAMS_ERROR,"密码强调过低"
        );
        ThrowUtils.throwIf(
                !userPassword.equals(confirmPassword),
                ErrorCode.PARAMS_ERROR,"两次输入的密码不一致"
        );

        // 2. 检查用户账号是否和数据库中已有的重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        Long count = this.baseMapper.selectCount(userQueryWrapper);
        ThrowUtils.throwIf(
                count>0,
                ErrorCode.PARAMS_ERROR,"账户已存在"
        );

        // 3. 密码一定要加密
        String encodedPassword = UserUtil.getEncodedPassword(userPassword);
        // 4. 插入数据到数据库中
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encodedPassword);
        user.setUserName(UserDefault.USER_NAME);
        user.setUserRole(UserRole.ROLE_USER);

        boolean saveRes = this.save(user);
        ThrowUtils.throwIf(
                !saveRes,
                ErrorCode.SYSTEM_ERROR,"注册失败，数据库错误"
        );
        return user.getId();
    }

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        ThrowUtils.throwIf(
                StrUtil.hasBlank(userAccount, userPassword),
                ErrorCode.PARAMS_ERROR, "参数为空"
        );
        ThrowUtils.throwIf (
                userAccount.length() < 4,
                ErrorCode.PARAMS_ERROR, "用户账号错误"
        );
        ThrowUtils.throwIf(
                userPassword.length() < 8,
                ErrorCode.PARAMS_ERROR, "用户密码错误"
        );
        // 2. 对用户传递的密码进行加密
        String encodedPassword = UserUtil.getEncodedPassword(userPassword);
        // 3. 查询数据库中的用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encodedPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        // 不存在，抛异常
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或者密码错误");
        }
        // 4. 保存用户的登录态
        request.getSession().setAttribute(UserStatus.LOGIN_USER, user);
        return UserUtil.getLoginUserVO(user);
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 判断是否已经登录
        Object userObj = request.getSession().getAttribute(UserStatus.LOGIN_USER);
        User currentUser = (User) userObj;
        ThrowUtils.throwIf(
                currentUser == null || currentUser.getId() == null,
                ErrorCode.NOT_LOGIN_ERROR
        );
        // 从数据库中查询（追求性能的话可以注释，直接返回上述结果）
//        Long userId = currentUser.getId();
//        currentUser = this.getById(userId);
//        ThrowUtils.throwIf(
//                currentUser == null,
//                ErrorCode.NOT_LOGIN_ERROR
//        );
        return currentUser;
    }
}




