package com.zhanzihao.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Liang
 * Created By 2021/2/3
 */
@Data
@TableName(value = "order")
public class Order {

    /**
     * 订单表ID
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名
     */
    private String name;

    /**
     * 购买金额
     */
    private BigDecimal fee;

    /**
     * 支付成功时间
     */
    private Long payAt;

    /**
     * 付款状态 :1-待支付,2-已支付
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 更新时间
     */
    private Long updatedAt;
}