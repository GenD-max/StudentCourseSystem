/*
 Navicat Premium Data Transfer

 Source Server         : 
 Source Server Type    : MySQL
 Source Server Version : 50651
 Source Host           : 
 Source Schema         : course-select

 Target Server Type    : MySQL
 Target Server Version : 50651
 File Encoding         : 65001

 Date: 27/12/2022 16:26:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `Aid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Aname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Apassword` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`Aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('19114731', 'Gendml', '123456');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `Cid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `Tid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Cname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Ccredit` float(2, 1) NOT NULL,
  `Cintroduction` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Ctype` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Cbelongcoll` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Cbelongpro` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Monday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Tuesday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Wednesday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Thursday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Friday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Csemester` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Courseweek` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Cclassroom` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Cteachbuilding` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Cmodtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1003', '@MTC8800', '面向对象程序设计', 3.0, 'JavaSE基础', '必修', '计算机学院', '计算机科学与技术', '1,2', '3', NULL, '3', NULL, '大三下学期', '1-10周', '107', '经计楼', NULL);
INSERT INTO `course` VALUES ('1005', '@ZFZ6699', '大学英语（2）', 2.0, '大学英语（2）', '必修', '外国语学院', '外国语所有专业', NULL, '2,4', NULL, NULL, '1,2', '大三下学期', '1-16周', '308', '经计楼', NULL);
INSERT INTO `course` VALUES ('1010', '@ZJL2256', '编译原理', 3.0, '计算机语言编译底层解析', '必修', '计算机学院', '计算机科学与技术', '', '5,4', '', '', '4', '大三下学期', '1-8周', '506', '博学楼', NULL);
INSERT INTO `course` VALUES ('2002', '@ZJL2256', '网络工程', 1.5, '网络安全、网络路由配置', '必修', '计算机学院', '网络工程', '', '', '', '2,3', '', '大三下学期', '1-6周', '401', '勤学楼', NULL);

-- ----------------------------
-- Table structure for courseplan
-- ----------------------------
DROP TABLE IF EXISTS `courseplan`;
CREATE TABLE `courseplan`  (
  `Cid` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Cname` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Sid` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Tid` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Monday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `Tuesday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Wednesday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Thursday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Friday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Cid`) USING BTREE,
  INDEX `cid`(`Cid`) USING BTREE,
  INDEX `tid`(`Tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of courseplan
-- ----------------------------
INSERT INTO `courseplan` VALUES ('1003', '面向对象程序设计', '19114731', '@MTC8800', '1,2', '3', '', '3', '');
INSERT INTO `courseplan` VALUES ('1010', '编译原理', '19114731', '@ZJL2256', '', '5,4', '', '', '4');

-- ----------------------------
-- Table structure for faculity
-- ----------------------------
DROP TABLE IF EXISTS `faculity`;
CREATE TABLE `faculity`  (
  `Fname` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of faculity
-- ----------------------------
INSERT INTO `faculity` VALUES ('信息与计算机学院');
INSERT INTO `faculity` VALUES ('外国语学院');
INSERT INTO `faculity` VALUES ('理学院');
INSERT INTO `faculity` VALUES ('植物保护学院');
INSERT INTO `faculity` VALUES ('动科院');
INSERT INTO `faculity` VALUES ('资源与环境学院');
INSERT INTO `faculity` VALUES ('经济管理学院');
INSERT INTO `faculity` VALUES ('继续教育学院');

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc`  (
  `Sid` char(12) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Tid` char(12) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `Cid` char(4) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Ggrade` int(3) NULL DEFAULT NULL,
  `Gmodtime` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('19114731', '@ZJL2256', '1010', 99, '2022-12-27 00:00:00');
INSERT INTO `sc` VALUES ('19114731', '@MTC8800', '1003', NULL, NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `Sid` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `Sname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Sidcard` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Ssex` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Spassword` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Sage` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Sgrade` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Sclass` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Sprofession` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Sinstitution` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Sphotourl` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Smodtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Sid`) USING BTREE,
  INDEX `class`(`Sclass`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('19114731', 'Gendml', '110111201404104596', '男', 'zxcvbnm', '', '', '一班', '计算机科学与技术', '信息与计算机学院', NULL, '2022-12-27 16:25:14');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `Tid` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `Tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Tpassword` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Tsex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Tposition` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Tphone` bigint(20) NULL DEFAULT NULL,
  `Temail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Tphotourl` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Tintroduction` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Tmodtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('@MTC8800', '苗添池', 'tiaotianchi', '男', '副教授', 18691784907, 'hgv1231@gmail.com', 'student1.png', NULL, NULL);
INSERT INTO `teacher` VALUES ('@ZFZ6699', '	\r\n郑夫子', 'zhengfuzi', '男', '副教授', 19856967830, 'fhFq@live.com', 'student1.png', NULL, NULL);
INSERT INTO `teacher` VALUES ('@ZJL2256', '周杰伦', 'zhoujielun', '男', '教授', 18827264529, '2BNN6v2@163.com', 'student1.png', '哎呦不错呦', '2022-12-27 16:23:16');

SET FOREIGN_KEY_CHECKS = 1;
