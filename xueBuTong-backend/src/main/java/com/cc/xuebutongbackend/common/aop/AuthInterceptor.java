package com.cc.xuebutongbackend.common.aop;

import com.cc.xuebutongbackend.common.annotation.AuthCheck;
import com.cc.xuebutongbackend.common.exception.ErrorCode;
import com.cc.xuebutongbackend.common.utils.ThrowUtils;
import com.cc.xuebutongbackend.user.constant.UserStatus;
import com.cc.xuebutongbackend.user.model.entity.User;
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

    @Around("@annotation(authCheck)")
    public Object authCheck(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        // 没有指定就放行
        if (mustRole==null){
            return joinPoint.proceed();
        }
        // 指定了得判断是否有权限，这里先全都抛出
        // 获取当前请求用户
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Object attribute = request.getSession().getAttribute(UserStatus.LOGIN_USER);

        // 没登录
        ThrowUtils.throwIf(attribute == null,
                ErrorCode.NO_AUTH_ERROR
        );
        LoginUserVO user = UserUtil.getLoginUserVO((User) attribute);
        String userRole = user.getUserRole();

        // 权限不足
        ThrowUtils.throwIf(!mustRole.equals(userRole),
                ErrorCode.NO_AUTH_ERROR
        );

        return joinPoint.proceed();
    }

}
