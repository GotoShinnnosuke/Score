CREATE TABLE `Subject` (
	`id` SERIAL NOT NULL,
	`school_cd` CHAR(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学校コード',
	`subject_cd` CHAR(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '科目コード',
	`subject_name` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '科目名',
	`is_delete` BOOLEAN DEFAULT TRUE COMMENT 'フラグ',
	PRIMARY KEY (`school_cd`,`subject_cd`)
) ENGINE=InnoDB;