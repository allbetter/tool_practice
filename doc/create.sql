set global time_zone='+8:00';
CREATE database tool_platform CHARSET utf8mb4 COLLATE utf8mb4_general_ci;
USE tool_platform;
CREATE TABLE `business_unit` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `bu_name` VARCHAR(128) NOT NULL COMMENT 'BU名称',
    `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
    PRIMARY KEY (`id`)
) COMMENT 'BU信息';