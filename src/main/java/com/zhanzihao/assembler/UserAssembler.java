package com.zhanzihao.assembler;

import com.zhanzihao.dto.admin.user.AdminUserResponse;
import com.zhanzihao.model.User;
import org.mapstruct.Mapper;

/**
 * @author zhanzihao
 * 2021-01-31
 */
@Mapper(componentModel = "spring")
public interface UserAssembler {

    AdminUserResponse toResponse(User user);
}
