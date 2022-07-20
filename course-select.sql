/*
 Navicat Premium Data Transfer

 Source Server         : 10.4.28.77-MySQL
 Source Server Type    : MySQL
 Source Server Version : 50536
 Source Host           : 127.0.0.1:3306
 Source Schema         : course-select

 Target Server Type    : MySQL
 Target Server Version : 50536
 File Encoding         : 65001

 Date: 20/07/2022 22:08:00
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
INSERT INTO `admin` VALUES ('@Admin123', 'Admin', '123456');

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
  `Cmodtime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`Cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1003', '0005', '面向对象程序设计', 3.0, 'JavaSE基础', '必修', '计算机学院', '计算机科学与技术', '1,2', '3', NULL, '3', NULL, '大三下学期', '1-10周', '107', '经计楼', NULL);
INSERT INTO `course` VALUES ('1005', '0022', '大学英语（2）', 2.0, '大学英语（2）', '必修', '外国语学院', '外国语所有专业', NULL, '2,4', NULL, NULL, '1,2', '大三下学期', '1-16周', '308', '经计楼', NULL);
INSERT INTO `course` VALUES ('1010', '1010', '编译原理', 3.0, '计算机语言编译底层解析', '必修', '计算机学院', '计算机科学与技术', NULL, '5', NULL, NULL, NULL, '大三下学期', '1-8周', '506', '博学楼', NULL);
INSERT INTO `course` VALUES ('2002', '5010', '网络工程', 1.5, '网络安全、网络路由配置', '必修', '计算机学院', '网络工程', NULL, NULL, NULL, '2', NULL, '大三下学期', '1-6周', '401', '勤学楼', NULL);
INSERT INTO `course` VALUES ('2003', '1003', 'C语言基础', 2.0, 'C语言介绍使用', '必修', '计算机学院', '所有', NULL, NULL, '3', NULL, NULL, '大三下学期', '12-16周', '308', '经计楼', NULL);
INSERT INTO `course` VALUES ('2004', '0022', '大学英语（1）', 1.5, '大学英语', '必修', '所有', '所有', '3,4', NULL, NULL, NULL, '1,3', '大三下学期', '2-10周', '308', '经计楼', NULL);

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
  `Gmodtime` datetime NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sc
-- ----------------------------

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
  `Smodtime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`Sid`) USING BTREE,
  INDEX `class`(`Sclass`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('19114655', '刘浩存', '123456789', '女', '666666', NULL, '2019', NULL, NULL, '理学院', NULL, '2022-05-30 23:46:34');
INSERT INTO `student` VALUES ('19114731', '董梦龙', '34151513234185', '男', '123456', '', '', '22', '22', '信息与计算机学院', NULL, '2022-07-20 22:02:36');
INSERT INTO `student` VALUES ('19114744', '李悦悦', '140581996101262156', '男', '123123', '22', '2019', '一班', '计算机科学与技术', '信息与计算机学院', NULL, NULL);
INSERT INTO `student` VALUES ('19114766', '周杰伦', '848155645615646615', '男', '999999', '42', '1980', '三班', '机械与自动化', '工学院', 'student1.png', NULL);

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
  `Tmodtime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`Tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('0002', '张友华', '789789', '男', '教授', 12345678229, '123456789@163.com', 'student1.png', 'AHAU信息与计算机学院院长', NULL);
INSERT INTO `teacher` VALUES ('0005', '涂立静', '123123', '女', '讲师', 19584527899, '1235698456@qq.com', NULL, '小美好~', NULL);
INSERT INTO `teacher` VALUES ('0022', '崔芳芳', '111111', '女', '讲师', 19623558496, '52698342@ahau.edu.cn', NULL, 'I am Ms.Cui.', NULL);
INSERT INTO `teacher` VALUES ('1003', '吴国栋', '123456', '男', '教授', 16356987455, 'wugd@ahau.edu.cn', NULL, '随和！~', NULL);
INSERT INTO `teacher` VALUES ('1010', '许高建', '999999', '男', '副教授', 147569725367, '123235@qq.com', NULL, '教学有方', '2022-07-20 21:51:45');
INSERT INTO `teacher` VALUES ('5010', '高宁', '123321', '女', '讲师', 13659842553, '1256982364@qq.com', NULL, '和蔼可亲', NULL);
INSERT INTO `teacher` VALUES ('@Wu123', '吴云志', '123456', '女', '副教授', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
