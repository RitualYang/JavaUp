/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 20/05/2020 15:36:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `ID` int(11) NOT NULL COMMENT '编号',
  `UID` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `MONEY` double NULL DEFAULT NULL COMMENT '金额',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_Reference_8`(`UID`) USING BTREE,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`UID`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 41, 1000);
INSERT INTO `account` VALUES (2, 45, 1000);
INSERT INTO `account` VALUES (3, 41, 2000);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `ID` int(11) NOT NULL COMMENT '编号',
  `ROLE_NAME` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '院长', '管理整个学院');
INSERT INTO `role` VALUES (2, '总裁', '管理整个公司');
INSERT INTO `role` VALUES (3, '校长', '管理整个学校');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名称',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (41, '老王', '2018-02-27 17:47:08', '男', '北京');
INSERT INTO `user` VALUES (42, '小二王', '2018-03-02 15:09:37', '男', '北京金燕龙');
INSERT INTO `user` VALUES (43, '小二王', '2018-03-04 11:34:34', '女', '北京金燕龙');
INSERT INTO `user` VALUES (45, '天下', '2018-03-04 12:04:06', '男', '北京金燕龙');
INSERT INTO `user` VALUES (46, '老王', '2018-03-07 17:37:26', '男', '北京');
INSERT INTO `user` VALUES (48, '小马宝莉', '2018-03-08 11:44:00', '女', '北京修正');
INSERT INTO `user` VALUES (49, '朝天骄', '2020-05-18 09:12:01', '男', '北京市顺义区');
INSERT INTO `user` VALUES (50, '吕小树', '2020-05-18 10:16:14', '女', '北京市顺义区');
INSERT INTO `user` VALUES (51, '朝天骄', '2020-05-19 08:42:54', '男', '北京市顺义区');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `UID` int(11) NOT NULL COMMENT '用户编号',
  `RID` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`UID`, `RID`) USING BTREE,
  INDEX `FK_Reference_10`(`RID`) USING BTREE,
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`RID`) REFERENCES `role` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`UID`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (41, 1);
INSERT INTO `user_role` VALUES (45, 1);
INSERT INTO `user_role` VALUES (41, 2);

-- ----------------------------
-- Procedure structure for addStudent
-- ----------------------------
DROP PROCEDURE IF EXISTS `addStudent`;
delimiter ;;
CREATE PROCEDURE `addStudent`(in num int)
begin
declare i int;
set i=1;
delete from student;
while num>=i do
insert student values (
       LPAD(convert(i,char(5)),5,'0'),
       CreateName(),
       if(ceil(rand()*10)%2=0,'男','女'),
       RPAD(convert(ceil(rand()*1000000000000000000),char(18)),18,'0'),
Concat(convert(ceil(rand()*10)+1980,char(4)),'-',LPAD(convert(ceil(rand()*12),
char(2)),2,'0'),'-',LPAD(convert(ceil(rand()*28),char(2)),2,'0')),
       Concat(PINYIN(sname),'@hotmail.com'),
       case ceil(rand()*3) when 1 then '网络与网站开发' when 2 then 'JAVA' ELSE 'NET' END,
       NOW()
);
set i=i+1;
end while;
select * from student;
End
;;
delimiter ;

-- ----------------------------
-- Function structure for CreateName
-- ----------------------------
DROP FUNCTION IF EXISTS `CreateName`;
delimiter ;;
CREATE FUNCTION `CreateName`()
 RETURNS varchar(3) CHARSET utf8mb4
begin
DECLARE LN VARCHAR(300);
DECLARE MN VARCHAR(500);
DECLARE FN VARCHAR(500);
DECLARE LN_N INT;
DECLARE MN_N INT;
DECLARE FN_N INT;
SET LN='李王张刘陈杨黄赵周吴徐孙朱马胡郭林何高梁郑罗宋谢唐韩曹许邓萧冯曾程蔡彭潘袁于董余苏叶吕魏蒋田杜丁沈姜范江傅钟卢汪戴崔任陆廖姚方金邱夏谭韦贾邹石熊孟秦阎薛侯雷白龙段郝孔邵史毛常万顾赖武康贺严尹钱施牛洪龚';
SET MN='伟刚勇春菊毅俊峰强军平保东文辉力明永健世广志瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥新利筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒义兴良海山仁波宁贵福生龙元全国胜学祥亮政谦亨奇固之岚苑富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪清飞彬娜静淑惠珠翠雅芝妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦素伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘';
SET FN='伟刚勇毅俊云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧才发武丽琳轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德河哲江超浩璐娅琦晶裕华慧巧美婕馨影荔枝思心邦承乐绍功松善厚庆磊民友玉萍红娥玲芬芳燕彩兰凤洁梅秀娟英行时泰盛雄琛钧冠策腾楠榕风航弘峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘';
SET LN_N=CHAR_LENGTH(LN);
SET MN_N=CHAR_LENGTH(MN);
SET FN_N=CHAR_LENGTH(FN);
return Concat(substring(LN,ceil(rand()*LN_N),1),substring(MN,ceil(rand()*MN_N),1),substring(FN,ceil(rand()*FN_N),1));
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for fillScore
-- ----------------------------
DROP PROCEDURE IF EXISTS `fillScore`;
delimiter ;;
CREATE PROCEDURE `fillScore`()
begin
DECLARE St_Num INT;
DECLARE Sb_Num INT;
DECLARE i1 INT;
DECLARE i2 INT;
set i1=1;
set i2=1;
delete from score;
select count(*) into St_Num from `student`;
select count(*) into Sb_Num from `subject`;
while St_Num>=i1 do
set i2=1;
while Sb_Num>=i2 do
insert score values
 (LPAD(convert(i1,char(5)),5,'0'),LPAD(convert(i2,char(4)),4,'0'),ceil(50+rand()*50));
set i2=i2+1;
END WHILE;
set i1=i1+1;
END WHILE;
End
;;
delimiter ;

-- ----------------------------
-- Function structure for PINYIN
-- ----------------------------
DROP FUNCTION IF EXISTS `PINYIN`;
delimiter ;;
CREATE FUNCTION `PINYIN`(str CHAR(255))
 RETURNS char(255) CHARSET utf8mb4
BEGIN
DECLARE hexCode char(4);
DECLARE pinyin varchar(255);
DECLARE firstChar char(1);
DECLARE aChar char(1);
DECLARE pos int;
DECLARE strLength int;

SET pinyin    = '';
SET strLength = CHAR_LENGTH(LTRIM(RTRIM(str)));
SET pos       = 1;
SET @str      = (CONVERT(str USING gbk));
WHILE pos <= strLength DO
    SET @aChar = SUBSTRING(@str,pos,1);
    SET hexCode = HEX(@aChar); 

    IF hexCode >= "8140" AND hexCode <= "FEA0" THEN
        SELECT letter into firstChar
        FROM   pinyin
        WHERE  chinese >= @aChar
        LIMIT  1;
    ELSE 
      SET firstChar = @aChar;
    END IF;

    SET pinyin = CONCAT(pinyin,firstChar);
    SET pos = pos + 1;
END WHILE;  

RETURN UPPER(pinyin);
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
