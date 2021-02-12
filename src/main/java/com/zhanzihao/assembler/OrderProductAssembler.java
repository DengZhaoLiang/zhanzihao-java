package com.zhanzihao.assembler;

import com.zhanzihao.dto.api.order.ProductResponse;
import com.zhanzihao.model.OrderProduct;
import org.mapstruct.Mapper;

/**
 * @author zhanzihao
 * 2021-02-12
 */
@Mapper(componentModel = "spring")
public interface OrderProductAssembler {

    ProductResponse toProducts(OrderProduct orderProduct);
}
