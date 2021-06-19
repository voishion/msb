/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : express

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:14:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for o_express
-- ----------------------------
DROP TABLE IF EXISTS `o_express`;
CREATE TABLE `o_express` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0' COMMENT '创建人',
  `ctime` bigint DEFAULT '0' COMMENT '创建时间',
  `del` bigint DEFAULT '0' COMMENT '软删除',
  `mid` bigint DEFAULT '0' COMMENT '修改人',
  `utime` bigint DEFAULT '0' COMMENT '修改时间',
  `address_id` bigint DEFAULT '0' COMMENT '地址ID',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '市',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '区/县',
  `street` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `address_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '收货地址（详细）',
  `receipt_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '收货人姓名',
  `receipt_tel` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '收货人电话',
  `user_id` bigint DEFAULT '0' COMMENT '用户ID',
  `order_id` bigint DEFAULT '0' COMMENT '订单ID',
  `out_trade_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '订单号',
  `create_order_time` bigint DEFAULT '0' COMMENT '物流创建订单时间',
  `topic_id` bigint DEFAULT '0' COMMENT '商品ID',
  `packages_id` bigint DEFAULT '0',
  `regtype` bigint DEFAULT '0',
  `term` bigint DEFAULT '0' COMMENT '班级',
  `sup` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `product_id` bigint DEFAULT '0' COMMENT '商品ID',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '物流商品名',
  `product_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '物流商品版本',
  `product_type` int DEFAULT '0' COMMENT '0-盒子随材,1-关单赠品,2-小熊商城,-推荐有礼,4-邀请有奖',
  `replenish_type` int DEFAULT '0' COMMENT '0-整盒补发,1-单件补发',
  `replenish_reason` int DEFAULT '0' COMMENT '0-发货漏发,-运输损坏,2-其他',
  `source_type` int DEFAULT '0' COMMENT '0-非课程订单消息,1-课程订单消息,2-放课消息,3-续费消息,4-关单赠品,5-补货,6-导入',
  `delivery_type` int DEFAULT '0' COMMENT '0-线下,-物流中台\n',
  `supplier_id` bigint DEFAULT '0',
  `center_product_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `center_express_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `center_ctime` bigint DEFAULT '0',
  `center_utime` bigint DEFAULT '0',
  `delivery_time` bigint DEFAULT '0' COMMENT '发货时间',
  `delivery_collect_time` bigint DEFAULT '0',
  `signing_time` bigint DEFAULT '0' COMMENT '签收时间',
  `express_nu` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '快递单号',
  `express_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '物流公司',
  `express_company_nu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '物流公司单号',
  `express_status` int DEFAULT '0' COMMENT '0-已创建, 1-待发货, 2-已经发货, 3-已签收, 4-签收失败, 5-已退货, 6-待确认发货, 7-无效, 8-疑难, 9-暂停',
  `express_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `express_status_center` int DEFAULT '0',
  `express_status_kuaidi100` int DEFAULT '0',
  `operator_id` bigint DEFAULT '0',
  `teacher_id` bigint DEFAULT '0',
  `team_id` bigint DEFAULT '0',
  `last_teacher_id` bigint DEFAULT '0',
  `last_team_id` bigint DEFAULT '0',
  `o_ctime` bigint DEFAULT '0',
  `pay_teacher_id` bigint DEFAULT '0',
  `pay_channel` bigint DEFAULT '0',
  `subject` tinyint DEFAULT '0' COMMENT '科目：0美术 1写字 2学院',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ctime` (`ctime`) USING BTREE,
  KEY `utime` (`utime`) USING BTREE,
  KEY `signing_time` (`signing_time`) USING BTREE,
  KEY `delivery_time` (`delivery_time`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `order_id` (`order_id`),
  KEY `delivery_collect_time` (`delivery_collect_time`),
  KEY `team_id` (`team_id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `last_team_id` (`last_team_id`),
  KEY `last_teacher_id` (`last_teacher_id`),
  KEY `pay_teacher_id` (`pay_teacher_id`),
  KEY `express_nu` (`express_nu`) USING BTREE,
  KEY `receipt_tel` (`receipt_tel`) USING BTREE,
  KEY `center_express_id` (`center_express_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=554086802978377753 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='快递表';

SET FOREIGN_KEY_CHECKS = 1;
