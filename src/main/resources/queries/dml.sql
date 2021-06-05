/* UserProfile Table queries */
INSERT INTO `demo_user`.`user_profile_tbl` (`user_id`, `user_name`, `first_name`, `last_name`, `email_id`, `contact_no`, `password`, `is_active`) VALUES ('1', 'naresh', 'Naresh', 'Bairwa', 'naresh@gmail.com', '9990382328', 'tr3XzLKQ18tIdHh6/awqzw==', '1');
INSERT INTO `demo_user`.`user_profile_tbl` (`user_id`, `user_name`, `first_name`, `last_name`, `email_id`, `contact_no`, `password`, `is_active`) VALUES ('2', 'neha', 'Neha', 'Maheshwari', 'neha@gmail.com', '9992847229', 'tr3XzLKQ18tIdHh6/awqzw==', '1');
INSERT INTO `demo_user`.`user_profile_tbl` (`user_id`, `user_name`, `first_name`, `last_name`, `email_id`, `contact_no`, `password`, `is_active`) VALUES ('3', 'devyani', 'Devyani', 'Joshi', 'devyani@gmail.com', '9993829838', 'tr3XzLKQ18tIdHh6/awqzw==', '1');
INSERT INTO `demo_user`.`user_profile_tbl` (`user_id`, `user_name`, `first_name`, `last_name`, `email_id`, `contact_no`, `password`, `is_active`) VALUES ('4', 'arpan', 'Apran', 'B', 'arpan@gmail.com', '9937383933', 'tr3XzLKQ18tIdHh6/awqzw==', '1');
INSERT INTO `demo_user`.`user_profile_tbl` (`user_id`, `user_name`, `first_name`, `last_name`, `email_id`, `contact_no`, `password`, `is_active`) VALUES ('5', 'rahil', 'Rahil', 'Zafar', 'rahil@gmail.com', '9927374837', 'tr3XzLKQ18tIdHh6/awqzw==', '0');
INSERT INTO `demo_user`.`user_profile_tbl` (`user_id`, `user_name`, `first_name`, `last_name`, `email_id`, `contact_no`, `password`, `is_active`) VALUES ('6', 'arjun', 'Arjun', 'Kumar', 'arjun@gmail.com', '9938384373', 'tr3XzLKQ18tIdHh6/awqzw==', '1');

/* Role table queries */
INSERT INTO `demo_user`.`role_tbl`(`role_id`,`role_name`,`is_active`) VALUES (1,"ADMIN",1);
INSERT INTO `demo_user`.`role_tbl`(`role_id`,`role_name`,`is_active`) VALUES (2,"APPROVER",1);
INSERT INTO `demo_user`.`role_tbl`(`role_id`,`role_name`,`is_active`) VALUES (3,"CONTRIBUTOR",1);
INSERT INTO `demo_user`.`role_tbl`(`role_id`,`role_name`,`is_active`)VALUES(4,"BASIC",1);

/* User role  mapping table queries */
INSERT INTO `demo_user`.`user_role_tbl` (`user_id`, `role_id`) VALUES ('2', '1');
INSERT INTO `demo_user`.`user_role_tbl` (`user_id`, `role_id`) VALUES ('1', '2');
INSERT INTO `demo_user`.`user_role_tbl` (`user_id`, `role_id`) VALUES ('3', '3');
INSERT INTO `demo_user`.`user_role_tbl` (`user_id`, `role_id`) VALUES ('4', '4');
INSERT INTO `demo_user`.`user_role_tbl` (`user_id`, `role_id`) VALUES ('6', '1');
INSERT INTO `demo_user`.`user_role_tbl` (`user_id`, `role_id`) VALUES ('6', '2');
INSERT INTO `demo_user`.`user_role_tbl` (`user_id`, `role_id`) VALUES ('6', '3');




