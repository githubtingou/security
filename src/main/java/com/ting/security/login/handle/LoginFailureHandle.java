package com.ting.security.login.handle;

import com.ting.security.utils.ResultUtils;
import com.ting.security.vo.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录失败处理
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
@Component
public class LoginFailureHandle implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ResultVO<AuthenticationException> error = ResultUtils.error(exception.getMessage());
        ResultUtils.buildResultByResponse(error, response);
    }
}
