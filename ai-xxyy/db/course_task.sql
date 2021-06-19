/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : course_task

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:14:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tg_flag_record
-- ----------------------------
DROP TABLE IF EXISTS `tg_flag_record`;
CREATE TABLE `tg_flag_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `flag_id` bigint NOT NULL DEFAULT '0' COMMENT '标记ID即是tg_student_course_task_comment的id',
  `student_id` bigint NOT NULL DEFAULT '0' COMMENT 'student_id',
  `type` int NOT NULL DEFAULT '0' COMMENT '0学生作品点赞记录,1教师点评作品语音播放,2是否开启新逻辑分配老师 flagId = 1开启,3教师点评作品语音播放,4 type1系统课0体验课,最后一次选择的课程计划 flagId=planId+type,5水军,6自己查看周报记录,7自己分享周报记录,8自己查看点评聚合页记录',
  `subject` tinyint DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sid_type` (`student_id`,`type`) USING BTREE,
  KEY `index_ctime` (`ctime`),
  KEY `index_sid_type_utime` (`student_id`,`type`,`del`,`utime`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7629 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tg_student_comment_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tg_student_comment_feedback`;
CREATE TABLE `tg_student_comment_feedback` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `student_id` bigint NOT NULL DEFAULT '0' COMMENT '学生id',
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课件id',
  `task_id` bigint NOT NULL DEFAULT '0' COMMENT '作品id',
  `feedback_type` int DEFAULT '0' COMMENT '反馈类型（0：不满意，1：一般，2：满意）',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '反馈标签',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '反馈内容',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_studentId_taskId` (`student_id`,`task_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tg_student_course_task
-- ----------------------------
DROP TABLE IF EXISTS `tg_student_course_task`;
CREATE TABLE `tg_student_course_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `course_id` bigint NOT NULL DEFAULT '0',
  `lesson_no` int DEFAULT '0' COMMENT '课程编号',
  `like_count` int DEFAULT '0' COMMENT '喜欢数量',
  `share` tinyint DEFAULT '0' COMMENT '是否晒学习报告,1:未晒',
  `student_id` bigint NOT NULL DEFAULT '0',
  `task_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '作业图片',
  `task_sound` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '作品语音',
  `task_sound_second` int DEFAULT '0' COMMENT '作品语音秒数',
  `teacher_id` bigint NOT NULL DEFAULT '0',
  `team_id` bigint NOT NULL DEFAULT '0',
  `wall` tinyint DEFAULT '0' COMMENT '是否提交作品墙,1:提交',
  `task_video` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '作品视频',
  `task_report_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '海报图片地址',
  `task_image_height` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `task_image_width` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `task_video_second` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `course_type` int DEFAULT '0' COMMENT '课程类型, 1:体验课  2:系统课  3:主题课 4:特价体验课',
  `is_comment` int DEFAULT '0' COMMENT '是否点评过作品；0 未点评，1 已点评',
  `listen_comment_self` bigint DEFAULT '0' COMMENT '自己听点评次数',
  `task_image_box` varchar(255) DEFAULT '' COMMENT '相框',
  `task_month` int DEFAULT '0' COMMENT '上传作品时用户的月龄',
  `lat` decimal(14,10) NOT NULL DEFAULT '0.0000000000',
  `lng` decimal(14,10) NOT NULL DEFAULT '0.0000000000',
  `task_city_id` int DEFAULT '0' COMMENT '上传作品时用户的city id',
  `task_province_id` int DEFAULT '0' COMMENT '上传作品时用户的省份id',
  `subject` tinyint DEFAULT '0',
  `category` int NOT NULL DEFAULT '0' COMMENT '班级类别',
  `is_transmit` int DEFAULT '0' COMMENT '是否转交作品；0 未转交，1 已转交',
  `batch_task_id` varchar(255) DEFAULT NULL COMMENT '批次任务taskId',
  `batch_course_id` varchar(255) DEFAULT NULL COMMENT '批次CourseId',
  `is_feedback` int DEFAULT '0' COMMENT '学生是否已反馈0未反馈1已反馈',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_cid` (`course_id`) USING BTREE,
  KEY `idx_sid` (`student_id`) USING BTREE,
  KEY `idx_teamId` (`team_id`),
  KEY `idx_teacherId_wall` (`teacher_id`,`wall`),
  KEY `index_ctime` (`ctime`),
  KEY `idx_teacherId_isComment_wall` (`teacher_id`,`is_comment`,`wall`),
  KEY `idx_isComment_wall` (`is_comment`,`wall`),
  KEY `idx_studentId_courseId` (`student_id`,`course_id`),
  KEY `idx_courseId_wall_studentId` (`course_id`,`wall`,`student_id`),
  KEY `idx_task_province_id` (`task_province_id`,`id`) USING BTREE,
  KEY `idx_student_id` (`student_id`,`id`) USING BTREE,
  KEY `idx_task_month` (`task_month`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12670284 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tg_student_course_task_comment
-- ----------------------------
DROP TABLE IF EXISTS `tg_student_course_task_comment`;
CREATE TABLE `tg_student_course_task_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `task_id` bigint NOT NULL DEFAULT '0',
  `sound_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '作业语音点评',
  `sound_comment_second` int DEFAULT '0' COMMENT '作业语音点评秒数',
  `student_id` bigint NOT NULL DEFAULT '0',
  `teacher_id` bigint NOT NULL DEFAULT '0',
  `team_id` bigint NOT NULL DEFAULT '0',
  `type` int DEFAULT '0' COMMENT '点评类型；0 人工点评 1 智能点评 2 真人点评 3 语音库',
  `listen_comment_self` bigint DEFAULT '0',
  `subject` tinyint DEFAULT '0',
  `comment_teacher_type` tinyint DEFAULT NULL COMMENT '点评老师类型；0 非兼职老师，1 兼职老师',
  `batch_task_id` varchar(255) DEFAULT NULL COMMENT '批次任务taskId',
  `is_feedback` int DEFAULT '0' COMMENT '学生是否已反馈,0未反馈，1已反馈',
  `batch_course_id` varchar(255) DEFAULT NULL COMMENT '批次任务courseId',
  `video_url` varchar(255) DEFAULT '' COMMENT '视频地址',
  `content_json` varchar(255) DEFAULT '' COMMENT 'json地址',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_teamId` (`team_id`),
  KEY `idx_taskId` (`task_id`),
  KEY `idx_studentId` (`student_id`),
  KEY `idx_teacherId` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=81662799 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tg_student_course_task_comment_extends
-- ----------------------------
DROP TABLE IF EXISTS `tg_student_course_task_comment_extends`;
CREATE TABLE `tg_student_course_task_comment_extends` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `other_comment_json` text COMMENT '其他点评信息的json',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81662799 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tg_student_course_task_extends
-- ----------------------------
DROP TABLE IF EXISTS `tg_student_course_task_extends`;
CREATE TABLE `tg_student_course_task_extends` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `student_course_task_id` bigint NOT NULL DEFAULT '0',
  `branch_id` varchar(255) DEFAULT '',
  `field_1` varchar(255) DEFAULT '',
  `field_2` varchar(255) DEFAULT '',
  `field_3` varchar(255) DEFAULT '',
  `field_4` bigint NOT NULL DEFAULT '0',
  `field_5` bigint NOT NULL DEFAULT '0',
  `field_6` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_taskid` (`student_course_task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tg_student_task_dispatch_log
-- ----------------------------
DROP TABLE IF EXISTS `tg_student_task_dispatch_log`;
CREATE TABLE `tg_student_task_dispatch_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课程id',
  `lesson_no` int DEFAULT '0' COMMENT '第几节课',
  `is_comment` int DEFAULT '0' COMMENT '是否点评过作品；0 未点评，1 已点评',
  `student_id` bigint NOT NULL DEFAULT '0' COMMENT '学生id',
  `subject` int DEFAULT NULL,
  `task_comment_id` bigint NOT NULL DEFAULT '0' COMMENT '作品点评id',
  `task_comment_time` bigint NOT NULL DEFAULT '0' COMMENT '作品点评时间',
  `task_id` bigint NOT NULL DEFAULT '0' COMMENT '作品id',
  `task_upload_time` bigint NOT NULL DEFAULT '0' COMMENT '作品上传时间',
  `teacher_id` bigint DEFAULT '0' COMMENT '教师id',
  `team_id` bigint DEFAULT '0' COMMENT '班级id',
  `term` int DEFAULT '0' COMMENT '期数',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `course_type` int DEFAULT '0',
  `batch_task_id` varchar(255) DEFAULT NULL,
  `sup` varchar(255) DEFAULT '' COMMENT '难度级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作品分配记录表';

SET FOREIGN_KEY_CHECKS = 1;
