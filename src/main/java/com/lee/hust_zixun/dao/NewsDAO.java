package com.lee.hust_zixun.dao;

import com.lee.hust_zixun.model.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/1/16 0:01
 * @desc
 **/
@Mapper
public interface NewsDAO {
    String TABLE_NAME = "news";
    String INSERT_FIELDS = " title, link, image, like_count, comment_count, created_date, user_id ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{title},#{link},#{image},#{likeCount},#{commentCount},#{createdDate},#{userId})"})
    int addNews(News news);

    @SelectProvider(type = NewsSqlProvider.class, method = "selectByUserIdAndOffset")
    List<News> selectByUserIdAndOffset(@Param("userId") int userId, @Param("offset") int offset,
                                       @Param("limit") int limit);
}
