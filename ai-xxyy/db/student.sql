/*
 Navicat Premium Data Transfer

 Source Server         : localhost_docker_xxyy
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : docker-mysql:3306
 Source Schema         : student

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/04/2021 10:14:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for u_anonym_logger
-- ----------------------------
DROP TABLE IF EXISTS `u_anonym_logger`;
CREATE TABLE `u_anonym_logger` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `appname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `appversion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `buildcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `carrier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `channel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `device_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '机型',
  `headers` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `market` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '来源市场',
  `mcc_mnc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `ostype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `ratio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `real_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `system_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '系统版本',
  `token` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `user_agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `idfa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `role` varchar(255) DEFAULT '',
  `uid` bigint DEFAULT '0',
  `device_type` varchar(255) DEFAULT '',
  `subject` tinyint DEFAULT '0' COMMENT '科目：0美术 1写字 2学院',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `anonymlogger_uid_index` (`uid`),
  KEY `anonymlogger_deviceId_index` (`device_id`),
  KEY `anonymlogger_idfa_index` (`idfa`),
  KEY `index_ctime` (`ctime`)
) ENGINE=InnoDB AUTO_INCREMENT=154152 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='C端用户行为数据———每次打开app时上报';

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0' COMMENT '创建ID',
  `ctime` bigint DEFAULT '0' COMMENT '创建时间',
  `del` bigint DEFAULT '0' COMMENT '软删除',
  `mid` bigint DEFAULT '0' COMMENT '修改ID',
  `utime` bigint DEFAULT '0' COMMENT '修改时间',
  `birthday` bigint DEFAULT '0' COMMENT '用户生日',
  `channel` bigint DEFAULT '0' COMMENT '用户渠道来源ID',
  `country` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '国家代码',
  `head` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '用户头像',
  `join_date` bigint DEFAULT '0' COMMENT '用户注册时间',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '手机号',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '用户昵称',
  `page_origin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '页面来源',
  `page_origin_id` bigint DEFAULT '0' COMMENT '页面来源ID',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '用户密码',
  `send_id` bigint DEFAULT '0' COMMENT '推荐者ID',
  `sex` int DEFAULT '0' COMMENT '用户性别',
  `status` int DEFAULT '0' COMMENT '用户跟进状态 0已注册,1已体验课,2体验课完课,3已月系统课,4月系统课完课,5已年系统课,6年系统课完课,7年系统课续费,8注销失效,9已季系统课,10季系统课完课,11已半年系统课,12半年系统课完课,13半年系统课续费',
  `subscribe` int DEFAULT '0' COMMENT '用户预约状态 0未预约体验课,1已预约体验课',
  `type` int DEFAULT '0' COMMENT '用户账户类型 0普通用户,1测试用户,2奶牛用户,3推广大使',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '用户姓名',
  `weixin_openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '微信openID',
  `weixin_unionid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '微信ID',
  `user_num` bigint DEFAULT '0' COMMENT '用户编号',
  `base_painting` int DEFAULT '0' COMMENT '用户绘画基础',
  `sensors_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '神测数据标示',
  `teacher_id` bigint DEFAULT '0' COMMENT '老师ID',
  `team_id` bigint DEFAULT '0' COMMENT '班级ID',
  `last_team_id` bigint DEFAULT '0' COMMENT '最新班级ID',
  `last_teacher_id` bigint DEFAULT '0' COMMENT '最新老师ID',
  `wechat_nikename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '微信昵称',
  `wechat_follow_time` bigint DEFAULT '0',
  `added_wechat` bigint DEFAULT '0' COMMENT '添加微信',
  `added_group` bigint DEFAULT '0',
  `import_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '用户导入-备注',
  `import_time` bigint DEFAULT '0' COMMENT '用户导入时间',
  `mobile_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '手机号归属城市\n\n',
  `mobile_province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '手机号归属省份\n\n',
  `first_order_send_id` bigint DEFAULT '0' COMMENT '首单推荐者ID',
  `export_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '用户导出-备注',
  `user1v1_id` bigint DEFAULT '0' COMMENT '1V1用户ID',
  `birthday_activity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '用户生日--落地页',
  `wall_background` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '作品背景墙',
  `spread_code` varchar(20) DEFAULT '' COMMENT '分销推广唯一标识',
  `city_id` bigint DEFAULT '0' COMMENT '城市ID',
  `province_id` bigint DEFAULT '0' COMMENT '省份ID',
  `grade` tinyint DEFAULT NULL,
  `come` varchar(255) DEFAULT '' COMMENT '来源关键词',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `UKphe4gxqq6m6069piwvs5hb95n` (`mobile`),
  KEY `ctime` (`ctime`) USING BTREE,
  KEY `utime` (`utime`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4615222298057154571 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Table structure for u_user_behavior_log
-- ----------------------------
DROP TABLE IF EXISTS `u_user_behavior_log`;
CREATE TABLE `u_user_behavior_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `action_time` bigint DEFAULT '0',
  `action_type` int DEFAULT '0' COMMENT '行为类型,0缺省行为,1打开APP,2完成订单[购课],3参课,4完课,5听点评,6上传作品,7分配班级',
  `base_painting` int DEFAULT '0' COMMENT '用户绘画基础',
  `birthday` bigint DEFAULT '0',
  `channel` bigint DEFAULT '0' COMMENT 'channel_id',
  `comment_id` bigint DEFAULT '0' COMMENT '点评id',
  `course_id` bigint DEFAULT '0' COMMENT '课程ID',
  `course_title` varchar(255) DEFAULT '' COMMENT '课程标题',
  `current_lesson` varchar(255) DEFAULT '' COMMENT '当前课程',
  `device_model` varchar(255) DEFAULT '' COMMENT '设备型号',
  `first_order_send_id` bigint DEFAULT '0' COMMENT '首单推荐者ID',
  `head` varchar(255) DEFAULT '' COMMENT '用户头像',
  `join_date` bigint DEFAULT '0' COMMENT '用户注册时间',
  `mobile` varchar(255) DEFAULT '',
  `mobile_city` varchar(255) DEFAULT '',
  `mobile_province` varchar(255) DEFAULT '',
  `nickname` varchar(255) DEFAULT '',
  `order_id` bigint DEFAULT '0',
  `order_regtype` int DEFAULT '0' COMMENT '订单类型 0默认-无类型,1体验,2首单,3续费,4宝石兑换,5积分兑换,6赠送,7素质课-星球课程,8兑换,9营销活动',
  `order_status` int DEFAULT '0' COMMENT '订单状态 0 未激活状态,1已激活,2已支付,3已完成,4申请退款,5退款中,6部分退款,7退款完成,8关闭',
  `out_trade_no` varchar(255) DEFAULT '' COMMENT '外部商户号 -- 订单号',
  `sex` int DEFAULT '0',
  `stage` bigint DEFAULT '0' COMMENT '期号',
  `status` int DEFAULT '0' COMMENT '用户跟进状态 0已注册,1已体验课,2体验课完课,3已月系统课,4月系统课完课,5已年系统课,6年系统课完课,7年系统课续费,8注销失效,9已季系统课,10季系统课完课,11已半年系统课,12半年系统课完课,13半年系统课续费',
  `task_id` bigint DEFAULT '0',
  `teacher_department_id` bigint DEFAULT '0',
  `teacher_department_name` varchar(255) DEFAULT '',
  `teacher_id` bigint DEFAULT '0',
  `teacher_name` varchar(255) DEFAULT '',
  `team_id` bigint DEFAULT '0',
  `team_name` varchar(255) DEFAULT '',
  `team_state` int DEFAULT '0' COMMENT '班级状态 0未上课,1上课中,2已结课',
  `uid` bigint DEFAULT '0',
  `username` varchar(255) DEFAULT '',
  `weixin_nick_name` varchar(255) DEFAULT '',
  `dt` varchar(255) DEFAULT '' COMMENT '更新时间 yyyy-MM-dd',
  `course_state` int NOT NULL DEFAULT '0',
  `subject` tinyint DEFAULT '0' COMMENT '科目：0美术 1写字 2学院 3音乐',
  PRIMARY KEY (`id`),
  KEY `idx_ctime` (`ctime`),
  KEY `idx_uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=46840 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户行为轨迹表';

-- ----------------------------
-- Table structure for u_user_extends
-- ----------------------------
DROP TABLE IF EXISTS `u_user_extends`;
CREATE TABLE `u_user_extends` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `added_wechat` bigint DEFAULT '0' COMMENT '添加微信',
  `base_painting` int DEFAULT '0',
  `birthday` bigint DEFAULT '0',
  `birthday_activity` varchar(255) DEFAULT '',
  `channel` bigint DEFAULT '0',
  `city_id` bigint DEFAULT '0',
  `country` varchar(20) DEFAULT '',
  `export_remark` varchar(255) DEFAULT '',
  `first_order_send_id` bigint DEFAULT '0',
  `head` varchar(255) DEFAULT '',
  `import_remark` varchar(255) DEFAULT '',
  `import_time` bigint DEFAULT '0',
  `join_date` bigint DEFAULT '0',
  `last_teacher_id` bigint DEFAULT '0',
  `last_team_id` bigint DEFAULT '0',
  `mobile` varchar(255) DEFAULT '',
  `nickname` varchar(255) DEFAULT '',
  `page_origin` varchar(255) DEFAULT '',
  `page_origin_id` bigint DEFAULT '0',
  `password` varchar(255) DEFAULT '',
  `province_id` bigint DEFAULT '0',
  `send_id` bigint DEFAULT '0',
  `sensors_id` varchar(255) DEFAULT '',
  `sex` int DEFAULT '0',
  `spread_code` varchar(20) DEFAULT '' COMMENT '分销推广唯一标识',
  `status` int DEFAULT '0',
  `subject` tinyint DEFAULT '0' COMMENT '科目：0美术 1写字 2学院',
  `subscribe` int DEFAULT '0',
  `team_id` bigint DEFAULT '0',
  `u_id` bigint DEFAULT '0',
  `user_num` bigint DEFAULT '0',
  `username` varchar(255) DEFAULT '',
  `wall_background` varchar(255) DEFAULT '',
  `wechat_nikename` varchar(255) DEFAULT '',
  `weixin_openid` varchar(255) DEFAULT '',
  `weixin_unionid` varchar(255) DEFAULT '',
  `grade` tinyint DEFAULT '0' COMMENT '年级',
  `wechat_no` varchar(255) DEFAULT '' COMMENT '微信号',
  `wechat_id` varchar(255) DEFAULT '' COMMENT '微信id',
  `wechat_avatar` varchar(255) DEFAULT '' COMMENT '微信头像',
  `wechat_nick_name` varchar(255) DEFAULT '' COMMENT '微信昵称',
  PRIMARY KEY (`id`),
  KEY `u_user_extends_u_id` (`u_id`),
  KEY `uid_subject_idx` (`u_id`,`subject`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=519916189015872504 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Table structure for u_user_logindata
-- ----------------------------
DROP TABLE IF EXISTS `u_user_logindata`;
CREATE TABLE `u_user_logindata` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `login_time` bigint DEFAULT '0',
  `uid` bigint DEFAULT '0',
  `login_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '登陆来源',
  `new_token` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `old_token` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `appname` varchar(255) DEFAULT '',
  `appversion` varchar(255) DEFAULT '',
  `buildcode` varchar(255) DEFAULT '',
  `carrier` varchar(255) DEFAULT '',
  `channel` varchar(255) DEFAULT '',
  `device_id` varchar(255) NOT NULL DEFAULT '',
  `device_model` varchar(255) DEFAULT '',
  `headers` text,
  `idfa` varchar(255) DEFAULT '',
  `market` varchar(255) DEFAULT '',
  `mcc_mnc` varchar(255) DEFAULT '',
  `ostype` varchar(255) DEFAULT '',
  `ratio` varchar(255) DEFAULT '' COMMENT '分辨率',
  `real_ip` varchar(255) DEFAULT '',
  `system_version` varchar(255) DEFAULT '',
  `user_agent` varchar(255) DEFAULT '',
  `device_type` varchar(255) DEFAULT '',
  `subject` tinyint DEFAULT '0' COMMENT '科目：0美术 1写字 2学院',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uid` (`uid`),
  KEY `index_ctime` (`ctime`),
  KEY `index_login_source` (`login_source`)
) ENGINE=InnoDB AUTO_INCREMENT=44416 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for u_weixin_user
-- ----------------------------
DROP TABLE IF EXISTS `u_weixin_user`;
CREATE TABLE `u_weixin_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT '0',
  `ctime` bigint DEFAULT '0',
  `del` bigint DEFAULT '0',
  `mid` bigint DEFAULT '0',
  `utime` bigint DEFAULT '0',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `fast_follow_time` bigint DEFAULT '0' COMMENT '首次关注公众号时间',
  `follow` int DEFAULT '0' COMMENT '是否关注微信公众账号0非1代表关注',
  `language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `sex` int DEFAULT '0',
  `teacher_id` bigint DEFAULT '0',
  `type` int DEFAULT '0' COMMENT '用户类型 0未绑定用户,1用户,2后台员工',
  `uid` bigint DEFAULT '0',
  `unionid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `send` int DEFAULT '0' COMMENT '是否推送消息',
  `checked` int DEFAULT '0' COMMENT '是否已检查:0-否 1-是',
  `subject` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE,
  KEY `openid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=118948 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
