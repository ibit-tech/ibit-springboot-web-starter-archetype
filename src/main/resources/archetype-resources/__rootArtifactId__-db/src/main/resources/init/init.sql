DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise` (
  `enterprise_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '企业id',
  `name` varchar(200) NOT NULL COMMENT '企业名称',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`enterprise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='企业';


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(32) NOT NULL COMMENT '用户名，登陆用的，英文',
  `password` varchar(255) NOT NULL,
  `nick_name` varchar(128) NOT NULL COMMENT '用户昵称',
  `gender` tinyint(1) NOT NULL COMMENT '性别，0：未知，1：男，2：女',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(190) DEFAULT NULL COMMENT '绑定邮箱',
  `wechat` varchar(128) DEFAULT NULL COMMENT '微信号',
  `enterprise_id` int(11) DEFAULT '0' COMMENT '所属企业id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_uniq` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';