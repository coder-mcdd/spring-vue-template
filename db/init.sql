CREATE TABLE IF NOT EXISTS `tb_account` (
                                            `id` INT AUTO_INCREMENT COMMENT 'ID',
                                            `username` VARCHAR(255) NOT NULL COMMENT '用户名',
                                            `password` VARCHAR(255) NOT NULL COMMENT '密码',
                                            `email` VARCHAR(255) NOT NULL COMMENT '邮箱',
                                            `role` VARCHAR(255) NOT NULL COMMENT '角色',
                                            `avatar` VARCHAR(255) NOT NULL COMMENT '头像 link',
                                            `account_expired` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '账号是否过期',
                                            `credentials_expired` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '凭证是否过期',
                                            `account_locked` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '账号是否锁定',
                                            `enabled` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '账号是否启用',
                                            `register_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
                                            `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                            PRIMARY KEY (`id`),
                                            UNIQUE KEY `unique_email` (`email`),
                                            UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
