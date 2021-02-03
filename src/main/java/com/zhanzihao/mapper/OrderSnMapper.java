package com.zhanzihao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhanzihao.model.OrderSn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhanzihao
 * 2021-02-03
 */
@Mapper
@Repository
public interface OrderSnMapper extends BaseMapper<OrderSn> {

}
