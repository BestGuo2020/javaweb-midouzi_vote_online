/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : vote

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 29/01/2021 13:09:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名',
  `introduce` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '活动介绍',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '/user/common/default2.jpg' COMMENT '活动封面',
  `status` int(11) DEFAULT 0 COMMENT '活动状态，0未开始，1开始、2结束',
  `countVotes` int(11) DEFAULT 0 COMMENT '总票数',
  `contestantCount` int(11) DEFAULT 0 COMMENT '选手总数',
  `startTime` date NOT NULL COMMENT '活动开始时间',
  `stopTime` date NOT NULL COMMENT '活动结束时间',
  `userId` int(11) DEFAULT NULL COMMENT '用户id',
  `times` int(11) DEFAULT NULL COMMENT '最多投几次',
  `looks` int(11) UNSIGNED ZEROFILL DEFAULT 00000000000 COMMENT '浏览次数',
  PRIMARY KEY (`id`) USING BTREE COMMENT '活动表主键',
  INDEX `fk_activity_user_id`(`userId`) USING BTREE,
  CONSTRAINT `fk_activity_user_id` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (57, '“小手拉大手 用爱传递 为祖国加油” 公益活动', '', '/user/common/default2.jpg', 2, 11, 7, '2020-12-14', '2020-12-30', 1, 3, 00000000073);
