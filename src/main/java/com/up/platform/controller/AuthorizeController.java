package com.up.platform.controller;


import com.up.platform.constant.CookieConstant;
import com.up.platform.constant.RedisConstant;
import com.up.platform.dto.ResultDTO;
import com.up.platform.enums.AuthorizeEnum;
import com.up.platform.service.UserService;
import com.up.platform.utils.CookieUtil;
import com.up.platform.utils.JsonUtil;
import com.up.platform.utils.ResultDTOUtil;
import com.up.platform.validation.LoginByNameValidation;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
public class AuthorizeController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO login(@RequestBody @Valid LoginByNameValidation loginValidation, HttpServletResponse response) {

        // 1. 用户信息和数据库匹配
        // TODO 没有正确的用户名返回没有判断
        Integer userId = userService.checkUserPassword(loginValidation.getUserName(), loginValidation.getUserPassword());
        // TODO 存储到redis的信息放到map里，要转成json的字符串
        Map<String, String> redisInfoMap = new HashMap<>();
        // TODO userId不写死，获取数据源也不写死
        redisInfoMap.put("userId", userId.toString());
        if (userId > 0) {
            // 2. 设置token至redis
            String token = UUID.randomUUID().toString();
            Integer expire = RedisConstant.EXPIRE;
            redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), JsonUtil.Map2JsonString(redisInfoMap), expire, TimeUnit.SECONDS);

            // 3. 设置token至cookie
            CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
            return ResultDTOUtil.success(true);
        } else {
            return ResultDTOUtil.error(AuthorizeEnum.PASSWORD_ERROR.getCode(), AuthorizeEnum.PASSWORD_ERROR.getMessage());
        }
    }

    @ApiOperation(value = "登出")
    @PostMapping(value = "/logout", produces = {"application/json;charset=UTF-8"})
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO logout(HttpServletRequest request, HttpServletResponse response) {

        //1. 从cookie查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
            return ResultDTOUtil.success(true);
        } else {
            return ResultDTOUtil.error(AuthorizeEnum.AUTH_NO_COOKIE.getCode(), AuthorizeEnum.AUTH_NO_COOKIE.getMessage());
        }



    }

}
