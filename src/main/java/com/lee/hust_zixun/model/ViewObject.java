package com.lee.hust_zixun.model;

import freemarker.template.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/1/16 13:27
 * @desc
 **/
public class ViewObject {
    private Map<String, Object> objs = new HashMap<String, Object>();

    public void set(String key, Object value) {
        objs.put(key, value);
    }

    public Object get(String key) {
        return objs.get(key);
    }
}

