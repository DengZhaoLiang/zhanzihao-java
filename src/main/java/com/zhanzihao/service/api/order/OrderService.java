package com.zhanzihao.service.api.order;

import com.zhanzihao.model.Order;
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
    Page<Order> pageOrders(Long userId, Pageable pageable);
}