INSERT INTO `activity` VALUES (58, '童舞堂首届独舞大赛网络评选234', '<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; color: #464e5f; font-family: Poppins, Helvetica, sans-serif; font-size: 13px; background-color: #ffffff;\"><span style=\"box-sizing: border-box; color: #d14841;\"><span style=\"box-sizing: border-box; font-weight: 600;\">2019年6月8号，童舞堂首届少儿独舞大赛在金融街.融御成功举行。本次大赛共收到326份初选视频，经过评选有65位学员晋级现场比赛。她们当中有在童舞堂学舞六年以上的大姐姐，也有刚来童舞堂一个学期的小妹妹，无论她们的表演是游刃有余还是稚嫩青涩，只要勇于表现自己，就已经成功了！请大家欣赏选手们精彩纷呈的表演，并投上您宝贵的一票为她们喝彩、加油！相信您的鼓励，一定会让孩子们更加努力与自信！</span></span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\'; min-height: 12px; font-size: 11px;\"><span style=\"box-sizing: border-box; font-weight: 600;\"><span style=\"box-sizing: border-box; color: #28324e; font-size: 13px;\">投票日期：2019年6月19日-6月25日</span></span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\'; min-height: 12px; font-size: 11px;\"><span style=\"box-sizing: border-box; font-weight: 600;\"><span style=\"box-sizing: border-box; color: #28324e; font-size: 13px;\">投票方式：每人每天可投一票</span></span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\'; font-size: 11px;\"><span style=\"box-sizing: border-box; font-weight: 600;\"><span style=\"box-sizing: border-box; color: #28324e; font-size: 13px;\">网络投票将根据得票数，由高到低依次角逐出以下奖项！</span></span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 11px; line-height: normal; font-family: \'Helvetica Neue\'; background-color: #ffffff; min-height: 12px;\">&nbsp;</p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; font-size: 13px; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\';\"><span style=\"box-sizing: border-box; color: #d14841;\"><span style=\"box-sizing: border-box; font-weight: 600;\">奖项设置：</span></span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; font-size: 13px; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\';\"><span style=\"box-sizing: border-box; color: #553982;\">最佳表演奖 共2名</span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; font-size: 13px; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\';\"><span style=\"box-sizing: border-box; color: #553982;\">最具潜力奖 共3名</span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; font-size: 13px; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\';\"><span style=\"box-sizing: border-box; color: #553982;\">最佳人气奖 共4名</span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; font-size: 13px; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\';\"><span style=\"box-sizing: border-box; color: #553982;\">最具台风奖 共5名</span></p>', '/user/1/activity/1608032914210.jpeg', 2, 0, 3, '2020-12-15', '2020-12-24', 1, 1, 00000000005);
INSERT INTO `activity` VALUES (59, '童舞堂首届独舞大赛网络评选233', '<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; color: #464e5f; font-family: Poppins, Helvetica, sans-serif; font-size: 13px; background-color: #ffffff;\"><span style=\"box-sizing: border-box; color: #d14841;\"><span style=\"box-sizing: border-box; font-weight: 600;\">2019年6月8号，童舞堂首届少儿独舞大赛在金融街.融御成功举行。本次大赛共收到326份初选视频，经过评选有65位学员晋级现场比赛。她们当中有在童舞堂学舞六年以上的大姐姐，也有刚来童舞堂一个学期的小妹妹，无论她们的表演是游刃有余还是稚嫩青涩，只要勇于表现自己，就已经成功了！请大家欣赏选手们精彩纷呈的表演，并投上您宝贵的一票为她们喝彩、加油！相信您的鼓励，一定会让孩子们更加努力与自信！</span></span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\'; min-height: 12px; font-size: 11px;\"><span style=\"box-sizing: border-box; font-weight: 600;\"><span style=\"box-sizing: border-box; color: #28324e; font-size: 13px;\">投票日期：2019年6月19日-6月25日</span></span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\'; min-height: 12px; font-size: 11px;\"><span style=\"box-sizing: border-box; font-weight: 600;\"><span style=\"box-sizing: border-box; color: #28324e; font-size: 13px;\">投票方式：每人每天可投一票</span></span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\'; font-size: 11px;\"><span style=\"box-sizing: border-box; font-weight: 600;\"><span style=\"box-sizing: border-box; color: #28324e; font-size: 13px;\">网络投票将根据得票数，由高到低依次角逐出以下奖项！</span></span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 11px; line-height: normal; font-family: \'Helvetica Neue\'; background-color: #ffffff; min-height: 12px;\">&nbsp;</p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; font-size: 13px; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\';\"><span style=\"box-sizing: border-box; color: #d14841;\"><span style=\"box-sizing: border-box; font-weight: 600;\">奖项设置：</span></span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; font-size: 13px; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\';\"><span style=\"box-sizing: border-box; color: #553982;\">最佳表演奖 共2名</span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; font-size: 13px; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\';\"><span style=\"box-sizing: border-box; color: #553982;\">最具潜力奖 共3名</span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; font-size: 13px; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\';\"><span style=\"box-sizing: border-box; color: #553982;\">最佳人气奖 共4名</span></p>\r\n<p style=\"box-sizing: border-box; margin: 0px; cursor: text; padding: 0px; counter-reset: list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0; font-size: 13px; background-color: #ffffff; font-stretch: normal; line-height: normal; font-family: \'Helvetica Neue\';\"><span style=\"box-sizing: border-box; color: #553982;\">最具台风奖 共5名</span></p>', '/user/common/default2.jpg', 2, 0, 0, '2020-12-15', '2020-12-24', 1, 1, 00000000008);

-- ----------------------------
-- Table structure for contestant
-- ----------------------------
DROP TABLE IF EXISTS `contestant`;
CREATE TABLE `contestant`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '选手表主键自增id',
  `contestantId` int(11) NOT NULL COMMENT '选手id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选手名',
  `introduce` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '选手介绍',
  `giveup` int(11) UNSIGNED ZEROFILL DEFAULT 00000000000 COMMENT '选手弃权：0不放弃，1放弃',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '/user/common/default2.jpg' COMMENT '选手图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of contestant
