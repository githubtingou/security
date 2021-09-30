package com.ting.security.login;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ting.security.common.SecurityCommon;
import com.ting.security.domain.SysUser;
import com.ting.security.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 登录用户管理
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
@Slf4j
@Service
public class LoginServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 获取登录的信息
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("{}用户进行的登录验证", username);
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getLoginName, username);
        SysUser sysUser = userMapper.selectOne(lambdaQueryWrapper);

//        if (ObjectUtils.isEmpty(sysUser)) {
//            throw new UsernameNotFoundException("没有改用户名");
//        }

        LoginUserDetails loginUserDetails = new LoginUserDetails();
        loginUserDetails.setLoginName(username);
        loginUserDetails.setDeleteFlag(SecurityCommon.UN_DELETE);
        loginUserDetails.setPassword("123456");
        loginUserDetails.setStatus(SecurityCommon.NORMAL_STATUS);
        return loginUserDetails;
    }
}
