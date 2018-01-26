package com.lee.hust_zixun.service;

import com.lee.hust_zixun.dao.LoginTicketDao;
import com.lee.hust_zixun.dao.UserDAO;
import com.lee.hust_zixun.model.LoginTicket;
import com.lee.hust_zixun.model.User;
import com.lee.hust_zixun.util.ZixunUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.*;

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

    @Autowired
    LoginTicketDao loginTicketDao;

    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(username)) {
            map.put("msgname", "用户名不能为空");
            return map;
        }
        if (StringUtils.isEmpty(password)) {
            map.put("msgpwd", "密码不能为空");
            return map;
        }

        User user = userDAO.selectByName(username);
        if (user != null) {
            map.put("msgname", "用户名已存在");
            return map;
        }
        user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setPassword(ZixunUtils.MD5(password + user.getSalt()));
        userDAO.addUser(user);
        map.put("users", user);

        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        return map;
    }


    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(username)) {
            map.put("msgname", "用户名不能为空");
            return map;
        }
        if (StringUtils.isEmpty(password)) {
            map.put("msgpwd", "密码不能为空");
            return map;
        }

        User user = userDAO.selectByName(username);
        if (user == null) {
            map.put("msgname", "用户名不存在");
            return map;
        }
        if (!ZixunUtils.MD5(password + user.getSalt()).equals(user.getPassword())) {
            map.put("msgpwd", "密码不正确");
            return map;
        }
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        map.put("users", user);
        return map;
    }

    public void logout(String ticket) {
        loginTicketDao.updateStatus(ticket, 1);
    }


    private String addLoginTicket(int userId) {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 24);
        loginTicket.setExpired(date);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDao.addTicket(loginTicket);
        return loginTicket.getTicket();
    }

    public User getUser(int id) {
        return userDAO.selectById(id);
    }
}
