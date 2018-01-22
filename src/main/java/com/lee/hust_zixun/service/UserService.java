package com.lee.hust_zixun.service;

import com.lee.hust_zixun.dao.UserDAO;
import com.lee.hust_zixun.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/1/15 23:52
 * @desc
 **/
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User getUser(int id) {
        return userDAO.selectById(id);
    }
}
