-- 管理员
CREATE TABLE `admin`
(
    `id`         BIGINT(13)   NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255) NOT NULL DEFAULT '' COMMENT '姓名',
    `account`    VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '账号',
    `avatar`     VARCHAR(512) NOT NULL DEFAULT '' COMMENT '头像',
    `password`   VARCHAR(64)  NOT NULL COMMENT '密码',
    `created_at` BIGINT(13)   NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at` BIGINT(13)   NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `admin_account_index` (`account`),
    KEY `admin_name_index` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE utf8mb4_unicode_ci COMMENT '管理员';

-- 用户
CREATE TABLE `user`
(
    `id`            BIGINT(13)  NOT NULL AUTO_INCREMENT,
    `email`         VARCHAR(32) NOT NULL DEFAULT '' COMMENT '邮箱',
    `password`      VARCHAR(64) NOT NULL DEFAULT '' COMMENT '密码',
    `name`          VARCHAR(64) NOT NULL DEFAULT '' COMMENT '昵称',
    `avatar`        TEXT COMMENT '头像',
    `last_login_at` BIGINT(13) COMMENT '最新登录时间',
    `created_at`    BIGINT(13)  NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at`    BIGINT(13)  NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `user_email_index` (`email`),
    KEY `user_name_index` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- 商品
CREATE TABLE `product`
(
    `id`         BIGINT(13)     NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255)   NOT NULL DEFAULT '' COMMENT '商品名',
    `image`      VARCHAR(255)   NOT NULL DEFAULT '' COMMENT '主图',
    `status`     TINYINT(1)     NOT NULL DEFAULT 0 COMMENT '状态 0-下架 1-上架',
    `price`      DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '价格',
    `inventory`  BIGINT(10)     NOT NULL DEFAULT 0 COMMENT '库存量',
    `detail`     LONGTEXT COMMENT '图文详情',
    `created_at` BIGINT(13)     NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at` BIGINT(13)     NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `product_price_index` (`price`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- 支付订单表
CREATE TABLE `payment`
(
    `id`         BIGINT(20) UNSIGNED     NOT NULL AUTO_INCREMENT,
    `order_sn`   CHAR(32)                NOT NULL DEFAULT '' COMMENT '订单号',
    `user_id`    BIGINT(20) UNSIGNED     NOT NULL COMMENT '用户ID',
    `fee`        DECIMAL(12, 2) UNSIGNED NOT NULL COMMENT '订单金额',
    `remain_fee` DECIMAL(12, 2) UNSIGNED NOT NULL COMMENT '剩余可退款金额',
    `status`     INT(1) UNSIGNED         NOT NULL DEFAULT 1 COMMENT '付款状态:1-待支付,2-已支付,3-已关闭,4-已退款',
    `pay_mode`   INT(1) UNSIGNED                  DEFAULT 0 COMMENT '支付类型 1 微信 2 支付宝',
    `type`       INT(1) UNSIGNED         NOT NULL COMMENT '订单类型',
    `pay_at`     BIGINT(13) UNSIGNED COMMENT '支付成功时间',
    `created_at` BIGINT(13) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at` BIGINT(13) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `payment_order_sn_unique` (`order_sn`),
    KEY `payment_user_id_index` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '支付订单表';

-- 订单号表
CREATE TABLE `order_sn`
(
    `id`         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `no`         CHAR(32)            NOT NULL COMMENT '订单号',
    `created_at` BIGINT(13) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at` BIGINT(13) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `order_sn_no_unique` (`no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '订单号表';

-- 订单表
CREATE TABLE `order`
(
    `id`          BIGINT(20) UNSIGNED     NOT NULL AUTO_INCREMENT,
    `order_sn`    CHAR(32)                NOT NULL DEFAULT '' COMMENT '订单号',
    `user_id`     BIGINT(20) UNSIGNED     NOT NULL COMMENT '用户ID',
    `total_price` DECIMAL(12, 2) UNSIGNED NOT NULL COMMENT '购买总金额',
    `pay_at`      BIGINT(13) UNSIGNED COMMENT '支付成功时间',
    `status`      INT(1) UNSIGNED         NOT NULL DEFAULT 1 COMMENT '付款状态 :1-待支付,2-已支付',
    `address_id`  BIGINT(20) UNSIGNED     NOT NULL COMMENT '地址ID',
    `created_at`  BIGINT(13) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at`  BIGINT(13) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `order_order_sn_unique` (`order_sn`),
    KEY `order_user_id_index` (`user_id`),
    KEY `order_address_id_index` (`address_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '订单表';

-- 订单商品表
CREATE TABLE `order_product`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `order_sn`      CHAR(32)            NOT NULL DEFAULT '' COMMENT '订单号',
    `product_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '商品ID',
    `product_name`  VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '商品名',
    `product_image` VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '商品主图',
    `product_price` DECIMAL(10, 2)      NOT NULL DEFAULT 0 COMMENT '单价',
    `quantity`      INT(9)              NOT NULL DEFAULT 0 COMMENT '购买数量',
    `created_at`    BIGINT(13) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at`    BIGINT(13) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `order_product_order_sn_unique` (`order_sn`),
    KEY `order_product_product_id_index` (`product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '订单商品表';

-- 地址表
CREATE TABLE `address`
(
    `id`         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '收件人',
    `phone`      VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '电话号码',
    `detail`     VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '详细地址',
    `user_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    `created_at` BIGINT(13) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at` BIGINT(13) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `address_user_id_index` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '地址表';