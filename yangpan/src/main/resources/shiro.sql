/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.20-log : Database - shiro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shiro` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shiro`;

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `MENU_ID` varchar(32) NOT NULL COMMENT '菜单ID',
  `MENU_NO` varchar(16) NOT NULL COMMENT '菜单编码',
  `MENU_NAME` varchar(60) NOT NULL COMMENT '菜单名称',
  `UP_MENU_NO` varchar(16) NOT NULL COMMENT '上级菜单编码',
  `MENU_LEVEL` varchar(10) DEFAULT NULL COMMENT '菜单级别',
  `MENU_ISPARENT` int(11) DEFAULT NULL COMMENT '是否是父菜单',
  `MENU_ORDER` varchar(10) DEFAULT NULL COMMENT '菜单顺序',
  `MENU_URL` varchar(256) DEFAULT NULL COMMENT '菜单功能链接',
  `MENU_DESCR` varchar(60) DEFAULT NULL COMMENT '菜单描述menu_descr',
  `MENU_IMG` varchar(60) DEFAULT NULL COMMENT '菜单图标',
  `STATUS` varchar(1) DEFAULT NULL COMMENT '菜单状态\n                        参数表->状态：(0-无效; 1-有效)',
  `CREATED_BY` varchar(100) DEFAULT NULL COMMENT '创建人',
  `CREATED_DATE` date DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(100) DEFAULT NULL COMMENT '更新人',
  `UPDATED_DATE` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`MENU_ID`,`MENU_NO`,`MENU_NAME`,`UP_MENU_NO`,`MENU_LEVEL`,`MENU_ISPARENT`,`MENU_ORDER`,`MENU_URL`,`MENU_DESCR`,`MENU_IMG`,`STATUS`,`CREATED_BY`,`CREATED_DATE`,`UPDATED_BY`,`UPDATED_DATE`) values ('1','100100','系统管理','','0',0,'desc','.html',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('2','100100','用户管理','','0',0,'desc','.html',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('3','100100','权限管理','','0',0,'desc','.html',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('4','100100','角色管理','','0',0,'desc','.html',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('5','100100','登录管理','','1',1,'desc','.html',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('6','100100','日志管理','','1',1,'desc','.html',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('7','100100','用户列表','','1',1,'desc','.html',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('8','100100','角色列表','','1',1,'desc','.html',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('9','100100','菜单列表','','1',1,'desc','.html',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `PERMISSION_ID` varchar(50) NOT NULL COMMENT '权限id',
  `PERMISSION_NAME` varchar(60) NOT NULL COMMENT '功能名称',
  `INTERFACE_FUNCTION` varchar(60) NOT NULL COMMENT '接口方法',
  `PERMISSION_URL` varchar(120) NOT NULL COMMENT '服务URL',
  `STATUS` varchar(1) DEFAULT NULL COMMENT '状态\n                        参数表->状态：(0-无效; 1-有效)',
  `KEYWORD` varchar(50) DEFAULT NULL COMMENT '关键词',
  `REMARK` varchar(60) DEFAULT NULL COMMENT '备注',
  `CREATED_BY` varchar(100) DEFAULT NULL COMMENT '创建人',
  `CREATED_DATE` date DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(100) DEFAULT NULL COMMENT '更新人',
  `UPDATED_DATE` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务功能表 SYS_PERMISSION';

/*Data for the table `permission` */

insert  into `permission`(`PERMISSION_ID`,`PERMISSION_NAME`,`INTERFACE_FUNCTION`,`PERMISSION_URL`,`STATUS`,`KEYWORD`,`REMARK`,`CREATED_BY`,`CREATED_DATE`,`UPDATED_BY`,`UPDATED_DATE`) values ('100','增加','add','/add','1','add',NULL,NULL,NULL,NULL,NULL),('101','删除','delete','/detete','1','delete',NULL,NULL,NULL,NULL,NULL),('102','修改','update','/update','1','update',NULL,NULL,NULL,NULL,NULL),('103','查询','select','/select','1','select',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(60) NOT NULL COMMENT '角色名称',
  `ROLE_DESCR` varchar(60) DEFAULT NULL COMMENT '角色描述',
  `KEYWORD` varchar(50) DEFAULT NULL COMMENT '关键词',
  `STATUS` varchar(1) DEFAULT NULL COMMENT '状态\n                        参数表->状态：(0-无效; 1-有效)',
  `CREATED_BY` varchar(100) DEFAULT NULL COMMENT '创建人',
  `CREATED_DATE` date DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(100) DEFAULT NULL COMMENT '更新人',
  `UPDATED_DATE` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表SYS_ROLE';

/*Data for the table `role` */

insert  into `role`(`ROLE_ID`,`ROLE_NAME`,`ROLE_DESCR`,`KEYWORD`,`STATUS`,`CREATED_BY`,`CREATED_DATE`,`UPDATED_BY`,`UPDATED_DATE`) values ('11','超级管理员','拥有一切权限的人','administrator','1',NULL,NULL,NULL,NULL),('12','普通员工','有用增加查看权限的角色','employee','1',NULL,NULL,NULL,NULL);

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `MENU_ID` varchar(32) DEFAULT NULL COMMENT '菜单ID',
  KEY `FK_Reference_10` (`MENU_ID`),
  KEY `FK_Reference_6` (`ROLE_ID`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`MENU_ID`) REFERENCES `menu` (`MENU_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

/*Data for the table `role_menu` */

insert  into `role_menu`(`ROLE_ID`,`MENU_ID`) values ('11','1'),('11','2'),('11','3'),('11','4'),('11','5'),('11','6'),('11','7'),('11','8'),('11','9'),('12','1'),('12','2'),('12','3'),('12','4');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `PERMISSION_ID` varchar(50) DEFAULT NULL COMMENT '权限id',
  KEY `FK_Reference_5` (`ROLE_ID`),
  KEY `FK_Reference_7` (`PERMISSION_ID`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `permission` (`PERMISSION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色 权限关联表';

/*Data for the table `role_permission` */

insert  into `role_permission`(`ROLE_ID`,`PERMISSION_ID`) values ('11','102'),('11','103'),('12','100'),('12','103');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `USER_ID` varchar(32) NOT NULL COMMENT '用户id',
  `USER_NO` varchar(16) DEFAULT NULL COMMENT '用户编号：默认员工号',
  `USERNAME` varchar(30) DEFAULT NULL COMMENT '登录名',
  `CERT_NO` varchar(20) DEFAULT NULL COMMENT '证件号码',
  `MOBILE_TEL` varchar(20) NOT NULL COMMENT '移动电话',
  `EMAIL` varchar(50) NOT NULL COMMENT '电子邮箱',
  `STATUS` varchar(1) NOT NULL COMMENT '用户状态　\n                        参数表 USER_STATUS\n                        1 正常  0 停用  2 锁定  默认：1',
  `PSWD` varchar(64) DEFAULT NULL COMMENT '登录密码',
  `PSWD_LEVEL` varchar(1) DEFAULT NULL COMMENT '登录密码强度\n                        1-5级',
  `REMARK` varchar(60) DEFAULT NULL COMMENT '备注',
  `CREATED_BY` varchar(100) DEFAULT NULL COMMENT '创建人',
  `CREATED_DATE` date DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(100) DEFAULT NULL COMMENT '更新人',
  `UPDATED_DATE` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础信息表USER';

/*Data for the table `user` */

insert  into `user`(`USER_ID`,`USER_NO`,`USERNAME`,`CERT_NO`,`MOBILE_TEL`,`EMAIL`,`STATUS`,`PSWD`,`PSWD_LEVEL`,`REMARK`,`CREATED_BY`,`CREATED_DATE`,`UPDATED_BY`,`UPDATED_DATE`) values ('1','10011','yangpan',NULL,'18091681804','ypconfig@163.com','0','123456','1',NULL,NULL,NULL,NULL,NULL),('2','10001','zhangsan',NULL,'18091681805','zhangsan@qq.com','0','zhangsan123','2',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户id',
  KEY `FK_Reference_8` (`ROLE_ID`),
  KEY `FK_Reference_9` (`USER_ID`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

/*Data for the table `user_role` */

insert  into `user_role`(`ROLE_ID`,`USER_ID`) values ('11','1'),('12','2');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
