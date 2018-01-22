package com.lee.hust_zixun.dao;

import com.lee.hust_zixun.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/1/15 22:40
 * @desc
 **/
@Mapper
public interface UserDAO {
    String TABLE_NAME = "user";
    String INSERT_FILED = "name, password,salt,head_url";
    String SELECT_FILED = "id,name,password,salt,head_url";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FILED, ") " +
            "values(#{name},#{password},#{salt},#{headUrl})"})  //这里是headUrl变量名
    int addUser(User user);

    @Select({"select ", SELECT_FILED, "from ", TABLE_NAME, "where id=#{id}"})
    User selectById(int id);

    @Update({"update ", TABLE_NAME, "set password=#{password} where id=#{id}"})
    void updatePassword(User user);


    @Delete({"delete from ", TABLE_NAME, "where id=#{id}"})
    void deleteById(int id);
}
