CREATE TABLE `Teacher` (
	`id` SERIAL NOT NULL,
	`teacher_id` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '教員ID',
	`teacher_name` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '氏名',
	`password` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'パスワード',
	`school_cd` CHAR(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学校コード',
	PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB;