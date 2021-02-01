package com.zhanzihao.service.admin.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhanzihao.assembler.UserAssembler;
import com.zhanzihao.dto.admin.user.AdminUserRequest;
import com.zhanzihao.dto.admin.user.AdminUserResponse;
import com.zhanzihao.mapper.UserMapper;
import com.zhanzihao.model.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhanzihao
 * 2021-01-31
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private UserMapper mUserMapper;

    @Autowired
    private UserAssembler mUserAssembler;

    @Override
    public Page<AdminUserResponse> pageUsers(AdminUserRequest request, Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(Strings.isNotBlank(request.getName()), "name", request.getName());
        wrapper.eq(Strings.isNotBlank(request.getEmail()), "email", request.getEmail());
        List<AdminUserResponse> users = mUserMapper.selectList(wrapper)
                .stream()
                .map(user -> mUserAssembler.toResponse(user))
                .collect(Collectors.toList());
        return new PageImpl<>(users.stream()
                .skip((page.getPageNumber()) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList()), page, users.size());
    }

    @Override
    public void deleteUser(Long id) {
        mUserMapper.deleteById(id);
    }

    @Override
    public void batchDeleteUsers(List<Long> ids) {
        mUserMapper.deleteBatchIds(ids);
    }
}
