package com.up.platform.utils;

import com.up.platform.enums.RedisEnum;

import java.util.Map;

public class UserUtil {

    public static Integer getUserId(Map<String, Object> maps) {
        Integer userId = (Integer) maps.get(RedisEnum.USER_ID.getMessage());
        return userId;
    }
}
