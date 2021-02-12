package com.zhanzihao.service.api.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhanzihao.assembler.OrderAssembler;
import com.zhanzihao.assembler.OrderProductAssembler;
import com.zhanzihao.constant.PaymentType;
import com.zhanzihao.dto.api.order.OrderResponse;
import com.zhanzihao.mapper.AddressMapper;
import com.zhanzihao.mapper.OrderMapper;
import com.zhanzihao.mapper.OrderProductMapper;
import com.zhanzihao.model.OrderProduct;
import com.zhanzihao.service.alipay.AliPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhanzihao
 * 2021-02-12
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper mOrderMapper;

    @Autowired
    private OrderAssembler mOrderAssembler;

    @Autowired
    private OrderProductMapper mOrderProductMapper;

    @Autowired
    private OrderProductAssembler mOrderProductAssembler;

    @Autowired
    private AddressMapper mAddressMapper;

    @Autowired
    private AliPayService mAliPayService;

    @Override
    public Page<OrderResponse> pageOrders(Long userId, Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        List<OrderResponse> orders = mOrderMapper.listByUserId(userId)
                .stream()
                .map(order -> {
                    OrderResponse response = mOrderAssembler.toApiResponse(order);
                    QueryWrapper<OrderProduct> wrapper = new QueryWrapper<>();
                    wrapper.eq("order_sn", response.getOrderSn());
                    response.setProducts(
                            mOrderProductMapper.selectList(wrapper)
                                    .stream()
                                    .map(mOrderProductAssembler::toProducts)
                                    .collect(Collectors.toList()));
                    response.setAddress(mAddressMapper.selectById(order.getAddressId()));
                    return response;
                }).collect(Collectors.toList());
        log.info(orders.toString());
        return new PageImpl<>(orders.stream()
                .skip((page.getPageNumber()) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList()), page, orders.size());
    }

    @Override
    public void createOrder() {
        mAliPayService.createOrder(1L, "9999", new BigDecimal(100), "test", PaymentType.BUY);
    }
}
