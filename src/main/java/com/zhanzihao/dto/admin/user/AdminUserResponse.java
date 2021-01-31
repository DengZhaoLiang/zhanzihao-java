package com.zhanzihao.dto.admin.user;

import lombok.Data;

/**
 * @author zhanzihao
 * 2021-01-31
 */
@Data
public class AdminUserResponse {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 最近登录时间
     */
    private Long lastLoginAt;

    /**
     * 创建时间
     */
    private Long createdAt;
}
