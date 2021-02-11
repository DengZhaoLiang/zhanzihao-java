package com.zhanzihao.service.api.order;

import com.zhanzihao.mapper.OrderMapper;
import com.zhanzihao.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhanzihao
 * 2021-02-12
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper mOrderMapper;

    @Override
    public Page<Order> pageOrders(Long userId, Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        List<Order> orders = mOrderMapper.listByUserId(userId);
        return new PageImpl<>(orders.stream()
                .skip((page.getPageNumber()) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList()), page, orders.size());
    }
}
