package com.zhanzihao.assembler;

import com.zhanzihao.dto.admin.order.AdminOrderResponse;
import com.zhanzihao.model.Order;
import org.mapstruct.Mapper;

/**
 * @author zhanzihao
 * 2021-02-03
 */
@Mapper(componentModel = "spring")
public interface OrderAssembler {

    AdminOrderResponse toResponse(Order order);
}
