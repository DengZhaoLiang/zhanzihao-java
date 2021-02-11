package com.zhanzihao.service.api.user;

import com.zhanzihao.dto.api.user.LoginRequest;
import com.zhanzihao.model.User;

/**
 * @author zhanzihao
 * 2021-02-10
 */
public interface UserService {

    /**
     * 登录
     */
    User login(LoginRequest request);

    /**
     * 注册
     */
    void register(User user);

    /**
     * 更新用户
     */
    User updateUser(User user);
}
