package com.lee.hust_zixun.configuration;

import com.lee.hust_zixun.interceptor.LoginRequiredInterceptor;
import com.lee.hust_zixun.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/1/26 11:13
 * @desc
 **/
public class ZixunWebConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    PassportInterceptor passportInterceptor;

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/setting*");
        super.addInterceptors(registry);
    }
}
