/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : label

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:14:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jl_label_info
-- ----------------------------
DROP TABLE IF EXISTS `jl_label_info`;
CREATE TABLE `jl_label_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `name` varchar(255) DEFAULT '' COMMENT '标签组名称:{用户状态}/{}课{}期S{}{}班',
  `change_rule` int DEFAULT '0' COMMENT '标签更改机制',
  `desc` varchar(255) DEFAULT '' COMMENT '标签定义描述',
  `label_attr` int DEFAULT '0' COMMENT '标签属性:0-恒量 1-变量 2-艾客系统标签分组',
  `sort` bigint DEFAULT '0',
  `type` int DEFAULT '0' COMMENT '标签类型:0-系统标签 1-自定义标签 2-店铺分组(不可删除) 4-艾客系统标签分组(自定义)',
  `label_type` int DEFAULT '0',
  `status` int DEFAULT '0' COMMENT '状态:1-有效 0-无效',
  `tag_group_id` int DEFAULT '0' COMMENT '艾客标签分组id',
  `teacher_id` bigint DEFAULT '0' COMMENT '创建分组标签老师ID',
  `uteacher_id` bigint DEFAULT '0' COMMENT '修改标签老师',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for jl_label_record_info
-- ----------------------------
DROP TABLE IF EXISTS `jl_label_record_info`;
CREATE TABLE `jl_label_record_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `label_id` bigint DEFAULT '0' COMMENT '标签id(jl_label)',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '给用户打的标签名称',
  `uid` bigint DEFAULT '0',
  `teacher_id` bigint DEFAULT '0',
  `ulabel_type` int DEFAULT '0',
  `subject` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKf020u7j4kr5q26o6n8yqmp1hp` (`label_id`,`uid`),
  UNIQUE KEY `UK3l8budbv73utlb9swwtrqoqf7` (`label_id`,`uid`,`teacher_id`),
  UNIQUE KEY `idx_label_uid_tid` (`label_id`,`uid`,`teacher_id`),
  KEY `label_id` (`label_id`),
  KEY `uid` (`uid`),
  KEY `teacher_id` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5872 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
