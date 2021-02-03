package com.zhanzihao.service.alipay;

import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;
import com.zhanzihao.constant.PaymentType;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author Liang
 * Created By 2020/12/23
 */
public interface AliPayService {

    /**
     * 支付宝下单
     */
    AlipayTradeAppPayResponse createOrder(Long userId, String orderNo, BigDecimal fee,
                                          String goodsName, PaymentType paymentType);

    /**
     * 支付宝订单回调
     */
    void aliPayNotify(HttpServletRequest request);
}
