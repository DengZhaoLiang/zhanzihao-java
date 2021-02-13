package com.zhanzihao.handler.payment;

import com.zhanzihao.constant.PaymentStatus;
import com.zhanzihao.mapper.OrderMapper;
import com.zhanzihao.model.Order;
import com.zhanzihao.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhanzihao
 * 2021-02-13
 */
@Slf4j
@Component
public class BuyHandler implements PaymentHandler {

    @Autowired
    private OrderMapper mOrderMapper;

    @Override
    public void onPaySuccess(Payment payment) {
        Order order = mOrderMapper.selectByOrderSn(payment.getOrderSn());

        // 修改订单付款状态
        mOrderMapper.updateStatus(payment.getOrderSn(), PaymentStatus.PAID.getCode());
    }
}
