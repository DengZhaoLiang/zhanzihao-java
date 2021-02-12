package com.zhanzihao.constant;

import com.zhanzihao.handler.payment.PaymentHandler;
import com.zhanzihao.model.Payment;
import com.zhanzihao.utils.SpringContextUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Liang
 * Created By 2020/12/22
 */
@RequiredArgsConstructor
public enum PaymentType implements EnumCode, PaymentHandler {

    /**
     * 购买商品
     */
    BUY(1, null),

    ;


    @Getter
    private final int code;

    /**
     * 处理器类
     */
    private final Class<? extends PaymentHandler> handlerClass;

    private PaymentHandler handler;

    @Override
    public void onPaySuccess(Payment payment) {
        getHandler().onPaySuccess(payment);
    }

    private PaymentHandler getHandler() {
        if (handler == null) {
            handler = SpringContextUtils.getBean(handlerClass);
        }
        return handler;
    }
}
