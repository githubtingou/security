package com.ting.security.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "sys_user")
public class SysUser {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 登录用户名
     */
    @TableField(value = "login_name")
    private String loginName;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 添加时间
     */
    @TableField(value = "add_time")
    private Date addTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 删除标记，0：正常，删除
     */
    @TableLogic(value = "del_flag")
    private Integer delFlag;

    /**
     * 用户状态，0正常，1禁用
     */
    @TableField(value = "`status`")
    private Integer status;
}