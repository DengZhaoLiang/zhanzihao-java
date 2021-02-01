package com.zhanzihao.assembler;

import com.zhanzihao.dto.admin.product.AdminProductResponse;
import com.zhanzihao.model.Product;
import org.mapstruct.Mapper;

/**
 * @author zhanzihao
 * 2021-02-01
 */
@Mapper(componentModel = "spring")
public interface ProductAssembler {

    AdminProductResponse toResponse(Product product);
}
