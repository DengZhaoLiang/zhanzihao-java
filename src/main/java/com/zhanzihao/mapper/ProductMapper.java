package com.zhanzihao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhanzihao.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhanzihao
 * 2021-02-01
 */
@Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {
}
