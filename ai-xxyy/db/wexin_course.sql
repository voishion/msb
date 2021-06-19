/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : wexin_course

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:15:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_weixin_course_difficulty
-- ----------------------------
DROP TABLE IF EXISTS `t_weixin_course_difficulty`;
CREATE TABLE `t_weixin_course_difficulty` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `course_difficulty` int DEFAULT '0' COMMENT '课程难度级别,s1,s2,s3....',
  `course_type` int DEFAULT '0' COMMENT '课程类型,t_weixin_course_relation里的首月系统课,体验课,年系统课，特价课',
  `status` int DEFAULT '0' COMMENT '0未开启,1开启',
  `weixin_id` bigint DEFAULT '0',
  `weixin_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `course` int DEFAULT '0',
  `team_size` int DEFAULT '60' COMMENT '班级总人数',
  `sum_team_size` int DEFAULT '60' COMMENT '教师带班总人数',
  `period` int DEFAULT '0' COMMENT '期数',
  `course_version` varchar(20) DEFAULT '' COMMENT '负责课件版本',
  `course_category` varchar(255) DEFAULT '0' COMMENT '课件分类,多个逗号分隔，比如1,3代表同时负责29和49的体验课',
  `subject` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_courseType_period_status` (`course_type`,`period`,`status`),
  KEY `idx_weixinId` (`weixin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1646 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_weixin_course_difficulty_conversion
-- ----------------------------
DROP TABLE IF EXISTS `t_weixin_course_difficulty_conversion`;
CREATE TABLE `t_weixin_course_difficulty_conversion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `course_category` varchar(255) DEFAULT '',
  `course_difficulty` int DEFAULT '0',
  `course_type` int DEFAULT '0',
  `course_version` varchar(20) DEFAULT '',
  `pace` int DEFAULT '0',
  `period` int DEFAULT '0',
  `status` int DEFAULT '0',
  `subject` tinyint DEFAULT '0' COMMENT '科目：0美术 1写字 2学院',
  `sum_team_size` int DEFAULT '60',
  `team_size` int DEFAULT '60',
  `weixin_id` bigint DEFAULT '0',
  `weixin_no` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_weixin_course_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_weixin_course_relation`;
CREATE TABLE `t_weixin_course_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `experience_lesson` int DEFAULT '0' COMMENT '体验课0未开始，1开启',
  `first_month_system_lesson` int DEFAULT '0' COMMENT '首月系统课0未开始，1开始',
  `weixin_id` bigint DEFAULT '0',
  `weixin_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `year_system_lesson` int DEFAULT '0' COMMENT '年系统课0未开始，1开启',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
