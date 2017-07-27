/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : ikould_decorate

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-07-27 18:32:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123');

-- ----------------------------
-- Table structure for `material`
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeId` int(11) DEFAULT NULL,
  `seriesId` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `subtitle` varchar(200) DEFAULT NULL,
  `picPath` varchar(200) DEFAULT NULL,
  `marketValue` varchar(20) DEFAULT NULL,
  `sellingValue` varchar(20) DEFAULT NULL,
  `originalPrice` varchar(20) DEFAULT NULL,
  `textId` int(11) DEFAULT NULL,
  `date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------

-- ----------------------------
-- Table structure for `materialseries`
-- ----------------------------
DROP TABLE IF EXISTS `materialseries`;
CREATE TABLE `materialseries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `msg` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of materialseries
-- ----------------------------

-- ----------------------------
-- Table structure for `materialtext`
-- ----------------------------
DROP TABLE IF EXISTS `materialtext`;
CREATE TABLE `materialtext` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `materialId` int(11) DEFAULT NULL,
  `text` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of materialtext
-- ----------------------------

-- ----------------------------
-- Table structure for `materialtype`
-- ----------------------------
DROP TABLE IF EXISTS `materialtype`;
CREATE TABLE `materialtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `msg` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of materialtype
-- ----------------------------
