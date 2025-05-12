package com.cc.xuebutongbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.cc.xuebutongbackend.*.model.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class XueBuTongBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(XueBuTongBackendApplication.class, args);
    }

}
