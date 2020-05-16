/*
SQLyog v10.2 
MySQL - 5.6.39 : Database - weizhidatabase
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`weizhidatabase` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `weizhidatabase`;

/*Table structure for table `appversionupdate` */

DROP TABLE IF EXISTS `appversionupdate`;

CREATE TABLE `appversionupdate` (
  `AVU_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `AVU_VERSION` varchar(10) NOT NULL,
  `AVU_NAME` varchar(20) NOT NULL,
  `AVU_URL` varchar(100) NOT NULL,
  `AVU_DESCRIBE` varchar(200) NOT NULL,
  `AVU_IMAG` varchar(200) DEFAULT NULL,
  `AVU_DATE` varchar(20) NOT NULL,
  `AVD_SIZE` varchar(10) NOT NULL,
  `AVD_IS_MANDATORY` varchar(5) NOT NULL,
  PRIMARY KEY (`AVU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `appversionupdate` */

insert  into `appversionupdate`(`AVU_ID`,`AVU_VERSION`,`AVU_NAME`,`AVU_URL`,`AVU_DESCRIBE`,`AVU_IMAG`,`AVU_DATE`,`AVD_SIZE`,`AVD_IS_MANDATORY`) values (1,'2.0.2','OnlyKnow.apk','http://101.132.168.25:8090/WeiZhiService/APP/OnlyKnow.apk','1.新增干货营模块,享受每日美女福利\\n2.我的模块新增评论查看\\n3.替换内置浏览器内核为CrossWalk,拥有更好的浏览体验.\\n4.搜索模块增强,支持聚合搜索.\\n5.社交功能增强,可以发送图片,以及分享!','http://101.132.168.25:8090/WeiZhiService/APP/app_update.jpg','2018/02/07/22/35','34.0MB','NO');

/*Table structure for table `cardbrowsing` */

DROP TABLE IF EXISTS `cardbrowsing`;

CREATE TABLE `cardbrowsing` (
  `CB_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `CARD_ID` tinyint(4) NOT NULL,
  `USER_NAME` varchar(20) NOT NULL,
  `Equipment` varchar(100) NOT NULL,
  `CB_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`CB_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Data for the table `cardbrowsing` */

insert  into `cardbrowsing`(`CB_ID`,`CARD_ID`,`USER_NAME`,`Equipment`,`CB_DATE`) values (1,1,'TongXingWen','861305039867743','2018/01/28/09/26'),(2,3,'TongXingWen','861305039867743','2018/01/28/10/50'),(3,2,'TongXingWen','861305039867743','2018/01/28/10/50'),(4,4,'TongXingWen','861305039867743','2018/01/28/11/21'),(5,5,'TongXingWen','861305039867743','2018/01/28/11/23'),(6,1,'Anonymous','861305039867743','2018/01/30/01/05'),(7,5,'Anonymous','861305039867743','2018/01/30/01/06'),(8,3,'Anonymous','861305039867743','2018/01/30/02/09'),(9,4,'Anonymous','861305039867743','2018/01/30/02/22'),(10,2,'Anonymous','861305039867743','2018/01/30/02/32'),(11,6,'TongXingWen','861305039867743','2018/01/30/16/46'),(12,7,'TongXingWen','861305039867743','2018/01/30/16/53'),(13,12,'TongXingWen','861305039867743','2018/01/30/17/54'),(14,19,'TongXingWen','861305039867743','2018/01/30/19/30'),(15,20,'TongXingWen','861305039867743','2018/01/30/19/30'),(16,9,'TongXingWen','861305039867743','2018/01/30/19/32'),(17,21,'TongXingWen','861305039867743','2018/01/30/19/33'),(18,3,'PaoPaoPao2','861305039867743','2018/01/30/22/01'),(19,3,'OnlyKnow','861305039867743','2018/01/31/08/40'),(20,2,'OnlyKnow','861305039867743','2018/01/31/08/41'),(21,21,'OnlyKnow','861305039867743','2018/01/31/08/41'),(22,4,'OnlyKnow','861305039867743','2018/01/31/08/41'),(23,12,'OnlyKnow','861305039867743','2018/01/31/08/42'),(24,1,'OnlyKnow','861305039867743','2018/01/31/08/42'),(25,23,'TongXingWen','861305039867743','2018/01/31/12/43'),(26,22,'TongXingWen','861305039867743','2018/01/31/12/43'),(27,24,'TongXingWen','861305039867743','2018/01/31/20/38'),(28,25,'TongXingWen','861305039867743','2018/01/31/20/38'),(29,12,'Anonymous','862119034023163','2018/01/31/21/35'),(30,12,'qianqianqian','862119034023163','2018/01/31/21/40'),(31,1,'qianqianqian','862119034023163','2018/01/31/21/41'),(32,28,'TongXingWen','861305039867743','2018/01/31/22/00'),(33,26,'TongXingWen','861305039867743','2018/01/31/22/02'),(34,27,'TongXingWen','861305039867743','2018/01/31/22/02'),(35,29,'TongXingWen','861305039867743','2018/02/01/00/52'),(36,32,'TongXingWen','861305039867743','2018/02/01/03/43'),(37,35,'TongXingWen','861305039867743','2018/02/01/03/43'),(38,30,'TongXingWen','861305039867743','2018/02/01/15/23'),(39,31,'TongXingWen','861305039867743','2018/02/01/17/47'),(40,34,'TongXingWen','861305039867743','2018/02/01/19/06'),(41,33,'TongXingWen','861305039867743','2018/02/01/19/06'),(42,36,'TongXingWen','861305039867743','2018/02/01/19/25'),(43,37,'TongXingWen','861305039867743','2018/02/02/08/33'),(44,38,'TongXingWen','861305039867743','2018/02/03/22/44'),(45,39,'OnlyKnow','861305039867743','2018/02/04/01/45'),(46,38,'OnlyKnow','861305039867743','2018/02/04/02/03'),(47,39,'TongXingWen','861305039867743','2018/02/04/12/12'),(48,39,'Anonymous','861305039867743','2018/02/05/09/17'),(49,24,'Anonymous','861305039867743','2018/02/05/09/18'),(50,21,'Anonymous','861305039867743','2018/02/05/09/18'),(51,40,'TongXingWen','861305039867743','2018/02/07/09/14'),(52,41,'TongXingWen','861305039867743','2018/02/07/16/36'),(53,41,'Anonymous','861305039867743','2018/02/08/10/13'),(54,21,'ttyj71111','860046034581357','2018/02/08/19/03'),(55,2,'ttyj71111','860046034581357','2018/02/08/19/03'),(56,1,'ttyj71111','860046034581357','2018/02/08/19/04'),(57,41,'ttyj71111','860046034581357','2018/02/08/19/04'),(58,42,'TongXingWen','861305039867743','2018/02/09/15/11'),(59,44,'TongXingWen','861305039867743','2018/02/09/15/15'),(60,43,'TongXingWen','861305039867743','2018/02/09/15/17'),(61,45,'TongXingWen','861305039867743','2018/02/09/16/07'),(62,46,'TongXingWen','861305039867743','2018/02/09/16/09'),(63,47,'TongXingWen','861305039867743','2018/02/09/17/14'),(64,48,'TongXingWen','861305039867743','2018/02/09/17/14'),(65,50,'TongXingWen','861305039867743','2018/02/09/17/17'),(66,50,'OnlyKnow','861305039867743','2018/02/09/17/21'),(67,48,'OnlyKnow','861305039867743','2018/02/09/17/22'),(68,51,'OnlyKnow','861305039867743','2018/02/09/17/22'),(69,52,'TongXingWen','861305039867743','2018/02/09/21/18'),(70,49,'TongXingWen','861305039867743','2018/02/09/21/19'),(71,51,'TongXingWen','861305039867743','2018/02/09/21/21');

/*Table structure for table `cardbrowsingstatistics` */

DROP TABLE IF EXISTS `cardbrowsingstatistics`;

CREATE TABLE `cardbrowsingstatistics` (
  `CBS_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `CARD_ID` tinyint(4) NOT NULL,
  `CBS_SUM` tinyint(4) DEFAULT '1',
  `CBS_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`CBS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Data for the table `cardbrowsingstatistics` */

insert  into `cardbrowsingstatistics`(`CBS_ID`,`CARD_ID`,`CBS_SUM`,`CBS_DATE`) values (1,1,5,'2018/01/28/09/26'),(2,3,4,'2018/01/28/10/50'),(3,2,4,'2018/01/28/10/50'),(4,4,2,'2018/01/28/11/21'),(5,5,2,'2018/01/28/11/23'),(6,6,1,'2018/01/30/16/46'),(7,7,1,'2018/01/30/16/53'),(8,12,4,'2018/01/30/17/54'),(9,19,1,'2018/01/30/19/30'),(10,20,1,'2018/01/30/19/30'),(11,9,1,'2018/01/30/19/32'),(12,21,4,'2018/01/30/19/33'),(13,23,1,'2018/01/31/12/43'),(14,22,1,'2018/01/31/12/43'),(15,24,2,'2018/01/31/20/38'),(16,25,1,'2018/01/31/20/38'),(17,28,1,'2018/01/31/22/00'),(18,26,1,'2018/01/31/22/02'),(19,27,1,'2018/01/31/22/02'),(20,29,1,'2018/02/01/00/52'),(21,32,1,'2018/02/01/03/43'),(22,35,1,'2018/02/01/03/43'),(23,30,1,'2018/02/01/15/23'),(24,31,1,'2018/02/01/17/47'),(25,34,1,'2018/02/01/19/06'),(26,33,1,'2018/02/01/19/06'),(27,36,1,'2018/02/01/19/25'),(28,37,1,'2018/02/02/08/33'),(29,38,2,'2018/02/03/22/44'),(30,39,3,'2018/02/04/01/45'),(31,40,1,'2018/02/07/09/14'),(32,41,3,'2018/02/07/16/36'),(33,42,1,'2018/02/09/15/11'),(34,44,1,'2018/02/09/15/15'),(35,43,1,'2018/02/09/15/17'),(36,45,1,'2018/02/09/16/07'),(37,46,1,'2018/02/09/16/09'),(38,47,1,'2018/02/09/17/14'),(39,48,2,'2018/02/09/17/14'),(40,50,2,'2018/02/09/17/17'),(41,51,2,'2018/02/09/17/22'),(42,49,1,'2018/02/09/21/19');

/*Table structure for table `cardreport` */

DROP TABLE IF EXISTS `cardreport`;

CREATE TABLE `cardreport` (
  `CRP_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `CARD_ID` tinyint(4) NOT NULL,
  `CRP_MESSAGE` varchar(250) NOT NULL,
  `CRP_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`CRP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `cardreport` */

insert  into `cardreport`(`CRP_ID`,`USER_NAME`,`CARD_ID`,`CRP_MESSAGE`,`CRP_DATE`) values (1,'TongXingWen',4,'JuBao_itme1/JuBao_itme2/','2018/01/29/23/56'),(2,'TongXingWen',1,'JuBao_itme3/JuBao_itme4','2018/01/30/10/10'),(3,'TongXingWen',4,'JuBao_itme1/JuBao_itme2/JuBao_itme3/JuBao_itme4','2018/02/02/19/09');

/*Table structure for table `cityid` */

DROP TABLE IF EXISTS `cityid`;

CREATE TABLE `cityid` (
  `CITY_ID` varchar(100) NOT NULL,
  `CITY_NAME` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cityid` */

/*Table structure for table `commentpraise` */

DROP TABLE IF EXISTS `commentpraise`;

CREATE TABLE `commentpraise` (
  `CP_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `COM_ID` tinyint(4) NOT NULL,
  `CP_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`CP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `commentpraise` */

insert  into `commentpraise`(`CP_ID`,`USER_NAME`,`COM_ID`,`CP_DATE`) values (1,'TongXingWen',1,'2018/01/28/12/34'),(2,'TongXingWen',6,'2018/01/28/12/38'),(3,'TongXingWen',2,'2018/01/29/19/01'),(4,'TongXingWen',3,'2018/01/29/19/01'),(5,'TongXingWen',4,'2018/01/30/02/52'),(6,'TongXingWen',9,'2018/01/30/02/53'),(7,'TongXingWen',8,'2018/01/30/02/55'),(8,'TongXingWen',5,'2018/01/30/02/57'),(9,'TongXingWen',7,'2018/01/30/02/58'),(10,'TongXingWen',10,'2018/01/30/03/00'),(11,'TongXingWen',11,'2018/01/30/09/20'),(12,'TongXingWen',13,'2018/01/31/12/53'),(13,'TongXingWen',12,'2018/01/31/12/54'),(14,'TongXingWen',14,'2018/02/02/00/21'),(15,'TongXingWen',15,'2018/02/02/00/22'),(16,'TongXingWen',23,'2018/02/03/22/09'),(17,'TongXingWen',24,'2018/02/03/22/09'),(18,'TongXingWen',22,'2018/02/03/22/09'),(19,'TongXingWen',25,'2018/02/03/22/09'),(20,'TongXingWen',26,'2018/02/03/22/09'),(21,'OnlyKnow',27,'2018/02/04/01/39'),(22,'OnlyKnow',28,'2018/02/04/01/44'),(23,'OnlyKnow',29,'2018/02/04/01/44'),(24,'TongXingWen',30,'2018/02/07/09/15'),(25,'TongXingWen',29,'2018/02/07/20/12'),(26,'TongXingWen',28,'2018/02/07/20/12'),(27,'TongXingWen',32,'2018/02/09/16/14'),(28,'TongXingWen',33,'2018/02/09/16/14');

/*Table structure for table `commentreplypraise` */

DROP TABLE IF EXISTS `commentreplypraise`;

CREATE TABLE `commentreplypraise` (
  `CRP_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `COMR_ID` tinyint(4) NOT NULL,
  `CRP_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`CRP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `commentreplypraise` */

insert  into `commentreplypraise`(`CRP_ID`,`USER_NAME`,`COMR_ID`,`CRP_DATE`) values (1,'TongXingWen',2,'2018/01/28/13/01'),(2,'TongXingWen',1,'2018/01/28/13/01'),(3,'TongXingWen',4,'2018/01/29/19/01'),(4,'TongXingWen',3,'2018/01/30/02/53'),(5,'TongXingWen',5,'2018/01/30/10/03'),(6,'TongXingWen',6,'2018/01/31/12/53'),(7,'TongXingWen',7,'2018/01/31/12/54');

/*Table structure for table `commentreplyreport` */

DROP TABLE IF EXISTS `commentreplyreport`;

CREATE TABLE `commentreplyreport` (
  `COMRERP_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `COMR_ID` tinyint(4) NOT NULL,
  `COMRERP_MESSAGE` varchar(250) NOT NULL,
  `COMRERP_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`COMRERP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `commentreplyreport` */

/*Table structure for table `commentreport` */

DROP TABLE IF EXISTS `commentreport`;

CREATE TABLE `commentreport` (
  `COMRP_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `COM_ID` tinyint(4) NOT NULL,
  `COMRP_MESSAGE` varchar(250) NOT NULL,
  `COMRP_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`COMRP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `commentreport` */

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `FB_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `Equipment` varchar(100) NOT NULL,
  `FB_DESCRIBE` varchar(1000) NOT NULL,
  `FB_IMAG` mediumtext,
  `FB_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`FB_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `feedback` */

insert  into `feedback`(`FB_ID`,`USER_NAME`,`Equipment`,`FB_DESCRIBE`,`FB_IMAG`,`FB_DATE`) values (1,'TongXingWen','861305039867743','hgtfgjkkjhhhhhhhhhhhgggggvvvvvvvvvvvvvvvvvvvvvvvvvhbhhhbbgvhhhhhhhhhhhhcvhjjjjhhggftggghhhhfvhjjhhavhjhuygggfgdsadfgvvfhnhhgwvvgddfhhhgvfggggbbfdzgggfggggfvggg','http://101.132.168.25:8090/WeiZhiService/FeedBack/b823c3e6104048d5a7453d9d89600b52.jpg','2018/01/30/10/15');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `GD_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `GD_NAME` varchar(20) NOT NULL,
  `GD_TYPE` varchar(150) DEFAULT NULL,
  `GD_DESCRIBE` varchar(500) DEFAULT NULL,
  `GD_PRICE` tinyint(4) NOT NULL,
  `GD_DATE` varchar(20) NOT NULL,
  `GD_INTRODUCTION` varchar(250) NOT NULL,
  `GD_ICON_URL` varchar(150) NOT NULL,
  `GD_IMAGE_URL` varchar(150) NOT NULL,
  PRIMARY KEY (`GD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`GD_ID`,`GD_NAME`,`GD_TYPE`,`GD_DESCRIBE`,`GD_PRICE`,`GD_DATE`,`GD_INTRODUCTION`,`GD_ICON_URL`,`GD_IMAGE_URL`) values (1,'青铜VIP1','ADD_CARD','卡片发表无需等待审批,快人一步,直接发布!',100,'2018/01/30/10/28','卡片发表免审批权限','http://101.132.168.25:8090/WeiZhiService/Goods/goodsIcon.png','http://101.132.168.25:8090/WeiZhiService/Goods/gd_id_3.jpg'),(2,'白银VIP1','https://www.aliyun.com/','每天给你一个么么哒!',127,'2018/02/01/15/14','么么哒!','http://101.132.168.25:8090/WeiZhiService/Goods/goodsIcon.png','http://101.132.168.25:8090/WeiZhiService/Goods/gd_id_4.jpg'),(3,'IPhonX','ARTICLE','IPhoneX手机一台!',127,'2018/02/05/14/30','实物商品','http://101.132.168.25:8090/WeiZhiService/Goods/goodsIcon.png','http://101.132.168.25:8090/WeiZhiService/Goods/gd_id_1.jpg');

/*Table structure for table `headpicture` */

DROP TABLE IF EXISTS `headpicture`;

CREATE TABLE `headpicture` (
  `GROUP_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `HP_IMAGE_URL1` varchar(100) DEFAULT 'NULL',
  `HP_IMAGE_URL2` varchar(100) DEFAULT 'NULL',
  `HP_IMAGE_URL3` varchar(100) DEFAULT 'NULL',
  `HP_IMAGE_URL4` varchar(100) DEFAULT 'NULL',
  `HP_IMAGE_URL5` varchar(100) DEFAULT 'NULL',
  `AD_IMAGE_URL1` varchar(100) DEFAULT 'NULL',
  `AD_IMAGE_URL2` varchar(100) DEFAULT 'NULL',
  `AD_IMAGE_URL3` varchar(100) DEFAULT 'NULL',
  `AD_LINK_URL1` varchar(100) DEFAULT 'NULL',
  `AD_LINK_URL2` varchar(100) DEFAULT 'NULL',
  `AD_LINK_URL3` varchar(100) DEFAULT 'NULL',
  `GROUP_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `headpicture` */

insert  into `headpicture`(`GROUP_ID`,`HP_IMAGE_URL1`,`HP_IMAGE_URL2`,`HP_IMAGE_URL3`,`HP_IMAGE_URL4`,`HP_IMAGE_URL5`,`AD_IMAGE_URL1`,`AD_IMAGE_URL2`,`AD_IMAGE_URL3`,`AD_LINK_URL1`,`AD_LINK_URL2`,`AD_LINK_URL3`,`GROUP_DATE`) values (1,'http://101.132.168.25:8090/WeiZhiService/Carousel/game001_02.jpg','http://101.132.168.25:8090/WeiZhiService/Carousel/game001_11.jpg','http://101.132.168.25:8090/WeiZhiService/Carousel/game001_12.jpg','http://101.132.168.25:8090/WeiZhiService/Carousel/game001_06.jpg','http://101.132.168.25:8090/WeiZhiService/Carousel/game001_09.jpg','http://101.132.168.25:8090/WeiZhiService/AD/ad001_01.jpg','http://101.132.168.25:8090/WeiZhiService/AD/ad001_02.jpg','http://101.132.168.25:8090/WeiZhiService/AD/ad001_05.jpg','https://github.com/TongXingWen22/OnlyKnow','http://jcodecraeer.com/plus/list.php?tid=31','https://www.ithome.com/','2018/02/05/14/27');

/*Table structure for table `purchaserecords` */

DROP TABLE IF EXISTS `purchaserecords`;

CREATE TABLE `purchaserecords` (
  `PR_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `GD_ID` tinyint(4) NOT NULL,
  `USER_NAME` varchar(20) NOT NULL,
  `PR_CONSUME` tinyint(4) NOT NULL,
  `PR_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`PR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `purchaserecords` */

insert  into `purchaserecords`(`PR_ID`,`GD_ID`,`USER_NAME`,`PR_CONSUME`,`PR_DATE`) values (1,1,'TongXingWen',100,'2018/02/01/18/21'),(2,1,'OnlyKnow',100,'2018/02/04/01/29'),(3,2,'OnlyKnow',127,'2018/02/04/01/29'),(4,2,'TongXingWen',127,'2018/02/06/09/00');

/*Table structure for table `sessionmessage` */

DROP TABLE IF EXISTS `sessionmessage`;

CREATE TABLE `sessionmessage` (
  `CML_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME_REC` varchar(20) NOT NULL,
  `USER_NAME_SEND` varchar(20) NOT NULL,
  `NEIRON_TEXT` varchar(1000) DEFAULT 'NULL',
  `CML_LINK` varchar(100) DEFAULT 'NULL',
  `CML_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`CML_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sessionmessage` */

/*Table structure for table `teaminfo` */

DROP TABLE IF EXISTS `teaminfo`;

CREATE TABLE `teaminfo` (
  `TEAM_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `PRINCIPAL` varchar(20) NOT NULL,
  PRIMARY KEY (`TEAM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teaminfo` */

/*Table structure for table `tracking` */

DROP TABLE IF EXISTS `tracking`;

CREATE TABLE `tracking` (
  `TK_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `Equipment` varchar(100) NOT NULL,
  `TK_Longitude` double NOT NULL,
  `TK_Dimension` double NOT NULL,
  `TK_DTAE` varchar(20) NOT NULL,
  PRIMARY KEY (`TK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tracking` */

/*Table structure for table `useragreement` */

DROP TABLE IF EXISTS `useragreement`;

CREATE TABLE `useragreement` (
  `UA_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `UA_VERSION` varchar(10) NOT NULL,
  `UA_NAME` varchar(20) NOT NULL,
  `UA_CONTENT` text,
  `UA_TYPE` varchar(20) NOT NULL,
  `UA_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`UA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `useragreement` */

insert  into `useragreement`(`UA_ID`,`UA_VERSION`,`UA_NAME`,`UA_CONTENT`,`UA_TYPE`,`UA_DATE`) values (1,'1.0.1','用户使用协议','简体中文:\r\n1唯知空间以下简称OnlyKnow,一切移动客户端用户在下载并使用OnlyKnow软件时均被视为已经仔细阅读本条款并完全同意。凡以任何方式登陆OnlyKnow,或直接 间接使用OnlyKnow资料者,均被视为自愿接受OnlyKnow相关声明和用户服务协议的约束。\r\n2,OnlyKnow手机APP提供的的互联网推送内容并不代表OnlyKnow手机APP之意见及观点，也不意味着OnlyKnow赞同其观点或证实其内容的真实性,仅为广告提供商意见。\r\n3,OnlyKnow手机APP扫描到的文字、图片、音视频等资料均由用户手机内存提供，其内容真实性、准确性和合法性由用户负责。而由此造成的用户资料泄露,丢失问题OnlyKnow手机APP不承担任何法律责任。\r\n4,OnlyKnow手机APP的标志,标识,UI设计,如果侵犯了第三方的知识产权或其他权利，责任由作者承担，OnlyKnow 对此不承担责任。\r\n5,OnlyKnow手机APP不保证为向用户提供便利而设置的外部链接的准确性和完整性,同时,对于该外部链接指向的不由OnlyKnow手机APP实际控制的任何网页上的内容,OnlyKnow手机APP不承担任何责任。\r\n6,用户明确并同意其使用OnlyKnow手机APP网络服务所存在的风险将完全由其本人承担;因其使用OnlyKnow手机APP网络服务而产生的一切后果也由其本人承担,OnlyKnow手机APP对此不承担任何责任。\r\n7,除OnlyKnow手机APP注明之服务条款外,其它因不当使用OnlyKnow而导致的任何意外、疏忽、合约毁坏、诽谤、版权或其他知识产权侵犯及其所造成的任何损失,OnlyKnow手机APP概不负责，亦不承担任何法律责任。\r\n8,对于因不可抗力或因黑客攻击、通讯线路中断等OnlyKnow手机APP不能控制的原因造成的网络服务中断或其他缺陷,导致用户不能正常使用OnlyKnow手机APP,OnlyKnow手机APP不承担任何责任,但将尽力减少因此给用户造成的损失或影响。\r\n9,本声明未涉及的问题请参见国家有关法律法规,当本声明与国家有关法律法规冲突时,以国家法律法规为准。\r\n10,OnlyKnow相关声明版权及其修改权,更新权和最终解释权均属OnlyKnow手机APP所有。\r\nEnglish:\r\n1,OnlyKnow services hereinafter referred to as the OnlyKnow, all mobile client users in the download and use the OnlyKnow software are considered have carefully read and fully agree with the terms.Any landing OnlyKnow in any way, or indirectly using OnlyKnow data directly, all be regarded as voluntary OnlyKnow related statements and constraints of the user service agreement.\r\n2,OnlyKnow phone APP to provide Internet push content does not represent the opinions and views of OnlyKnow phone APP, doesn\'t mean OnlyKnow agree with their point of view or confirm the authenticity of its contents, only for advertising providers.OnlyKnow phone APP to provide Internet push content does not represent the opinions and views of OnlyKnow phone APP, doesn\'t mean OnlyKnow agree with their point of view or confirm the authenticity of its contents, only for advertising providers.\r\n3,OnlyKnow phone APP scans to text, pictures, audio and video data are supplied by the user mobile phone memory and its contents shall be the responsibility of the user of the truthfulness, accuracy and legitimacy.And this may cause the user information leak, loss problem OnlyKnow phone APP does not assume any legal liability.\r\n4,OnlyKnow phone APP logo, logo, UI design, if the infringement of the third party\'s intellectual property or other rights, liability shall be borne by the author, OnlyKnow is not liable.\r\n5,OnlyKnow phone APP does not guarantee to provide convenience to the user and the accuracy and completeness of the external links set, at the same time, for the external links pointing to the can OnlyKnow phone APP physical control of any web page content, OnlyKnow phone APP does not undertake any responsibility.\r\n6,Users a clear and agreed to use OnlyKnow the risk in the phone APP network service will be fully borne by himself;Because of its using OnlyKnow the phone APP web services and produce all the consequences shall be borne by himself, OnlyKnow phone APP does not undertake any responsibility.\r\n7,In addition to the OnlyKnow phone APP indicate the terms of service, other any accident caused by improper use OnlyKnow, negligence, contract damage, defamation, infringement of copyright or other intellectual property rights and as a result of any loss, OnlyKnow phone APP is not responsible for, also does not assume any legal liability.\r\n8,Due to force majeure or due to hacker attacks, communications lines, such as OnlyKnow phone APP causes beyond the control of the network service interruption or other defects, to the normal user cannot use the OnlyKnow phone APP, OnlyKnow phone APP does not assume any responsibility, but will do our best to reduce losses from diseases, so to the user or influence.\r\n9,Not this statement in the question, please refer to the relevant state laws and regulations, when this statement conflict with the relevant state laws and regulations, the state laws and regulations shall prevail.\r\n10,OnlyKnow related copyright statement and its modification, update and final interpretation belong to the OnlyKnow all mobile phone APP.\r\n','USER_UA','2018/02/05/12/42');

/*Table structure for table `userattention` */

DROP TABLE IF EXISTS `userattention`;

CREATE TABLE `userattention` (
  `UAT_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME_MAIN` varchar(20) NOT NULL,
  `USER_NAME_RETE` varchar(20) NOT NULL,
  `UAT_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`UAT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `userattention` */

insert  into `userattention`(`UAT_ID`,`USER_NAME_MAIN`,`USER_NAME_RETE`,`UAT_DATE`) values (4,'OnlyKnow','TongXingWen','2018/01/31/08/40'),(5,'qianqianqian','TongXingWen','2018/01/31/21/46'),(11,'TongXingWen','OnlyKnow','2018/02/03/22/30'),(12,'OnlyKnow','OnlyKnow','2018/02/04/01/45'),(13,'TongXingWen','TongXingWen','2018/02/04/19/03'),(14,'ttyj71111','TongXingWen','2018/02/08/19/03');

/*Table structure for table `usercardmain` */

DROP TABLE IF EXISTS `usercardmain`;

CREATE TABLE `usercardmain` (
  `CARD_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `TITLE_TEXT` varchar(20) NOT NULL,
  `TITLE_IMAGE_URL` varchar(100) DEFAULT 'NULL',
  `CARD_TYPE` varchar(10) NOT NULL,
  `CONTENT_IMAGE_URL` text,
  `CONTENT_TITLE_TEXT` varchar(50) DEFAULT 'NULL',
  `CONTENT_TEXT` text,
  `CREATE_DATE` varchar(20) NOT NULL,
  `LABELLING` varchar(20) DEFAULT 'NULL',
  `ZAN_NUM` int(11) DEFAULT '0',
  `SHOUCHAN_NUM` int(11) DEFAULT '0',
  `PINGLUN_NUM` int(11) DEFAULT '0',
  `MESSAGE_LINK` varchar(100) DEFAULT 'NULL',
  PRIMARY KEY (`CARD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

/*Data for the table `usercardmain` */

insert  into `usercardmain`(`CARD_ID`,`USER_NAME`,`TITLE_TEXT`,`TITLE_IMAGE_URL`,`CARD_TYPE`,`CONTENT_IMAGE_URL`,`CONTENT_TITLE_TEXT`,`CONTENT_TEXT`,`CREATE_DATE`,`LABELLING`,`ZAN_NUM`,`SHOUCHAN_NUM`,`PINGLUN_NUM`,`MESSAGE_LINK`) values (1,'OnlyKnow','唯知','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/OnlyKnow20180204013829.jpg','IMAGE_TEXT','http://101.132.168.25:8090/WeiZhiService/CardImage/uuid1757965313.jpg','苹果承认限制老款iPhone性能 目的旨在减少耗电','苹果称限制老款iPhone性能目的在于避免老设备电池耗尽自动关机\r\n此前有网友发布信息表示，“苹果故意限制了旧款iPhone性能，有可能是为了让果粉们购买最新款。”对此苹果发布声明称,特定条件下限制部分老款机型处理器峰值性能可以降低能耗，目的在于避免老设备电池耗尽自动关机\r\n本报讯（记者 任笑元）昨日苹果发出的一份技术声明中，就电池及产品性能相关问题进行阐述。声明相关信息中承认，最近带来的iOS系统更新在低温等条件下能够限制部分老款机型的处理器峰值性能，其目的旨在减少耗电，防止老设备因为电池电量耗尽的原因导致关机。\r\n此前有网友发布信息表示，“苹果故意限制了旧款iPhone性能，有可能是为了让果粉们购买最新款。”苹果的声明没有直接回应上述传闻，但表示特定条件下限制部分老款机型处理器峰值性能可以降低能耗，目的在于避免老设备电池耗尽自动关机。\r\n苹果在声明中表示，苹果的目标是向客户提供最好的体验，其中包括总体性能和延长设备使用寿命。在低温环境中，锂离子电池提供峰值电流的能力会降低，随着时间推移，存储的电量会减少，这会导致设备为保护电子部件而关机。\r\n据介绍，去年苹果针对iPhone 6、iPhone 6s和iPhone SE发布了一个特性，在需要时平抑瞬时峰值电流，防止设备在上述情况下意外关机。在iOS 11.2中，苹果为iPhone 7发布了这一特性，并计划在未来使之支持其他产品。\r\n信息显示，对于部分老款iPhone的卡顿问题，近期屡有用户吐槽，也有海外机构援引手机跑分数据进行测试。针对这一现象，有行业人士分析指出，手机性能降低的程度部分取决于电池损耗程度。\r\n“锂离子电池技术依赖于化学反应，有一定的使用寿命，因此性能会随时间推移而减退。许多因素会造成锂离子电池损耗，其中包括使用情况、充电/放电周期和温度。”上述人士同时认为，选择更换电池的用户，可能会发现问题严重程度有所减轻，同时，这与测试过程中手机使用环境有关。','2017/12/22/10/06','科技,手机,IOS',2,2,22,'https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9965106133366152733%'),(2,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','TEXT','NULL','技术宅用机器学习给小电影换脸：叶奈法“真实出演”','最近一帮国外的绅士们开始研究起了怎么利用机器学习（machine learning algorithm，人工智能的一个分支）技术来将名人、女星的头像替换到小电影里，他们发现只需要一台电脑、几段高质量的视频素材以及一些随便就能入手的软件就能达到惊人的效果。\r\n于是乎，这群绅士们就开始尝试起把自己最喜欢的游戏角色头像替换到小电影里。例如就有一位国外论坛的网友尝试了把《巫师3》特莉丝的头像提取出来，然后“嫁接”小电影的一个演员身上。\r\n进行实验的绅士在相关论坛表示自己的实验进行地还挺顺利，他在评论里表示：“我认为这个用上特莉丝头像的实验品还不错，尽管训练数据（training data）并不理想。”\r\n还有绅士尝试了把叶奈法的脸“转移”到另一部小电影里，效果看起来并不是很理想，不过在模糊的画面下似乎还真的挺像那么回事...','2018/01/28/10/46','PHONE,GAME,DEVICE',2,2,1,'https://www.ithome.com/html/it/345427.htm'),(3,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','http://101.132.168.25:8090/WeiZhiService/CardImage/20180128085415_7132.jpg','NULL',NULL,'2018/01/28/10/46','IMAGE',2,4,0,'https://www.ithome.com/html/it/345427.htm'),(4,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','http://101.132.168.25:8090/WeiZhiService/CardImage/uuid_1234567899987.jpg','NULL',NULL,'2018/01/28/10/50','IMAGE',2,2,1,'NULL'),(12,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','http://101.132.168.25:8090/WeiZhiService/CardImage/314c34de4b5a40ad83e1db760dac5ee0.jpg','','','2018/01/30/17/53','',2,1,0,''),(21,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','http://101.132.168.25:8090/WeiZhiService/CardImage/ae645e6c9528475b8a020b954611b139.jpg','','','2018/01/30/19/33','',1,2,1,''),(24,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','http://101.132.168.25:8090/WeiZhiService/CardImage/ad6587f5b80748baa7408a4f91e90af4.jpg','','','2018/01/31/20/38','',0,0,0,''),(38,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','{\"count\":3,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/da103fc854524a12b438841be063ace1.jpg\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/b75dfdfcefca4b209653ffa60481ca24.jpg\",\"urlImage3\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/804d1cb9a36746ffb3f661809ba9a5a4.png\",\"urlImage4\":\"\",\"urlImage5\":\"\"}','','','2018/02/03/22/43','',0,0,0,''),(39,'OnlyKnow','唯知','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/OnlyKnow20180204013829.jpg','IMAGE_TEXT','{\"count\":5,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/47f6daa47b174f8990f0ca65230d1dc7.jpg\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/6c7b2379c6984b6eab15d52afc624f49.jpg\",\"urlImage3\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/fdd86c45f84944438a21137456aad0b9.jpg\",\"urlImage4\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/5219c9cb5d294ee2933c2e903d053ab3.jpg\",\"urlImage5\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/84c17209beb94456a2e90d48288c12c8.jpg\"}','阿房宫是谁烧的','从前，有位县督学来到县立中学视察工作。他一进校门，便见到该校的壁报上写有杜牧的诗句：停车坐爱枫林晚，霜叶红于二月花。\n\n　　督学的文学底子很厚，看到杜牧的诗句，油然想起《阿房宫赋》，于是顺口问起了学生： “你们知道阿房宫是谁烧的？”学生们一脸惶恐，不住地摇头：“不是我烧的，不是我烧的！”\n\n　　看到眼前的窘迫情景，督学啼笑皆非。他立即来到校长室，对校长指责道：“贵校学生国文程度低下，居然说阿房宫不是他烧的。”\n\n　　校长心平气和地说：“本校学生一向诚实，既然说不是他烧的，就一定不是他烧的。”\n\n　　督学非常气恼，他万万没有想到大名鼎鼎的校长居然不知道阿房宫是怎么一回事！他一气之下，写了一封呈文，给教育局长，禀明原委。\n\n　　不久，督学收到局长复函：“烧掉就算了，再拨经费重建阿房宫。”\n\n　　督学看后，目瞪口呆。','2018/02/04/01/43','小故事',1,1,2,'http://wap.xigushi.com/ymgs/14020.html'),(40,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE_TEXT','{\"count\":5,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/fb3b03da7466461f871c8167a96e5e03.jpg\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/71f026f9c07d4cd193317b2e923cf6d9.jpg\",\"urlImage3\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/f9770e42a307480c85903fbc42281080.png\",\"urlImage4\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/eb70917a3461499da136c3be415ba17d.jpg\",\"urlImage5\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/e9577ae3f01643b09e40211c2468881e.jpg\"}','继何炅干爹后，谢娜女儿干妈身份曝光，不是颖宝，而是做主持的她','继何炅干爹后，谢娜女儿干妈身份曝光，不是颖宝，而是做主持的她\n\n衣品家园\n\n·昨天\n\n自从娜娜在2月1日生下了一对双胞胎之后，几乎收到了整个娱乐圈好朋友们的祝福。不得不说娜娜这些年在娱乐圈集下了太多的好人缘。在怀孕的这段时间，虽说娜娜停工一个人在家里养胎，但好朋友们真的是陆陆续续来到她家来看望他。\n\n￼\n\n大家应该都知道刚刚荣升为爸爸的杰哥，瞬间就变成了一位晒娃狂魔了。自从1号开始，杰哥已经连续发了三条关于两个女儿的微博了。看来又是一位“女儿奴”上线了，刚刚晒娃女儿的脚丫后，又晒起了女儿的小手，并且夸赞自己的女儿很有弹钢琴的天赋。看来杰哥已经为女儿想好之后走的道路了，再加上和国际钢琴师郎朗是好朋友，看来女儿的钢琴天赋是不用愁了。\n\n￼\n\n昨天又晒出了为女儿写的新歌《Precious》，旋律中都是张杰满满的父爱。这首歌在几年前就已经突发奇想写好了。看来杰哥对宝宝的渴望是那么的真切。\n\n￼\n\n除了杰哥之外，最激动的应该就是何老师了。在娜娜生产那一天，何老师也是全程和杰哥一起陪着娜娜。也在第一时间和大家分享其中的动态。表示：有些变化真的是转瞬的。娜娜亲着宝宝含着眼泪的时候，小杰哄着说：爸爸在哦，爸爸在哦的时候，瞬间感觉两个人小孩长大了，真的很美好！还担心两个小天使干爹疼不过来。\n\n￼\n\n继何炅干爹之后，大家开始猜测干妈。之前网上曝出颖宝要担任娜娜女儿的干妈，这几天相继还为两个孩子送来了一对金锁。大家纷纷表示：颖宝要当干妈是下了血本了。不过这只是猜测，还没有被证实。直到这条微博曝出才知道真相。在娜娜生产的那天，著名的主持人阿雅发微博表示激动的心情：真的太开心了，从怀孕到现在你的变化是那么美好，我们一边缝娃娃一边想着宝宝出生后的模样。两个可爱的幸福小女孩，欢迎到来世界上，恭喜小杰和娜娜。\n\n','2018/02/07/09/14','娱乐',1,1,1,'没有参考链接'),(41,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','{\"count\":4,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/0b99f5df1ba14e299c5cb44664d34931.png\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/4360e87af55d4677847d5684fecbaecc.png\",\"urlImage3\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/673ca3ae265f43329a3065a5ec1252d2.png\",\"urlImage4\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/302dbd5f87684ba7a7436b11a364f388.jpg\",\"urlImage5\":\"\"}','','','2018/02/07/16/35','',0,0,0,''),(42,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE_TEXT','{\"count\":3,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/4da76a536a724a41b6c536b290f4f677.jpg\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/5be55ca0acae421cbc1feb21cbc0dce7.jpg\",\"urlImage3\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/079c99eaf2684e699e73d49e29ddf68d.jpg\",\"urlImage4\":\"\",\"urlImage5\":\"\"}','特殊情况来看啦啦啦','考虑考虑天天就考虑考虑','2018/02/09/15/10','下雨天啦啦啦啦',0,0,0,'图纪录片'),(43,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','TEXT','{\"count\":0,\"urlImage1\":\"\",\"urlImage2\":\"\",\"urlImage3\":\"\",\"urlImage4\":\"\",\"urlImage5\":\"\"}','哈哈哈','哟哟哟哟哟','2018/02/09/15/13','嘿嘿嘿',0,0,0,'喔喔'),(44,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','{\"count\":3,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/a5e6aea27fbe47eb9ef0e52566cea675.gif\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/150fa9b34d6c4f5fb70b326568d420aa.gif\",\"urlImage3\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/7ade5a73e98d430e9bef73b2c2807eb6.gif\",\"urlImage4\":\"\",\"urlImage5\":\"\"}','','','2018/02/09/15/13','',0,0,1,''),(45,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','{\"count\":2,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_cef77c59a6a74d33b84ab241d585790d.gif\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_06d8a2241b414d94ada3d0a752b3e5d2.gif\",\"urlImage3\":\"\",\"urlImage4\":\"\",\"urlImage5\":\"\"}','','','2018/02/09/16/07','',0,0,1,''),(46,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE_TEXT','{\"count\":2,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_d0fe613b623a437bb04e28272f323dfb.gif\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_11683e6df070483d82bb431916ad9252.gif\",\"urlImage3\":\"\",\"urlImage4\":\"\",\"urlImage5\":\"\"}','不错哟','哈哈哈拆白茶还白改吧改吧海白菜彼此彼此才','2018/02/09/16/09','懵逼',0,0,0,'木有'),(47,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','{\"count\":4,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_21096d4683c448a28d51dbff6e558bf3.jpg\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_e4467ef1995f45bc9cb84b0554887d5b.jpg\",\"urlImage3\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_f0b7d5299e1e464fa380fac25767556d.jpg\",\"urlImage4\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_409f46b8b57743579eba031fbf79a54f.jpg\",\"urlImage5\":\"\"}','','','2018/02/09/17/13','',0,0,0,''),(48,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','{\"count\":1,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_dcf050e2e5a64b9fb13becdaf38a46ef.gif\",\"urlImage2\":\"\",\"urlImage3\":\"\",\"urlImage4\":\"\",\"urlImage5\":\"\"}','','','2018/02/09/17/14','',0,0,0,''),(49,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','{\"count\":5,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_fdfe2e0fb53d40f98707f140d8f27fd9.jpg\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_813da4bfde5a4a319fd1af71cefd1ace.jpg\",\"urlImage3\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_3878ebba824b4e44ade6a57a8c167fa9.jpg\",\"urlImage4\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_0ac5858ae8d34a35a48ab57c1e91d2c4.jpg\",\"urlImage5\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_62ce2b21c068401985fa53ea60e9db22.jpg\"}','','','2018/02/09/17/15','',0,0,0,''),(50,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','{\"count\":4,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_8dcb4c695dc34923a5c221639091242f.gif\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_5d37d3c676484437b6942a59241702e9.gif\",\"urlImage3\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_6314d303cc864817b121e022c6d5e04c.jpg\",\"urlImage4\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_5a144f0975c94be8b9a40dc64f68f107.gif\",\"urlImage5\":\"\"}','','','2018/02/09/17/17','',0,0,0,''),(51,'OnlyKnow','唯知','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/OnlyKnow20180204013829.jpg','IMAGE','{\"count\":3,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/OnlyKnow_9c9fa4ab5f584383a2d04fe1b4e02743.jpg\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/OnlyKnow_d6fc2b4a45874f81a237843c8f50c7eb.jpg\",\"urlImage3\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/OnlyKnow_51bbc546edbd495495ce4ee2c8a612a5.jpg\",\"urlImage4\":\"\",\"urlImage5\":\"\"}','','','2018/02/09/17/19','',0,0,0,''),(52,'TongXingWen','我是童童','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','IMAGE','{\"count\":2,\"urlImage1\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_fba4a834ced741689119b8f8a95b73d4.jpg\",\"urlImage2\":\"http://101.132.168.25:8090/WeiZhiService/CardImage/TongXingWen_21723ca02c474068897e7eb692beb527.jpg\",\"urlImage3\":\"\",\"urlImage4\":\"\",\"urlImage5\":\"\"}','','','2018/02/09/20/47','',0,0,0,'');

/*Table structure for table `usercardminor` */

DROP TABLE IF EXISTS `usercardminor`;

CREATE TABLE `usercardminor` (
  `CARD_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `TITLE_TEXT` varchar(20) NOT NULL,
  `TITLE_IMAGE_URL` varchar(100) DEFAULT 'NULL',
  `CARD_TYPE` varchar(10) NOT NULL,
  `CONTENT_IMAGE_URL` text,
  `CONTENT_TITLE_TEXT` varchar(50) DEFAULT 'NULL',
  `CONTENT_TEXT` text,
  `CREATE_DATE` varchar(20) NOT NULL,
  `LABELLING` varchar(20) DEFAULT 'NULL',
  `ZAN_NUM` int(11) DEFAULT '0',
  `SHOUCHAN_NUM` int(11) DEFAULT '0',
  `PINGLUN_NUM` int(11) DEFAULT '0',
  `MESSAGE_LINK` varchar(100) DEFAULT 'NULL',
  PRIMARY KEY (`CARD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `usercardminor` */

insert  into `usercardminor`(`CARD_ID`,`USER_NAME`,`TITLE_TEXT`,`TITLE_IMAGE_URL`,`CARD_TYPE`,`CONTENT_IMAGE_URL`,`CONTENT_TITLE_TEXT`,`CONTENT_TEXT`,`CREATE_DATE`,`LABELLING`,`ZAN_NUM`,`SHOUCHAN_NUM`,`PINGLUN_NUM`,`MESSAGE_LINK`) values (1,'qianqianqian','记得勇敢','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/qianqianqian20180131214029.jpg','IMAGE','http://101.132.168.25:8090/WeiZhiService/CardImage/f7e444d6426749d684c5664300cc3052.jpeg','','','2018/01/31/21/48','',0,0,0,''),(2,'qianqianqian','记得勇敢','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/qianqianqian20180131214029.jpg','IMAGE','http://101.132.168.25:8090/WeiZhiService/CardImage/0561edaca5104a799fdb246f2e30f5bf.jpeg','','','2018/01/31/21/54','',0,0,0,'');

/*Table structure for table `usercomment` */

DROP TABLE IF EXISTS `usercomment`;

CREATE TABLE `usercomment` (
  `COM_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `CARD_ID` tinyint(4) NOT NULL,
  `MESSAGE` varchar(1000) DEFAULT 'NULL',
  `COM_ZAN` tinyint(4) DEFAULT '0',
  `COM_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`COM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `usercomment` */

insert  into `usercomment`(`COM_ID`,`USER_NAME`,`CARD_ID`,`MESSAGE`,`COM_ZAN`,`COM_DATE`) values (1,'TongXingWen',1,'ç«¥ææ',1,'2018/01/28/12/26'),(2,'TongXingWen',1,'ç«¥ææ',1,'2018/01/28/12/26'),(3,'TongXingWen',1,'ç«¥ææ',1,'2018/01/28/12/26'),(4,'TongXingWen',1,'AAAAA',1,'2018/01/28/12/26'),(5,'TongXingWen',1,'AAAAA',1,'2018/01/28/12/26'),(6,'TongXingWen',1,'AAAAA',1,'2018/01/28/12/26'),(7,'TongXingWen',1,'wwwww',1,'2018/01/28/12/27'),(8,'TongXingWen',1,'uuuuuu',1,'2018/01/28/14/33'),(9,'TongXingWen',1,'Hai',1,'2018/01/29/19/00'),(10,'TongXingWen',1,'hhhhhh',1,'2018/01/30/03/00'),(11,'TongXingWen',1,'jjjjjjj',1,'2018/01/30/03/01'),(12,'TongXingWen',1,'傻逼',1,'2018/01/31/10/39'),(13,'TongXingWen',4,'不错不错',1,'2018/01/31/12/53'),(14,'TongXingWen',27,'哈哈',1,'2018/02/02/00/21'),(15,'TongXingWen',1,'你是傻逼',1,'2018/02/02/00/22'),(16,'TongXingWen',22,'不错哟',0,'2018/02/02/08/31'),(17,'TongXingWen',37,'ððððð',0,'2018/02/02/08/34'),(18,'TongXingWen',1,'哈哈',0,'2018/02/02/09/48'),(19,'TongXingWen',1,'你是傻逼吗',0,'2018/02/02/09/48'),(20,'TongXingWen',1,'哈哈',0,'2018/02/02/09/50'),(21,'TongXingWen',1,'傻逼',0,'2018/02/02/09/50'),(22,'TongXingWen',1,'哈哈哈',1,'2018/02/02/09/53'),(23,'TongXingWen',1,'可以滴',1,'2018/02/03/21/06'),(24,'TongXingWen',1,'傻逼',1,'2018/02/03/21/10'),(25,'TongXingWen',1,'德恩',1,'2018/02/03/21/11'),(26,'TongXingWen',1,'弄哦哦',1,'2018/02/03/21/11'),(27,'OnlyKnow',2,'你真是个250',1,'2018/02/04/01/39'),(28,'OnlyKnow',39,' 欢迎来评论',2,'2018/02/04/01/44'),(29,'OnlyKnow',39,'督学非常气恼，他万万没有想到大名鼎鼎的校长居然不知道阿房宫是怎么一回事！他一气之下，写了一封呈文，给教育局长，禀明原委\n督学非常气恼，他万万没有想到大名鼎鼎的校长居然不知道阿房宫是怎么一回事！他一气之下，写了一封呈文，给教育局长，禀明原委',2,'2018/02/04/01/44'),(30,'TongXingWen',40,'不错不错',1,'2018/02/07/09/15'),(31,'ttyj71111',21,'1',0,'2018/02/08/19/03'),(32,'TongXingWen',45,'美女啊',1,'2018/02/09/16/14'),(33,'TongXingWen',44,'美女',1,'2018/02/09/16/14');

/*Table structure for table `usercommentreply` */

DROP TABLE IF EXISTS `usercommentreply`;

CREATE TABLE `usercommentreply` (
  `COMR_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `COM_ID` tinyint(4) NOT NULL,
  `USER_NAME` varchar(20) NOT NULL,
  `MESSAGE` varchar(1000) DEFAULT 'NULL',
  `COMR_ZAN` tinyint(4) DEFAULT '0',
  `COMR_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`COMR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `usercommentreply` */

insert  into `usercommentreply`(`COMR_ID`,`COM_ID`,`USER_NAME`,`MESSAGE`,`COMR_ZAN`,`COMR_DATE`) values (1,6,'TongXingWen','aaa',1,'2018/01/28/12/41'),(2,6,'TongXingWen','aaa',1,'2018/01/28/12/41'),(3,6,'TongXingWen','bbbb',1,'2018/01/28/14/33'),(4,9,'TongXingWen','haha',1,'2018/01/29/19/01'),(5,6,'TongXingWen','dssdvv',1,'2018/01/30/10/03'),(6,13,'TongXingWen','哈哈',1,'2018/01/31/12/53'),(7,13,'TongXingWen','哈哈',1,'2018/01/31/12/53'),(8,10,'TongXingWen','哈哈',0,'2018/02/07/21/32');

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `USER_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `USER_NICKNAME` varchar(20) NOT NULL,
  `USER_TYPE` varchar(10) NOT NULL,
  `USER_PASSWORD` varchar(20) NOT NULL,
  `USER_PHONE` varchar(20) NOT NULL,
  `USER_EMAIL` varchar(20) NOT NULL,
  `SEX` char(5) NOT NULL,
  `AGE` int(11) DEFAULT '-1',
  `BIRTH_DATE` varchar(20) DEFAULT 'NULL',
  `RE_DATE` varchar(20) NOT NULL,
  `HEAD_PORTRAIT_URL` varchar(100) NOT NULL,
  `HOMEPAGE_URL` varchar(100) NOT NULL,
  `TAG` varchar(200) DEFAULT 'NULL',
  `EDIT_DATE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

insert  into `userinfo`(`USER_ID`,`USER_NAME`,`USER_NICKNAME`,`USER_TYPE`,`USER_PASSWORD`,`USER_PHONE`,`USER_EMAIL`,`SEX`,`AGE`,`BIRTH_DATE`,`RE_DATE`,`HEAD_PORTRAIT_URL`,`HOMEPAGE_URL`,`TAG`,`EDIT_DATE`) values (1,'OnlyKnow','唯知','GR','19950912','17608408847','1031312698@qq.com','NAN',21,'1996/09/12','2017/12/22/09/46','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/OnlyKnow20180204013829.jpg','http://101.132.168.25:8090/WeiZhiService/UserHead/OnlyKnow.jpg','欢迎来到唯知空间!','2018/02/04/01/38/29'),(2,'TongXingWen','我是童童','GR','19950912TXW','13243695692','3232086497@qq.com','NAN',0,'1995/09/12','2018/01/28/02/42','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/TongXingWen20180130100840.jpg','http://101.132.168.25:8090/WeiZhiService/UserHead/TongXingWen.jpg','哈哈真开心！','2018/01/31/12/41/56'),(3,'PaoPaoPao2','POPOPO','GR','123456789TXW','13174265119','761297812@qq.com','NAN',0,'NULL','2018/01/30/21/58','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/PaoPaoPao2.jpg','http://101.132.168.25:8090/WeiZhiService/UserHead/PaoPaoPao2.jpg','NULL','2018/01/30/21/58/33'),(4,'1031312698','童星文同学','GR','123456789','13174265118','124333775@qq.com','NAN',0,'NULL','2018/01/31/01/01','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/1031312698.jpg','http://101.132.168.25:8090/WeiZhiService/UserHead/1031312698.jpg','NULL','2018/01/31/01/01/28'),(5,'qianqianqian','记得勇敢','GR','qianqian','15211353792','914782836@qq.com','NV',0,'1996/10/19','2018/01/31/21/39','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/qianqianqian20180131214029.jpg','http://101.132.168.25:8090/WeiZhiService/UserHead/qianqianqian.jpg','人这一生几个弯几个伴。','2018/01/31/22/04/22'),(6,'ttyj71111','11','GR','1','11475544','sklso@qq.com','null',0,'NULL','2018/02/08/19/03','http://101.132.168.25:8090/WeiZhiService/UserHeadPortrait/ttyj71111.jpg','http://101.132.168.25:8090/WeiZhiService/UserHead/ttyj71111.jpg','NULL','2018/02/08/19/03/12');

/*Table structure for table `userlocation` */

DROP TABLE IF EXISTS `userlocation`;

CREATE TABLE `userlocation` (
  `UL_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `Loc_Longitude` double NOT NULL,
  `Loc_Dimension` double NOT NULL,
  `UL_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`UL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `userlocation` */

insert  into `userlocation`(`UL_ID`,`USER_NAME`,`Loc_Longitude`,`Loc_Dimension`,`UL_DATE`) values (1,'TongXingWen',112.913604,112.913604,'2018/01/28/03/12'),(2,'OnlyKnow',112.986123,112.986123,'2018/01/30/21/51'),(3,'PaoPaoPao2',112.913478,112.913478,'2018/01/30/22/00'),(4,'qianqianqian',112.405691,112.405691,'2018/01/31/22/10');

/*Table structure for table `usermessage` */

DROP TABLE IF EXISTS `usermessage`;

CREATE TABLE `usermessage` (
  `UM_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME_REC` varchar(20) NOT NULL,
  `USER_NAME_SEND` varchar(20) NOT NULL,
  `NEIRON_TEXT` varchar(1000) DEFAULT 'NULL',
  `UM_DATE` varchar(20) NOT NULL,
  `UM_STATE` int(11) DEFAULT '-1',
  PRIMARY KEY (`UM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usermessage` */

/*Table structure for table `userpraise` */

DROP TABLE IF EXISTS `userpraise`;

CREATE TABLE `userpraise` (
  `UPR_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `CARD_ID` tinyint(4) NOT NULL,
  `UPR_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`UPR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `userpraise` */

insert  into `userpraise`(`UPR_ID`,`USER_NAME`,`CARD_ID`,`UPR_DATE`) values (1,'TongXingWen',1,'2018/01/28/09/27'),(2,'TongXingWen',2,'2018/01/29/09/35'),(3,'TongXingWen',3,'2018/01/30/00/43'),(4,'TongXingWen',5,'2018/01/30/00/45'),(5,'TongXingWen',4,'2018/01/30/00/45'),(6,'OnlyKnow',3,'2018/01/31/08/40'),(7,'OnlyKnow',2,'2018/01/31/08/41'),(8,'OnlyKnow',21,'2018/01/31/08/41'),(9,'OnlyKnow',4,'2018/01/31/08/41'),(10,'TongXingWen',12,'2018/01/31/12/53'),(11,'qianqianqian',12,'2018/01/31/21/55'),(12,'TongXingWen',36,'2018/02/01/19/32'),(13,'OnlyKnow',39,'2018/02/04/01/44'),(14,'OnlyKnow',1,'2018/02/04/02/03'),(15,'TongXingWen',40,'2018/02/07/09/14');

/*Table structure for table `userreport` */

DROP TABLE IF EXISTS `userreport`;

CREATE TABLE `userreport` (
  `URP_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME_MAIN` varchar(20) NOT NULL,
  `USER_NAME_RETE` varchar(20) NOT NULL,
  `URP_MESSAGE` varchar(250) NOT NULL,
  `URP_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`URP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `userreport` */

insert  into `userreport`(`URP_ID`,`USER_NAME_MAIN`,`USER_NAME_RETE`,`URP_MESSAGE`,`URP_DATE`) values (1,'TongXingWen','OnlyKnow','JuBao_itme2/JuBao_itme3/JuBao_itme4','2018/01/29/23/56');

/*Table structure for table `uservalue` */

DROP TABLE IF EXISTS `uservalue`;

CREATE TABLE `uservalue` (
  `UV_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `ME_WATCH` int(11) DEFAULT '0',
  `ME_ATTENTION` int(11) DEFAULT '0',
  `ME_INTEGRAL` int(11) DEFAULT '0',
  `ME_ARTICLE` int(11) DEFAULT '0',
  `UP_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`UV_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `uservalue` */

insert  into `uservalue`(`UV_ID`,`USER_NAME`,`ME_WATCH`,`ME_ATTENTION`,`ME_INTEGRAL`,`ME_ARTICLE`,`UP_DATE`) values (1,'OnlyKnow',5,2,9834,2,'2018/02/04/02/03'),(2,'TongXingWen',5,2,21,31,'2018/02/07/09/14'),(3,'PaoPaoPao2',0,0,0,0,'2018/01/30/21/58'),(4,'1031312698',0,0,0,0,'2018/01/31/01/01'),(5,'qianqianqian',0,1,11,2,'2018/01/31/21/55'),(6,'ttyj71111',1,1,15,0,'2018/02/08/19/03');

/*Table structure for table `userwatch` */

DROP TABLE IF EXISTS `userwatch`;

CREATE TABLE `userwatch` (
  `UW_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `CARD_ID` tinyint(4) NOT NULL,
  `UW_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`UW_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `userwatch` */

insert  into `userwatch`(`UW_ID`,`USER_NAME`,`CARD_ID`,`UW_DATE`) values (1,'TongXingWen',1,'2018/01/28/09/27'),(4,'TongXingWen',5,'2018/01/30/00/45'),(6,'OnlyKnow',3,'2018/01/31/08/40'),(7,'OnlyKnow',2,'2018/01/31/08/41'),(9,'OnlyKnow',4,'2018/01/31/08/41'),(12,'TongXingWen',36,'2018/02/01/19/32'),(13,'OnlyKnow',39,'2018/02/04/01/44'),(14,'OnlyKnow',1,'2018/02/04/02/03'),(15,'TongXingWen',3,'2018/02/04/19/03'),(16,'TongXingWen',40,'2018/02/07/09/14'),(17,'ttyj71111',21,'2018/02/08/19/03');

/*Table structure for table `withoutapproval` */

DROP TABLE IF EXISTS `withoutapproval`;

CREATE TABLE `withoutapproval` (
  `WA_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `WA_TYPE` varchar(10) NOT NULL,
  `WA_DESCRIBE` varchar(100) DEFAULT '该用户免审批过程',
  `WA_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`WA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `withoutapproval` */

insert  into `withoutapproval`(`WA_ID`,`USER_NAME`,`WA_TYPE`,`WA_DESCRIBE`,`WA_DATE`) values (1,'TongXingWen','ADD_CARD','该用户免审批过程','2018/01/30/16/30'),(2,'TongXingWen','ADD_CARD','该用户免审批过程','2018/02/01/18/21'),(3,'OnlyKnow','ADD_CARD','该用户免审批过程','2018/02/04/01/29');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
