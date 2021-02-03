package com.zhanzihao.service.admin.order;

import com.zhanzihao.dto.admin.order.AdminOrderRequest;
import com.zhanzihao.dto.admin.order.AdminOrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zhanzihao
 * 2021-02-03
 */
public interface AdminOrderService {

    /**
     * 分页获取订单列表
     */
    Page<AdminOrderResponse> pageOrders(AdminOrderRequest request, Pageable pageable);
}
