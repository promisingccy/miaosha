/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.5.56-log : Database - miaosha
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`miaosha` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `miaosha`;

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:男， 1: 女',
  `age` int(11) NOT NULL DEFAULT '0' COMMENT '年龄',
  `telphone` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `register_mode` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '注册模式 bywechat,byalipay,byphone',
  `third_party_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '第三方id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


/*Table structure for table `user_password` */

DROP TABLE IF EXISTS `user_password`;

CREATE TABLE `user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` DOUBLE NOT NULL DEFAULT 0 COMMENT '商品价格',
  `sales` INT(11) NOT NULL DEFAULT 0 COMMENT '商品销量',
  `description` VARCHAR(500) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品描述',
  `img_url` VARCHAR(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品图片url',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `stock` INT(11) NOT NULL DEFAULT 0 COMMENT '商品库存',
  `item_id` INT(11) NOT NULL DEFAULT 0 COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `order_info` */

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `item_id` int(11) NOT NULL DEFAULT '0',
  `item_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `amount` int(11) NOT NULL DEFAULT '0',
  `order_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `promo_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


/*Table structure for table `sequence_info` */

DROP TABLE IF EXISTS `sequence_info`;

CREATE TABLE `sequence_info` (
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `current_value` int(11) NOT NULL DEFAULT '0',
  `step` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sequence_info` */

insert  into `sequence_info`(`name`,`current_value`,`step`) values
('order_info',4,1);

/*Table structure for table `promo` */

DROP TABLE IF EXISTS `promo`;

CREATE TABLE `promo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `start_date` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `end_date` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `item_id` int(11) NOT NULL DEFAULT '0',
  `promo_item_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
