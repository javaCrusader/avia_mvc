CREATE DATABASE  IF NOT EXISTS `test`;
USE `test`;


DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `roles`;


--
-- Table structure for table `user`
--
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONENUMBER` varchar(255) DEFAULT NULL,
  `BALANCE` int(11) DEFAULT NULL,
  `PASSPORT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--
-- Table structure for table `role`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



CREATE TABLE user_roles (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_role_roleid_idx` (`role_id`),
  CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`role_id`) REFERENCES roles (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`user_id`) REFERENCES users (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `persistent_logins`;
-- autologin

CREATE TABLE persistent_logins (
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
);

--
-- Dumping data for table `role`
--

LOCK TABLES `roles` WRITE;
INSERT INTO `roles` VALUES (1,'ROLE_USER');
INSERT INTO `roles` VALUES (2,'ROLE_ADMIN');
INSERT INTO `roles` VALUES (3,'ROLE_CREWMANAGER');
INSERT INTO `roles` VALUES (4,'ROLE_ACCOUNTANT');
INSERT INTO `roles` VALUES (5,'ROLE_FLIGHTMANAGER');
INSERT INTO `roles` VALUES (6,'ROLE_AIRCRAFTMANAGER');
UNLOCK TABLES;

DROP TABLE IF EXISTS `company_roles`;
CREATE TABLE `company_roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `company_roles` (`ID`, `NAME`) VALUES ('1', 'FUNC_PILOT');
INSERT INTO `company_roles` (`ID`, `NAME`) VALUES ('2', 'FUNC_SECOND_PILOT');
INSERT INTO `company_roles` (`ID`, `NAME`) VALUES ('3', 'FUNC_STEWARD');
INSERT INTO `company_roles` (`ID`, `NAME`) VALUES ('4', 'FUNC_ENGINEER');

DROP TABLE IF EXISTS `class_data`;
CREATE TABLE `class_data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `class_data` (`ID`, `NAME`) VALUES ('1', 'luxe');
INSERT INTO `class_data` (`ID`, `NAME`) VALUES ('2', 'econom');
INSERT INTO `class_data` (`ID`, `NAME`) VALUES ('3', 'business');
INSERT INTO `class_data` (`ID`, `NAME`) VALUES ('4', 'super_luxe');

DROP TABLE IF EXISTS `country_list`;
CREATE TABLE `country_list` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

LOCK TABLES `country_list` WRITE;
INSERT INTO `country_list` (`ID`,`NAME`) VALUES (2,'America');
INSERT INTO `country_list` (`ID`,`NAME`) VALUES (5,'England');
INSERT INTO `country_list` (`ID`,`NAME`) VALUES (3,'Germany');
INSERT INTO `country_list` (`ID`,`NAME`) VALUES (6,'Kazakhstan');
INSERT INTO `country_list` (`ID`,`NAME`) VALUES (1,'Russia');
INSERT INTO `country_list` (`ID`,`NAME`) VALUES (4,'Spain');
UNLOCK TABLES;

DROP TABLE IF EXISTS `city_list`;
CREATE TABLE `city_list` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LATITUDE` double DEFAULT NULL,
  `LONGITUDE` double DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `country_id` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`),
  KEY `FK_city_list_country_id` (`country_id`),
  CONSTRAINT `FK_city_list_country_id` FOREIGN KEY (`country_id`) REFERENCES `country_list` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


LOCK TABLES `city_list` WRITE;
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (1,NULL,NULL,'Moscow',1);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (2,NULL,NULL,'Ulyanovsk',1);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (3,NULL,NULL,'Saint-petrsburg',1);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (4,NULL,NULL,'Volgograd',1);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (5,NULL,NULL,'New york',2);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (6,NULL,NULL,'Vasington',2);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (7,NULL,NULL,'Chicago',2);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (8,NULL,NULL,'San francisko',2);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (9,NULL,NULL,'Berlin',3);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (10,NULL,NULL,'Zaltsburg',3);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (11,NULL,NULL,'Madrid',4);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (12,NULL,NULL,'Barcelona',4);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (13,NULL,NULL,'London',5);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (14,NULL,NULL,'Manchester',5);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (15,NULL,NULL,'Birmingem',5);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (16,NULL,NULL,'Astana',6);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (17,NULL,NULL,'Uralsk',6);
INSERT INTO `city_list` (`ID`,`LATITUDE`,`LONGITUDE`,`NAME`,`country_id`) VALUES (18,NULL,NULL,'Almata',6);
UNLOCK TABLES;

DROP TABLE IF EXISTS `vacations`;
CREATE TABLE `vacations` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `END` date DEFAULT NULL,
  `START` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `vacations` (`ID`,`END`,`START`) VALUES (1,'2019-03-04','2019-03-04');
INSERT INTO `vacations` (`ID`,`END`,`START`) VALUES (2,'2018-02-02','2018-03-05');
INSERT INTO `vacations` (`ID`,`END`,`START`) VALUES (3,'2018-08-03','2018-06-09');
INSERT INTO `vacations` (`ID`,`END`,`START`) VALUES (4,'2021-07-03','2018-07-03');
INSERT INTO `vacations` (`ID`,`END`,`START`) VALUES (5,'2019-11-03','2019-10-10');
INSERT INTO `vacations` (`ID`,`END`,`START`) VALUES (6,'2018-05-03','2018-04-06');
INSERT INTO `vacations` (`ID`,`END`,`START`) VALUES (7,'2018-06-03','2018-05-03');
INSERT INTO `vacations` (`ID`,`END`,`START`) VALUES (8,'2019-02-02','2019-02-05');


DROP TABLE IF EXISTS `crew`;
CREATE TABLE `crew` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `SALARYINHOUR` int(11) DEFAULT NULL,
  `function_id` int(11) NOT NULL,
  `vacation_id` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_crew_function_id` (`function_id`),
  KEY `FK_crew_vacation_id` (`vacation_id`),
  CONSTRAINT `FK_crew_function_id` FOREIGN KEY (`function_id`) REFERENCES `company_roles` (`ID`),
  CONSTRAINT `FK_crew_vacation_id` FOREIGN KEY (`vacation_id`) REFERENCES `vacations` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (1,'ivan',2323,1,1);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (2,'blevan',232,2,2);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (3,'kolyan',342,3,3);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (4,'serega',343,4,4);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (5,'hower',23,1,5);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (6,'samsung',23,2,6);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (7,'preved',2323,3,7);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (8,'diman',2323,4,8);

