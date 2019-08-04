package com.up.platform.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class JsonUtil {

    public static String Map2JsonString(Map<String, Object> map) {
        return JSON.toJSONString(map);
    }

    public static Map<String, Object> JsonString2Map(String string) {
        JSONObject jsonObject = JSONObject.parseObject(string);
        Map<String, Object> map = (Map) jsonObject;
        return map;
    }
}
