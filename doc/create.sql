set global time_zone='+8:00';
CREATE database tool_platform CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

USE tool_platform;

CREATE TABLE `department` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `department_name` VARCHAR(128) NOT NULL COMMENT '部门名称',
    `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
    PRIMARY KEY (`id`)
) COMMENT '部门信息';

CREATE TABLE group_info
(
	`id` INT AUTO_INCREMENT,
	`type_id` INT DEFAULT '0' NOT NULL COMMENT '关联Id',
	`group_name` VARCHAR(64) NOT NULL COMMENT '分组名',
	`group_define` INT(4) DEFAULT '0' NOT NULL COMMENT '自定义: 0不是, 是',
	`group_sort` INT(4) DEFAULT '0' COMMENT '排序',
	`group_type` INT(4) NOT NULL COMMENT '分组类别：0数据库',
	`user_id` INT DEFAULT '0' NOT NULL COMMENT '用户Id',
	`create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
	PRIMARY KEY (`id`)
) COMMENT '分组表';

CREATE TABLE db_manage
(
	`id` INT AUTO_INCREMENT,
	`db_name` VARCHAR(255) NOT NULL COMMENT '自定义连接名',
	`db_host` VARCHAR(255) NOT NULL COMMENT '主机名',
	`db_port` INT(4) DEFAULT '0' NOT NULL COMMENT '端口号',
	`db_username` VARCHAR(32) NOT NULL COMMENT '连接用户名',
	`db_password` VARCHAR(128) NOT NULL COMMENT '连接密码',
	`db_schema_name` VARCHAR(32) NOT NULL COMMENT '实例名',
	`department_id` INT DEFAULT '0' NOT NULL COMMENT '关联部门Id',
	`user_id` INT DEFAULT '0' NOT NULL COMMENT '用户Id',
	`create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
	PRIMARY KEY (`id`)
) COMMENT '数据库表';

CREATE TABLE db_table
(
	`id` INT AUTO_INCREMENT,
	`db_id` INT DEFAULT '0' NOT NULL COMMENT '关联数据库Id',
	`table_zh_name` VARCHAR(255) NOT NULL COMMENT '表中文名',
	`table_eng_name` VARCHAR(255) NOT NULL COMMENT '表英文名',
	`table_remark` VARCHAR(1024) NOT NULL COMMENT '表备注',
	`table_discarded` INT(4) NOT NULL COMMENT '表废弃: 0未废弃, 1废弃',
	`user_id` INT DEFAULT '0' NOT NULL COMMENT '用户Id',
	`create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (`id`)
) COMMENT '表信息表';

CREATE TABLE db_column
(
	`id` INT AUTO_INCREMENT,
	`table_id` INT DEFAULT '0' NOT NULL COMMENT '关联表Id',
	`column_eng_name` VARCHAR(255) NOT NULL COMMENT '字段英文名',
	`column_type` VARCHAR(32) NOT NULL COMMENT '字段数据类型',
	`column_remark` VARCHAR(1024) NOT NULL COMMENT '字段备注',
	`column_discarded` INT(4) NOT NULL COMMENT '字段废弃: 0未废弃, 1废弃',
  `user_id` INT DEFAULT '0' NOT NULL COMMENT '用户Id',
	`create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (`id`)
) COMMENT '字段表';

CREATE TABLE group_relation
(
	`id` INT AUTO_INCREMENT,
	`relation_other_id` INT DEFAULT '0' NOT NULL COMMENT '关联Id',
	`group_id` INT DEFAULT '0' NOT NULL COMMENT '分组Id',
	`user_id` INT DEFAULT '0' NOT NULL COMMENT '用户Id',
	`create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (`id`)
) COMMENT '表-分组关系表';

CREATE TABLE user
(
	`id` INT AUTO_INCREMENT,
	`user_name` VARCHAR(32) DEFAULT '0' NOT NULL COMMENT '用户名',
	`user_email` VARCHAR(128) DEFAULT '0' NOT NULL COMMENT '用户邮箱',
	`user_password` VARCHAR(128) DEFAULT '0' NOT NULL COMMENT '用户密码',
	`department_id` INT DEFAULT '0' NOT NULL COMMENT '部门Id',
	`create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (`id`)
) COMMENT '用户信息表';

select schema_name from information_schema.schemata;
SELECT TABLE_NAME, TABLE_COMMENT, CREATE_TIME, UPDATE_TIME FROM information_schema.TABLES where table_schema='tool_platform';
SELECT COLUMN_NAME, COLUMN_TYPE, COLUMN_COMMENT from information_schema.columns where table_name='db_table' and table_schema='tool_platform';