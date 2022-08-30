package com.example.currentlimiting.config;

import com.example.currentlimiting.aop.RedisLimiting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    private RedisLimiting redisLimiting;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(redisLimiting).addPathPatterns("/**");
    }
}
