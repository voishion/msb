/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : handover

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:14:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_teacher_handover_record
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher_handover_record`;
CREATE TABLE `t_teacher_handover_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `handover_type` bigint DEFAULT '0' COMMENT '交接类型,0微信交接,1班级交接',
  `student_steam_id` bigint DEFAULT '0' COMMENT '班级id',
  `teacher_receive_id` bigint DEFAULT '0' COMMENT '接收老师微信id',
  `teacher_send_id` bigint DEFAULT '0' COMMENT '交出方老师ID',
  `weixin_receive_id` bigint DEFAULT '0' COMMENT '接收老师微信id',
  `weixin_send_id` bigint DEFAULT '0' COMMENT '交出老师微信id"',
  `if_new` bigint DEFAULT '0' COMMENT '是否最新0老数据1是2否',
  PRIMARY KEY (`id`),
  KEY `idx_teacherSendId` (`teacher_send_id`),
  KEY `idx_teacherReceiveId` (`teacher_receive_id`)
) ENGINE=InnoDB AUTO_INCREMENT=575 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
