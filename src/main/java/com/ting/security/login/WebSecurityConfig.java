package com.ting.security.login;

import com.ting.security.login.handle.LoginErrorHandle;
import com.ting.security.login.handle.LoginSuccessHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 数据权限配置类
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.// 登录
                formLogin()
                .loginProcessingUrl("/login")
                // 成功处理
                .successHandler(new LoginSuccessHandle())
                .failureHandler(new LoginErrorHandle())
                .usernameParameter("loginName")
                .usernameParameter("password")
                .and()
                // 权限管理
                .authorizeRequests()
                .anyRequest().authenticated();
        
    }

    /**
     * 密码加解密
     *
     * @return {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
