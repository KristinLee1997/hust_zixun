package com.lee.hust_zixun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/1/26 12:35
 * @desc
 **/
@Controller
public class SettingController {
    @RequestMapping("/setting")
    @ResponseBody
    public String setting() {
        return "Setting:OK";
    }
}
