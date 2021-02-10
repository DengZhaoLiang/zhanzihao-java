package com.zhanzihao.dto.api.user;

import lombok.Data;

/**
 * @author zhanzihao
 * 2021-02-10
 */
@Data
public class LoginRequest {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;
}
