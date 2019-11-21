CREATE DATABASE rep_service;

CREATE TABLE `comment`
(
  `id` int(11) NOT NULL AUTO_INCREMENT,

  `comment` varchar(225) NOT NULL,

  `user_id` int(11) NOT NULL,

  `date` datetime NOT NULL,

  PRIMARY KEY (`id`)
);

CREATE TABLE `request` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `request` varchar(225) NOT NULL,

  `status` varchar(15) NOT NULL,

  `price` bigint(20) NOT NULL,

  `reason` varchar(225) DEFAULT NULL,

  `creator` varchar(25) NOT NULL,

  `user_id` int(11) DEFAULT NULL,

  `request_number` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`)
);

CREATE TABLE `role` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `name` varchar(15) NOT NULL,

  PRIMARY KEY (`id`)
);

CREATE TABLE `user` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `email` varchar(64) NOT NULL,

  `password` varchar(70) NOT NULL,

  `active` tinyint(1) NOT NULL,

  PRIMARY KEY (`id`)
);

CREATE TABLE `user_role` (

  `user_id` int(11) NOT NULL,

  `role_id` int(11) NOT NULL
);


INSERT INTO `reg_form`.`user` (`email`, `password`, `active`) VALUES ('m@m.m', '11111', '1');

INSERT INTO `reg_form`.`user` (`email`, `password`, `active`) VALUES ('master1@m', '11111', '1');

INSERT INTO `reg_form`.`user` (`email`, `password`, `active`) VALUES ('u@u.u', '11111', '1');


INSERT INTO `reg_form`.`role` (`id`, `name`) VALUES ('1', 'ROLE_USER');

INSERT INTO `reg_form`.`role` (`id`, `name`) VALUES ('2', 'ROLE_MANAGER');

INSERT INTO `reg_form`.`role` (`id`, `name`) VALUES ('3', 'ROLE_MASTER');


INSERT INTO `reg_form`.`user_role` (`user_id`, `role_id`) VALUES ('1', '2');

INSERT INTO `reg_form`.`user_role` (`user_id`, `role_id`) VALUES ('2', '3');
INSERT INTO `reg_form`.`user_role` (`user_id`, `role_id`) VALUES ('3', '1');

INSERT INTO `reg_form`.`request` (`id`, `request`, `status`, `price`, `creator`) VALUES ('1', 'My phone is broken. Please do smth with it', 'new', '0', 'u@u.u');

INSERT INTO `reg_form`.`request` (`id`, `request`, `status`, `price`, `creator`) VALUES ('2', 'My laptop is broken. Please do smth with it', 'new', '0', 'u@u.u');


INSERT INTO `reg_form`.`comment` (`id`, `comment`, `user_id`, `date`) VALUES ('1', 'Nice job', '1', '2019-08-31 00:00:00');

INSERT INTO `reg_form`.`comment` (`id`, `comment`, `user_id`, `date`) VALUES ('2', 'Good job', '1', '2019-08-27 00:00:00');
