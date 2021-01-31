package com.zhanzihao.controller.admin.user;

import com.zhanzihao.dto.IDSRequest;
import com.zhanzihao.dto.admin.user.AdminUserRequest;
import com.zhanzihao.dto.admin.user.AdminUserResponse;
import com.zhanzihao.service.admin.user.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanzihao
 * 2021-01-31
 */
@Validated
@RestController
@RequestMapping("api/admin/user")
public class AdminUserController {

    @Autowired
    private AdminUserService mAdminUserService;

    @GetMapping("")
    public Page<AdminUserResponse> pageUsers(AdminUserRequest request, Pageable pageable) {
        return mAdminUserService.pageUsers(request, pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        mAdminUserService.deleteUser(id);
    }

    @DeleteMapping("")
    public void batchDeleteUsers(@RequestBody @Validated IDSRequest request) {
        mAdminUserService.batchDeleteUsers(request.getIds());
    }
}
