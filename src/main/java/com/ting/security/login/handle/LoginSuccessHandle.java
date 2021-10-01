package com.ting.security.login.handle;

import com.alibaba.fastjson.JSON;
import com.ting.security.utils.ResultUtils;
import com.ting.security.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 登录成功处理
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
@Component
public class LoginSuccessHandle implements AuthenticationSuccessHandler {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 将用户放入redis中
        final String name = authentication.getName();
        stringRedisTemplate.opsForValue().set(name, name, 10L, TimeUnit.MINUTES);
        ResultVO<Authentication> success = ResultUtils.success(authentication);
        ResultUtils.buildResultByResponse(success, response);
    }
}
