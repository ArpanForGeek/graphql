CREATE DATABASE demo_user;

USE demo_user;


CREATE TABLE `role_tbl` (
  `role_id` int NOT NULL,
  `role_name` varchar(100) NOT NULL,
  `is_active` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
);


CREATE TABLE `user_profile_tbl` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(200) NOT NULL,
  `first_name` varchar(200) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `email_id` varchar(200) NOT NULL,
  `contact_no` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `is_active` int NOT NULL DEFAULT '1',
  `is_default` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `email_id_UNIQUE` (`email_id`),
  UNIQUE KEY `contact_no_UNIQUE` (`contact_no`)
) ;


CREATE TABLE `user_role_tbl` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role_tbl` (`role_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user_profile_tbl` (`user_id`)
) ;