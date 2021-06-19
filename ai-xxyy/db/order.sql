/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : order

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:14:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for o_order
-- ----------------------------
DROP TABLE IF EXISTS `o_order`;
CREATE TABLE `o_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0' COMMENT '创建人',
  `ctime` bigint DEFAULT '0' COMMENT '创建时间',
  `del` bigint DEFAULT '0' COMMENT '软删除',
  `mid` bigint DEFAULT '0' COMMENT '修改人',
  `utime` bigint DEFAULT '0' COMMENT '修改时间',
  `address_id` bigint DEFAULT '0' COMMENT '收货地址ID',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '应付金额',
  `bear_integral` decimal(10,2) DEFAULT '0.00' COMMENT '小象积分兑换',
  `buytime` bigint DEFAULT '0' COMMENT '支付时间',
  `discount_amount` decimal(10,2) DEFAULT '0.00' COMMENT '优惠金额',
  `endtime` bigint DEFAULT '0' COMMENT '订单结束时间',
  `gem_integral` decimal(10,2) DEFAULT '0.00' COMMENT '宝石积分兑换',
  `groupon_id` bigint DEFAULT '0' COMMENT '拼团ID',
  `invite_prize_id` bigint DEFAULT '0' COMMENT '邀请领奖记录ID--赠送时使用此字段',
  `packages_course_day` int DEFAULT '0' COMMENT '总课时天',
  `packages_course_week` int DEFAULT '0' COMMENT '总课时周',
  `packages_id` bigint DEFAULT '0' COMMENT '套餐ID',
  `packages_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '套餐名称',
  `packages_type` int DEFAULT '0' COMMENT '课程类型：1.体验 2.月系统课 3.季系统课 4.半年系统课 5.年系统课 6.赠送礼品\n0-体验课\n1-月系统课\n2-季系统课\n3-半年系统课\n4-年系统课',
  `pay_channel` bigint DEFAULT '0' COMMENT '支付渠道ID',
  `pay_channel_user` bigint DEFAULT '0' COMMENT '支付渠道推荐人',
  `pay_page_origin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '支付页面类型',
  `regtype` int DEFAULT '0' COMMENT '订单类型：0默认,1体验,2首单系统课,3续费系统课,4宝石兑换,5积分兑换,6赠送,7素质课-星球课程,8兑换,9营销活动',
  `stage` bigint DEFAULT '0' COMMENT '期数',
  `status` int DEFAULT '0' COMMENT '订单状态：0.未激活 1.已激活 2.已支付 3.已完成 4.申请退款 5.退款中 6.部分退款 7.退款完成 8.订单关闭',
  `sup` int DEFAULT '0' COMMENT 'S难度系数',
  `teacher_id` bigint DEFAULT '0' COMMENT '首次分配的老师ID',
  `topic_id` bigint DEFAULT '0' COMMENT '主题ID',
  `total_amount` decimal(10,2) DEFAULT '0.00' COMMENT '订单总金额',
  `type` int DEFAULT '0' COMMENT '订单支付类型：0默认，1单独购买，2拼团',
  `uid` bigint DEFAULT '0' COMMENT '用户ID',
  `out_trade_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '外部商户号--订单号',
  `apple_product_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT 'apple商店商品ID',
  `coupon_user_id` bigint DEFAULT '0' COMMENT '用户领取优惠券对应ID',
  `isrefund` int DEFAULT '0' COMMENT '退款状态：0默认，1退款已完成，2申请退款，3退款中',
  `team_id` bigint DEFAULT '0' COMMENT '班级ID',
  `last_team_id` bigint DEFAULT '0' COMMENT '最近班级ID',
  `last_teacher_id` bigint DEFAULT '0' COMMENT '最近分配的老师ID',
  `pay_teacher_id` bigint DEFAULT '0' COMMENT '成单老师ID',
  `import_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '订单导入备注',
  `import_time` bigint DEFAULT '0' COMMENT '订单导入时间',
  `sale_department_id` bigint DEFAULT '0',
  `sale_department_pid` bigint DEFAULT '0',
  `trial_stage` bigint DEFAULT '0',
  `trial_team_id` bigint DEFAULT '0',
  `import_1v1_oid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '订单导入-1V1订单ID',
  `remaining_amount` decimal(10,2) DEFAULT '0.00' COMMENT '剩余金额',
  `first_order_send_id` bigint DEFAULT '0' COMMENT '首单推荐者ID',
  `trial_pay_channel` bigint DEFAULT '0' COMMENT '购买体验课支付渠道ID',
  `spread_code` varchar(20) DEFAULT '' COMMENT '分销推广唯一标识',
  `course_category` int DEFAULT '0' COMMENT '课程种类',
  `exchange_code` varchar(20) DEFAULT '' COMMENT '兑换码',
  `invoice_code` varchar(255) DEFAULT '',
  `invoice_status` int DEFAULT '0',
  `invoice_type` int DEFAULT '0',
  `subject` tinyint DEFAULT '0' COMMENT '科目：0美术 1写字 2学院',
  `come` varchar(255) DEFAULT '' COMMENT '来源关键词',
  `pay_teacher_duty_id` bigint DEFAULT '0',
  `transfer` tinyint DEFAULT NULL COMMENT '转移：0默认未转移、1订单转出',
  `poster_id` bigint DEFAULT NULL COMMENT '渠道上报海报id',
  `pay_seq_id` bigint DEFAULT NULL COMMENT '中台支付订单id',
  `refund_seq_id` bigint DEFAULT NULL COMMENT '中台退款订单id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE,
  KEY `packages_id` (`packages_id`) USING BTREE,
  KEY `stage_status` (`stage`,`status`) USING BTREE,
  KEY `ctime` (`ctime`) USING BTREE,
  KEY `utime` (`utime`) USING BTREE,
  KEY `buytime` (`buytime`) USING BTREE,
  KEY `pay_teacher_id` (`pay_teacher_id`),
  KEY `trial_stage` (`trial_stage`),
  KEY `trial_team_id` (`trial_team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=554086212709781572 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

