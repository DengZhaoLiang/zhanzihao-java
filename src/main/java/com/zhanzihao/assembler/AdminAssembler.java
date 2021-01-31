package com.zhanzihao.assembler;

import com.zhanzihao.dto.admin.AdminResponse;
import com.zhanzihao.model.Admin;
import org.mapstruct.Mapper;

/**
 * @author zhanzihao
 * 2021-01-30
 */
@Mapper(componentModel = "spring")
public interface AdminAssembler {

    AdminResponse toResponse(Admin admin);
}
