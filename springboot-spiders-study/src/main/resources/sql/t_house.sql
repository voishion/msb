CREATE TABLE `t_house`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `source_name`        varchar(64)          DEFAULT '' COMMENT '来源',
    `item_id`            varchar(64) NOT NULL DEFAULT '' COMMENT 'item ID',
    `city`               varchar(32)          DEFAULT '' COMMENT '城市',
    `location`           varchar(32)          DEFAULT '' COMMENT '地区',
    `item_url`           varchar(128)         DEFAULT '' COMMENT '详情url',
    `address`            varchar(128)         DEFAULT '' COMMENT '详细地址',
    `area_size`          float                DEFAULT '0' COMMENT '建筑面积',
    `price`              float                DEFAULT '0' COMMENT '单价',
    `market_total_price` float                DEFAULT '0' COMMENT '市场总价',
    `sell_total_price`   float                DEFAULT '0' COMMENT '成交总价',
    `sell_status`        varchar(32)          DEFAULT '' COMMENT '成交状态',
    `sell_date`          timestamp   NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '成交时间',
    `update_time`        timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time`        timestamp   NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '入库时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_item_id` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_article`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `article_id`   varchar(64)  DEFAULT '' COMMENT '文章编号',
    `source_name`  varchar(50)  DEFAULT '' COMMENT '名字',
    `title`        varchar(50)  DEFAULT '' COMMENT '标题',
    `description`  varchar(255) DEFAULT '' COMMENT '描述',
    `author`       varchar(20)  DEFAULT '' COMMENT '作者',
    `url`          varchar(255) DEFAULT '' COMMENT '地址',
    `img_url`      varchar(255) DEFAULT '' COMMENT '图片地址',
    `category`     varchar(50)  DEFAULT '' COMMENT '类别',
    `publish_time` varchar(20)  DEFAULT '' COMMENT '发布时间',
    `status`       int(1) DEFAULT '0' COMMENT '状态',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




















