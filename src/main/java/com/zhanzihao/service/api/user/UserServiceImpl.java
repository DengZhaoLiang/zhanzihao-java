package com.zhanzihao.service.api.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhanzihao.dto.api.user.LoginRequest;
import com.zhanzihao.mapper.UserMapper;
import com.zhanzihao.model.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

/**
 * @author zhanzihao
 * 2021-02-10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mUserMapper;

    @Value("${server.base.url}")
    private String url;

    @Override
    public User login(LoginRequest request) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", request.getEmail());
        wrapper.eq("password", request.getPassword());
        User user = Optional.ofNullable(mUserMapper.selectOne(wrapper))
                .orElseThrow(() -> new RuntimeException("账号密码错误，请重新输入"));
        user.setLastLoginAt(Instant.now().getEpochSecond());
        mUserMapper.updateById(user);
        return user;
    }

    @Override
    public void register(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", user.getEmail());
        Optional.ofNullable(mUserMapper.selectOne(wrapper))
                .ifPresent(it -> {
                    throw new RuntimeException("邮箱已被注册");
                });
        user.setName(user.getEmail());
        user.setAvatar(url + "/static/defaultAvatar.png");
        mUserMapper.insert(user);
    }

    @Override
    public User updateUser(User user) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set(Strings.isNotBlank(user.getAvatar()), "avatar", user.getAvatar());
        wrapper.set(Strings.isNotBlank(user.getName()), "name", user.getName());
        wrapper.eq("id", user.getId());
        mUserMapper.update(user, wrapper);
        return mUserMapper.selectById(user.getId());
    }
}
