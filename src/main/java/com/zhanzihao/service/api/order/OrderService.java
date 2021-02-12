package com.zhanzihao.service.api.order;

import com.zhanzihao.dto.api.order.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zhanzihao
 * 2021-02-12
 */
public interface OrderService {

    /**
     * 分页获取订单
     */
    Page<OrderResponse> pageOrders(Long userId, Pageable pageable);

    /**
     * 创建订单
     */
    void createOrder();
}
