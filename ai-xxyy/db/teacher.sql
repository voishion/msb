/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : teacher

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:14:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0' COMMENT '创建人',
  `ctime` bigint DEFAULT '0' COMMENT '创建时间',
  `del` bigint DEFAULT '0' COMMENT '软删除',
  `mid` bigint DEFAULT '0' COMMENT '修改人',
  `utime` bigint DEFAULT '0' COMMENT '修改时间',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '昵称',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '手机号',
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '真实姓名',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '登录账号',
  `weixin_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '管理微信号',
  `is_login` int DEFAULT '0' COMMENT '是否允许登录',
  `join_date` datetime DEFAULT NULL COMMENT '入职时间',
  `leave_date` datetime DEFAULT NULL COMMENT '离职日期',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '密码',
  `status` int DEFAULT '0' COMMENT '任职状态：0 在职, 1 离职',
  `level` int DEFAULT '0' COMMENT '教师权重等级',
  `duty_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '职务ID：1-体验课 2-系统课',
  `rank_id` bigint DEFAULT '0' COMMENT '职级ID',
  `head_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '教师头像URL',
  `leave_train` datetime DEFAULT NULL COMMENT '下组时间（培训结束时间）',
  `sex` int DEFAULT '0' COMMENT '性别：0 男，1 女',
  `department_id` bigint DEFAULT '0' COMMENT '所属部门ID',
  `ding_userid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '对应钉钉ID',
  `data_auth` varchar(1024) DEFAULT '' COMMENT '数据权限',
  `work_place` varchar(255) DEFAULT '' COMMENT '职场',
  `work_place_code` int DEFAULT '0' COMMENT '职场对应城市code',
  `job_number` varchar(255) DEFAULT '' COMMENT '钉钉工号',
  `subject` varchar(255) DEFAULT NULL COMMENT '所属科目',
  `vip_teacher_id` bigint DEFAULT '0' COMMENT '1v1教师id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_departmentId` (`department_id`),
  KEY `idx_rankId` (`rank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1450442949990420640 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教师表';

-- ----------------------------
-- Table structure for t_teacher_channel_directional_config
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher_channel_directional_config`;
CREATE TABLE `t_teacher_channel_directional_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `channel_id` bigint DEFAULT '0' COMMENT '渠道id',
  `course_category` int DEFAULT '0' COMMENT '课件分类',
  `period` int DEFAULT '0' COMMENT '期号',
  `teacher_id` bigint DEFAULT '0' COMMENT '教师id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
