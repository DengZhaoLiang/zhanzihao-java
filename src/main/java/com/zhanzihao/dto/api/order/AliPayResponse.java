package com.zhanzihao.dto.api.order;

import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhanzihao
 * 2021-02-13
 */
@Data
public class AliPayResponse {

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 付款总金额
     */
    private BigDecimal totalPrice;

    /**
     * 下单结果
     */
    private String response;
}
