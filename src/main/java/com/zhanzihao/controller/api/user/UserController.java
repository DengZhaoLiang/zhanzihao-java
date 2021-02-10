package com.zhanzihao.controller.api.user;

import com.zhanzihao.dto.api.user.LoginRequest;
import com.zhanzihao.model.User;
import com.zhanzihao.service.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanzihao
 * 2021-02-10
 */
@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService mUserService;

    @PostMapping("login")
    public User login(@RequestBody @Validated LoginRequest request) {
        return mUserService.login(request);
    }

    @PostMapping("register")
    public void register(@RequestBody @Validated User user) {
        mUserService.register(user);
    }
}