-- ----------------------------
-- Table structure for o_user_order_success
-- ----------------------------
DROP TABLE IF EXISTS `o_user_order_success`;
CREATE TABLE `o_user_order_success` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `sales_teacher_id` bigint DEFAULT '0' COMMENT '销售老师',
  `sales_teacher_name` varchar(255) DEFAULT '' COMMENT '销售老师名称',
  `system_first_amount` decimal(10,2) DEFAULT '0.00' COMMENT '系统课首单支付金额',
  `system_first_buytime` bigint DEFAULT '0' COMMENT '系统课首单购买时间',
  `system_first_ctime` bigint DEFAULT '0' COMMENT '系统课首单创建时间',
  `system_first_id` bigint DEFAULT '0' COMMENT '系统课首单ID',
  `system_first_packages_id` bigint DEFAULT '0' COMMENT '系统课首单购买套餐ID',
  `system_first_packages_name` varchar(255) DEFAULT '' COMMENT '系统课首单购买套餐名称',
  `system_first_stage` bigint DEFAULT '0' COMMENT '系统课首单订单期数',
  `system_first_sup` int DEFAULT '0' COMMENT '系统课首单S难度系数',
  `system_renew_amounts` decimal(10,2) DEFAULT '0.00' COMMENT '系统课续费订单总金额 - 多个（金额累加）',
  `system_renew_counts` int DEFAULT '0' COMMENT '系统课续费订单总条数',
  `system_renew_ids` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '系统课续费订单ID - 多个（英文逗号隔开）',
  `trial_amount` decimal(10,2) DEFAULT '0.00' COMMENT '体验课订单支付金额',
  `trial_buytime` bigint DEFAULT '0' COMMENT '体验课订单购买时间',
  `trial_channel` bigint DEFAULT '0' COMMENT '体验课订单支付渠道',
  `trial_ctime` bigint DEFAULT '0' COMMENT '体验课订单创建时间',
  `trial_first_teacher_id` bigint DEFAULT '0' COMMENT '体验课首次分配的老师ID',
  `trial_first_teacher_name` varchar(255) DEFAULT '' COMMENT '体验课首次分配老师名称',
  `trial_id` bigint DEFAULT '0' COMMENT '体验课订单ID',
  `trial_packages_id` bigint DEFAULT '0' COMMENT '体验课订单购买套餐ID',
  `trial_packages_name` varchar(255) DEFAULT '' COMMENT '体验课订单购买套餐名称',
  `trial_send_id` bigint DEFAULT '0' COMMENT '体验课订单推荐人ID',
  `trial_stage` bigint DEFAULT '0' COMMENT '体验课订单期数',
  `trial_sup` int DEFAULT '0' COMMENT '体验课S难度系数',
  `trial_team_id` bigint DEFAULT '0' COMMENT '体验课班级ID',
  `trial_team_name` varchar(255) DEFAULT '' COMMENT '体验课班级名称',
  `uid` bigint DEFAULT '0' COMMENT '用户ID',
  `trial_status` int DEFAULT '0' COMMENT '体验课订单状态',
  `trial_channel_class_id` bigint DEFAULT '0' COMMENT '体验课渠道分类ID',
  `subject` int DEFAULT '0' COMMENT 'APP科目',
  `come` varchar(255) DEFAULT '' COMMENT '来源关键词',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_unique_uid` (`uid`,`subject`) USING BTREE,
  KEY `uid` (`uid`),
  KEY `trial_channel` (`trial_channel`),
  KEY `trial_stage` (`trial_stage`),
  KEY `trial_ctime` (`trial_ctime`),
  KEY `trial_buytime` (`trial_buytime`),
  KEY `trial_team_id` (`trial_team_id`),
  KEY `ctime` (`ctime`),
  KEY `utime` (`utime`),
  KEY `trial_channel_uid` (`trial_channel`,`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=39869 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
