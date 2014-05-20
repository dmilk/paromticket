/*
-- Query: SELECT * FROM restservice.user_role
LIMIT 0, 1000

-- Date: 2014-05-12 14:43
*/
delete from security_user_role;
INSERT INTO `security_user_role` (`security_user_id`,`role_id`) VALUES (1,1);
INSERT INTO `security_user_role` (`security_user_id`,`role_id`) VALUES (2,2);
INSERT INTO `security_user_role` (`security_user_id`,`role_id`) VALUES (3,2);
INSERT INTO `security_user_role` (`security_user_id`,`role_id`) VALUES (3,1);
