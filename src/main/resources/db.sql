CREATE DATABASE  IF NOT EXISTS `test`;
USE `test`;

--
-- Table structure for table `user_role`
--
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;



CREATE TABLE users (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Table structure for table `role`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


--
-- Table structure for table `user`
--

CREATE TABLE user_roles (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_role_roleid_idx` (`role_id`),
  CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`role_id`) REFERENCES roles (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`user_id`) REFERENCES users (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



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
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN');
INSERT INTO `roles` VALUES (1,'ROLE_CREWMANAGER');
INSERT INTO `roles` VALUES (1,'ROLE_ACCOUNTANT');
INSERT INTO `roles` VALUES (1,'ROLE_FLIGHTMANAGER');
INSERT INTO `roles` VALUES (1,'ROLE_AIRCRAFTMANAGER');
UNLOCK TABLES;


LOCK TABLES `country_list` WRITE;
INSERT INTO `country_list` (`ID`,`NAME`) VALUES (2,'America');
INSERT INTO `country_list` (`ID`,`NAME`) VALUES (5,'England');
INSERT INTO `country_list` (`ID`,`NAME`) VALUES (3,'Germany');
INSERT INTO `country_list` (`ID`,`NAME`) VALUES (6,'Kazakhstan');
INSERT INTO `country_list` (`ID`,`NAME`) VALUES (1,'Russia');
INSERT INTO `country_list` (`ID`,`NAME`) VALUES (4,'Spain');
UNLOCK TABLES;

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


LOCK TABLES `crew` WRITE;
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (20,'preved',13,1,90);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (21,'andrew',4,4,91);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (23,'kolyan',5,9,93);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (24,'leha',13,1,94);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (25,'boris',13,10,95);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (26,'ivan',10,1,96);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (27,'mary',5,9,97);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (28,'serega',13,10,98);
INSERT INTO `crew` (`ID`,`NAME`,`SALARYINHOUR`,`function_id`,`vacation_id`) VALUES (29,'eduard',20,4,99);
UNLOCK TABLES;

