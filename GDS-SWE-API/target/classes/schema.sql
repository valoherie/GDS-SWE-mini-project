CREATE TABLE users (
    `user_id` INTEGER NOT NULL AUTO_INCREMENT,
    `name` varchar(200) NOT NULL,
    `salary` decimal(10,2) NOT NULL,
    `rundate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`)
);







