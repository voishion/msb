/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : package

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:14:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for p_packages
-- ----------------------------
DROP TABLE IF EXISTS `p_packages`;
CREATE TABLE `p_packages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `cost_price` decimal(10,2) DEFAULT '0.00',
  `course_day` decimal(10,2) DEFAULT '0.00',
  `course_week` decimal(10,2) DEFAULT '0.00',
  `delivery_type` decimal(10,0) DEFAULT '0',
  `group_type` int DEFAULT '0',
  `groupon` int DEFAULT '0',
  `groupon_number` int DEFAULT '0',
  `groupon_price` decimal(10,2) DEFAULT '0.00',
  `groupon_total` decimal(10,2) DEFAULT '0.00',
  `label` varchar(255) DEFAULT '',
  `name` varchar(255) DEFAULT '',
  `original` decimal(10,0) DEFAULT '0',
  `payment` int DEFAULT '0',
  `price` decimal(10,2) DEFAULT '0.00',
  `sales` int DEFAULT '0',
  `status` int DEFAULT '0',
  `sub_title` varchar(255) DEFAULT '',
  `text` varchar(255) DEFAULT '',
  `type` int DEFAULT '0',
  `apple_product_id` varchar(255) DEFAULT '',
  `center_product_code` varchar(255) DEFAULT '',
  `subject` tinyint DEFAULT '0' COMMENT '科目：0美术 1写字 2学院',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=601 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for p_packages_product
-- ----------------------------
DROP TABLE IF EXISTS `p_packages_product`;
CREATE TABLE `p_packages_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `abolish_time` bigint DEFAULT '0',
  `is_gifts` decimal(10,0) DEFAULT '0',
  `packages_id` bigint DEFAULT '0',
  `packages_name` varchar(255) DEFAULT '',
  `product_id` bigint DEFAULT '0',
  `product_name` varchar(255) DEFAULT '',
  `status` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3007 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for p_packages_topic
-- ----------------------------
DROP TABLE IF EXISTS `p_packages_topic`;
CREATE TABLE `p_packages_topic` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `abolish_time` bigint DEFAULT '0',
  `relation_id` bigint DEFAULT '0',
  `relation_name` varchar(255) DEFAULT '',
  `status` int DEFAULT '0',
  `topic_id` bigint DEFAULT '0',
  `topic_name` varchar(255) DEFAULT '',
  `type` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=408 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for p_topic
-- ----------------------------
DROP TABLE IF EXISTS `p_topic`;
CREATE TABLE `p_topic` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `desc` varchar(255) DEFAULT '',
  `name` varchar(255) DEFAULT '',
  `sales` int DEFAULT '0',
  `status` int DEFAULT '0',
  `text` varchar(255) DEFAULT '',
  `type` int DEFAULT '0',
  `logistics_mode` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
