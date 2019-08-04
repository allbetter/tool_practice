package com.up.platform.aspect;

import com.up.platform.constant.CookieConstant;
import com.up.platform.constant.RedisConstant;
import com.up.platform.entity.SysUser;
import com.up.platform.exception.AuthorizeException;
import com.up.platform.manager.RequestHolder;
import com.up.platform.utils.CookieUtil;
import com.up.platform.utils.JsonUtil;
import com.up.platform.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
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
import java.util.Map;

@Aspect
// TODO @Configuration 和 @Component的区别
//@Configuration
@Component
public class AuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("within(com.up.platform.controller..*)" +
            "&& !execution(public * com.up.platform.controller.AuthorizeController.*(..))")
    public void verify() {}

    // TODO SecurityContextHolder的鉴权方式
    // TODO 移除对应的ThreadLocal对象，防止内存泄漏
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
        Map<String, Object> redisInfoMap = JsonUtil.JsonString2Map(tokenValue);
        Integer userId = UserUtil.getUserId(redisInfoMap);
        if (StringUtils.isEmpty(tokenValue)) {
            throw new AuthorizeException();
        }

        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        RequestHolder.add(sysUser);
        RequestHolder.add(request);
    }
}
