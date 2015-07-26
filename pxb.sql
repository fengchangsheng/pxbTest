/*
Navicat MySQL Data Transfer

Source Server         : fcs
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : pxb

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-07-26 22:55:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_authority`;
CREATE TABLE `t_authority` (
  `id` varchar(36) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_authority
-- ----------------------------
INSERT INTO `t_authority` VALUES ('1', 'user.do?myjsp', '我的页面');
INSERT INTO `t_authority` VALUES ('2', 'login.do?main', '主页面');
INSERT INTO `t_authority` VALUES ('3', 'login.do?logout', '注销');

-- ----------------------------
-- Table structure for t_nex_pjt
-- ----------------------------
DROP TABLE IF EXISTS `t_nex_pjt`;
CREATE TABLE `t_nex_pjt` (
  `id` varchar(255) NOT NULL,
  `EndTime` datetime DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `StartTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_nex_pjt
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', '管理员');

-- ----------------------------
-- Table structure for t_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_role_auth`;
CREATE TABLE `t_role_auth` (
  `id` varchar(36) NOT NULL,
  `rid` varchar(36) DEFAULT NULL,
  `authid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_auth
-- ----------------------------
INSERT INTO `t_role_auth` VALUES ('1', '1', '1');
INSERT INTO `t_role_auth` VALUES ('2', '1', '2');
INSERT INTO `t_role_auth` VALUES ('3', '1', '3');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(36) NOT NULL,
  `usercode` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'fcs', 'admin', '21232f297a57a5a743894a0e4a801fc3');
INSERT INTO `t_user` VALUES ('2', 'xiaoy', 'xiaoy', '21232f297a57a5a743894a0e4a801fc3');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` varchar(36) NOT NULL,
  `uid` varchar(36) DEFAULT NULL,
  `rid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1');
INSERT INTO `t_user_role` VALUES ('2', '2', '1');
