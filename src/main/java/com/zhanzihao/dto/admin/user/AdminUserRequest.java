package com.zhanzihao.dto.admin.user;

import lombok.Data;

/**
 * @author zhanzihao
 * 2021-01-31
 */
@Data
public class AdminUserRequest {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String name;
}
