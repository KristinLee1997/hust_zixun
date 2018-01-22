package com.lee.hust_zixun.service;

import com.lee.hust_zixun.dao.NewsDAO;
import com.lee.hust_zixun.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/1/16 0:16
 * @desc
 **/
@Service
public class NewsService {
    @Autowired
    private NewsDAO newsDAO;

    public List<News> getLatestNews(int userId, int offset, int limit) {
        return newsDAO.selectByUserIdAndOffset(userId, offset, limit);
    }
}
