package com.up.platform.aspect;

import com.up.platform.constant.CookieConstant;
import com.up.platform.constant.RedisConstant;
import com.up.platform.exception.AuthorizeException;
import com.up.platform.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
// TODO @Configuration 和 @Component的区别
//@Configuration
//@Component
public class AuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("within(com.up.platform.controller..*)" +
            "&& !execution(public * com.up.platform.controller.AuthorizeController.*(..))")
    public void verify() {}

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            throw new AuthorizeException();
        }

        // 去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            throw new AuthorizeException();
        }
    }
}
