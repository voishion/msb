/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : courseware

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:14:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for k_courseware
-- ----------------------------
DROP TABLE IF EXISTS `k_courseware`;
CREATE TABLE `k_courseware` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `content_state` int DEFAULT '0' COMMENT '是否分段 0不分段 1 分段"',
  `content_url` varchar(1024) DEFAULT '',
  `courseware_no` varchar(255) DEFAULT '',
  `cover_image` varchar(255) DEFAULT '',
  `desc` varchar(255) DEFAULT '',
  `encyclopedias` varchar(500) DEFAULT '',
  `holiday` varchar(255) DEFAULT '' COMMENT '节日名称',
  `introduce_image` varchar(255) DEFAULT '',
  `level_no` int DEFAULT '0' COMMENT '级别编号',
  `live_url` varchar(1000) DEFAULT '',
  `no` varchar(255) DEFAULT '' COMMENT '课程编号所有上级分类拼在一起',
  `professional` varchar(255) DEFAULT '',
  `stage_no` int DEFAULT '0' COMMENT '阶段编号',
  `start_date` bigint DEFAULT '0',
  `status` int DEFAULT '0',
  `teacher_head` varchar(255) DEFAULT '',
  `teacher_name` varchar(255) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  `type_no` int DEFAULT '0' COMMENT '体系编号',
  `unit_no` int DEFAULT '0' COMMENT '单元编号',
  `masterplate_url` varchar(255) DEFAULT '' COMMENT '模版地址',
  `team_ids` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `old_id` bigint DEFAULT NULL,
  `courseware_json` text,
  `courseware_version` varchar(255) DEFAULT '',
  `paragraph_type_id` int DEFAULT '0',
  `room_id` varchar(50) DEFAULT '',
  `time_leath` int DEFAULT '0',
  `course_lock` int DEFAULT '0',
  `teacher_id` int DEFAULT '0',
  `background_image` varchar(1024) DEFAULT '',
  `background_music` varchar(1024) DEFAULT '',
  `adaptation` int DEFAULT '0',
  `course_type` int DEFAULT '0',
  `compose` int DEFAULT '0',
  `compose_img` varchar(1024) DEFAULT '',
  `subject` int DEFAULT '0',
  `video_code` varchar(1024) DEFAULT '',
  `evaluable` int DEFAULT '0' COMMENT '是否可评论 0禁止 1开启',
  `course_order` int DEFAULT NULL,
  `music_theory` varchar(1024) DEFAULT '',
  `music_score` varchar(1024) DEFAULT '',
  `dance_img` varchar(1024) DEFAULT '',
  `drum_mp3` varchar(255) DEFAULT '',
  `melody_mp3` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `no` (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=1232874 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for k_courseware_system
-- ----------------------------
DROP TABLE IF EXISTS `k_courseware_system`;
CREATE TABLE `k_courseware_system` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `desc` varchar(255) DEFAULT '',
  `status` int DEFAULT '0' COMMENT '状态1启用0不启用',
  `subtitle` varchar(255) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  `type_no` int DEFAULT '0' COMMENT '体系编号 0体验课 1系统课',
  `subject` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
