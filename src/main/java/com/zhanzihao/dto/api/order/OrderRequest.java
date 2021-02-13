package com.zhanzihao.dto.api.order;

import lombok.Data;

import java.util.List;

/**
 * @author zhanzihao
 * 2021-02-13
 */
@Data
public class OrderRequest {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 地址Id
     */
    private Long addressId;

    /**
     * 购买的产品列表
     */
    private List<OrderProductResponse> products;
}
