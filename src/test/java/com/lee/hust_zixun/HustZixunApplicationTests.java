package com.lee.hust_zixun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)  //此处调用Spring单元测试类
@SpringBootTest
@WebAppConfiguration //调用javaWEB的组件，比如自动注入ServletContext Bean等等
public class HustZixunApplicationTests {

    @Test
    public void contextLoads() {

    }

}
