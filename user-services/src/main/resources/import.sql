--
-- Sample dataset containing a number of Users and related Workgroups.   :)
--
-- =================================================================================================

insert into app_user(user_id, original_id, email_id, active_ind, src_system, is_synced, is_system_acc, inherited_user_id, supervisor_id) values (0, 'empSys', 'system@test.com', 'ACTIVE', 'SYSTEM', 'N', 'Y', 0, 0);

insert into app_user(user_id, original_id, first_name, last_name, email_id, active_ind, src_system, is_synced, is_system_acc, inherited_user_id, supervisor_id) values (1, 'emp101', 'Rao', 'Ganguri', 'rao@test.com', 'ACTIVE', 'SYSTEM', 'N', 'N', 0, 0);
insert into app_user(user_id, original_id, first_name, last_name, email_id, active_ind, src_system, is_synced, is_system_acc, inherited_user_id, supervisor_id) values (2, 'emp102', 'Bhalla', 'Bikas', 'bhalla@test.com', 'ACTIVE', 'SYSTEM', 'N', 'N', 0, 0);
insert into app_user(user_id, original_id, first_name, last_name, email_id, active_ind, src_system, is_synced, is_system_acc, inherited_user_id, supervisor_id) values (3, 'emp103', 'Jaiswal', 'Mohit', 'jaiswal@test.com', 'ACTIVE', 'SYSTEM', 'N', 'N', 0, 0);

insert into app_group(group_id, group_name, group_desc, active_ind, owner_id, group_type) values (101, 'HASALL', 'General for all the users', 'ACTIVE', 0, 'STATIC');
insert into app_group(group_id, group_name, group_desc, active_ind, owner_id, group_type) values (102, 'TESTALL', 'General for all the testers', 'ACTIVE', 0, 'STATIC');

insert into app_user_group_role(role_id, user_id, group_id, active_ind, role_name) values (1001, 1, 101, 'ACTIVE', 'TECH_ALL');
insert into app_user_group_role(role_id, user_id, group_id, active_ind, role_name) values (1002, 2, 101, 'ACTIVE', 'TECH_ALL');

insert into app_user_group_role(role_id, user_id, group_id, active_ind, role_name) values (1003, 3, 102, 'ACTIVE', 'TECH_ALL');
