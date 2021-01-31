package com.zhanzihao.mapper.admin.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhanzihao.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhanzihao
 * 2021-01-31
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
