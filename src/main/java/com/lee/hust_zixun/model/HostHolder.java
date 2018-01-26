package com.lee.hust_zixun.model;

import org.springframework.stereotype.Component;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/1/26 10:56
 * @desc 可以同时登录多个用户, 每个线程管理一个用户
 **/
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    public void clear() {
        users.remove();
    }
}
