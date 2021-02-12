package com.zhanzihao.assembler;

import com.zhanzihao.dto.admin.order.AdminOrderResponse;
import com.zhanzihao.dto.api.order.OrderResponse;
import com.zhanzihao.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author zhanzihao
 * 2021-02-03
 */
@Mapper(componentModel = "spring")
public interface OrderAssembler {

    AdminOrderResponse toResponse(Order order);

    @Mapping(target = "products", ignore = true)
    OrderResponse toApiResponse(Order order);
}