-- ----------------------------
INSERT INTO `contestant` VALUES (1, 1, '张三', '测试1', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (2, 2, '李四', '测试2', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (3, 3, '王五', '测试3', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (4, 1, '唐老鸭', '测试介绍2', 00000000001, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (5, 2, '杀马特团长2', '测试介绍2', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (6, 1, 'hehehe123', '<div>1111111111111</div>\r\n<div>2222222222222</div>\r\n<div>3333333333333</div>\r\n<div>4444444444444</div>', 00000000000, '/user/1/contestant/1608473638298.png');
INSERT INTO `contestant` VALUES (7, 2, 'heiheihei', '<div>222222222222</div>\r\n<div>333333333333</div>\r\n<div>111111111111</div>\r\n<div>222333344444</div>', 00000000001, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (10, 3, 'hahaha', '<div>hahahahahaha</div>\r\n<div>hahahahahaha</div>\r\n<div>hahahahahaha</div>', 00000000001, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (11, 1, '张三', '<p>1234</p>', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (12, 2, '李四', '<p>333333</p>', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (13, 3, '王五', '<p>222222</p>', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (14, 4, '赵六', '<p>666666</p>', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (15, 5, '小强', '<p>333333</p>', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (16, 6, '赫赫', '<p>123123</p>', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (17, 7, '丽华', '<p>qwqwqw</p>', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (18, 1, '鼓浪屿', '<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">百米高台为日光岩顶峰，这海拔92.7米，加上圆台，号称&ldquo;百米高台&rdquo;。淋浴天风，倾听海涛，眺望远处，水天一色，令人忘却人世间的诸多烦恼，全身心地融入这优美和谐的世界中去。到日光岩旅游，妙就妙在；不单单用肉眼看景，而应该用心灵去感受，做一番彻底的神游。看脚下的鼓浪屿，各种风格的建筑错落有致，好象从这钢琴之岛上弹奏出来音符，凝固成一曲最浪漫的旋律。这是一座神奇的岛屿，在这里的每一栋典雅的楼房里都有一段精彩的传奇故事。往北看，红色、圆穹顶部的建筑十分明显，那就是鼓浪屿最有名的&ldquo;八卦楼&rdquo;稍离我们近点的，这竖着十字架的建筑，就是闽南最有名的大教堂&ldquo;三一堂&rdquo;。</div>\r\n<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">再往前看，鼓浪屿隔鹭江与厦门相望，这里的特色建筑与厦门的现代化高楼大厦截然不同，仿佛时间在这里停滞了，将我们留在东西文化强烈撞击的19世纪末20世纪初的历史中。举目西望嵩屿电厂的烟囱高高耸起，著名的海沧开发区就在眼前，通过新建的海沧大桥与厦门连成一体。朝南看，对面就是漳州的中银开发区，南太武群山起伏。朝东望，远处有吴屿、青屿、大担 小担、大金门、小金门诸岛，越过海峡，就是台湾。</div>', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (19, 2, '湖里山炮台', '<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">炮台前沿建有&ldquo;望归台&rdquo;、&ldquo;盼归台&rdquo;，游客可通过望远镜了望金门所辖的<a style=\"color: #136ec2; text-decoration-line: none;\" href=\"https://baike.baidu.com/item/%E5%A4%A7%E6%8B%85%E5%B2%9B\" target=\"_blank\" rel=\"noopener\">大担岛</a>和<a style=\"color: #136ec2; text-decoration-line: none;\" href=\"https://baike.baidu.com/item/%E4%BA%8C%E6%8B%85%E5%B2%9B\" target=\"_blank\" rel=\"noopener\">二担岛</a>。炮台院内还新建有大型壁雕《民族魂》，壁雕左侧，新设了&ldquo;戎泉&rdquo;，颇为新颖。荣光宝藏博物院也设在胡里山炮台原兵营，其全部展品均由新加坡收藏家张荣光先生提供。其中镇院之宝是一块&ldquo;黄河之水天上来&rdquo;的奇石，来自缅甸，重2.5吨，画面上有一条自东北到西南的节理纹脉，似是黄河之水天上来。该馆还有一门世界上最小的火炮，长仅11厘米，重0.22公斤，口径0.8厘米，与世界上最大的大炮&ldquo;克虏伯大炮&rdquo;相映成趣。</div>\r\n<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">门票：25元/人</div>\r\n<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">开放时间：07:30 -18:00（冬季17:30）</div>\r\n<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">表演时间：</div>\r\n<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">1、&ldquo;迎客仪式&rdquo;表演时间：每天上午8：30；</div>\r\n<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">2、红夷火炮清兵操演表演时间（每日两场）：每天上午10：00，下午16：00。</div>\r\n<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">建议游玩时间： 1-2小时</div>\r\n<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">位置： 厦门市思明区曾厝安路2号</div>\r\n<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">最佳旅游时节：春秋最佳。初夏也不错，夏天较为炎热，冬季较温暖，每年8月前后，是台风多发季节，要多关注气候情况。</div>', 00000000000, '/user/3/contestant/1609979177947.jpg');
INSERT INTO `contestant` VALUES (20, 3, '曾厝垵', '<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 28px; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">曾厝垵（Tsan-tsh&ugrave;-uann），中国最文艺渔村，为&ldquo;曾厝垵文创村&rdquo;的简称。别名&ldquo;曾里&rdquo;，又称&ldquo;曾家沃&rdquo;、&ldquo;曾家湾&rdquo;，位于厦门岛东南部，有兔耳岭之草，太姥山之石，火山岛之礁，自然人文为一体。至今已有八百多年历史。周边景点包括厦门大学、胡里山炮台、厦大白城、环岛路、南普陀寺、万石植物园等。</div>\r\n<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 28px; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">作为景区的曾厝垵文创村只占0.33平方公里。</div>\r\n<div class=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; color: #333333; margin-bottom: 15px; text-indent: 28px; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif; background-color: #ffffff;\">曾厝垵是一个社区，现有曾厝垵社（曾厝垵文创村）、仓里社、前田社、西边社、前后厝社、东宅社、上里社、胡里山社8个自然村。共有10个居民小组，户数1473户，总人口4252人。总称曾厝垵社区居民委员会（位于厦门市思明区滨海街道曾厝垵仓里社17号，占地面积1450㎡），社区设党委会，下设三个党支部，隶属厦门市思明区滨海街道办事处管辖。</div>', 00000000000, '/user/3/contestant/1610108201043.jpg');
INSERT INTO `contestant` VALUES (21, 4, '中山路步行街', '<p><span style=\"color: #444444; font-family: Arial, \'Lucida Grande\', \'Microsoft Yahei\', \'Hiragino Sans GB\', \'Hiragino Sans GB W3\', SimSun, \'PingFang SC\', STHeiti; font-size: 16px;\">中山路就是一条购物节 中午晚上都可以 建议中午可以去逛街吃饭 宴遇创意中餐 推荐～ 曾厝庵建议去一天 比较适合慢慢玩 猫咪咖啡厅 下午茶 租自行车在附近玩 吃小吃 做陶艺 可玩的东西很多 小街小巷 白天去可以照小清新的照片 晚上夜景也好看哦 出来不愿能到海边 可以看个日落</span></p>', 00000000000, '/user/3/contestant/1610108228269.jpg');
INSERT INTO `contestant` VALUES (22, 5, '厦门科技馆', '', 00000000000, '/user/3/contestant/1610109080754.jpg');
INSERT INTO `contestant` VALUES (23, 6, '白鹭洲公园', '', 00000000000, '/user/3/contestant/1610108861647.jpg');
INSERT INTO `contestant` VALUES (24, 7, '番婆楼', '', 00000000000, '/user/3/contestant/1610108877873.jpg');
INSERT INTO `contestant` VALUES (25, 8, '泰安影视城', '', 00000000000, '/user/3/contestant/1610108899350.jpg');
INSERT INTO `contestant` VALUES (26, 9, '天竺山森林公园', '', 00000000000, '/user/3/contestant/1610108924407.jpg');
INSERT INTO `contestant` VALUES (27, 10, '天沐温泉度假村', '', 00000000001, '/user/3/contestant/1610108944660.jpg');
INSERT INTO `contestant` VALUES (28, 1, '曾厝垵', '<p>欢迎来到曾厝垵，这里有很多漂亮的小姐姐</p>', 00000000000, '/user/3/contestant/1610157103374.jpg');
INSERT INTO `contestant` VALUES (29, 2, '中山路步行街', '', 00000000000, '/user/3/contestant/1610156878701.jpg');
INSERT INTO `contestant` VALUES (30, 3, '厦门科技馆', '', 00000000000, '/user/3/contestant/1610156924015.jpg');
INSERT INTO `contestant` VALUES (31, 4, '白鹭洲公园', '', 00000000000, '/user/3/contestant/1610156943614.jpg');
INSERT INTO `contestant` VALUES (32, 5, '番婆楼', '', 00000000000, '/user/3/contestant/1610156964843.jpg');
INSERT INTO `contestant` VALUES (33, 6, '同安影视城', '', 00000000000, '/user/3/contestant/1610156991223.jpg');
INSERT INTO `contestant` VALUES (34, 7, '天竺山森林公园', '', 00000000000, '/user/3/contestant/1610157026063.jpg');
INSERT INTO `contestant` VALUES (35, 8, '天沐温泉度假村', '', 00000000000, '/user/3/contestant/1610157047213.jpg');
INSERT INTO `contestant` VALUES (36, 9, '鼓浪屿', '', 00000000000, '/user/3/contestant/1610157073798.jpg');
INSERT INTO `contestant` VALUES (37, 10, 'qwer', '<p>eqwsdsfdsfd</p>', 00000000001, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (38, 4, 'asd', '<p>sdfdg</p>', 00000000000, '/user/common/default2.jpg');
INSERT INTO `contestant` VALUES (39, 12, '厦门理工', '<p>一份关怀</p>', 00000000000, NULL);
INSERT INTO `contestant` VALUES (40, 1, '121212', '<p>43434</p>', 00000000000, NULL);

-- ----------------------------
-- Table structure for participate
-- ----------------------------
DROP TABLE IF EXISTS `participate`;
CREATE TABLE `participate`  (
  `cId` int(11) NOT NULL COMMENT '选手表主键自增id',
  `aId` int(11) NOT NULL COMMENT '活动id',
  `votes` int(11) NOT NULL DEFAULT 0 COMMENT '投票数',
  PRIMARY KEY (`cId`, `aId`) USING BTREE,
  INDEX `fk_participate_a_id`(`aId`) USING BTREE,
  CONSTRAINT `fk_participate_a_id` FOREIGN KEY (`aId`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_participate_c_id` FOREIGN KEY (`cId`) REFERENCES `contestant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of participate
-- ----------------------------
INSERT INTO `participate` VALUES (6, 58, 0);
INSERT INTO `participate` VALUES (7, 58, 0);
INSERT INTO `participate` VALUES (10, 58, 0);
INSERT INTO `participate` VALUES (11, 57, 2);
INSERT INTO `participate` VALUES (12, 57, 2);
INSERT INTO `participate` VALUES (13, 57, 3);
INSERT INTO `participate` VALUES (14, 57, 1);
INSERT INTO `participate` VALUES (15, 57, 2);
INSERT INTO `participate` VALUES (16, 57, 1);
INSERT INTO `participate` VALUES (17, 57, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'user/common/default.png' COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '唐老鸭', '1q2w3e4r5t6y', '/user/1/image/header.JPG', 'user_midouzi@qq.com');
INSERT INTO `user` VALUES (4, '花束', '1a2s3d', 'user/common/default.png', '12346@163.com');
INSERT INTO `user` VALUES (5, 'jsp', '123456', 'user/common/default.png', '2312@aa.com');
INSERT INTO `user` VALUES (6, '1233', '1234', 'user/common/default.png', '111@qq.com');
INSERT INTO `user` VALUES (9, 'hehe3', '111111', 'user/common/default.png', 'hehe3@qq.com');
INSERT INTO `user` VALUES (15, 'hehe11', 'hehe11', 'user/common/default.png', 'hehe11@qq.com');

-- ----------------------------
-- Table structure for voterecord
-- ----------------------------
DROP TABLE IF EXISTS `voterecord`;
CREATE TABLE `voterecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ip地址',
  `cId` int(11) NOT NULL COMMENT '选手id',
  `aId` int(11) DEFAULT NULL COMMENT '活动id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `aId`(`aId`) USING BTREE,
  CONSTRAINT `voterecord_ibfk_1` FOREIGN KEY (`aId`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
