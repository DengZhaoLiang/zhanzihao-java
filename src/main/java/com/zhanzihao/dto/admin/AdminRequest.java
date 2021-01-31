package com.zhanzihao.dto.admin;

import lombok.Data;

/**
 * @author zhanzihao
 * 2021-01-30
 */
@Data
public class AdminRequest {

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;
}
