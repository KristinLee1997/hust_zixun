package com.lee.hust_zixun.interceptor;

import com.lee.hust_zixun.dao.LoginTicketDao;
import com.lee.hust_zixun.dao.UserDAO;
import com.lee.hust_zixun.model.HostHolder;
import com.lee.hust_zixun.model.LoginTicket;
import com.lee.hust_zixun.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/1/25 16:52
 * @desc 拦截器
 **/
@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginTicketDao loginTicketDao;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = null;
        //判断cookie中是否存在ticket,如果存在说明用户已经登录,然后获取到ticket的值
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
//如果存在ticket并不能说明该用户确实已登录,需要判断ticket的值是否正确,去数据库查询是否存在该ticket
        if (ticket != null) {
            LoginTicket loginTicket = loginTicketDao.selectByTicket(ticket);
            if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0) {
                return true;
            }

            User user = userDAO.selectById(loginTicket.getUserId());
            hostHolder.setUser(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && hostHolder.getUser() != null) {
            modelAndView.addObject("user", hostHolder.getUser());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        hostHolder.clear();
    }
}
