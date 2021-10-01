package com.ting.security.login.handle;

import com.ting.security.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录自定义处理
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
@Slf4j
public class LogoutSuccessHandle implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String name = authentication.getName();
        log.info("{}用户退出系统：", name);
        if (!ObjectUtils.isEmpty(authentication)) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            // 清除token 获取jwt (暂定存储方式)
            response.setHeader("token", null);
            ResultUtils.buildResultByResponse(ResultUtils.success(), response);
        }


    }
}
