package com.zhanzihao.mapper.admin.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhanzihao.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhanzihao
 * 2021-01-30
 */
@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
}
