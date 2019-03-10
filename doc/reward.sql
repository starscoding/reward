/*
Navicat MySQL Data Transfer

Source Server         : root(3306)
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : reward

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-03-10 11:59:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app_buy_invitecode`
-- ----------------------------
DROP TABLE IF EXISTS `app_buy_invitecode`;
CREATE TABLE `app_buy_invitecode` (
  `id_` varchar(32) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `code_` varchar(32) NOT NULL,
  `price` decimal(12,2) NOT NULL,
  `buy_time` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_buy_invitecode
-- ----------------------------
INSERT INTO `app_buy_invitecode` VALUES ('5683CB4C68E4BDF52816034FACAD405E', 'agency01', 'n02967nk71LF', '1.00', '2018-05-18 01:16:35');
INSERT INTO `app_buy_invitecode` VALUES ('5C627CB0D0A7EDAB2C2C52641E27BE3B', 'agency01', 'yo41s06Yl4s2', '1.00', '2018-05-18 01:16:35');
INSERT INTO `app_buy_invitecode` VALUES ('61A4DD14C080FD154817C3AE9F4A8C5F', 'agency01', '9RwuJ067ty09', '1.00', '2018-05-18 01:16:35');
INSERT INTO `app_buy_invitecode` VALUES ('6506810ECF440577773C2A4ED114C658', 'agency01', 'V9P105Z7B9Lg', '1.00', '2018-05-18 01:16:35');
INSERT INTO `app_buy_invitecode` VALUES ('67F20BE12340500A6D9158B3B77ABCC0', 'agency01', 't0866NMl5189', '1.00', '2018-05-18 01:16:35');
INSERT INTO `app_buy_invitecode` VALUES ('928A8611703340332A764949FB071B17', 'agency01', '33kk4S1j05CI', '1.00', '2018-05-18 01:16:35');
INSERT INTO `app_buy_invitecode` VALUES ('A301908EDB38E31060FC374161AE92CE', 'agency01', 'tun6w3hy787s', '1.00', '2018-05-18 01:16:35');
INSERT INTO `app_buy_invitecode` VALUES ('A6D4BC7B4A4C60F404622DB01BD46EBE', 'agency01', 'cdeF6779253M', '1.00', '2018-05-18 01:16:16');
INSERT INTO `app_buy_invitecode` VALUES ('CB71B396E8A90A2E674E827361647E51', 'agency01', '5HZ4sB537c51', '1.00', '2018-05-18 01:16:35');
INSERT INTO `app_buy_invitecode` VALUES ('E63EF37288E286A42F0EE1BF518E8D71', 'agency01', 'J62iV18828c8', '1.00', '2018-05-18 01:16:35');

-- ----------------------------
-- Table structure for `app_domain_info`
-- ----------------------------
DROP TABLE IF EXISTS `app_domain_info`;
CREATE TABLE `app_domain_info` (
  `domain` varchar(100) NOT NULL,
  `status_` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`domain`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_domain_info
-- ----------------------------
INSERT INTO `app_domain_info` VALUES ('133c4ac4.ngrok.io', '0');
INSERT INTO `app_domain_info` VALUES ('1353668c.ngrok.io', '2');
INSERT INTO `app_domain_info` VALUES ('www.smile.com', '2');

-- ----------------------------
-- Table structure for `app_invitation_code_info`
-- ----------------------------
DROP TABLE IF EXISTS `app_invitation_code_info`;
CREATE TABLE `app_invitation_code_info` (
  `CODE_` varchar(32) NOT NULL,
  `CREATE_TIME` varchar(25) DEFAULT NULL,
  `STATUS_` varchar(5) DEFAULT NULL,
  `ACTIVE_NAME` varchar(100) DEFAULT NULL,
  `CREATE_NAME` varchar(100) DEFAULT NULL,
  `ACTIVE_TIME` varchar(25) DEFAULT NULL COMMENT '激活时间',
  PRIMARY KEY (`CODE_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_invitation_code_info
-- ----------------------------
INSERT INTO `app_invitation_code_info` VALUES ('01Hm2u813KtJ', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('03rc06q02E5I', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('08l7J045ID68', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('0OwILb45PQ3x', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('108C27N9U676', '2018-04-27 11:22:07', '1', 'agency01', 'partner', '2018-04-30 12:00:00');
INSERT INTO `app_invitation_code_info` VALUES ('10I5v654Wb5I', '2018-04-27 11:22:07', '1', 'agency02', 'partner', '2018-05-17 15:25:17');
INSERT INTO `app_invitation_code_info` VALUES ('181q46SX2vN2', '2018-04-26 05:08:01', '1', 'test', 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('1EG750HS140b', '2018-04-27 11:22:07', '1', 'agency03', 'partner', '2018-05-24 19:03:33');
INSERT INTO `app_invitation_code_info` VALUES ('1I1oRu96Ma04', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('1w861Q5I9R56', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('2092Y5y0O8G8', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('33kk4S1j05CI', '2018-05-18 01:16:35', '0', null, 'agency01', null);
INSERT INTO `app_invitation_code_info` VALUES ('34d21K043K89', '2018-04-27 11:22:07', '1', 'agency04', 'partner', '2018-05-24 19:07:51');
INSERT INTO `app_invitation_code_info` VALUES ('3686dQWb9u43', '2018-04-27 11:22:07', '0', null, 'partner', null);
INSERT INTO `app_invitation_code_info` VALUES ('391q3E336fM0', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('4iKZ0gYM62Vg', '2018-04-27 11:22:07', '0', null, 'partner', null);
INSERT INTO `app_invitation_code_info` VALUES ('4mfG7u039yOP', '2018-04-27 11:22:07', '0', null, 'partner', null);
INSERT INTO `app_invitation_code_info` VALUES ('5683Kg20q9A5', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('5HZ4sB537c51', '2018-05-18 01:16:35', '0', null, 'agency01', null);
INSERT INTO `app_invitation_code_info` VALUES ('5T1ku1568e32', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('5z3y2DC727m7', '2018-04-27 11:22:07', '0', null, 'partner', null);
INSERT INTO `app_invitation_code_info` VALUES ('641x20Hu0v60', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('65KTCFgvo9xt', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('68v8kZ4jbi7G', '2018-04-27 11:22:07', '0', null, 'partner', null);
INSERT INTO `app_invitation_code_info` VALUES ('6Ed323nhLU88', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('71R5B3vU14zT', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('838LvlA46ez9', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('85poiGN89r2B', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('9Oj5RpD2Y158', '2018-04-27 11:22:07', '0', null, 'partner', null);
INSERT INTO `app_invitation_code_info` VALUES ('9r9m0N87xU50', '2018-04-27 11:22:07', '0', null, 'partner', null);
INSERT INTO `app_invitation_code_info` VALUES ('9RwuJ067ty09', '2018-05-18 01:16:35', '0', null, 'agency01', null);
INSERT INTO `app_invitation_code_info` VALUES ('ar0M5V416CV2', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('cdeF6779253M', '2018-05-18 01:16:16', '1', 'agency0101', 'agency01', '2018-05-24 19:04:23');
INSERT INTO `app_invitation_code_info` VALUES ('dP669Uo7J6HU', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('DpW50WUo3lwT', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('e75eD09bf7gT', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('F378u7OMQb63', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('h0sH9y2BgTOt', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('H9K0B959154Y', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('I4szi64f0EJ5', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('i80y11OeFL0i', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('J62iV18828c8', '2018-05-18 01:16:35', '0', null, 'agency01', null);
INSERT INTO `app_invitation_code_info` VALUES ('J6k27646d98l', '2018-04-27 11:22:07', '0', null, 'partner', null);
INSERT INTO `app_invitation_code_info` VALUES ('Jb66i00EBMxa', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('JLn3WKMsRq14', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('K0V4431299Ku', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('KOLhU38Kwx51', '2018-04-27 11:22:07', '0', null, 'partner', null);
INSERT INTO `app_invitation_code_info` VALUES ('l0VKdb1627g1', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('LA25sn4K87o9', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('lyG4CtM9f0Md', '2018-04-27 11:22:07', '0', null, 'partner', null);
INSERT INTO `app_invitation_code_info` VALUES ('n02967nk71LF', '2018-05-18 01:16:35', '0', null, 'agency01', null);
INSERT INTO `app_invitation_code_info` VALUES ('O82pbG197148', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('p25gKS6BbRXa', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('R54Dxi72F3nX', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('R95Ly3i2B97d', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('Reojo4110nn9', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('srX1Uq3f18WT', '2018-04-27 11:22:07', '0', null, 'partner', null);
INSERT INTO `app_invitation_code_info` VALUES ('t0866NMl5189', '2018-05-18 01:16:35', '0', null, 'agency01', null);
INSERT INTO `app_invitation_code_info` VALUES ('tun6w3hy787s', '2018-05-18 01:16:35', '0', null, 'agency01', null);
INSERT INTO `app_invitation_code_info` VALUES ('u15719l0YgV2', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('u6KV1D73pz56', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('u7287p088z8q', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('V9P105Z7B9Lg', '2018-05-18 01:16:35', '0', null, 'agency01', null);
INSERT INTO `app_invitation_code_info` VALUES ('W1260U3nG207', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('w99l3U7623W1', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('x40gr212802v', '2018-04-27 10:45:41', '0', null, 'test', null);
INSERT INTO `app_invitation_code_info` VALUES ('X4674806J9qh', '2018-04-26 05:29:13', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('x86O5v3187t5', '2018-04-26 05:08:01', '0', null, 'admin', null);
INSERT INTO `app_invitation_code_info` VALUES ('yo41s06Yl4s2', '2018-05-18 01:16:35', '0', null, 'agency01', null);
INSERT INTO `app_invitation_code_info` VALUES ('ysd2t2DDxilB', '2018-04-27 10:45:41', '0', null, 'test', null);

-- ----------------------------
-- Table structure for `app_link_info`
-- ----------------------------
DROP TABLE IF EXISTS `app_link_info`;
CREATE TABLE `app_link_info` (
  `ID` varchar(32) NOT NULL,
  `VIDEO_ID` varchar(32) NOT NULL,
  `USER_NAME` varchar(100) NOT NULL,
  `VIDEO_PRICE` decimal(10,2) NOT NULL,
  `RECORD_TIME` varchar(25) DEFAULT NULL,
  `SHORT_URL` varchar(100) NOT NULL COMMENT '短链接',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_link_info
-- ----------------------------
INSERT INTO `app_link_info` VALUES ('08858E406F01701F93E96DA750CEF12C', '75BDB69D712FC543C903EB91254DAD3F', 'agency01', '0.01', '2018-06-12 10:49:23', 'http://t.cn/RBcCGO2');
INSERT INTO `app_link_info` VALUES ('188DA70CD1604612A6CADF6C44E33CC9', '3FBC7193DF6DF0FB2E763179A034C1F4', 'agency01', '0.01', '2018-06-12 10:49:43', 'http://t.cn/RBcCVlH');
INSERT INTO `app_link_info` VALUES ('2474ADD55F5D97B9CF491F134DCD72CA', '5B40772063012076C827BD93D4D33013', 'agency01', '0.01', '2018-06-12 10:49:43', 'http://t.cn/RBcCVQc');
INSERT INTO `app_link_info` VALUES ('6A9B5CE949772A60C40F431E488C1685', '1A13E2D8942FDFF3FD1E02017BCC882F', 'agency01', '0.01', '2018-06-12 10:49:43', 'http://t.cn/RBcCVNe');
INSERT INTO `app_link_info` VALUES ('A21824B6A411F885194118833E2EED3F', '41D121E4D2E0D8914081E44E05F80E73', 'agency01', '0.01', '2018-06-12 10:49:23', 'http://t.cn/RBcCGXT');
INSERT INTO `app_link_info` VALUES ('C0A5037231CE7BF57B12AA6B2893D5D4', 'D2B94A82CDF8B5F1676ADBA067031144', 'agency01', '0.01', '2018-06-12 10:49:23', 'http://t.cn/RBcCGHw');
INSERT INTO `app_link_info` VALUES ('C8E9F64225C5281E070A04F2B993C732', 'CD20752F97371A731F15D886604EC3E3', 'agency01', '0.01', '2018-06-12 10:49:43', 'http://t.cn/RBcCVE3');

-- ----------------------------
-- Table structure for `app_reward_info`
-- ----------------------------
DROP TABLE IF EXISTS `app_reward_info`;
CREATE TABLE `app_reward_info` (
  `ID` varchar(32) NOT NULL,
  `NAME_` varchar(100) DEFAULT NULL,
  `AMOUNT` decimal(12,2) DEFAULT NULL,
  `VIDEO_ID` varchar(32) DEFAULT NULL,
  `STAFF_ID` varchar(32) DEFAULT NULL,
  `REWARD_TIME` varchar(25) DEFAULT NULL,
  `LINK_ID` varchar(32) DEFAULT NULL,
  `IP_ADDRESS` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_reward_info
-- ----------------------------
INSERT INTO `app_reward_info` VALUES ('1034115287853864687', 'agency01', '0.01', '5B40772063012076C827BD93D4D33013', null, '2018-06-12 14:36:37', '2474ADD55F5D97B9CF491F134DCD72CA', '193.112.38.136');
INSERT INTO `app_reward_info` VALUES ('1034115287854599272', 'agency01', '0.01', '41D121E4D2E0D8914081E44E05F80E73', null, '2018-06-12 14:43:11', 'A21824B6A411F885194118833E2EED3F', '193.112.38.136');
INSERT INTO `app_reward_info` VALUES ('1034115287856575399', 'agency01', '0.01', '41D121E4D2E0D8914081E44E05F80E73', null, '2018-06-12 14:46:31', 'A21824B6A411F885194118833E2EED3F', '193.112.38.136');
INSERT INTO `app_reward_info` VALUES ('1034115287869518331', 'agency01', '0.01', '3FBC7193DF6DF0FB2E763179A034C1F4', null, '2018-06-12 15:02:40', '188DA70CD1604612A6CADF6C44E33CC9', '106.6.178.147');
INSERT INTO `app_reward_info` VALUES ('20309_15264906536699', 'agency01', '10.00', '6B22CBAE78D7408518C14445B451D4C8', null, '2018-05-17 01:11:11', '672B9773FEEFA2BCA50367270FD37F31', '0:0:0:0:0:0:0:1');
INSERT INTO `app_reward_info` VALUES ('20309_15264916407739', 'agency01', '0.01', '6B22CBAE78D7408518C14445B451D4C8', null, '2018-05-17 01:27:29', '672B9773FEEFA2BCA50367270FD37F31', '0:0:0:0:0:0:0:1');
INSERT INTO `app_reward_info` VALUES ('20309_15264921324146', 'agency01', '1000.00', '41D121E4D2E0D8914081E44E05F80E73', null, '2018-06-11 01:35:41', '672B9773FEEFA2BCA50367270FD37F31', '0:0:0:0:0:0:0:1');
INSERT INTO `app_reward_info` VALUES ('20309_15264922359780', 'agency01', '15000.00', '6B22CBAE78D7408518C14445B451D4C8', null, '2018-05-17 01:37:28', '672B9773FEEFA2BCA50367270FD37F31', '0:0:0:0:0:0:0:1');
INSERT INTO `app_reward_info` VALUES ('20309_15264927677706', 'agency01', '0.01', '6B22CBAE78D7408518C14445B451D4C8', null, '2018-05-17 01:46:16', '672B9773FEEFA2BCA50367270FD37F31', '0:0:0:0:0:0:0:1');
INSERT INTO `app_reward_info` VALUES ('20309_15264934287623', 'agency01', '0.01', '6B22CBAE78D7408518C14445B451D4C8', null, '2018-05-17 01:57:16', '672B9773FEEFA2BCA50367270FD37F31', '0:0:0:0:0:0:0:1');
INSERT INTO `app_reward_info` VALUES ('20309_15264956218665', 'agency01', '0.01', '1941E73E4B9397AA0480A02565F38F7B', null, '2018-05-17 02:33:52', '0EC86F280932C00BB200A5A981FB2197', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for `app_video_info`
-- ----------------------------
DROP TABLE IF EXISTS `app_video_info`;
CREATE TABLE `app_video_info` (
  `ID_` varchar(32) NOT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `TITLE_` varchar(300) DEFAULT NULL,
  `PUBLISH_TIME` varchar(25) DEFAULT NULL,
  `TIME_LENGTH` varchar(20) DEFAULT NULL,
  `URL_` varchar(200) NOT NULL,
  `type_` varchar(5) DEFAULT NULL COMMENT '0-公共，1-私有',
  `FILE_SIZE` varchar(30) DEFAULT NULL,
  `FILE_NAME` varchar(200) DEFAULT NULL,
  `IMG_NAME` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_video_info
-- ----------------------------

-- ----------------------------
-- Table structure for `app_wechatpay_info`
-- ----------------------------
DROP TABLE IF EXISTS `app_wechatpay_info`;
CREATE TABLE `app_wechatpay_info` (
  `APPID` varchar(100) NOT NULL,
  `COMMENT_` varchar(100) DEFAULT NULL,
  `APPSECRET` varchar(50) DEFAULT NULL,
  `BIZ_ACCOUNT` varchar(50) DEFAULT NULL,
  `KEY_` varchar(50) DEFAULT NULL,
  `TXT_URL` varchar(100) DEFAULT NULL,
  `FILE_NAME` varchar(100) DEFAULT NULL,
  `STATUS_` varchar(2) DEFAULT NULL COMMENT '接口状态0-使用中 1-备用中 2-已废弃',
  PRIMARY KEY (`APPID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_wechatpay_info
-- ----------------------------
INSERT INTO `app_wechatpay_info` VALUES ('4', '4', '4', '4', '4', 'http://localhost:7090/rewardweb/fileinput.min.js', 'fileinput.min.js', '2');
INSERT INTO `app_wechatpay_info` VALUES ('a', '', 'd', 'a', 'a', 'C:\\Code\\Java\\Space\\rewardweb\\src\\main\\webapp\\data\\fileinput.js', 'fileinput.js', '1');
INSERT INTO `app_wechatpay_info` VALUES ('b', 'b', 'b', 'b', 'b', 'C:\\Code\\Java\\Space\\rewardweb\\src\\main\\webapp\\data\\fileinput.js', 'fileinput.js', '2');
INSERT INTO `app_wechatpay_info` VALUES ('wx4dd60a7084ebd8e6', '吴礼仔', '917236f04f6d8d0d9237407c839ffc31', '1500452392', 'FANGSHI1817893582518178935927JIE', '/verfytxt/', 'abc.txt', '0');

-- ----------------------------
-- Table structure for `app_withdraw_info`
-- ----------------------------
DROP TABLE IF EXISTS `app_withdraw_info`;
CREATE TABLE `app_withdraw_info` (
  `ID_` varchar(32) NOT NULL,
  `NAME_` varchar(100) DEFAULT NULL,
  `AMOUNT` decimal(15,2) DEFAULT NULL,
  `ALIPAYACCOUNT` varchar(50) DEFAULT NULL,
  `FULL_NAME` varchar(50) DEFAULT NULL,
  `TIME_` varchar(20) DEFAULT NULL,
  `OP_STATUS` varchar(5) DEFAULT NULL,
  `URL_` varchar(100) DEFAULT NULL COMMENT '相对项目目录地址',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_withdraw_info
-- ----------------------------
INSERT INTO `app_withdraw_info` VALUES ('9AB01974867C284519224468EBD69B32', 'agency01', '100.00', 'ali', 'ali', '2018-05-05 08:14:16', '1', null);
INSERT INTO `app_withdraw_info` VALUES ('DECCC06EAE48D8212C2D0333348A09A5', 'agency01', '100.00', 'abc', 'smile', '2018-05-05 08:43:24', '2', null);

-- ----------------------------
-- Table structure for `sys_conf_info`
-- ----------------------------
DROP TABLE IF EXISTS `sys_conf_info`;
CREATE TABLE `sys_conf_info` (
  `id` varchar(32) NOT NULL,
  `group_name` varchar(100) DEFAULT NULL,
  `name_` varchar(100) DEFAULT NULL,
  `name_ch` varchar(100) DEFAULT NULL,
  `value_` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_conf_info
-- ----------------------------
INSERT INTO `sys_conf_info` VALUES ('0688BEC32A8BA45733BBCEAC75CAB0FA', 'sysConf', 'authDomain', '支付接口鉴权域名', '133c4ac4.ngrok.io');
INSERT INTO `sys_conf_info` VALUES ('0D26BEE06840982E264D5A598ADF4D41', 'sysConf', 'withdraw', '提现扣除佣金（百分比)', '10');
INSERT INTO `sys_conf_info` VALUES ('15F432544A1150F34FBC280B494EF0B2', 'shieldPay', 'cloudName', '商户名称', 'sk-xy340');
INSERT INTO `sys_conf_info` VALUES ('21E65BDECE001A097B4FEC3C49A1D28C', 'filepath', 'videoPath', '视频保存路径', 'C:/Code/Spring/Space/reward/src/main/webapp/video/');
INSERT INTO `sys_conf_info` VALUES ('27A6F940DEDE3C8A626C429719D80300', 'sysConf', 'notice', '公告', '<h1>abc</h1>');
INSERT INTO `sys_conf_info` VALUES ('343F57204FEFE5B49BA1BC59C45850EC', 'agencyConf', 'generalReward', '初级代理佣金', '22');
INSERT INTO `sys_conf_info` VALUES ('3E43869D07E6FFADBA1EA04E2556DF90', 'shieldPay', 'shieldKey', '三方支付key', 'e4a1fbe1398e0f684ee7a15a9609030a');
INSERT INTO `sys_conf_info` VALUES ('421B585F1B5AEA4CFEB6739214F9C6D8', 'sysConf', 'maintainflag', '是否开启维护0-开启，1-不开启', '1');
INSERT INTO `sys_conf_info` VALUES ('432856267C8571FA235B13AE7AD304C2', 'sysConf', 'invitationCondition', '邀请码条件', '1');
INSERT INTO `sys_conf_info` VALUES ('4400D195440860F032C674A4B5390866', 'shieldPay', 'cloudUrl', '接口地址', 'http://mms.weixin881.com:8000/ltPayBusiness/order/prepareOrder');
INSERT INTO `sys_conf_info` VALUES ('47D159DDA953009998578D441B91D4CA', 'agencyConf', 'effectiveJunior', '有效下级', '1');
INSERT INTO `sys_conf_info` VALUES ('50BCC0294DD31FCBB2C62E2E50C2D118', 'shieldPay', 'shieldAppId', '三方支付appid', '20309');
INSERT INTO `sys_conf_info` VALUES ('524793AFF60C4A7102F8F0C7DDDBB4AC', 'agencyConf', 'primaryAgency', '普通代理有效下级数量', '10');
INSERT INTO `sys_conf_info` VALUES ('54D7B8B3BDFEAA5C31A17E209966A7D8', 'shieldPay', 'shieldUrl', '支付地址', 'http://api.ykhet.cn');
INSERT INTO `sys_conf_info` VALUES ('8229A5DC2D29B6184410BB3DDEF2610F', 'agencyConf', 'advancedReward', '高级代理佣金', '4');
INSERT INTO `sys_conf_info` VALUES ('886473347A628E2E3F4BCCA2FCC3C3BF', 'shieldPay', 'payType', '支付方式', 'cloud');
INSERT INTO `sys_conf_info` VALUES ('895C39819E7EBA4D58F1A77A096E7AD6', 'sysConf', 'codeNotice', '邀请码说明', '<p>◄:◄:◄:夜间不处理任何提现，提现到账时间每天中午12点到13点统一处理。&nbsp;请各位代理填好个人支付信息，为了你的安全，超过2000元请联系客服安全提现！&nbsp;客服微信：<span style=\"font-family: 微软雅黑; color: rgb(194, 79, 74); font-weight: bold;\">mao10202717</span>,客服<span style=\"font-weight: bold; color: rgb(194, 79, 74);\">QQ:1234</span></p>');
INSERT INTO `sys_conf_info` VALUES ('8F3615DBEDDC5DBF4BFA96FF10B0B605', 'agencyConf', 'primaryReward', '普通代理佣金', '3');
INSERT INTO `sys_conf_info` VALUES ('BFE9DA8BC9CCE56E1F4FD979001CAD89', 'sysConf', 'invitationCode', '邀请码价格', '1');
INSERT INTO `sys_conf_info` VALUES ('C365DB21C75C8AD06B06426E9413E781', 'agencyConf', 'advancedAgency', '高级代理有效下级数量', '30');
INSERT INTO `sys_conf_info` VALUES ('C916E13ADCD15F60F5FA880048C9881C', 'sysConf', 'kfWechat', '客服微信', 'smile');
INSERT INTO `sys_conf_info` VALUES ('C929A990BA0E8E5FEAFDC1FB1AA4C2BF', 'shieldPay', 'cloudAppId', '商户号', '10341');
INSERT INTO `sys_conf_info` VALUES ('CEF6B9128FBC604FD47B206E11F253B5', 'sysConf', 'withdrawNotice', '提现说明', '<p>◄:◄:◄:夜间不处理任何提现，提现到账时间每天中午12点到13点统一处理。&nbsp;请各位代理填好个人支付信息，为了你的安全，超过2000元请联系客服安全提现！&nbsp;客服微信：mao10202717,客服QQ:123</p>');
INSERT INTO `sys_conf_info` VALUES ('D4564D771AEBE7E3CA6C6EA36790254C', 'filepath', 'authTxtPath', '验证文件路径', '/usr/java/tomcat9/webapps/reward/');
INSERT INTO `sys_conf_info` VALUES ('D6A140EEE12D72D194105BABA8B2FE77', 'shieldPay', 'cloudKey', '商户密钥', 'f9247904d12c0438492b5c7b4c3a61f9');
INSERT INTO `sys_conf_info` VALUES ('D987D7BDF9B082F35E3D642D1B377B9F', 'sysConf', 'safeDomain', '安全域名', '133c4ac4.ngrok.io');
INSERT INTO `sys_conf_info` VALUES ('E75D2A489EBBF74CF03E4F09FAC8E8B5', 'filepath', 'videoImgPath', '视频缩略图', 'C:/Code/Spring/Space/reward/src/main/webapp/data/videoImg');

-- ----------------------------
-- Table structure for `sys_logs`
-- ----------------------------
DROP TABLE IF EXISTS `sys_logs`;
CREATE TABLE `sys_logs` (
  `ID_` varchar(32) NOT NULL,
  `APP_HOST` varchar(16) NOT NULL,
  `HOST_` varchar(160) DEFAULT NULL,
  `LOG_LEVEL` varchar(1) DEFAULT NULL,
  `MESSAGE_` varchar(2000) DEFAULT NULL,
  `MODULE_CODE` varchar(32) DEFAULT NULL,
  `OPERATE_TYPE` varchar(2) DEFAULT NULL,
  `QRYUSED_TIME` varchar(10) DEFAULT NULL,
  `RECORD_TIME` varchar(24) DEFAULT NULL,
  `USERNAME_` varchar(32) DEFAULT NULL,
  `AUDIT_ID` varchar(20) DEFAULT NULL,
  `AUTH_FLAG` varchar(1) DEFAULT NULL,
  `OPT_REASON` varchar(500) DEFAULT NULL,
  `ORDER_ID_` varchar(32) DEFAULT NULL,
  `OUTER_USER_` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_logs
-- ----------------------------
INSERT INTO `sys_logs` VALUES ('4913F9F8E88D71AB1C06691406C52BBD', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '51', '2017-10-04 09:32:13', 'unknown', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('8E3C1508D5A1E0E60232CB19F6F981DA', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '53', '2017-10-04 09:44:28', 'unknown', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A988B7EDE510FE133575D12AE9188203', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '54', '2017-10-04 09:51:03', 'unknown', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('2AE9413BE5F3B01E581952AF498D4D16', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '617', '2017-10-04 10:49:19', 'unknown', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('2020270BF04F6B1406DCE3689E27DD2E', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '509', '2017-10-04 10:49:19', 'unknown', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('CB6F85E73595B7C3EC523422002121AC', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '405', '2017-10-04 10:49:19', 'unknown', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('8FFA3FE9A5D4DBDEF0C7C1E786397BAC', '192.168.0.222', '192.168.0.222', '1', '操作类型:[修改],参数:[用户名:root,中文名:超级管理员,电子邮箱:,手机号码:13912345678,用户级别:超级管理员,性别:1],目标:[修改],结果:[成功!]', 'unconfigured', '08', '611', '2017-10-04 10:49:41', 'unknown', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('DFABEC3E09559DD228292F6F6C25712A', '192.168.0.222', '192.168.0.222', '1', '用户:超级管理员(root)登录成功!SESSIONID:CEEEE3E7DDE60FDD9FFEC181FB73A96A,登录入口:本系统', 'login', '09', '34', '2017-10-04 10:50:01', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A6DEA0CE6227029A1E2B967C3EA3506C', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '21', '2017-10-04 10:50:02', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('ABD3C338561C733996653C3C01B5BED6', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '21', '2017-10-04 10:50:34', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('EEC3A2BF271A06013928F54323B6E199', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '16', '2017-10-04 10:50:46', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('09E6185DE23781499C325FD8D0D0B40D', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '15', '2017-10-04 10:50:56', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('586DE24EC752C77F521B6B343B0BB2F2', '192.168.0.222', '192.168.0.222', '1', '用户:超级管理员(root)退出登录!SESSIONID:CEEEE3E7DDE60FDD9FFEC181FB73A96A', 'logout', '09', '7', '2017-10-04 10:52:38', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('0DC4391BD78F4648133DCC71B69000ED', '192.168.0.222', '192.168.0.222', '1', '用户:超级管理员(root)登录成功!SESSIONID:3F65940704F81585F4111D470E5E8D5D,登录入口:本系统', 'login', '09', '3', '2017-10-04 10:52:40', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('EE81C75439F1FFCA2AA132FF8A2F9A16', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '14', '2017-10-04 10:52:40', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('3DD646B7FBA276E29D5207FA167E6A68', '127.0.0.1', '127.0.0.1', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '25', '2017-10-04 10:53:03', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('7B5EAE5A51E7D9798EE5AC482FD00BBA', '127.0.0.1', '127.0.0.1', '1', '操作类型:[查询],参数:[关键字:null],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '26', '2017-10-04 10:53:06', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('6D7D3863E1DD2733B3CC09FBBF3D4582', '127.0.0.1', '127.0.0.1', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '28', '2017-10-04 10:53:07', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('E18D2CA4F06E6D6BAFE09586EA5FB442', '127.0.0.1', '127.0.0.1', '1', '操作类型:[查询],参数:[关键字:null],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '29', '2017-10-04 10:53:08', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('678A7B1CE1E2E898137F838D0B1F2805', '127.0.0.1', '127.0.0.1', '1', '操作类型:[查询],参数:[关键字:null],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '28', '2017-10-04 10:53:17', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('19211810A376239DF560497BEBA87FBA', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '33', '2017-10-04 10:53:45', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('7E7CA72A119D3FE5912220EFCC516227', '192.168.0.222', '192.168.0.222', '1', '用户:超级管理员(root)登录成功!SESSIONID:A8028181EB6E9F9570D1723FC94BE65C,登录入口:本系统', 'login', '09', '10', '2017-10-04 10:59:33', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('EEB87FF114EFC5B2B346D5C21C597403', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '109', '2017-10-04 10:59:34', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('DB417A7492F6EAD5576E29F9E5A8B89F', '192.168.0.222', '192.168.0.222', '1', '操作类型:[授权],参数:[名称:dxadmin,新增资源:日志管理,系统日志查询,个人公告管理,系统基础数据管理,系统数据维护,新增,修改,删除,查询,Portal管理,Portal工具管理,查找,添加,删除,修改,删除资源:],目标:[授权],结果:[成功!]', 'rolemng', '08', '89', '2017-10-04 11:08:23', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('E3ACB207B9C5927A42E4C977E344792C', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '25', '2017-10-04 11:08:28', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('962AF6A766E69A0F18B8188F2EB46E49', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '16', '2017-10-04 11:09:25', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D2BF0E90BC541D7758C9987D9F1E329E', '192.168.0.222', '192.168.0.222', '1', '操作类型:[授权],参数:[名称:dxadmin,新增资源:用户管理,添加帐户,编辑账户,角色授权,部门授权,启用/停用账户,删除账户,查询账户详情,批量新增,部门管理,新增,修改,删除,资源管理,新增资源,修改资源,删除资源,系统公告管理,新增公告,编辑公告,生效/终止公告,删除公告,控制面板,Redis Keys 管理,系统选项,删除资源:],目标:[授权],结果:[成功!]', 'rolemng', '08', '85', '2017-10-04 11:09:48', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('B81BDDD9D5BA70F989636831B442F5E2', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '14', '2017-10-04 11:09:51', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('9678089FFF7EC9EFDECFD23A06497926', '192.168.0.222', '192.168.0.222', '1', '用户:超级管理员(root)登录成功!SESSIONID:A8028181EB6E9F9570D1723FC94BE65C,登录入口:本系统', 'login', '09', '3', '2017-10-04 11:09:55', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('C144AE42F7FD89F217CC469C079F86D4', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '15', '2017-10-04 11:09:56', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('3DD17C874357A8B4B7222C71929FC2CA', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '39', '2017-10-04 11:09:59', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D9F594E4269412CF0B1FBEE49BFE7830', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '17', '2017-10-04 11:10:06', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('163D9551C9927B85272B7E9B379C7ED8', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2017-10-04 11:10:28', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('AFC5385F82FC6AF22026074732E885C8', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '23', '2017-10-04 11:10:32', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('73130622F0BDDB17512D3BF624BF7C53', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:null],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '27', '2017-10-04 11:10:39', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('FD7438D206E7C1634EE57DC5D198DEBB', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:null],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '20', '2017-10-04 11:10:41', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('6B4EBB76EC36D27AFC15F1837D91817A', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '18', '2017-10-04 11:10:53', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('2466157138F759CDAD28CACE2F8B8974', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[是否全部:true,关键字:null,类型:null,开始时间:null,结束时间:null],目标:[查询],结果条数:[0],结果:[成功!]', 'unconfigured', '01', '51', '2017-10-04 11:10:58', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A9EB261F22765B9285592C675333078A', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[是否全部:true,关键字:null,类型:null,开始时间:null,结束时间:null],目标:[查询],结果条数:[0],结果:[成功!]', 'unconfigured', '01', '19', '2017-10-04 11:11:07', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('807BBC687CD8191305D754F2939F94C5', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{node=root, sort=[{\"property\":\"order\",\"direction\":\"ASC\"}], name=, _dc=1507086759736}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'baseinfo', '01', '52', '2017-10-04 11:12:39', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('10B72214E00AA1C41AA8569D62ABD082', '192.168.0.222', '192.168.0.222', '1', '用户:超级管理员(root)退出登录!SESSIONID:A8028181EB6E9F9570D1723FC94BE65C', 'logout', '09', '6', '2017-10-04 11:13:02', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('96B01B1E56EBEAE1ECB14618B3E96B21', '192.168.0.222', '192.168.0.222', '1', '用户:超级管理员(root)登录成功!SESSIONID:7CD49FBC32B8015D85ADC0A04E5A1EAE,登录入口:本系统', 'login', '09', '4', '2017-10-04 11:13:21', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('3BF9FF6FC9AD4526F4E9E2C3D08022C3', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '22', '2017-10-04 11:13:22', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('473055E8A55EAE84848A33D223F825D5', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '17', '2017-10-04 11:13:30', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('FD0C3EA8E3346D45ED0E6C0BAE7662EF', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '11', '2017-10-04 11:13:34', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('EDF3C92307ACBCDAB0D7FBC7660843AD', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2017-10-04 11:13:41', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D037CEFD978FA9B266414D7F344AFEFA', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '11', '2017-10-04 11:13:44', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('453237B7693BEF1BB643816BDBB2D613', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2017-10-04 11:13:47', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('5391D6B6074CDEC52450E1D2CE5CCF18', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '20', '2017-10-04 11:13:57', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('EC4D16FC49B918E6B85194AAA80AF27B', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '12', '2017-10-04 11:14:02', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('50A47ACA997E6F6A050C34FDFA32F889', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '24', '2017-10-04 11:14:04', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('027A00D150DE6FB00025BEC57B073D95', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '12', '2017-10-04 11:14:14', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('2ED6EBEB3B247E0EA13C8A580F675A22', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '28', '2017-10-04 11:14:23', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D5A282365F1B033940AAF748FBE948ED', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:null],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '18', '2017-10-04 11:14:24', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('AC134E3A264554700CB62735397F47C4', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '11', '2017-10-04 11:15:03', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('EA3EAFB0D7BFD44D7311B1F390A42D86', '192.168.0.222', '192.168.0.222', '1', '操作类型:[更新],参数:[{menuExpandDepth=0, titleWordNum=5, isShowSubNavi=1, portalId=default_page, desktopSrc=personaldesktop/desktop.jsp, timeBeforeMenuAutoHide=150, type=startup, portalPageSrc=/pages/personaldesktop/desktop.jsp, closeable=1, enableColumnHide=1, mainMenusNum=5, showMenuNum=5, requestOnlineUserInterval=0, isShowNotice=0, portalName=default_iframe, showUserGuide=0, desktopId=desktop_iframe, theme=_ALL_, menuQuickSearch=1, desktopName=desktop_iframe, floderStrong=0, showDesktopInWindow=1, portalNameCn=个人桌面, userGuideName=用户指南.docx, isShowHistroy=1, defaultPageSize=20, tabsNum=10}],目标:[更新],自定义信息:[成功!],结果:[null]', 'unconfigured', '03', '87', '2017-10-04 11:15:32', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('1950D4BA21F7DB5D8E84D16994932FF3', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '12', '2017-10-04 11:15:35', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('C188AFDBDE7F78B81C412597C9328F3F', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '26', '2017-10-04 11:15:55', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('09A7C876F1B32417294A3FF3D9FC95A3', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '12', '2017-10-04 11:16:15', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('129301988A1DFAC3160551BC361FD400', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2017-10-04 11:16:23', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('F85CA6EB2FA90BD037C18CD2C69F2F13', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '20', '2017-10-04 11:16:38', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('1FD35110EB25F290E41F5F6718ED7BCE', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2017-10-04 11:17:26', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D73EA63AFD6084DE80D00A8560B29107', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2017-10-04 11:18:18', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A91CCCFAAAFCB733D7D0FE3FAAAD0B0C', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '24', '2017-10-04 11:18:21', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('B270531F57D83D9DB8B0EC9EDEA86B32', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '450', '2017-10-04 11:18:26', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('C82C3B5F533C9D65531A4802CC701262', '127.0.0.1', '127.0.0.1', '1', '操作类型:[查询],参数:[开始时间:null,结束时间:null,模块名称:null,操作状态:null,IP地址:null,用户级别:null,用户名称:null,操作内容:null],目标:[查询],结果条数:[65],结果:[成功!]', 'unconfigured', '01', '43', '2017-10-04 11:20:16', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('E006E6733BFEBCE8C831E7EDEFACA563', '127.0.0.1', '127.0.0.1', '1', '操作类型:[查询],参数:[{limit=100, sort=[{\"property\":\"groupTime\",\"direction\":\"DESC\"}], start=0, page=1, readFlag=unRead, _dc=1507087230229, group=[{\"property\":\"groupTime\",\"direction\":\"DESC\"}]}],目标:[查询],结果条数:[0],自定义信息:[成功],结果:[null]', 'unconfigured', '01', '64', '2017-10-04 11:20:30', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('9C23113B42D733C2104E9F28287DA208', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:null],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '28', '2017-10-04 11:24:50', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('00853A0BE501A8A37C340750EE1ED648', '192.168.0.222', '192.168.0.222', '1', '用户:超级管理员(root)退出登录!SESSIONID:7CD49FBC32B8015D85ADC0A04E5A1EAE', 'logout', '09', '12', '2017-10-04 11:45:33', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('6FF02A918FA924FFCA8D334F777B853B', '192.168.0.222', '192.168.0.222', '1', '用户:超级管理员(root)登录成功!SESSIONID:931BF2C8689D5F0DB358976723D06C10,登录入口:本系统', 'login', '09', '572', '2017-10-04 13:58:09', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('9204A1FF713757F04C0C1D8DA998BF4D', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '22', '2017-10-04 13:58:10', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('870E171E5831C37EBEB3D907B30F0787', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '25', '2017-10-04 13:59:14', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('228795E23C6BF03515ED42654BC7714C', '192.168.0.222', '192.168.0.222', '1', '操作类型:[查询],参数:[关键字:null],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '38', '2017-10-04 13:59:20', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('C5E450F4E3AE2A6F600CF209E565F579', '192.168.11.223', '192.168.11.223', '1', '用户:超级管理员(root)登录成功!SESSIONID:986DA8C6B078A5638D07858E65E42F41,登录入口:本系统', 'login', '09', '7', '2017-12-21 22:18:47', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('80CBC72D04844ACE1F1B998B46484207', '192.168.11.223', '192.168.11.223', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '35', '2017-12-21 22:18:49', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('E6AFC01A8701A28190A521178CD17CA0', '192.168.11.223', '192.168.11.223', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '45', '2017-12-21 22:19:17', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('35F06D04BEED8ECF9B371D6CCA6D8D72', '192.168.11.223', '192.168.11.223', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '18', '2017-12-21 22:19:31', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('46BB7C47704FDF6534CA60DA113AFA54', '192.168.11.222', '192.168.11.222', '1', '用户:超级管理员(root)登录成功!SESSIONID:5C6B3364556B3DD21B0A4E99F1F9A902,登录入口:本系统', 'login', '09', '8', '2017-12-22 14:47:47', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('E63EDF37450A53C40D1439DE04B098E2', '192.168.11.222', '192.168.11.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '24', '2017-12-22 14:47:48', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('4AEBF8AA30EEE8798C5DE5B0CF1DF4F3', '192.168.11.222', '192.168.11.222', '1', '用户:超级管理员(root)退出登录!SESSIONID:5C6B3364556B3DD21B0A4E99F1F9A902', 'logout', '09', '0', '2017-12-22 15:09:22', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('B6B84149E51933C0BEC5168308DF545B', '192.168.11.222', '192.168.11.222', '1', '用户:超级管理员(root)登录成功!SESSIONID:0D3FB8948D976C9E775F1876BC094EC1,登录入口:本系统', 'login', '09', '3', '2017-12-22 17:32:22', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('60C71698E6B037A6149963B1311A3E8F', '192.168.11.222', '192.168.11.222', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '42', '2017-12-22 17:32:22', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D1DC621A5105433ABA7EABE6A6D5E61F', '192.168.11.222', '192.168.11.222', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '54', '2017-12-22 17:32:29', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('5BEEB4587E3EC8B660A90CC41D14E24C', '192.168.11.222', '192.168.11.222', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '20', '2017-12-22 17:33:00', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('5D8EF527F072361AC5526652A0CFD18C', '192.168.11.102', '192.168.11.102', '1', '用户:超级管理员(root)登录成功!SESSIONID:D5E0FB6D2848619930D0A83394DCAD22,登录入口:本系统', 'login', '09', '8', '2017-12-23 23:47:42', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('FD0748F9248C0FDFB7C808788CA22CE3', '192.168.11.102', '192.168.11.102', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '32', '2017-12-23 23:47:44', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('1185D7D7EB40135E7E2EBD6505CB64EF', '192.168.11.102', '192.168.11.102', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '44', '2017-12-23 23:48:28', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('939519E4A82FD33E3A521FBF0C340942', '192.168.11.102', '192.168.11.102', '1', '用户:超级管理员(root)登录成功!SESSIONID:D25B4F4F7D87414E6DCD030302E60F07,登录入口:本系统', 'login', '09', '6', '2017-12-24 00:06:46', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('977336380E59D0BE8E7A9507349724EF', '192.168.11.102', '192.168.11.102', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '28', '2017-12-24 00:06:48', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('2B3D048FE275F56A5842C1739F3932B8', '192.168.11.102', '192.168.11.102', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '43', '2017-12-24 00:06:54', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D0443E777D511113EF63E33E09E38CF0', '192.168.11.102', '192.168.11.102', '1', '用户:超级管理员(root)退出登录!SESSIONID:D25B4F4F7D87414E6DCD030302E60F07', 'logout', '09', '6', '2017-12-24 00:06:59', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A3C0A4542C7DA32EF3CA36EF6DF29ACD', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:001ABC809543A717E67AA98C02FDD0C5,登录入口:本系统', 'login', '09', '13', '2017-12-25 10:34:09', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('F0088AAFD8FDAB7F1652DAB9A0F284B7', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '18', '2017-12-25 10:34:10', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('7F92FD1929916E4862A8DCF5A951CE3D', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '33', '2017-12-25 10:34:27', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('53C7F9E9BECF1420C74328306269AEB2', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '17', '2017-12-25 10:34:35', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('070027AC9FA2F7995DD1A761D885CA63', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)退出登录!SESSIONID:001ABC809543A717E67AA98C02FDD0C5', 'logout', '09', '7', '2017-12-25 10:55:34', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('CF78DCD335CE2C857399928E0996E971', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:6686D8CBAF724D6C0C4FD745741A0632,登录入口:本系统', 'login', '09', '3', '2017-12-25 14:17:11', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('26C5B0ED1EBA52A6EB58BCFD1C78823A', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '16', '2017-12-25 14:17:11', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('638D6292AEA2908A3B48E7B9DEF633CF', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '28', '2017-12-25 14:17:30', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('8A35C0E8A60036C87CA9AEEE7EBC1253', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '30', '2017-12-25 14:17:57', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('68EAD9BFA326B4423F1B7A517BD4F7BA', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '30', '2017-12-25 14:17:59', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('921D2EA76BC56CF23E15840839ED6F3A', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '23', '2017-12-25 14:18:08', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('2824E0FB3B744970683559749083F79A', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '27', '2017-12-25 14:18:11', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('0DB94FCC144531137D5788A375ACA6AB', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '24', '2017-12-25 14:18:24', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('BBED9A92D96A9BBB8F8C864DFE7EF479', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '25', '2017-12-25 14:18:34', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('DA22217FDC761266D30DEC2AF5E00F98', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '27', '2017-12-25 14:18:36', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('36FCCD85BFE5CF949AF888A25B9E8DEF', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '25', '2017-12-25 14:24:44', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D212C00535790F5F7051AA58D47605F8', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:3A86E0836F29739083B1530C6C2E8881,登录入口:本系统', 'login', '09', '10', '2017-12-25 15:45:11', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('ACEBDC8E4BAF5F0699822B7962C6CBA0', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '22', '2017-12-25 15:45:12', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('E7ECD3A9A820853E6009D2B633F053DC', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '28', '2017-12-25 15:47:25', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('2C56864AEA10CFA02754E72DB905C77D', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[开始时间:null,结束时间:null,模块名称:null,操作状态:null,IP地址:null,用户级别:null,用户名称:null,操作内容:null],目标:[查询],结果条数:[110],结果:[成功!]', 'unconfigured', '01', '45', '2017-12-25 15:47:32', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('3DF885980A654B1FB7C671C988FD914C', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '9', '2017-12-25 15:47:40', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('360C619FF6066730F36AF268EA1967E3', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{limit=100, sort=[{\"property\":\"groupTime\",\"direction\":\"DESC\"}], page=1, start=0, readFlag=unRead, _dc=1514188066134, group=[{\"property\":\"groupTime\",\"direction\":\"DESC\"}]}],目标:[查询],结果条数:[0],自定义信息:[成功],结果:[null]', 'unconfigured', '01', '85', '2017-12-25 15:47:46', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('EE1728234D665BA3BD03BB6ACB8D8D17', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{node=root, sort=[{\"property\":\"order\",\"direction\":\"ASC\"}], name=, _dc=1514188070521}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'baseinfo', '01', '76', '2017-12-25 15:47:50', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A09F00D0C550BC826756527E300F8D2C', '192.168.11.100', '192.168.11.100', '1', '新增资源【应用功能】成功！', 'resourcemng', '02', '42', '2017-12-25 16:27:35', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('C3E6A2969899D63D684BBC91AA4C5B20', '192.168.11.100', '192.168.11.100', '1', '新增资源【打赏详细信息查看】成功！', 'resourcemng', '02', '31', '2017-12-25 16:28:15', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('98830FBC963CDAE7CE329EC2A96A2695', '192.168.11.100', '192.168.11.100', '1', '操作类型:[授权],参数:[名称:dxadmin,新增资源:应用功能,打赏详细信息查看,删除资源:],目标:[授权],结果:[成功!]', 'rolemng', '08', '56', '2017-12-25 16:28:28', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('597727D3A64E81220F1D3C41487C3D7F', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2017-12-25 16:28:33', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('93A5FEEDB7507ADDF11B8286E99AB44D', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:3A86E0836F29739083B1530C6C2E8881,登录入口:本系统', 'login', '09', '2', '2017-12-25 16:28:39', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('7B7629522818DBC68AFF2F2D136A9D77', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '11', '2017-12-25 16:28:39', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('85985A6D261D0578FBBB183D8FD923E5', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '30', '2017-12-25 16:30:20', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('6FC0C8A0EEFC50B369966279E9CEB5C7', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)退出登录!SESSIONID:3A86E0836F29739083B1530C6C2E8881', 'logout', '09', '0', '2017-12-25 16:50:58', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('2D3798780533441E2899B445E44F32E9', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:1DCB6A57971FADBFCBE61A443E39560D,登录入口:本系统', 'login', '09', '6', '2017-12-25 17:00:44', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('039A8F52789BD7223C8035578AC84449', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '10', '2017-12-25 17:00:44', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('7D68DCAC5822E70E17028EBF8C786314', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '19', '2017-12-25 17:09:50', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('BB517E96D1C96F9E92CFFDD8E18FB09F', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2017-12-25 17:10:27', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('2FB2DFC7D28DEC091919D0C28F106C0B', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2017-12-25 17:12:30', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D10F7AB049341E144515D58384DF0CFA', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '23', '2017-12-25 17:31:23', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('5F65D110EFAF0B4CF2B007F031089500', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '23', '2017-12-25 17:31:29', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('4A2C3595F4D790C90A1EF8BE2FF9F8EF', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[是否全部:true,关键字:null,类型:null,开始时间:null,结束时间:null],目标:[查询],结果条数:[0],结果:[成功!]', 'unconfigured', '01', '70', '2017-12-25 17:31:32', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('50D69535D50B43EF3F0A18E30AD12F6E', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{node=root, sort=[{\"property\":\"order\",\"direction\":\"ASC\"}], name=, _dc=1514194296018}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'baseinfo', '01', '21', '2017-12-25 17:31:36', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('04FB3C3DAD6E814E755EDA3B39C07999', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{limit=100, sort=[{\"property\":\"groupTime\",\"direction\":\"DESC\"}], page=1, start=0, readFlag=unRead, _dc=1514194298744, group=[{\"property\":\"groupTime\",\"direction\":\"DESC\"}]}],目标:[查询],结果条数:[0],自定义信息:[成功],结果:[null]', 'unconfigured', '01', '29', '2017-12-25 17:31:38', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('8A5CECDF8F41D4F18A5833935359A8D8', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[开始时间:null,结束时间:null,模块名称:null,操作状态:null,IP地址:null,用户级别:null,用户名称:null,操作内容:null],目标:[查询],结果条数:[132],结果:[成功!]', 'unconfigured', '01', '31', '2017-12-25 17:31:40', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('E5E3781E444CFB208E9F2854CF12E1D5', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:A480EFC57EEABB4941853DDA59794E8E,登录入口:本系统', 'login', '09', '0', '2017-12-25 17:51:17', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('23A5DF6F1AA508D3436B211AB76D65D4', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '0', '2017-12-25 17:51:17', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('39D7D7A17B6E13FA569BB473D1A076FB', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:9A5FB4244E483F6902A23FFE0469AC81,登录入口:本系统', 'login', '09', '12', '2017-12-26 09:38:59', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('448337FB8BF3A8114ADD686C1E2E02C1', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '28', '2017-12-26 09:39:01', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('34A258F1107F1C1B6317A4F94A59E214', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:742711CE520878EAA4EF832434F2AA19,登录入口:本系统', 'login', '09', '4', '2017-12-26 09:41:57', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('1CBF9422AA8678E70E7C3A9C13FF3948', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '24', '2017-12-26 09:41:57', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A701653DB2908420C7D9A23D162F10F2', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '8', '2017-12-26 09:43:51', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('C083905FD55D894290801B15F206CE95', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '12', '2017-12-26 09:44:56', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('59A603BABDDA1C7CB33C8D4C59F6CD21', '192.168.11.100', '192.168.11.100', '1', '操作类型:[更新],参数:[{menuExpandDepth=0, titleWordNum=5, isShowSubNavi=1, portalId=default_page, desktopSrc=personaldesktop/desktop.jsp, timeBeforeMenuAutoHide=150, type=startup, portalPageSrc=/pages/personaldesktop/desktop.jsp, closeable=1, enableColumnHide=1, mainMenusNum=5, showMenuNum=5, requestOnlineUserInterval=0, isShowNotice=0, portalName=default_iframe, showUserGuide=0, desktopId=desktop_iframe, theme=neptune, menuQuickSearch=1, desktopName=desktop_iframe, floderStrong=0, showDesktopInWindow=1, portalNameCn=个人桌面, userGuideName=用户指南.docx, tabsNum=10, defaultPageSize=20, isShowHistroy=1}],目标:[更新],自定义信息:[成功!],结果:[null]', 'unconfigured', '03', '36', '2017-12-26 09:45:32', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('26BAC448CDE0B8CED04756FA2C4ABC04', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:8B3D88FA6894B1011CFD71151B2823F0,登录入口:本系统', 'login', '09', '9', '2017-12-26 12:10:20', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('685F5722E303DF4562612894E2D739AD', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '29', '2017-12-26 12:10:22', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('9CA38C4E2D75B22BCFC672091095B2C8', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)退出登录!SESSIONID:8B3D88FA6894B1011CFD71151B2823F0', 'logout', '09', '10', '2017-12-26 12:32:11', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('F5295433D4D422264D7E74E8433588D2', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:2D7AA416B6ECDD7CF631EB7FA355D7AE,登录入口:本系统', 'login', '09', '8', '2017-12-26 13:07:54', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('B8CB0ABDFAB67A122CE2CCC3E52A9E21', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '18', '2017-12-26 13:07:55', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('855E418259E9B66C892C9C634783A18A', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:A88FA74CE917890CA5A4E27883979C91,登录入口:本系统', 'login', '09', '10', '2017-12-26 13:11:02', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('61DD3EAA69D05513AFBE500A7E45E7FE', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '36', '2017-12-26 13:11:04', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('0794FD97D0EF4909755C99C1A6590F5E', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:DEB19EEA8BFA96AF767679577D486967,登录入口:本系统', 'login', '09', '10', '2017-12-26 13:24:05', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A7A40115F1F9E86D5C9F90B89FFB2439', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '30', '2017-12-26 13:24:06', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('501F66C8BD0C982E7C0646569E4EEEA9', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)退出登录!SESSIONID:DEB19EEA8BFA96AF767679577D486967', 'logout', '09', '9', '2017-12-26 13:45:48', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('19E7751AB91ADF5229A6FFD5E1E745D5', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:308C4C6CDB785B46929D2671BB7A2C8B,登录入口:本系统', 'login', '09', '5', '2017-12-26 16:22:17', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('501C7E5B46037C68D0E4D6386BCE0032', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2017-12-26 16:22:18', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('38286FB2C901EC838AFA8A5F003ACDFB', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:35BC827DB69BA9CC6386FAE36E6DAF2E,登录入口:本系统', 'login', '09', '8', '2017-12-26 17:02:35', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('3B9884D50E189A53B3A94A8F9D1B2D92', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '33', '2017-12-26 17:02:37', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('44492C1FC230BA38EB38E58F89D83494', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:352CDAB8C008CE3985F415FDEB4E12D9,登录入口:本系统', 'login', '09', '7', '2017-12-26 17:06:52', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('C0AF6940E93BE307ECEF6B2926740A55', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '35', '2017-12-26 17:06:53', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('64D86F7A495043993CD3FE4416EDD288', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:95D4D84E1C7A855075B26423163DD0EF,登录入口:本系统', 'login', '09', '9', '2017-12-26 17:20:22', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('32F7E45E0527CC8BFAB4BD2F584B19C3', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '32', '2017-12-26 17:20:23', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('420230895B2AE96E7DCA0395D82F243A', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:B31785A67D09C3B0366B77CCCE540AC7,登录入口:本系统', 'login', '09', '8', '2017-12-26 17:22:54', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A30F4B2CCAE46DCDE3AC9B678BF41CBD', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:B31785A67D09C3B0366B77CCCE540AC7,登录入口:本系统', 'login', '09', '8', '2017-12-26 17:22:54', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('84A5333B06677846C09DF0BA4FA8029F', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '29', '2017-12-26 17:22:55', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('783AD233A4E60EACD50A47ADC1C79497', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:EE24A783151BF7AEAD2869ADB8B655DF,登录入口:本系统', 'login', '09', '12', '2017-12-26 17:29:17', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('59B6E8BAB554D84C5B2FA67F35844731', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '23', '2017-12-26 17:29:18', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('38438AECFD2471D6942C98358F9F7AE0', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '18', '2017-12-26 17:31:11', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('47D77C39BAAFB1E35D9BA352B7AA945A', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:054F52BF25FD37D793880C54D62FE1A8,登录入口:本系统', 'login', '09', '10', '2017-12-26 17:34:13', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('FD72918ED919F4466CD522F8BE6B9C25', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '37', '2017-12-26 17:34:14', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('AF1DC7ECDF774A59851B884AFF19A7A1', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:F4976C736DD723BD05B836036205621E,登录入口:本系统', 'login', '09', '8', '2017-12-26 17:39:27', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('0D777033EEF660C3DA97C90A85DEF28D', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '45', '2017-12-26 17:39:28', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('8DD528AD7E734CE3CB3C161859D5F917', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:1ED2621C83FED440F7629A7EC783A673,登录入口:本系统', 'login', '09', '9', '2017-12-26 17:42:13', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('5651010A41539850769CD3D5AE434843', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '24', '2017-12-26 17:42:14', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A6AD2933190FF730A2C9A638D7A81799', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:32DA3BD49BA8EF3698240347B0976108,登录入口:本系统', 'login', '09', '9', '2017-12-26 17:45:10', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('8499662292BB440246B58B506E41E9DE', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '29', '2017-12-26 17:45:11', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('E10699CC5519FF5FD25198893A41316A', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:01C33758D14CBB92E64B414F08AD3782,登录入口:本系统', 'login', '09', '7', '2017-12-26 17:47:18', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('FCC0DF01535B50AE65A34C08960D93F8', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '31', '2017-12-26 17:47:19', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('4F2BB6546A53312C4871C584225694B8', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:25320D12E147BA3DC616D0E1B8A1E4FB,登录入口:本系统', 'login', '09', '6', '2017-12-26 17:53:50', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('4CC6FDD73A6F4D13D49E2F78D2E01AC7', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '23', '2017-12-26 17:53:51', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('F7A7EB369DC49555B7D05DC9E46895B8', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:4F218A7AB42AA4D65973138F2FFE2015,登录入口:本系统', 'login', '09', '14', '2017-12-26 17:55:06', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('530A34FB4259564D9200AB84D895EF67', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '22', '2017-12-26 17:55:08', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('F688D1046F9891EE2C57429BAC0D6D6F', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:CB449C318420EEAB1204F278CCE69B2C,登录入口:本系统', 'login', '09', '9', '2017-12-26 17:57:33', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('BC28669C7EEF3377BA72CAF7975AF380', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '26', '2017-12-26 17:57:35', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('009E0DE37930DC7AB372E7B26727AE8B', '192.168.11.100', '192.168.11.100', '1', '用户:超级管理员(root)登录成功!SESSIONID:37061E5029E213858F5E5F322616A145,登录入口:本系统', 'login', '09', '10', '2017-12-26 18:00:24', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('101BAEBE7AB33C012E8EA16E0CF124FB', '192.168.11.100', '192.168.11.100', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '31', '2017-12-26 18:00:26', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('77A263E2DA542E672552E90BDA91FB0A', '192.168.11.102', '192.168.11.100', '1', '用户:超级管理员(root)退出登录!SESSIONID:37061E5029E213858F5E5F322616A145', 'logout', '09', '8', '2017-12-26 21:10:51', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('282D4794FD372D869657A3EB45CA1EC1', '192.168.11.179', '192.168.11.179', '1', '用户:超级管理员(root)登录成功!SESSIONID:F7CA3274BEDF3C72AF4CC22742DC02D6,登录入口:本系统', 'login', '09', '4', '2018-01-16 18:33:44', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('F7BF3D9D114A0FCD24D1DAAB755C20AF', '192.168.11.179', '192.168.11.179', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '16', '2018-01-16 18:33:46', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D69091D69D3EE447F550121A2768E4FD', '192.168.11.179', '192.168.11.179', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '59', '2018-01-16 18:35:36', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('B2591849435C730F1A64153294576C3F', '192.168.11.179', '192.168.11.179', '1', '用户:超级管理员(root)退出登录!SESSIONID:F7CA3274BEDF3C72AF4CC22742DC02D6', 'logout', '09', '9', '2018-01-16 18:56:16', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('5AD01F14A812FC3B5A0D9BCCCEE2CA34', '192.168.11.179', '192.168.11.179', '1', '用户:超级管理员(root)登录成功!SESSIONID:D8A40A6BF51C3DE5361F0297334D4B45,登录入口:本系统', 'login', '09', '7', '2018-01-16 19:51:37', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('03DDF1850BFB114E3C87899C8DB1F9D6', '192.168.11.179', '192.168.11.179', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '18', '2018-01-16 19:51:37', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A03DA346F0C096FB9F1362326558770A', '192.168.11.179', '192.168.11.179', '1', '新增资源【特殊提现】成功！', 'resourcemng', '02', '73', '2018-01-16 20:18:01', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('6BC59AC99751B285B86F47B252E25946', '192.168.11.179', '192.168.11.179', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '24', '2018-01-16 20:18:12', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('5A2E5F239912D8D7AB9F2C80944D9826', '192.168.11.179', '192.168.11.179', '1', '操作类型:[授权],参数:[名称:dxadmin,新增资源:特殊提现,删除资源:],目标:[授权],结果:[成功!]', 'rolemng', '08', '70', '2018-01-16 20:18:25', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D3FD801F4BCB8F7673964339B3E2D052', '192.168.11.179', '192.168.11.179', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '14', '2018-01-16 20:18:29', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('AF98D1F7419ABDF87EC49C0AB6CBC087', '192.168.11.179', '192.168.11.179', '1', '用户:超级管理员(root)登录成功!SESSIONID:D8A40A6BF51C3DE5361F0297334D4B45,登录入口:本系统', 'login', '09', '4', '2018-01-16 20:18:35', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('B8135A02E90D597B628E6384CA8B68BF', '192.168.11.179', '192.168.11.179', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '14', '2018-01-16 20:18:36', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A7B7FFAFC62207466F791F18CA53D20E', '192.168.11.179', '192.168.11.179', '1', '用户:超级管理员(root)登录成功!SESSIONID:5B56A43F93CAC43056C92EEB651BD166,登录入口:本系统', 'login', '09', '3', '2018-01-16 20:19:57', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('981FD63716EC917EC30D3C977CFC2301', '192.168.11.179', '192.168.11.179', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '16', '2018-01-16 20:19:58', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A99FA3049BB9096417B007ECA87C8C72', '192.168.11.179', '192.168.11.179', '1', '用户:超级管理员(root)登录成功!SESSIONID:5B56A43F93CAC43056C92EEB651BD166,登录入口:本系统', 'login', '09', '2', '2018-01-16 20:21:06', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('6678B30DD6EF3641480297A36E20806B', '192.168.11.179', '192.168.11.179', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '11', '2018-01-16 20:21:07', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('9055E442D038A54C7EC5AC79B5DE60A1', '192.168.11.179', '192.168.11.179', '1', '新增资源【合伙人查看】成功！', 'resourcemng', '02', '28', '2018-01-16 20:34:41', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('392146B7AEBD153DFFDA3259273D0C97', '192.168.11.179', '192.168.11.179', '1', '操作类型:[授权],参数:[名称:dxadmin,新增资源:合伙人查看,删除资源:],目标:[授权],结果:[成功!]', 'rolemng', '08', '32', '2018-01-16 20:35:28', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('C535043E6CB801A3383B556627A0583F', '192.168.11.179', '192.168.11.179', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2018-01-16 20:35:37', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('3DB2FA2D005F8DD92CB91FC85B2CFD1F', '192.168.11.179', '192.168.11.179', '1', '用户:超级管理员(root)登录成功!SESSIONID:5B56A43F93CAC43056C92EEB651BD166,登录入口:本系统', 'login', '09', '2', '2018-01-16 20:35:42', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('340CF486CF69CFD74C950DF65D92FBA2', '192.168.11.179', '192.168.11.179', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '10', '2018-01-16 20:35:43', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('FD14AAE4AD35AD42CC37EFFE6A272A82', '192.168.11.103', '192.168.11.179', '1', '用户:超级管理员(root)退出登录!SESSIONID:5B56A43F93CAC43056C92EEB651BD166', 'logout', '09', '41', '2018-01-16 22:15:55', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('834556F8E81E03565ECAB16B2A703E34', '192.168.11.103', '192.168.11.103', '1', '用户:超级管理员(root)登录成功!SESSIONID:135AB6037CDD57664ED88903F0DC3278,登录入口:本系统', 'login', '09', '5', '2018-01-16 23:28:32', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('639C79F36A9467BFF425599365F4E70D', '192.168.11.103', '192.168.11.103', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '17', '2018-01-16 23:28:32', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('93E7A227BC93B42188EBC01E9FE787B3', '192.168.11.103', '192.168.11.103', '1', '新增资源【代理用户】成功！', 'resourcemng', '02', '30', '2018-01-16 23:29:12', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('F54491A9F7957C1D7EB742B9160668B6', '192.168.11.103', '192.168.11.103', '1', '操作类型:[授权],参数:[名称:dxadmin,新增资源:代理用户,删除资源:],目标:[授权],结果:[成功!]', 'rolemng', '08', '31', '2018-01-16 23:29:23', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('37A3B178A2F96DB9DE2B7598325A2044', '192.168.11.103', '192.168.11.103', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2018-01-16 23:29:29', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('C1ECC3632196245212EE0004A5BF071C', '192.168.11.103', '192.168.11.103', '1', '用户:超级管理员(root)登录成功!SESSIONID:135AB6037CDD57664ED88903F0DC3278,登录入口:本系统', 'login', '09', '3', '2018-01-16 23:29:33', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('8173DC1877FDE339AB10ADF4C6C3D381', '192.168.11.103', '192.168.11.103', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '9', '2018-01-16 23:29:34', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('97C0126B0964919DA9723FAF88BB0C35', '192.168.11.103', '192.168.11.103', '1', '用户:超级管理员(root)退出登录!SESSIONID:135AB6037CDD57664ED88903F0DC3278', 'logout', '09', '29', '2018-01-16 23:49:56', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('10A27F225DFEB53E69EEFD09F0C2D619', '192.168.11.205', '192.168.11.205', '1', '用户:超级管理员(root)登录成功!SESSIONID:41798B321B279F1D17602F48FC2758DA,登录入口:本系统', 'login', '09', '5', '2018-01-17 18:54:28', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('3ABB8C548C48833D25611F83CDCD6155', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '21', '2018-01-17 18:54:29', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('BA46D13D52A8729107084ABFB68CC548', '192.168.11.205', '192.168.11.205', '1', '新增资源【上传用户】成功！', 'resourcemng', '02', '46', '2018-01-17 18:55:32', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('7976CCFB0F35EFE743FA2185D51ABB1B', '192.168.11.205', '192.168.11.205', '1', '操作类型:[授权],参数:[名称:dxadmin,新增资源:上传用户,删除资源:],目标:[授权],结果:[成功!]', 'rolemng', '08', '39', '2018-01-17 18:55:42', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A687D784B37090E1E543F08B897E8C2C', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '16', '2018-01-17 18:55:46', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A5EF1C2E4BA78CC48C0011A150E07726', '192.168.11.205', '192.168.11.205', '1', '用户:超级管理员(root)登录成功!SESSIONID:41798B321B279F1D17602F48FC2758DA,登录入口:本系统', 'login', '09', '3', '2018-01-17 18:55:54', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('507E2C55151B95F2EDBB81490F69FC2E', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '11', '2018-01-17 18:55:55', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('8CC185BA0D922AB69624B8003D744CF2', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '27', '2018-01-17 18:59:36', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('754DEFE8A331F4CE5E80E9FC1D88E239', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '15', '2018-01-17 19:00:23', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('5E46C00C30B34616DACF88984C83408C', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[关键字:null],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '21', '2018-01-17 19:00:23', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('E3B1942A1C243685EE6748E8153442F7', '192.168.11.205', '192.168.11.205', '1', '用户:超级管理员(root)退出登录!SESSIONID:41798B321B279F1D17602F48FC2758DA', 'logout', '09', '9', '2018-01-17 19:21:16', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D74577E82C7E3DDB0D6BC589D24C6BFB', '192.168.11.205', '192.168.11.205', '1', '用户:超级管理员(root)登录成功!SESSIONID:746F3CE59CA15D9FF959D73EA89F7FA3,登录入口:本系统', 'login', '09', '5', '2018-01-17 20:24:29', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('681BE6208296A535DD8D37FCAD47D080', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '11', '2018-01-17 20:24:29', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('6EB442CD59749451F413DE4E0A4A8F0D', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '23', '2018-01-17 20:24:34', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A76C76EF3AFDC37E02685D8E3931AFDD', '192.168.11.205', '192.168.11.205', '1', '操作类型:[修改],参数:[名称:eastcom_sw,描述:平台开发厂商,中文名:狼烟丰起],目标:[名称:eastcom_sw,描述:平台开发厂商,中文名:狼烟丰起],结果:[成功!]', 'departmentmng', '03', '40', '2018-01-17 20:25:04', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D3F3400F8B385ABCB35A08E70831675D', '192.168.11.205', '192.168.11.205', '1', '操作类型:[修改],参数:[名称:null,中文名称:狼烟丰起admin,详情:],目标:[修改],结果:[成功!]', 'rolemng', '03', '33', '2018-01-17 20:25:39', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('84BB4E3C25A716DA86D3546A9D32FBED', '192.168.11.205', '192.168.11.205', '1', '操作类型:[新增],参数:[名称:partner,中文名称:合伙人,详情:],目标:[新增],结果:[成功!]', 'rolemng', '02', '21', '2018-01-17 20:26:02', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('329AADBE06131C922818F463FFEDA4F7', '192.168.11.205', '192.168.11.205', '1', '操作类型:[新增],参数:[名称:agency,中文名称:代理,详情:],目标:[新增],结果:[成功!]', 'rolemng', '02', '24', '2018-01-17 20:26:14', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D497C6C2DA049BF9109F593992A09C04', '192.168.11.205', '192.168.11.205', '1', '操作类型:[新增],参数:[名称:uploader,中文名称:上传用户,详情:],目标:[新增],结果:[成功!]', 'rolemng', '02', '22', '2018-01-17 20:26:35', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('0F8B040FADAB4EACDF7DC1CFBB05C089', '192.168.11.205', '192.168.11.205', '1', '操作类型:[授权],参数:[名称:dxadmin,新增资源:,删除资源:Portal管理,Portal工具管理,查找,添加,删除,修改,个人公告管理,系统公告管理,新增公告,编辑公告,生效/终止公告,删除公告],目标:[授权],结果:[成功!]', 'rolemng', '08', '92', '2018-01-17 20:29:20', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('8E4CBA03876A0BBDD705CB9DF51F10DE', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '14', '2018-01-17 20:29:23', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('81DA72C09D7F1A7F3600B5E9CF5058F3', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[开始时间:null,结束时间:null,模块名称:null,操作状态:null,IP地址:null,用户级别:null,用户名称:null,操作内容:null],目标:[查询],结果条数:[236],结果:[成功!]', 'unconfigured', '01', '78', '2018-01-17 20:29:29', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D6D3CCFBD300797074D40B53A335AEA2', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[{}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '8', '2018-01-17 20:35:15', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('6BC70E4F7442CE595FACE2F6163EE9B1', '192.168.11.205', '192.168.11.205', '1', '操作类型:[更新],参数:[{menuExpandDepth=0, titleWordNum=5, isShowSubNavi=1, portalId=default_page, desktopSrc=personaldesktop/desktop.jsp, timeBeforeMenuAutoHide=150, type=startup, portalPageSrc=/pages/personaldesktop/desktop.jsp, closeable=1, enableColumnHide=1, mainMenusNum=5, showMenuNum=5, requestOnlineUserInterval=0, isShowNotice=0, portalName=default_iframe, showUserGuide=0, desktopId=desktop_iframe, theme=neptune, menuQuickSearch=1, desktopName=desktop_iframe, floderStrong=1, showDesktopInWindow=1, portalNameCn=个人桌面, userGuideName=用户指南.docx, isShowHistroy=0, defaultPageSize=20, tabsNum=10}],目标:[更新],自定义信息:[成功!],结果:[null]', 'unconfigured', '03', '58', '2018-01-17 20:35:44', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('04CA6CEBEBD2D1DF22AB5741C183585D', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '10', '2018-01-17 20:35:47', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('E2B0EB083B8779B69E531B90965EF458', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '10', '2018-01-17 20:36:27', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('498817887A89038B154D9E158DB50249', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '18', '2018-01-17 20:37:05', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('FEA401E8EB3A3102CA9978845177DF04', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[关键字:null],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '30', '2018-01-17 20:37:08', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('11EEC1A87DC4BA50C4DCC920F21503F8', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '14', '2018-01-17 20:37:19', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D54A92188DD4BFAAC347F960B56C82CA', '192.168.11.205', '192.168.11.205', '1', '操作类型:[查询],参数:[{}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '11', '2018-01-17 20:38:02', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('93DD8B5A033F99A285117A4CA7DC6FA0', '192.168.11.103', '192.168.11.205', '1', '用户:超级管理员(root)退出登录!SESSIONID:746F3CE59CA15D9FF959D73EA89F7FA3', 'logout', '09', '8', '2018-01-17 23:29:46', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('905504515B308A4738DD3750B521814B', '192.168.11.101', '192.168.11.101', '1', '用户:超级管理员(root)登录成功!SESSIONID:EC79A449DBFC19E1A3EF5B4468021582,登录入口:本系统', 'login', '09', '8', '2018-01-29 23:35:40', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('43206B8E6EC94D65A799802B4ED4720A', '192.168.11.101', '192.168.11.101', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '22', '2018-01-29 23:35:40', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('EE5FE94251C388BB52FF778BA4CA9C8C', '192.168.11.101', '192.168.11.101', '1', '新增资源【影视库管理】成功！', 'resourcemng', '02', '35', '2018-01-29 23:43:44', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('9179147727F9983B39CFF64047DD4696', '192.168.11.101', '192.168.11.101', '1', '操作类型:[授权],参数:[名称:dxadmin,新增资源:影视库管理,删除资源:],目标:[授权],结果:[成功!]', 'rolemng', '08', '34', '2018-01-29 23:44:08', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('7DADC3127092E8CE1A71997ED60E08A9', '192.168.11.101', '192.168.11.101', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '15', '2018-01-29 23:44:13', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('CA48A0AD271BFDCA88DEA34B54C9D861', '192.168.11.101', '192.168.11.101', '1', '用户:超级管理员(root)登录成功!SESSIONID:EC79A449DBFC19E1A3EF5B4468021582,登录入口:本系统', 'login', '09', '6', '2018-01-29 23:44:22', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('4512825FE9E46704CB3E5FC61044AEEC', '192.168.11.101', '192.168.11.101', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '10', '2018-01-29 23:44:22', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A3A0060DC5427A2D4EB501695F56349F', '192.168.11.101', '192.168.11.101', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2018-01-29 23:46:45', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('524D9A99C8B2FF44C644F0DF0F2FABD4', '192.168.11.101', '192.168.11.101', '1', '新增资源【域名管理】成功！', 'resourcemng', '02', '29', '2018-01-29 23:50:51', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('091F66DCB203D6B6C95041BE77BDCD8E', '192.168.11.101', '192.168.11.101', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '14', '2018-01-29 23:50:55', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('8C363B95925715B0D7BB12400E665EE8', '192.168.11.101', '192.168.11.101', '1', '操作类型:[授权],参数:[名称:dxadmin,新增资源:域名管理,删除资源:],目标:[授权],结果:[成功!]', 'rolemng', '08', '27', '2018-01-29 23:51:15', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('FDCF9A545710BD1B093859161674E26E', '192.168.11.101', '192.168.11.101', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '13', '2018-01-29 23:51:21', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('93209BACAA65A871D650BEAC2A358295', '192.168.11.101', '192.168.11.101', '1', '用户:超级管理员(root)登录成功!SESSIONID:EC79A449DBFC19E1A3EF5B4468021582,登录入口:本系统', 'login', '09', '3', '2018-01-29 23:51:33', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('343AD54337995AB2CB05034FAC5562D7', '192.168.11.101', '192.168.11.101', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '12', '2018-01-29 23:51:34', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('828A9170D0EC1A576D9D90FD506F5E4D', '192.168.11.101', '192.168.11.101', '1', '新增资源【IP库管理】成功！', 'resourcemng', '02', '33', '2018-01-29 23:55:17', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('37410C4BCE6D89884848B9CFCB6F15F3', '192.168.11.101', '192.168.11.101', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '17', '2018-01-29 23:55:23', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('A113B9210C7AD3CF41FCAC07AE21D6F2', '192.168.11.101', '192.168.11.101', '1', '操作类型:[授权],参数:[名称:dxadmin,新增资源:IP库管理,删除资源:],目标:[授权],结果:[成功!]', 'rolemng', '08', '24', '2018-01-29 23:55:40', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('881AF46E1E2BCFC2ACCB53AE5891A93C', '192.168.11.101', '192.168.11.101', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '11', '2018-01-29 23:55:44', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('99F01150D9BAB1F92C456894D826BC04', '192.168.11.101', '192.168.11.101', '1', '用户:超级管理员(root)登录成功!SESSIONID:EC79A449DBFC19E1A3EF5B4468021582,登录入口:本系统', 'login', '09', '2', '2018-01-29 23:55:56', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('731889BC63A744A617088E4CE48D7D56', '192.168.11.101', '192.168.11.101', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '8', '2018-01-29 23:55:56', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('41F05556410B658D713BCF208E165B10', '192.168.11.101', '192.168.11.101', '1', '用户:超级管理员(root)退出登录!SESSIONID:EC79A449DBFC19E1A3EF5B4468021582', 'logout', '09', '16', '2018-01-31 23:31:44', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('973B0B6E98D0ABDD6617230BACB6E3AA', '192.168.43.86', '192.168.43.86', '1', '用户:超级管理员(root)登录成功!SESSIONID:E18D9F4BD909ED5BB0DF696F4EFDE2F4,登录入口:本系统', 'login', '09', '6', '2018-02-16 15:24:52', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('69928E3EEF09E46543DD05A25518A22C', '192.168.43.86', '192.168.43.86', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '45', '2018-02-16 15:24:55', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('81AC46CAD9228699D7B433114B13364B', '192.168.43.86', '192.168.43.86', '1', '用户:超级管理员(root)退出登录!SESSIONID:E18D9F4BD909ED5BB0DF696F4EFDE2F4', 'logout', '09', '0', '2018-02-16 15:46:23', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('2701F6A4E55E0F974D47A08BDEA84D84', '192.168.0.123', '192.168.0.123', '1', '用户:超级管理员(root)登录成功!SESSIONID:65BFA74073E311BB04405782324CA0D2,登录入口:本系统', 'login', '09', '8', '2018-04-04 20:00:22', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('2EA383E64747EFE86E60BB431F0CEF59', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '25', '2018-04-04 20:00:24', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('1D9D062C5D420DC95E2075D897666086', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '48', '2018-04-04 20:00:56', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('9E286F3BD49895C18C73FED33BAC5888', '192.168.0.123', '192.168.0.123', '1', '操作类型:[修改],参数:[名称:null,中文名称:管理员,详情:],目标:[修改],结果:[成功!]', 'rolemng', '03', '32', '2018-04-04 20:01:22', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('368A532E147CCD92B718F53BE660DA9B', '192.168.0.123', '192.168.0.123', '1', '操作类型:[修改],参数:[用户名:root,中文名:超级管理员,电子邮箱:,手机号码:13912345678,用户级别:超级管理员,性别:1],目标:[修改],结果:[成功!]', 'unconfigured', '08', '77', '2018-04-04 20:01:48', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('ACB74D1768C7D82DEE6FBB4ACEA9E44E', '192.168.0.123', '192.168.0.123', '1', '操作类型:[修改],参数:[用户名:root,中文名:超级管理员,电子邮箱:,手机号码:13912345678,用户级别:超级管理员,性别:1],目标:[修改],结果:[成功!]', 'unconfigured', '08', '59', '2018-04-04 20:02:14', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('E6E88989B5FCB3B385D41A85ED0B11C6', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[关键字:null],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '35', '2018-04-04 20:02:21', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('1B97DF69BB753B985E6B434FAB944CE4', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '17', '2018-04-04 20:19:33', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('3E9A8DB2E1E480FA9412B597B8EA8705', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '12', '2018-04-04 20:23:06', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('15F3D772EFACC86D05F54E6FBB87B997', '192.168.0.123', '192.168.0.123', '1', '用户:超级管理员(root)退出登录!SESSIONID:65BFA74073E311BB04405782324CA0D2', 'logout', '09', '30', '2018-04-04 20:43:37', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('F1B4799ED695F0820200F035FB24124B', '192.168.0.123', '192.168.0.123', '1', '用户:超级管理员(root)登录成功!SESSIONID:DB2FDA89ABDB1E72A1C1674A0698294C,登录入口:本系统', 'login', '09', '5', '2018-04-04 21:39:39', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('FCF1BD80F73E0103F2DF3CC72C4BE0F0', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '17', '2018-04-04 21:39:40', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('FC2613B13D196A886B3B26CB5400A3CE', '192.168.0.123', '192.168.0.123', '1', '用户:超级管理员(root)退出登录!SESSIONID:DB2FDA89ABDB1E72A1C1674A0698294C', 'logout', '09', '45', '2018-04-04 22:00:40', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('7D893446DFB7470DFC544AC88D741248', '192.168.0.123', '192.168.0.123', '1', '用户:超级管理员(root)登录成功!SESSIONID:68283DA16BAEBA40E79B2F3FA1620BAE,登录入口:本系统', 'login', '09', '14', '2018-04-04 22:11:01', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('0ADB87EFCFAEFD282D82A4273BA88C32', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '35', '2018-04-04 22:11:02', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('AE7A4D071EA4569521C559F93E38F3D0', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[名称:,],目标:[查询],结果:[成功!]', 'unconfigured', '01', '18', '2018-04-04 22:11:38', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('9A995CFC170120F3BFE50C5B8A3D5208', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '25', '2018-04-04 22:11:52', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('D10EE46D67F66A8E4F3A054AC5A15892', '192.168.0.123', '192.168.0.123', '1', '用户:超级管理员(root)退出登录!SESSIONID:68283DA16BAEBA40E79B2F3FA1620BAE', 'logout', '09', '38', '2018-04-04 22:33:42', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('B41D731057BABE280D93246BAAF0E40C', '192.168.0.123', '192.168.0.123', '1', '用户:超级管理员(root)登录成功!SESSIONID:B21F8DDCCC0DB824C0A11C3FA04BD5D0,登录入口:本系统', 'login', '09', '13', '2018-04-05 10:01:38', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('877FCEC6215BA9BFA3FAA64149E20668', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '31', '2018-04-05 10:01:40', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('3E137E38BC89DF59AD0BE5B6694004D8', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '45', '2018-04-05 10:03:02', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('BE1112C8591B93615AA9F283405FB1EE', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[关键字:null],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '50', '2018-04-05 10:15:05', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('B0BA68D80C61AF339962F85AEBC9705A', '192.168.0.123', '192.168.0.123', '1', '用户:超级管理员(root)退出登录!SESSIONID:B21F8DDCCC0DB824C0A11C3FA04BD5D0', 'logout', '09', '40', '2018-04-05 10:35:25', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('B843F6A26CAD057D0F6EA1F44DBA5971', '192.168.0.123', '192.168.0.123', '1', '用户:超级管理员(root)登录成功!SESSIONID:574A9658DC6F30FEF8BD7758F9D88830,登录入口:本系统', 'login', '09', '10', '2018-04-13 03:52:27', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('5DEA6431FE887F2BCBAE28660332C1B1', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[{type=startup}],目标:[查询],结果条数:[1],自定义信息:[成功!],结果:[null]', 'unconfigured', '01', '36', '2018-04-13 03:52:29', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('361AD8B34BC84C34222DE193FC02C12A', '192.168.0.123', '192.168.0.123', '1', '操作类型:[查询],参数:[关键字:],目标:[查询],结果条数:[1],结果:[成功!]', 'unconfigured', '01', '41', '2018-04-13 03:53:01', 'root', null, null, null, null, null);
INSERT INTO `sys_logs` VALUES ('DE04BA90E1BDF47F59E614AB25518CFF', '192.168.0.123', '192.168.0.123', '1', '用户:超级管理员(root)退出登录!SESSIONID:574A9658DC6F30FEF8BD7758F9D88830', 'logout', '09', '123', '2018-04-13 04:22:10', 'root', null, null, null, null, null);

-- ----------------------------
-- Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` varchar(32) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `type_` varchar(10) DEFAULT NULL COMMENT '资源类别',
  `order_` int(11) DEFAULT NULL COMMENT '排序',
  `parent_id` varchar(32) DEFAULT NULL,
  `cls` varchar(100) DEFAULT NULL COMMENT '样式图标',
  `description` varchar(255) DEFAULT NULL COMMENT '资源描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8bwkb99txnsskncmlrljwqpby` (`value`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('00DABCCD45B47FB201A06D574E3F4431', 'agencyInfoMng', '账号管理', '/pages/agency/userInfoSet.jsp', '0', null, '', 'fa fa-cog', '');
INSERT INTO `sys_resource` VALUES ('078CF80CB6BB1A237D4FEA0BAB0BB62E', 'sysconfig', '系统配置', '/pages/sysconfig/sysconfig.jsp', '0', null, 'CBC681741355BCEA9B2EE6B3A6B665A2', 'fa fa-cog', '系统参数配置');
INSERT INTO `sys_resource` VALUES ('3571D662F726631829BBAA7F59985988', 'resourcemng', '资源管理', '/pages/resourcemng/resourceMng2.jsp', '0', null, 'CBC681741355BCEA9B2EE6B3A6B665A2', 'fa fa-cloud', '');
INSERT INTO `sys_resource` VALUES ('3C39144AAE6CB507982FD70DD0AABDDC', 'rewardDetail', '收入明细', '#', '1', null, '', 'fa fa-calendar', '收入详情');
INSERT INTO `sys_resource` VALUES ('3F199AD1B96EF88CB0AE160687DCA2C9', 'agencyDetail', '代理明细', '/pages/admin/agencyDetail.jsp', '0', null, '3C39144AAE6CB507982FD70DD0AABDDC', 'fa fa-user', '');
INSERT INTO `sys_resource` VALUES ('4C26B2F1372EB391D8FECDAC4D5C351F', 'videoMng', '视频管理', '/pages/videoMng/videoMng.jsp', '0', null, '', 'glyphicon glyphicon-facetime-video', '视频管理');
INSERT INTO `sys_resource` VALUES ('516844F6B62061ED0FCA9BFF4D2B0E5C', 'invitationCodeMng', '邀请码管理', '/pages/partner/invitationCodeMng.jsp', '0', null, '', 'fa fa-share-alt', '邀请码管理');
INSERT INTO `sys_resource` VALUES ('53502BAC4CBF4544FCD6AFB2C414636D', 'withdrawMng', '提现申请', '/pages/agency/withdrawMng.jsp', '0', null, '', 'fa fa-rmb', '提现管理');
INSERT INTO `sys_resource` VALUES ('53561DCEC000DCF0F2BB043ADB4214CB', 'invitecode4agency', '邀请码', '/pages/agency/invitecode4agency.jsp', '0', null, '', 'fa fa-share-alt', '邀请码-代理');
INSERT INTO `sys_resource` VALUES ('53FE31EE8556C0DB222018480FA3E14F', 'agencyMarketInfo', '推广详情', '/pages/agency/marketInfo.jsp', '0', null, '', 'fa fa-send', '');
INSERT INTO `sys_resource` VALUES ('643734B7EE667A64A7C6DE26F9A8A744', 'marketInfo', '推广详情', '/pages/partner/marketInfo.jsp', '0', null, '', 'fa fa-send', '合伙人推广明细');
INSERT INTO `sys_resource` VALUES ('7640923C500A50CD9A253FB0871828F3', 'ordermng', '订单详情', '/pages/orderinfo/orderinfo.jsp', '0', null, '3C39144AAE6CB507982FD70DD0AABDDC', 'icon_lightbulb', '工单管理');
INSERT INTO `sys_resource` VALUES ('78A2036A985EE9B6019BC8E247048849', 'createLink', '生成链接', '/pages/linkmng/createLink.jsp', '0', null, 'C18FAC88EA0A42BE14219C590352F727', 'fa fa-link', '生成链接');
INSERT INTO `sys_resource` VALUES ('8368B36CEDDC4BB5C540197B6B45BBBC', 'userInfoSet', '资料设置', '/pages/partner/userInfoSet.jsp', '0', null, '', 'fa fa-cog', '用户信息设置');
INSERT INTO `sys_resource` VALUES ('aaaaa', 'user', '用户管理', '/pages/usermng/userMng.jsp', '0', null, 'CBC681741355BCEA9B2EE6B3A6B665A2', 'fa fa-user', '');
INSERT INTO `sys_resource` VALUES ('AD6C5C0D78BE395753E67BA51F1340C1', 'orderinfo4agency', '打赏明细', '/pages/orderinfo/orderinfo4agency.jsp', '0', null, '', 'fa fa-bookmark', '代理用户打赏明细界面');
INSERT INTO `sys_resource` VALUES ('AE6993A1A62C86193D69CF09C1733F72', 'domainMng', '域名管理', '/pages/admin/domainMng.jsp', '0', null, '', 'fa fa-bullseye', '');
INSERT INTO `sys_resource` VALUES ('bbbbb', 'role', '角色管理', '/pages/rolemng/roleMng.jsp', '0', '0', 'CBC681741355BCEA9B2EE6B3A6B665A2', 'fa fa-rebel', 'test menu!');
INSERT INTO `sys_resource` VALUES ('C18FAC88EA0A42BE14219C590352F727', 'linksmng', '链接管理', '#', '1', null, '', 'fa  fa-bars', '');
INSERT INTO `sys_resource` VALUES ('CBC681741355BCEA9B2EE6B3A6B665A2', 'systemmng', '系统管理', '#', '1', null, '', 'fa fa-cogs', '系统管理总目录');
INSERT INTO `sys_resource` VALUES ('CCA4C311DA905F5B15BAFD2499093638', 'linkmng', '链接设置', '/pages/linkmng/linkmng.jsp', '0', null, 'C18FAC88EA0A42BE14219C590352F727', 'fa fa-link', '我的链接');
INSERT INTO `sys_resource` VALUES ('ccccc', 'resource', '资源管理', '/pages/resourcemng/resourceMng.jsp', '0', '0', 'CBC681741355BCEA9B2EE6B3A6B665A2', 'fa fa-cloud', 'test menu!');
INSERT INTO `sys_resource` VALUES ('E136B8E98B05145A7E0B0F964466C5DD', 'withdrawproc', '提现处理', '/pages/withdrawmng/withdrawmng.jsp', '0', null, '', 'fa fa-gavel', '提现处理');
INSERT INTO `sys_resource` VALUES ('FCD5B924697F8FDC980D401608D54124', 'partnerDetail', '合伙人明细', '/pages/admin/partnerDetail.jsp', '0', null, '3C39144AAE6CB507982FD70DD0AABDDC', 'fa fa-users  ', '');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('2989E02023072041B3D0542628EA3554', '系统管理员', 'system', '系统管理员');
INSERT INTO `sys_role` VALUES ('4BE44AC464FFCE79E15B1DF31F9DAB27', '代理', 'agencys', '代理');
INSERT INTO `sys_role` VALUES ('A31373B85770B3C64E571EB97776C058', '合伙人', 'partners', '合伙人');
INSERT INTO `sys_role` VALUES ('B0B78A22BAD49810EF120A9F05939114', '视频管理员', 'videoMngs', '视频管理');
INSERT INTO `sys_role` VALUES ('B0B78A22BAD49810EF120A9F05939115', '运营组', 'admins', '运营组');

-- ----------------------------
-- Table structure for `sys_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `role_id` varchar(32) NOT NULL,
  `resource_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`),
  KEY `FK_6ov1c9l6b3e2o9d3q889c90l` (`role_id`) USING BTREE,
  KEY `sys_role_resource_sys_resource_fk` (`resource_id`) USING BTREE,
  CONSTRAINT `sys_role_resource_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`),
  CONSTRAINT `sys_role_resource_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('2989E02023072041B3D0542628EA3554', '078CF80CB6BB1A237D4FEA0BAB0BB62E');
INSERT INTO `sys_role_resource` VALUES ('2989E02023072041B3D0542628EA3554', '3571D662F726631829BBAA7F59985988');
INSERT INTO `sys_role_resource` VALUES ('2989E02023072041B3D0542628EA3554', 'aaaaa');
INSERT INTO `sys_role_resource` VALUES ('2989E02023072041B3D0542628EA3554', 'bbbbb');
INSERT INTO `sys_role_resource` VALUES ('2989E02023072041B3D0542628EA3554', 'CBC681741355BCEA9B2EE6B3A6B665A2');
INSERT INTO `sys_role_resource` VALUES ('2989E02023072041B3D0542628EA3554', 'ccccc');
INSERT INTO `sys_role_resource` VALUES ('4BE44AC464FFCE79E15B1DF31F9DAB27', '00DABCCD45B47FB201A06D574E3F4431');
INSERT INTO `sys_role_resource` VALUES ('4BE44AC464FFCE79E15B1DF31F9DAB27', '53502BAC4CBF4544FCD6AFB2C414636D');
INSERT INTO `sys_role_resource` VALUES ('4BE44AC464FFCE79E15B1DF31F9DAB27', '53561DCEC000DCF0F2BB043ADB4214CB');
INSERT INTO `sys_role_resource` VALUES ('4BE44AC464FFCE79E15B1DF31F9DAB27', '53FE31EE8556C0DB222018480FA3E14F');
INSERT INTO `sys_role_resource` VALUES ('4BE44AC464FFCE79E15B1DF31F9DAB27', '78A2036A985EE9B6019BC8E247048849');
INSERT INTO `sys_role_resource` VALUES ('4BE44AC464FFCE79E15B1DF31F9DAB27', 'AD6C5C0D78BE395753E67BA51F1340C1');
INSERT INTO `sys_role_resource` VALUES ('4BE44AC464FFCE79E15B1DF31F9DAB27', 'C18FAC88EA0A42BE14219C590352F727');
INSERT INTO `sys_role_resource` VALUES ('4BE44AC464FFCE79E15B1DF31F9DAB27', 'CCA4C311DA905F5B15BAFD2499093638');
INSERT INTO `sys_role_resource` VALUES ('A31373B85770B3C64E571EB97776C058', '516844F6B62061ED0FCA9BFF4D2B0E5C');
INSERT INTO `sys_role_resource` VALUES ('A31373B85770B3C64E571EB97776C058', '643734B7EE667A64A7C6DE26F9A8A744');
INSERT INTO `sys_role_resource` VALUES ('A31373B85770B3C64E571EB97776C058', '8368B36CEDDC4BB5C540197B6B45BBBC');
INSERT INTO `sys_role_resource` VALUES ('B0B78A22BAD49810EF120A9F05939114', '4C26B2F1372EB391D8FECDAC4D5C351F');
INSERT INTO `sys_role_resource` VALUES ('B0B78A22BAD49810EF120A9F05939115', '078CF80CB6BB1A237D4FEA0BAB0BB62E');
INSERT INTO `sys_role_resource` VALUES ('B0B78A22BAD49810EF120A9F05939115', '3C39144AAE6CB507982FD70DD0AABDDC');
INSERT INTO `sys_role_resource` VALUES ('B0B78A22BAD49810EF120A9F05939115', '3F199AD1B96EF88CB0AE160687DCA2C9');
INSERT INTO `sys_role_resource` VALUES ('B0B78A22BAD49810EF120A9F05939115', '4C26B2F1372EB391D8FECDAC4D5C351F');
INSERT INTO `sys_role_resource` VALUES ('B0B78A22BAD49810EF120A9F05939115', '7640923C500A50CD9A253FB0871828F3');
INSERT INTO `sys_role_resource` VALUES ('B0B78A22BAD49810EF120A9F05939115', 'AE6993A1A62C86193D69CF09C1733F72');
INSERT INTO `sys_role_resource` VALUES ('B0B78A22BAD49810EF120A9F05939115', 'CBC681741355BCEA9B2EE6B3A6B665A2');
INSERT INTO `sys_role_resource` VALUES ('B0B78A22BAD49810EF120A9F05939115', 'E136B8E98B05145A7E0B0F964466C5DD');
INSERT INTO `sys_role_resource` VALUES ('B0B78A22BAD49810EF120A9F05939115', 'FCD5B924697F8FDC980D401608D54124');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `wechat` varchar(255) DEFAULT NULL COMMENT '微信号',
  `locked` varchar(2) DEFAULT '0',
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `invitation_code` varchar(100) DEFAULT NULL COMMENT '邀请码',
  `type_` varchar(32) DEFAULT NULL COMMENT '用户类型',
  `qq` varchar(18) DEFAULT NULL COMMENT 'QQ',
  `create_time` varchar(25) DEFAULT NULL COMMENT '创建时间',
  `parent_name` varchar(100) DEFAULT NULL COMMENT '上级用户',
  `fetch_pwd` varchar(100) DEFAULT NULL COMMENT '提现密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0944FB7F7D68E978BDA5996ECF97F53C', 'smile', null, 'root', 'root', null, '2989E02023072041B3D0542628EA3554', 'sunshine', '2018-05-12 08:41:38', 'admin', 'root');
INSERT INTO `sys_user` VALUES ('4AC18EA52E262026E1A79A709B1E31B6', null, null, 'test', 'partner02', null, 'A31373B85770B3C64E571EB97776C058', null, '2018-05-24 09:09:52', 'admin', null);
INSERT INTO `sys_user` VALUES ('57E2A8B2B3E3A6B6B5ABBD219FC1940B', 'xuzheng', null, 'shouwang', 'admin', '123', 'B0B78A22BAD49810EF120A9F05939115', '183265420', null, 'admin', 'admin');
INSERT INTO `sys_user` VALUES ('5D411F857ECEF04A233684D0CDA599BE', 'upload', null, 'test', 'upload', null, 'B0B78A22BAD49810EF120A9F05939114', '111', '2018-05-03 07:52:07', 'admin', 'test');
INSERT INTO `sys_user` VALUES ('CD8C6C5D02EF6462C6A944C0567961AE', null, null, 'test', 'agency02', '10I5v654Wb5I', '4BE44AC464FFCE79E15B1DF31F9DAB27', null, '2018-05-17 03:25:17', null, 'test');
INSERT INTO `sys_user` VALUES ('CF52F879AABA48E8A9536FE26A3CA611', 'smile', null, 'test', 'agency01', '108C27N9U676', '4BE44AC464FFCE79E15B1DF31F9DAB27', '10000', '2018-04-27 11:23:52', 'partner', 'test');
INSERT INTO `sys_user` VALUES ('DA40C5B28B9F74A0B6D0EC369D005AD6', null, null, 'test', 'agency03', '1EG750HS140b', '4BE44AC464FFCE79E15B1DF31F9DAB27', null, '2018-05-24 07:03:33', null, 'test');
INSERT INTO `sys_user` VALUES ('F5AC879038932BC1844FB1F3B66448A4', 'test', null, 'abc', 'partner', null, 'A31373B85770B3C64E571EB97776C058', '12345', '2018-04-27 10:56:18', 'test', 'test');
INSERT INTO `sys_user` VALUES ('F64D6EBBF764BF9A11A0889C3D760E76', null, null, 'test', 'agency0101', 'cdeF6779253M', '4BE44AC464FFCE79E15B1DF31F9DAB27', null, '2018-05-24 07:04:23', null, 'test');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_fxu3td9m5o7qov1kbdvmn0g0x` (`role_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('0944FB7F7D68E978BDA5996ECF97F53C', '2989E02023072041B3D0542628EA3554');
INSERT INTO `sys_user_role` VALUES ('CD8C6C5D02EF6462C6A944C0567961AE', '4BE44AC464FFCE79E15B1DF31F9DAB27');
INSERT INTO `sys_user_role` VALUES ('CF52F879AABA48E8A9536FE26A3CA611', '4BE44AC464FFCE79E15B1DF31F9DAB27');
INSERT INTO `sys_user_role` VALUES ('DA40C5B28B9F74A0B6D0EC369D005AD6', '4BE44AC464FFCE79E15B1DF31F9DAB27');
INSERT INTO `sys_user_role` VALUES ('F64D6EBBF764BF9A11A0889C3D760E76', '4BE44AC464FFCE79E15B1DF31F9DAB27');
INSERT INTO `sys_user_role` VALUES ('4AC18EA52E262026E1A79A709B1E31B6', 'A31373B85770B3C64E571EB97776C058');
INSERT INTO `sys_user_role` VALUES ('F5AC879038932BC1844FB1F3B66448A4', 'A31373B85770B3C64E571EB97776C058');
INSERT INTO `sys_user_role` VALUES ('5D411F857ECEF04A233684D0CDA599BE', 'B0B78A22BAD49810EF120A9F05939114');
INSERT INTO `sys_user_role` VALUES ('57E2A8B2B3E3A6B6B5ABBD219FC1940B', 'B0B78A22BAD49810EF120A9F05939115');
