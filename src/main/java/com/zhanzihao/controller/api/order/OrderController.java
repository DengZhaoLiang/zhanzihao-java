package com.zhanzihao.controller.api.order;

import com.zhanzihao.model.Order;
import com.zhanzihao.service.api.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanzihao
 * 2021-02-12
 */
@Validated
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService mOrderService;

    @GetMapping("/{userId}")
    public Page<Order> pageOrders(Pageable pageable, @PathVariable Long userId) {
        return mOrderService.pageOrders(userId, pageable);
    }
}
