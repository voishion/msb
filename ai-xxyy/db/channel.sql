/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : channel

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:13:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_channel
-- ----------------------------
DROP TABLE IF EXISTS `c_channel`;
CREATE TABLE `c_channel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `channel_inner_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '渠道种类',
  `channel_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '渠道链接',
  `channel_outer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '渠道对管理员名称',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `status` int DEFAULT '0' COMMENT '渠道状态 0未激活状态 已激活转改',
  `channel_type` int DEFAULT '0' COMMENT '渠道种类 0注册 1订单',
  `channel_sort` int DEFAULT '0' COMMENT '排序',
  `channel_class_id` bigint DEFAULT '0' COMMENT '渠道分类ID',
  `channel_link_short` varchar(255) DEFAULT '' COMMENT '渠道短链接',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `short_er_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '短链接二维码地址',
  `channel_level` int DEFAULT '0' COMMENT '渠道等级 B等级,目前最低等级,A等级,S等级,目前最高等级',
  `subject` int DEFAULT '0' COMMENT '科目3音乐',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2462 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for c_channel_adjuz
-- ----------------------------
DROP TABLE IF EXISTS `c_channel_adjuz`;
CREATE TABLE `c_channel_adjuz` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `appid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '回调地址',
  `callback_response` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '回调响应',
  `callback_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '回调URL',
  `idfa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '设备idfa',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '设备ip',
  `udid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '设备udid',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for c_channel_class
-- ----------------------------
DROP TABLE IF EXISTS `c_channel_class`;
CREATE TABLE `c_channel_class` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `channel_class_name` varchar(255) DEFAULT '' COMMENT '渠道分类名',
  `channel_class_parent_id` bigint DEFAULT '0' COMMENT '渠道分类上级ID',
  `channel_level` int DEFAULT '0' COMMENT '渠道等级 B等级,目前最低等级,A等级,S等级,目前最高等级',
  `subject` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for c_invite
-- ----------------------------
DROP TABLE IF EXISTS `c_invite`;
CREATE TABLE `c_invite` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `account_type` int DEFAULT '0' COMMENT '账户类型0:DEFAULT,1:现金,2:小熊币,3:宝石',
  `base_amount` decimal(10,2) DEFAULT '0.00' COMMENT '基础收益',
  `extra_amount` decimal(10,2) DEFAULT '0.00' COMMENT '额外收益',
  `order_time` bigint DEFAULT '0' COMMENT '订单时间',
  `relation_id` bigint DEFAULT '0' COMMENT '分享产品ID',
  `source_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '邀请者用户名',
  `source_uid` bigint DEFAULT '0' COMMENT '用户ID',
  `target_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '受邀请者用户名',
  `target_uid` bigint DEFAULT '0' COMMENT '受邀用户ID',
  `subject` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=556 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for c_invite_prize_log
-- ----------------------------
DROP TABLE IF EXISTS `c_invite_prize_log`;
CREATE TABLE `c_invite_prize_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `invite_count` int DEFAULT '0' COMMENT '邀请人数',
  `prize_time` bigint DEFAULT '0' COMMENT '领奖时间',
  `uid` bigint DEFAULT '0' COMMENT '领奖人uid',
  `subject` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
