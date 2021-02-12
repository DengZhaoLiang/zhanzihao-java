package com.zhanzihao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhanzihao.model.OrderProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhanzihao
 * 2021-02-12
 */
@Mapper
@Repository
public interface OrderProductMapper extends BaseMapper<OrderProduct> {
}
