package com.zhanzihao.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhanzihao
 * 2021-02-01
 */
@Data
public class Product {


    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 主图
     */
    private String image;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存量
     */
    private Integer inventory;

    /**
     * 状态
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
