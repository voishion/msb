/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : account

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:13:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for a_account
-- ----------------------------
DROP TABLE IF EXISTS `a_account`;
CREATE TABLE `a_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cid` bigint DEFAULT '0' COMMENT '创建人',
  `ctime` bigint DEFAULT '0' COMMENT '创建时间',
  `del` bigint DEFAULT '0' COMMENT '软删除',
  `mid` bigint DEFAULT '0' COMMENT '修改人',
  `utime` bigint DEFAULT '0' COMMENT '修改时间',
  `account_type` int DEFAULT '0' COMMENT '账户类型 0:DEFAULT,1:现金,2:小熊币,3:宝石',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '金额',
  `balance` decimal(10,2) DEFAULT '0.00' COMMENT '余额',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '交易描述',
  `expected_amount` decimal(10,0) DEFAULT '0' COMMENT '预计收入',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '图片/描述链接',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  `source` bigint DEFAULT '0' COMMENT '交易来源id',
  `target` bigint DEFAULT '0' COMMENT '交易目标id',
  `trans_type` int DEFAULT '0' COMMENT '交易类型 默认0,邀请有奖或推荐有礼或首单分享截图转介绍任务1,完成任务2,邀请有奖红包3,提现4,小熊币兑换 宝石兑换5,学习奖励6,初始化7,运营活动8,投诉补偿9,小熊币抽奖获得10,小熊币抽奖扣除11,小熊币兑吧添加12,小熊币兑吧扣除13,运营扣除14',
  `uid` bigint DEFAULT '0' COMMENT '用户id',
  `update_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新时间 yyyy-MM-dd hh24:mm:ss',
  `subject` tinyint DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_uid_account_type` (`uid`,`account_type`) USING BTREE,
  KEY `a_account_subject` (`subject`)
) ENGINE=InnoDB AUTO_INCREMENT=4996152652195286431 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for a_task
-- ----------------------------
DROP TABLE IF EXISTS `a_task`;
CREATE TABLE `a_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '奖励小熊币数-系统课',
  `buy_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '课程购买路径',
  `check_buy` int DEFAULT '0' COMMENT '校验购课',
  `courses` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '所属课程(0体验,1系统)',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '任务描述',
  `is_repeatable` int DEFAULT '0' COMMENT '是否可重复',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '任务名称',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  `star_value` int DEFAULT '0' COMMENT '星星和小熊币关系',
  `status` int DEFAULT '0' COMMENT '任务状态 未激活状态0,已激活状态1,任务过期2',
  `tag` int DEFAULT '0' COMMENT '任务标签 默认0,签到1,学习中奖励2,首次完成体验课3,首次完成系统课4,首次绑定微信公众号5,每天首次发布作品6,首次完善个人信息7,\n每天完成当日课程8,每天首次分享课后报告9,完成首周体验课10,完成第二周体验课11,体验课升级购买系统课12,每天首次分享学习报告13,完成会销课14,月初分享海报15,月中分享海报16',
  `task_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '任务详情路径',
  `type` int DEFAULT '0' COMMENT '任务类型 默认0,学习内容1,课程任务2,绑定公众账号3,完善信息4,签到打卡5,运营活动6',
  `need_fetch` int DEFAULT '0' COMMENT '需要领取',
  `experience_amount` decimal(10,0) DEFAULT '0' COMMENT '奖励小熊币数-体验课',
  `expired_time` bigint DEFAULT '0' COMMENT '过期时间',
  `repeat_num` tinyint DEFAULT '0' COMMENT '重复次数',
  `subject` int DEFAULT '0' COMMENT '科目类型：0美术,1写字,2学院,3音乐',
  `sub_title` varchar(255) DEFAULT '' COMMENT '副标题',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=540270057159266314 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for a_task_log
-- ----------------------------
DROP TABLE IF EXISTS `a_task_log`;
CREATE TABLE `a_task_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '奖励金额',
  `buy_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '购买路径',
  `is_sign` int DEFAULT '0' COMMENT '是否为签到任务',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  `signed_count` int DEFAULT '0' COMMENT '连续签到计数',
  `source` bigint DEFAULT '0' COMMENT '来源id',
  `status` int DEFAULT '0' COMMENT '任务状态1任务未领取,2任务已领取进行中,3任务完成,奖励未领取,4任务完成,奖励已领取,5已失效,完成任务,奖励超过3天未领取或对应任务过期,6超时未完成已失效,不需要展示的状态,7进行中:适用于产品自定义的进行中状态跳转,8审核中,9审核未通过',
  `task_id` bigint DEFAULT '0' COMMENT '任务id',
  `task_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '任务详情路径',
  `type` int DEFAULT '0' COMMENT '任务类型 默认0,学习内容1,课程任务2,绑定公众账号3,完善信息4,签到打卡5,运营活动6',
  `uid` bigint DEFAULT '0',
  `update_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新日期',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
  `user_status` tinyint DEFAULT '0' COMMENT '用户状态',
  `subject` int DEFAULT '0' COMMENT '科目类型：0美术,1写字,2学院,3音乐',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_task_id_uid` (`task_id`,`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=540270066881662978 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for a_transaction
-- ----------------------------
DROP TABLE IF EXISTS `a_transaction`;
CREATE TABLE `a_transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `account_type` int DEFAULT '0' COMMENT '类型 0默认,1现金账户,2小熊币账户,3宝石账户',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '金额',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述信息',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '图片/描述链接',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  `order_id` bigint DEFAULT '0',
  `status` int DEFAULT '0' COMMENT '交易状态 0默认,1提交审核,2审核通过,3提现完成,4失败',
  `trans_return` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '交易回执',
  `trans_type` int DEFAULT '0' COMMENT '交易类型 用户发起的交易行为,0默认,1提现,2兑换,3宝石兑换现金',
  `transfer_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '转账openId',
  `uid` bigint DEFAULT '0',
  `subject` int DEFAULT '0' COMMENT '科目类型：0美术,1写字,2学院,3音乐',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_uid_account_type` (`uid`,`account_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=400781885577498952 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
