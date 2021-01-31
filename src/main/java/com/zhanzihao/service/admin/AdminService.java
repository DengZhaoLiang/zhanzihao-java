package com.zhanzihao.service.admin;

import com.zhanzihao.dto.admin.AdminModifyRequest;
import com.zhanzihao.dto.admin.AdminRequest;
import com.zhanzihao.dto.admin.AdminResponse;

/**
 * @author zhanzihao
 * 2021-01-30
 */
public interface AdminService {

    /**
     * 获取管理员详情
     */
    AdminResponse profile();

    /**
     * 管理员登录
     */
    AdminResponse login(AdminRequest request);

    /**
     * 修改管理员信息
     */
    void modify(AdminModifyRequest request);
}
