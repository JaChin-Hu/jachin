SELECT 1
FROM DUAL;

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`    VARCHAR(30)     NOT NULL COMMENT '用户账号',
    `password`    VARCHAR(255)             DEFAULT '' COMMENT '密码',
    `email`       VARCHAR(50)              DEFAULT '' COMMENT '用户邮箱',
    `mobile`      VARCHAR(11)              DEFAULT '' COMMENT '手机号码',
    `nickname`    VARCHAR(30)     NOT NULL COMMENT '用户昵称',
    `avatar`      VARCHAR(100)             DEFAULT '' COMMENT '头像地址',
    `type`        VARCHAR(2)               DEFAULT '00' COMMENT '用户类型（00系统用户）',
    `sex`         CHAR(1)                  DEFAULT 1 COMMENT '用户性别（0男 1女 2未知）',
    `is_enabled`  TINYINT UNSIGNED         DEFAULT 1 COMMENT '帐号状态（0停用 1正常）',
    `is_deleted`  TINYINT UNSIGNED         DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
    `create_by`   VARCHAR(64)              DEFAULT '' COMMENT '创建者',
    `update_by`   VARCHAR(64)              DEFAULT '' COMMENT '更新者',
    `create_time` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      VARCHAR(500)             DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100 COMMENT = '用户信息表';

-- ----------------------------
-- 4、角色信息表
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`                  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `name`                VARCHAR(30)     NOT NULL COMMENT '角色名称',
    `role_key`            VARCHAR(100)    NOT NULL COMMENT '角色权限字符串',
    `sorted`              INT(4)          NOT NULL COMMENT '显示顺序',
    `data_scope`          CHAR(1)                  DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
    `menu_check_strictly` TINYINT UNSIGNED         DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
    `is_enabled`          TINYINT UNSIGNED         DEFAULT 1 COMMENT '角色状态（0停用 1正常）',
    `is_deleted`          TINYINT UNSIGNED         DEFAULT 0 COMMENT '删除标志（0正常 1删除）',
    `create_by`           VARCHAR(64)              DEFAULT '' COMMENT '创建者',
    `update_by`           VARCHAR(64)              DEFAULT '' COMMENT '更新者',
    `create_time`         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`              VARCHAR(500)             DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100 COMMENT = '角色信息表';


-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `name`        VARCHAR(50)     NOT NULL COMMENT '菜单名称',
    `pid`         BIGINT UNSIGNED          DEFAULT 0 COMMENT '父菜单ID',
    `sorted`      INT                      DEFAULT 0 COMMENT '显示顺序',
    `path`        VARCHAR(200)             DEFAULT '' COMMENT '路由地址',
    `component`   VARCHAR(255)             DEFAULT NULL COMMENT '组件路径',
    `query`       VARCHAR(255)             DEFAULT NULL COMMENT '路由参数',
    `is_frame`    TINYINT UNSIGNED         DEFAULT 0 COMMENT '是否为框架（0否 1是）',
    `is_cache`    TINYINT UNSIGNED         DEFAULT 1 COMMENT '是否缓存（0不缓存 1缓存）',
    `type`        CHAR(1)                  DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `is_visible`  TINYINT UNSIGNED         DEFAULT 1 COMMENT '菜单状态（0隐藏 1显示）',
    `is_enabled`  TINYINT UNSIGNED         DEFAULT 1 comment '菜单状态（0停用 1正常）',
    `perms`       VARCHAR(100)             DEFAULT NULL COMMENT '权限标识',
    `icon`        VARCHAR(100)             DEFAULT '#' COMMENT '菜单图标',
    `create_by`   VARCHAR(64)              DEFAULT 'admin' COMMENT '创建者',
    `update_by`   VARCHAR(64)              DEFAULT '' COMMENT '更新者',
    `create_time` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      VARCHAR(500)             DEFAULT '' COMMENT '备注',
    primary key (`id`)
) ENGINE = innodb
  AUTO_INCREMENT = 2000 COMMENT = '菜单权限表';

-- ----------------------------
-- 用户和角色关联表  用户N-1角色
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `uid` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    `rid` BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`uid`, `rid`)
) ENGINE = innodb COMMENT = '用户和角色关联表';


DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `rid` BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    `mid` BIGINT UNSIGNED NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`rid`, `mid`)
) ENGINE = innodb COMMENT = '角色和菜单关联表';

-- ----------------------------
-- 操作日志
-- ----------------------------
DROP TABLE IF EXISTS `sys_ope_log`;
CREATE TABLE `sys_ope_log`
(
    `id`             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日志ID',
    `title`          VARCHAR(50)              DEFAULT '' COMMENT '模块标题',
    `business_type`  INT(2)                   DEFAULT 0 COMMENT '业务类型(0其他 1新增 2修改 3删除)',
    `url`            VARCHAR(255)             DEFAULT '' COMMENT '请求url',
    `method`         VARCHAR(255)             DEFAULT '' COMMENT '方法名称',
    `request_method` VARCHAR(255)             DEFAULT '' COMMENT '请求方式',
    `request_param`  VARCHAR(2000)            DEFAULT '' COMMENT '请求参数',
    `result`         VARCHAR(2000)            DEFAULT '' COMMENT '返回结果',
    `username`       VARCHAR(30)              DEFAULT '' COMMENT '操作人员',
    `ip`             VARCHAR(128)             DEFAULT '' COMMENT '主机地址',
    `location`       VARCHAR(255)             DEFAULT '' COMMENT '操作地点',
    `remark`         VARCHAR(255)             DEFAULT '' COMMENT '操作描述',
    `create_time`    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 200 COMMENT = '操作日志表';

DROP TABLE IF EXISTS `login_info`;
CREATE TABLE `login_info`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '登录信息id',
    `username`    VARCHAR(30)  DEFAULT '' COMMENT '登录账号',
    `ip`          VARCHAR(128) DEFAULT '' COMMENT '登录ip',
    `location`    VARCHAR(255) DEFAULT '' COMMENT '登录地点',
    `browser`     VARCHAR(50)  DEFAULT '' COMMENT '浏览器类型',
    `os`          VARCHAR(50)  DEFAULT '' COMMENT '操作系统',
    `status`      char(1)      DEFAULT '0' COMMENT '登录状态: 0 成功 1失败',
    `msg`         VARCHAR(255) DEFAULT '' COMMENT '提示消息',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 200 COMMENT = '登录信息表';
