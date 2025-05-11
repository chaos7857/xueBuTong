package com.cc.xuebutongbackend.common.aop;

import com.cc.xuebutongbackend.common.annotation.AuthCheck;
import com.cc.xuebutongbackend.common.exception.BusinessException;
import com.cc.xuebutongbackend.common.exception.ErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthInterceptor {

    @Around("@annotation(authCheck)")
    public Object authCheck(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        // 没有指定就放行
        if (mustRole == null) {
            return joinPoint.proceed();
        }else {
            // 指定了得判断是否有权限，这里先全都抛出
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

    }

}
