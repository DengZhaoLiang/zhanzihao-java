package com.zhanzihao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhanzihao.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Liang
 * Created By 2021/2/3
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> listByConditions(@Param("orderSn") String orderSn,
                                 @Param("name") String name,
                                 @Param("status") Integer status);

    List<Order> listByUserId(@Param("userId") Long userId);
}
