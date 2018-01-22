package com.lee.hust_zixun.dao;

import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.runtime.Desc;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/1/22 14:53
 * @desc
 **/
public class NewsSqlProvider {
    public String selectByUserIdAndOffset(@Param("userId") int userId, @Param("offset") int offset,
                                          @Param("limit") int limit) {
        return new SQL() {{
            SELECT("id,title, link, image, like_count, comment_count,created_date,user_id");
            FROM("news");
            if (userId != 0) {
                WHERE("user_id = #{userId}");
            }
            ORDER_BY("id");

        }}.toString();
    }
}
