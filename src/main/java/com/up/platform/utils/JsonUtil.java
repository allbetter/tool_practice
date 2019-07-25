package com.up.platform.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class JsonUtil {

    public static String Map2JsonString(Map<String, String> map) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(map);
        return jsonArray.toJSONString();
    }

    public static Map<String, String> JsonString2Map(String string) {
        JSONObject jsonObject = JSONObject.parseObject(string);
        Map<String, String> map = (Map) jsonObject;
        return map;
    }
}
