package com.zhanzihao.service.api.product;

import com.zhanzihao.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zhanzihao
 * 2021-02-10
 */
public interface ProductService {

    /**
     * 获取商品
     */
    Page<Product> pageProducts(Pageable pageable);

    /**
     * 获取商品详情
     */
    Product detailProduct(Long id);
}
