package com.ting.security.login;

import com.ting.security.login.filter.RestLoginFilter;
import com.ting.security.login.handle.LoginFailureHandle;
import com.ting.security.login.handle.LoginSuccessHandle;
import com.ting.security.login.handle.LogoutSuccessHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 数据权限配置类
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 权限管理
                .authorizeRequests()
                // 不需要权限就可以登录的url
                .antMatchers("/", "/login", "/logout").permitAll()
                .anyRequest().authenticated()
                .and()

                // 表单提交
                .formLogin()
                .and()
                // 用户退出
                .logout().logoutSuccessHandler(new LogoutSuccessHandle());

        http.addFilterAt(restLoginFilter(), UsernamePasswordAuthenticationFilter.class);
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

    /**
     * 自动登录密码验证
     *
     * @return {@link RestLoginFilter}
     * @throws Exception
     */
    @Bean
    public RestLoginFilter restLoginFilter() throws Exception {
        RestLoginFilter restLoginFilter = new RestLoginFilter();
        restLoginFilter.setAuthenticationSuccessHandler(new LoginSuccessHandle());
        restLoginFilter.setAuthenticationFailureHandler(new LoginFailureHandle());
        restLoginFilter.setFilterProcessesUrl("/login");
        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        restLoginFilter.setAuthenticationManager(authenticationManagerBean());
        return restLoginFilter;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new LoginServiceImpl();
    }

}
