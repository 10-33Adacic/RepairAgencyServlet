CREATE DATABASE rep_service;

CREATE TABLE `comment`
(
  `id` int(11) NOT NULL AUTO_INCREMENT,

  `comment` varchar(1024) NOT NULL,

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

  `creator` varchar(64) NOT NULL,

  `user_id` int(11) DEFAULT NULL,

  `request_number` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`)
);

# CREATE TABLE `role` (
#
#   `id` int(11) NOT NULL AUTO_INCREMENT,
#
#   `name` varchar(15) NOT NULL,
#
#   PRIMARY KEY (`id`)
# );

CREATE TABLE `user` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `email` varchar(64) NOT NULL,

  `password` varchar(128) NOT NULL,

  `active` tinyint(1) NOT NULL,

  `role` varchar(64) NOT NULL,

  PRIMARY KEY (`id`)
);

# CREATE TABLE `user_role` (
#
#   `user_id` int(11) NOT NULL,
#
#   `role_id` int(11) NOT NULL
# );


INSERT INTO `rep_service`.`user` (`email`, `password`, `active`) VALUES ('m@m.m', '11111', '1');

INSERT INTO `rep_service`.`user` (`email`, `password`, `active`) VALUES ('master1@m', '11111', '1');

INSERT INTO `rep_service`.`user` (`email`, `password`, `active`) VALUES ('u@u.u', '11111', '1');


# INSERT INTO `rep_service`.`role` (`id`, `name`) VALUES ('1', 'ROLE_USER');
#
# INSERT INTO `rep_service`.`role` (`id`, `name`) VALUES ('2', 'ROLE_MANAGER');
#
# INSERT INTO `rep_service`.`role` (`id`, `name`) VALUES ('3', 'ROLE_MASTER');


# INSERT INTO `rep_service`.`user_role` (`user_id`, `role_id`) VALUES ('1', '2');
#
# INSERT INTO `rep_service`.`user_role` (`user_id`, `role_id`) VALUES ('2', '3');
# INSERT INTO `rep_service`.`user_role` (`user_id`, `role_id`) VALUES ('3', '1');

INSERT INTO `rep_service`.`request` (`id`, `request`, `status`, `price`, `creator`) VALUES ('1', 'I have problem with front wheel. Please do smth with it', 'new', '0', 'u@u.u');

INSERT INTO `rep_service`.`request` (`id`, `request`, `status`, `price`, `creator`) VALUES ('2', 'My brake system is broken. Can you do smth with it?', 'new', '0', 'u@u.u');


INSERT INTO `rep_service`.`comment` (`id`, `comment`, `user_id`, `date`) VALUES ('1', 'Nice job', '1', '2019-08-31 00:00:00');

INSERT INTO `rep_service`.`comment` (`id`, `comment`, `user_id`, `date`) VALUES ('2', 'Good job', '1', '2019-08-27 00:00:00');
