/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : course

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:13:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_management
-- ----------------------------
DROP TABLE IF EXISTS `s_management`;
CREATE TABLE `s_management` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `course_day` bigint DEFAULT '0' COMMENT '开课时间',
  `start_date` bigint DEFAULT '0',
  `end_date` bigint DEFAULT '0',
  `period` int DEFAULT '0' COMMENT '期数ID',
  `type` int DEFAULT '0' COMMENT '0 体验， 1 系统',
  `cumulative_sale` bigint DEFAULT '0' COMMENT '0,累计出售',
  `robin_num` int DEFAULT '0' COMMENT '0,分班人数轮询',
  `period_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '期数',
  `end_course_day` bigint DEFAULT '0' COMMENT '结课时间',
  `status` int DEFAULT '0' COMMENT '1 招生中   2待开课   3 开课中  4 已结课',
  `subject` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_period_type` (`period`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=314 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程排期表';

-- ----------------------------
-- Table structure for s_management_sell
-- ----------------------------
DROP TABLE IF EXISTS `s_management_sell`;
CREATE TABLE `s_management_sell` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `fake_limit` int DEFAULT '0' COMMENT '展示限售额基数',
  `fake_sales` int DEFAULT '0' COMMENT '展示销量基数',
  `limit` int DEFAULT '0' COMMENT '实际限售额',
  `management_id` bigint DEFAULT '0' COMMENT '课程排期id',
  `period` int DEFAULT '0' COMMENT '期数',
  `sell_date` bigint DEFAULT '0' COMMENT '售卖日期',
  `status` int DEFAULT '0' COMMENT '排期状态 0待预约,1预约中,2出售中,3已结束',
  `type` int DEFAULT '0' COMMENT '排期课程类型 0体验课,1系统课,2特价课',
  `subject` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sellDate` (`sell_date`) USING BTREE,
  KEY `idx_period_type` (`period`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30675 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tg_send_course_log
-- ----------------------------
DROP TABLE IF EXISTS `tg_send_course_log`;
CREATE TABLE `tg_send_course_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `student_id` bigint NOT NULL DEFAULT '0' COMMENT '学生ID',
  `course_id` int NOT NULL DEFAULT '0' COMMENT '课程ID,就是课件的id',
  `term` int NOT NULL DEFAULT '0' COMMENT '期数',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '图片',
  `week` int DEFAULT '0' COMMENT '周数',
  `team_category` int DEFAULT '0' COMMENT '班级类别',
  `learned` tinyint DEFAULT '0' COMMENT '是否学完,1:学完,0:没学完',
  `lesson` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '课程',
  `lesson_type` int NOT NULL DEFAULT '0' COMMENT '课类型 0体验,1系统课,周一至周四,2系统课周五,大师互动课,3系统课周六,名师直播课,4系统课节日主题,5公开直播课,6如何上体验课,7如何上系统课,8换老师,9新如何上课,10体验会销课,11体验课TV直播课,12系统课TV直播课,13广告',
  `live_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '直播URL',
  `plan_id` bigint NOT NULL DEFAULT '0' COMMENT '学习计划ID,体验课对应tg_student_trial_course的id,系统课对应tg_student_system_course的id',
  `star` int DEFAULT '0' COMMENT '星星',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '标签',
  `teacher_id` bigint DEFAULT '0' COMMENT '老师ID',
  `team_id` bigint DEFAULT '0' COMMENT '班级ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '标题',
  `display_date` varchar(255) DEFAULT '' COMMENT '显示日期:支持方案1',
  `start_date` bigint DEFAULT '0' COMMENT '上课时间',
  `time_leath` int DEFAULT '0' COMMENT '上课时长',
  `compose` int DEFAULT '0' COMMENT '课程内容的组成部分',
  `subject` tinyint DEFAULT '0' COMMENT '科目：0美术 1写字 2学院 3音乐',
  `order_id` int NOT NULL DEFAULT '0',
  `status` tinyint DEFAULT '0',
  `sup` varchar(255) DEFAULT '' COMMENT '难度级别',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_cid` (`course_id`) USING BTREE,
  KEY `idx_sid` (`student_id`) USING BTREE,
  KEY `idx_teamId` (`team_id`),
  KEY `index_ctime` (`ctime`),
  KEY `idx_teamId_courseId` (`team_id`,`course_id`),
  KEY `idx_studentId_teacherId_courseId` (`student_id`,`teacher_id`,`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=149901 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tg_student_complete_course_log
-- ----------------------------
DROP TABLE IF EXISTS `tg_student_complete_course_log`;
CREATE TABLE `tg_student_complete_course_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课件的id',
  `lesson` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '课程',
  `star` int NOT NULL DEFAULT '0' COMMENT '汇总星星数量',
  `state` int NOT NULL DEFAULT '0' COMMENT '0未激活1已激活',
  `student_id` bigint NOT NULL DEFAULT '0' COMMENT '学生ID',
  `team_id` bigint NOT NULL DEFAULT '0' COMMENT '班级ID',
  `complete_time` bigint DEFAULT '0' COMMENT '完成时间',
  `complete_send_day` tinyint NOT NULL DEFAULT '0' COMMENT '是否在放课当天完成',
  `open_send_day` tinyint NOT NULL DEFAULT '0' COMMENT '是否在放课当天开启',
  `category` int NOT NULL DEFAULT '0' COMMENT '班级类别',
  `subject` tinyint DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `index_cs` (`course_id`,`student_id`),
  KEY `idx_cid` (`course_id`) USING BTREE,
  KEY `idx_sid` (`student_id`) USING BTREE,
  KEY `idx_state` (`state`) USING BTREE,
  KEY `index_ctime` (`ctime`),
  KEY `index_student_team_course` (`student_id`,`team_id`,`course_id`),
  KEY `index_complete_time` (`complete_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22459 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tg_student_learn_course_log
-- ----------------------------
DROP TABLE IF EXISTS `tg_student_learn_course_log`;
CREATE TABLE `tg_student_learn_course_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `chapter_id` bigint NOT NULL DEFAULT '0' COMMENT '章节ID',
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课程ID',
  `paragraph_index` bigint NOT NULL DEFAULT '0' COMMENT '段落标识',
  `state` int NOT NULL DEFAULT '0' COMMENT '状态 0是open,1是关闭',
  `student_id` bigint NOT NULL DEFAULT '0' COMMENT '学生ID',
  `type` int NOT NULL DEFAULT '0' COMMENT '类型 0课程,1章节,2段落,3发布作品,4点评,5听点评',
  `task_sound` varchar(255) NOT NULL DEFAULT '' COMMENT '作业语音',
  `category` int NOT NULL DEFAULT '0' COMMENT '班级类别',
  `subject` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sid_cid` (`student_id`,`course_id`),
  KEY `idx_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=122001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tg_student_system_course
-- ----------------------------
DROP TABLE IF EXISTS `tg_student_system_course`;
CREATE TABLE `tg_student_system_course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0' COMMENT '创建人',
  `ctime` bigint DEFAULT '0' COMMENT '创建时间',
  `del` bigint DEFAULT '0' COMMENT '软删除',
  `mid` bigint DEFAULT '0' COMMENT '修改人',
  `utime` bigint DEFAULT '0' COMMENT '修改时间',
  `course_state` int NOT NULL DEFAULT '0' COMMENT '学习计划状态',
  `current_lesson` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '当前课程单元级别lesson',
  `current_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '当前课程级别L',
  `current_super` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '当前课程难度级别S',
  `current_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '当前课程单元级别U',
  `order_no` bigint NOT NULL DEFAULT '0' COMMENT '订单号',
  `reduce_week` int DEFAULT '0' COMMENT '剪掉周数',
  `remaining_week` int NOT NULL DEFAULT '0' COMMENT '剩余周数',
  `student_id` bigint NOT NULL DEFAULT '0' COMMENT '学生ID',
  `sup` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '难度',
  `teacher_id` bigint DEFAULT '0' COMMENT '教师ID',
  `teacher_wechat_id` bigint DEFAULT '0' COMMENT '老师微信ID',
  `team_id` bigint DEFAULT '0' COMMENT '班级ID',
  `team_state` int NOT NULL DEFAULT '0' COMMENT '班级状态',
  `team_type` int NOT NULL DEFAULT '0' COMMENT '班级类型',
  `term` int NOT NULL DEFAULT '0' COMMENT '期数',
  `finished_time` bigint NOT NULL DEFAULT '0' COMMENT '完成课时',
  `added_group` tinyint NOT NULL DEFAULT '0' COMMENT '待进辅导群：0未加，1已加',
  `added_wechat` tinyint NOT NULL DEFAULT '0' COMMENT '待加微信号：0未加，1已加 ',
  `team_category` int NOT NULL DEFAULT '0' COMMENT '班级类别',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备注',
  `changed_super` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '调整课程难度级别S',
  `plan_extends` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '扩展信息',
  `next_week` int NOT NULL DEFAULT '0' COMMENT '下一阶段周数"',
  `subject` tinyint DEFAULT '0' COMMENT '科目',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sid` (`student_id`) USING BTREE,
  KEY `idx_teamId` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2240 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统课表';

-- ----------------------------
-- Table structure for tg_student_team
-- ----------------------------
DROP TABLE IF EXISTS `tg_student_team`;
CREATE TABLE `tg_student_team` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0' COMMENT '创建人',
  `ctime` bigint DEFAULT '0' COMMENT '创建时间',
  `del` bigint DEFAULT '0' COMMENT '软删除',
  `mid` bigint DEFAULT '0' COMMENT '修改人',
  `utime` bigint DEFAULT '0' COMMENT '修改时间',
  `added_wechat` int DEFAULT '0' COMMENT '已加微信数',
  `current_lesson` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '当前课程',
  `enroll_state` int NOT NULL DEFAULT '0' COMMENT '招生状态,0开放招生,1停止招生,2满班后停止',
  `enrolled` int DEFAULT '0' COMMENT '已招生',
  `pre_enroll` int DEFAULT '0' COMMENT '预招生',
  `progress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '进度',
  `sup` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '难度级别',
  `teacher_id` bigint DEFAULT '0' COMMENT '老师ID',
  `teacher_wechat_id` bigint DEFAULT '0' COMMENT '老师微信ID',
  `team_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '班级名称',
  `team_state` int NOT NULL DEFAULT '0' COMMENT '班级状态：0 待开课 1 上课中 2 已结课',
  `team_type` int NOT NULL DEFAULT '0' COMMENT '班级类型：0-体验课 2-系统课',
  `term` int NOT NULL DEFAULT '0' COMMENT '期数',
  `unadd_wechat` int DEFAULT '0' COMMENT '未加微信数',
  `added_group` int DEFAULT '0' COMMENT '已进辅道群人数',
  `unadd_group` int DEFAULT '0' COMMENT '未进辅道群人数',
  `category` int NOT NULL DEFAULT '0' COMMENT '类型下的类别：0，3 - 体验课\n1-月系统课\n2-年系统课（含半年）',
  `course_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '课件版本',
  `switch_state` int NOT NULL DEFAULT '0' COMMENT '自动加好友开关',
  `subject` tinyint DEFAULT '0' COMMENT '科目：0美术 1写字 2学院',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_teacherId` (`teacher_id`),
  KEY `idx_term` (`term`),
  KEY `idx_sup_teacherWechatId` (`sup`,`teacher_wechat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=672 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班级表';

-- ----------------------------
-- Table structure for tg_student_trial_course
-- ----------------------------
DROP TABLE IF EXISTS `tg_student_trial_course`;
CREATE TABLE `tg_student_trial_course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0' COMMENT '创建时间',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0' COMMENT '更新时间',
  `course_state` int NOT NULL DEFAULT '0' COMMENT '学习计划状态,0:WAIT_START,1:STARTING,2:END,',
  `current_lesson` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '当前课程',
  `order_no` bigint NOT NULL DEFAULT '0' COMMENT '订单号',
  `remaining_week` int NOT NULL DEFAULT '0' COMMENT '剩余周数',
  `student_id` bigint NOT NULL DEFAULT '0' COMMENT '学生ID',
  `sup` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '体验课等级',
  `teacher_id` bigint DEFAULT '0' COMMENT '老师ID',
  `teacher_wechat_id` bigint DEFAULT '0' COMMENT '老师微信ID',
  `team_id` bigint DEFAULT '0' COMMENT '班级ID',
  `term` int NOT NULL DEFAULT '0' COMMENT '所属排期',
  `added_group` tinyint NOT NULL DEFAULT '0' COMMENT '加群状态：0-未加群；1已加群',
  `added_wechat` tinyint NOT NULL DEFAULT '0' COMMENT '添加老师微信状态：0未添加；1已添加',
  `team_category` int NOT NULL DEFAULT '0' COMMENT '班级类别,0-双周；3-单周',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备注',
  `delay_days` int NOT NULL DEFAULT '0',
  `subject` tinyint DEFAULT '0' COMMENT '科目：0美术 1写字 2学院',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sid` (`student_id`) USING BTREE,
  KEY `order_no` (`order_no`) USING BTREE,
  KEY `idx_teamId` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=93446055 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='体验课表';

SET FOREIGN_KEY_CHECKS = 1;
