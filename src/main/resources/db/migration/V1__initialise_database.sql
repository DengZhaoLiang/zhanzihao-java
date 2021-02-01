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
    `created_at` BIGINT(13)     NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_at` BIGINT(13)     NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `product_price_index` (`price`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;