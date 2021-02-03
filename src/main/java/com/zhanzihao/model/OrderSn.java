package com.zhanzihao.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhanzihao
 * 2021-02-01
 */
@Data
public class OrderSn {


    /**
     * 订单表ID
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 更新时间
     */
    private Long updatedAt;
}
