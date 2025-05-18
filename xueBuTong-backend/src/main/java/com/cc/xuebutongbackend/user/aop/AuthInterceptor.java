package com.cc.xuebutongbackend.user.aop;

import com.cc.xuebutongbackend.user.annotation.RequireRole;
import com.cc.xuebutongbackend.common.exception.ErrorCode;
import com.cc.xuebutongbackend.common.utils.ThrowUtils;
import com.cc.xuebutongbackend.user.constant.UserStatus;
import com.cc.xuebutongbackend.user.model.entity.User;
import com.cc.xuebutongbackend.user.model.enums.UserRoleEnum;
import com.cc.xuebutongbackend.user.model.vo.LoginUserVO;
import com.cc.xuebutongbackend.user.utils.UserUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthInterceptor {

    @Around("@annotation(requireRole)")
    public Object authCheck(ProceedingJoinPoint joinPoint, RequireRole requireRole) throws Throwable {
        String mustRole = requireRole.userRole();
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        //=================================================================================================
        // 确保已经登录
        // 获取当前请求用户
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Object attribute = request.getSession().getAttribute(UserStatus.LOGIN_USER);

        // 没登录
        ThrowUtils.throwIf(attribute == null,
                ErrorCode.NOT_LOGIN_ERROR
        );

        //=================================================================================================
        // 没有指定、指定了非法用户等级，应当确保最低已登录即可
        // 指定了得判断是否有权限
        if (mustRoleEnum == null) {
            return  joinPoint.proceed();
        }
        // 对于登录用户获取用户角色
        LoginUserVO user = UserUtil.getLoginUserVO((User) attribute);
        String userRole = user.getUserRole();

        // 权限不足
        ThrowUtils.throwIf(!mustRole.equals(userRole),
                ErrorCode.NO_AUTH_ERROR
        );

        return joinPoint.proceed();
    }

}
