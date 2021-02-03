package com.zhanzihao.service.admin.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhanzihao.assembler.OrderAssembler;
import com.zhanzihao.dto.admin.order.AdminOrderRequest;
import com.zhanzihao.dto.admin.order.AdminOrderResponse;
import com.zhanzihao.dto.admin.product.AdminProductResponse;
import com.zhanzihao.mapper.OrderMapper;
import com.zhanzihao.model.Order;
import com.zhanzihao.model.Product;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhanzihao
 * 2021-02-03
 */
@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    private OrderMapper mOrderMapper;

    @Autowired
    private OrderAssembler  mOrderAssembler;

    @Override
    public Page<AdminOrderResponse> pageOrders(AdminOrderRequest request, Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq(Strings.isNotBlank(request.getOrderSn()), "order_sn", request.getOrderSn());
        wrapper.like(Strings.isNotBlank(request.getName()), "name", request.getName());
        wrapper.eq(Objects.nonNull(request.getStatus()), "status", request.getStatus());
        List<AdminOrderResponse> orders = mOrderMapper.selectList(wrapper).stream()
                .map(order -> mOrderAssembler.toResponse(order))
                .collect(Collectors.toList());
        return new PageImpl<>(orders.stream()
                .skip((page.getPageNumber()) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList()), page, orders.size());
    }
}
