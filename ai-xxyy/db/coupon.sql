/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : coupon

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:13:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_coupon_user
-- ----------------------------
DROP TABLE IF EXISTS `s_coupon_user`;
CREATE TABLE `s_coupon_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0' COMMENT '创建人',
  `ctime` bigint DEFAULT '0' COMMENT '创建时间',
  `del` bigint DEFAULT '0' COMMENT '软删除',
  `mid` bigint DEFAULT '0' COMMENT '修改人',
  `utime` bigint DEFAULT '0' COMMENT '修改时间',
  `coupon_id` bigint DEFAULT '0' COMMENT '优惠券ID',
  `end_date` bigint DEFAULT '0' COMMENT '结束时间',
  `notity_date` bigint DEFAULT '0' COMMENT '提醒时间',
  `oid` bigint DEFAULT '0' COMMENT '订单ID',
  `start_date` bigint DEFAULT '0' COMMENT '生效时间',
  `status` int DEFAULT '0' COMMENT '0disable不可用 ；1noactive未使用；2active已使用；3close已过期',
  `uid` bigint DEFAULT '0' COMMENT '用户ID',
  `using_domain` tinyint DEFAULT '0' COMMENT '使用领域,默认0小熊,1素质课堂',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_couponId` (`coupon_id`) USING BTREE,
  KEY `idx_uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4681045681573765938 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户优惠券表';

SET FOREIGN_KEY_CHECKS = 1;
