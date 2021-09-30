package com.ting.security.login;

import com.ting.security.common.SecurityCommon;
import com.ting.security.domain.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * 登录用户
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
@Data
public class LoginUserDetails implements UserDetails {

    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 删除标记
     */
    private int deleteFlag;
    /**
     * 状态
     */
    private int status;

    /**
     * 数据权限
     */
    private Set<? extends GrantedAuthority> roleSet;

    /**
     * 返回授予用户的权限
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<RoleGrantedAuthority>() {{
            RoleGrantedAuthority authority = new RoleGrantedAuthority();
            authority.setRole("ROLE_ADMIN");
            add(authority);
        }};
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    /**
     * 指示用户的帐户是否已过期。无法验证过期帐户
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指示用户是被锁定还是未锁定。无法对锁定的用户进行身份验证。 @return <code>true<code> 如果用户没有被锁定，<code>false<code> 否则
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.status == SecurityCommon.NORMAL_STATUS;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == SecurityCommon.NORMAL_STATUS;
    }

    /**
     * 封装数据
     *
     * @param sysUser {@link SysUser}
     * @return {@link }
     */
    public UserDetails buildBySysUser(SysUser sysUser) {
        this.loginName = sysUser.getLoginName();
        this.deleteFlag = sysUser.getDelFlag();
        this.status = sysUser.getStatus();
        this.password = getPassword();
        return this;
    }
}
