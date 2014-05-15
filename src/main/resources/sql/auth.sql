delete from auth;

INSERT INTO `auth` (`user_id`, `user_name`, `password`) VALUES (1,'admin','12345');
INSERT INTO `auth` (`user_id`, `user_name`, `password`) VALUES (2,'user1','123');
INSERT INTO `auth` (`user_id`, `user_name`, `password`) VALUES (3,'user2','321');
--ALTER TABLE `restservice`.`auth`
--ADD INDEX `athe_f_idx` (`user_id` ASC);
--ALTER TABLE `restservice`.`auth`
--ADD CONSTRAINT `auth_f`
--  FOREIGN KEY (`user_id`)
--  REFERENCES `restservice`.`user` (`user_id`)
--  ON DELETE CASCADE
--  ON UPDATE CASCADE;
