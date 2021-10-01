package com.ting.security.login.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 自定义rest方式的登录
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
@Slf4j
public class RestLoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!HttpMethod.POST.name().equals(request.getMethod())) {
            throw new AuthenticationServiceException("该方法不支持该请求方式:" + request.getMethod());
        }

        if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {

            final RestLoginInfo loginRequest = getLoginRequest(request);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUserName(),
                    loginRequest.getPassword()
            );
            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);

        } else {
            return super.attemptAuthentication(request, response);

        }

    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
            return getLoginRequest(request).getUserName();
        } else {
            return super.obtainUsername(request);
        }

    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
            return getLoginRequest(request).getPassword();
        } else {
            return super.obtainPassword(request);

        }
    }

    @Data
    public static class RestLoginInfo {
        private String userName;
        private String password;

    }

    private RestLoginInfo getLoginRequest(HttpServletRequest request) {
        final RestLoginInfo loginInfo = new RestLoginInfo();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> result;
        try (ServletInputStream inputStream = request.getInputStream()) {
            result = mapper.readValue(inputStream, new TypeReference<Map<String, String>>() {
            });

            // 参考父类的方法
            String username = result.get("username");
            String password = result.get("password");

            username = (username != null) ? username : "";
            loginInfo.setUserName(username.trim());
            loginInfo.setPassword(password != null ? password : "");
        } catch (IOException e) {
            log.error("获取参数失败", e);
            throw new AuthenticationServiceException("登录数据解析失败");

        }
        return loginInfo;
    }
}
