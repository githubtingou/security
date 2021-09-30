package com.ting.security.login;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
@Data
public class RoleGrantedAuthority implements GrantedAuthority {

    /**
     * 权限
     */
    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
