CREATE TABLE `t_auth_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `authority` enum('NORMAL','ARGO','ADMIN','WECHAT','MANAGER') DEFAULT NULL COMMENT '角色',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `INDX_userName` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_auth_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名(登录名)',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(60) DEFAULT NULL COMMENT '密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `user_type` enum('NORMAL') DEFAULT NULL COMMENT '用户类型',
  `wechat_open_id` varchar(30) DEFAULT NULL COMMENT '微信openid',
  `wechat_union_id` varchar(30) DEFAULT NULL COMMENT '微信unionid',
  `enabled` int(1) DEFAULT NULL COMMENT '是否可用 1 是 0 否',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `last_password_reset_date` datetime DEFAULT NULL COMMENT '最后一次修改密码的时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_phone` (`phone`) USING BTREE,
  UNIQUE KEY `UNI_user_name` (`user_name`) USING BTREE,
  KEY `IDX_phone` (`phone`) USING BTREE,
  KEY `IDX_user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;