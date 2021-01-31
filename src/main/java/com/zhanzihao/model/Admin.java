package com.zhanzihao.model;

import lombok.Data;

/**
 * @author zhanzihao
 * 2021-01-30
 */
@Data
public class Admin {

    /**
     * 管理员ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 更新时间
     */
    private Long updatedAt;
}
