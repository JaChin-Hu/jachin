SELECT 1 FROM DUAL;

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          INT         NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`    VARCHAR(30) NOT NULL COMMENT '用户账号',
    `password`    VARCHAR(255) DEFAULT '' COMMENT '密码',
    `email`       VARCHAR(50)  DEFAULT '' COMMENT '用户邮箱',
    `mobile`      VARCHAR(11)  DEFAULT '' COMMENT '手机号码',
    `nickname`    VARCHAR(30) NOT NULL COMMENT '用户昵称',
    `avatar`      VARCHAR(100) DEFAULT '' COMMENT '头像地址',
    `type`        VARCHAR(2)   DEFAULT '00' COMMENT '用户类型（00系统用户）',
    `sex`         CHAR(1)      DEFAULT 1 COMMENT '用户性别（0男 1女 2未知）',
    `is_enabled`  TINYINT(1)   DEFAULT 1 COMMENT '帐号状态（0停用 1正常）',
    `is_deleted`  TINYINT(1)   DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
    `create_time` DATETIME COMMENT '创建时间',
    `create_by`   VARCHAR(64)  DEFAULT '' COMMENT '创建者',
    `update_time` DATETIME COMMENT '更新时间',
    `update_by`   VARCHAR(64)  DEFAULT '' COMMENT '更新者',
    `remark`      VARCHAR(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100 COMMENT = '用户信息表';

-- ----------------------------
-- 4、角色信息表
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`                  INT          NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `name`                VARCHAR(30)  NOT NULL COMMENT '角色名称',
    `role_key`            VARCHAR(100) NOT NULL COMMENT '角色权限字符串',
    `sorted`              INT(4)       NOT NULL COMMENT '显示顺序',
    `data_scope`          CHAR(1)      DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
    `menu_check_strictly` TINYINT(1)   DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
    `is_enabled`          TINYINT(1)   DEFAULT 1 COMMENT '角色状态（0正常 1停用）',
    `is_deleted`          TINYINT(1)   DEFAULT 0 COMMENT '删除标志（0存在 1删除）',
    `create_time`         DATETIME COMMENT '创建时间',
    `create_by`           VARCHAR(64)  DEFAULT '' COMMENT '创建者',
    `update_time`         DATETIME COMMENT '更新时间',
    `update_by`           VARCHAR(64)  DEFAULT '' COMMENT '更新者',
    `remark`              VARCHAR(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100 COMMENT = '角色信息表';


-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          INT         NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `name`        VARCHAR(50) NOT NULL COMMENT '菜单名称',
    `pid`         BIGINT(20)   DEFAULT 0 COMMENT '父菜单ID',
    `sorted`      INT(4)       DEFAULT 0 COMMENT '显示顺序',
    `path`        VARCHAR(200) DEFAULT '' COMMENT '路由地址',
    `component`   VARCHAR(255) DEFAULT NULL COMMENT '组件路径',
    `query`       VARCHAR(255) DEFAULT NULL COMMENT '路由参数',
    `is_frame`    TINYINT(1)   DEFAULT 0 COMMENT '是否为外链（0否 1是）',
    `is_cache`    TINYINT(1)   DEFAULT 1 COMMENT '是否缓存（0不缓存 1缓存）',
    `type`        CHAR(1)      DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `is_visible`  TINYINT(1)   DEFAULT 1 COMMENT '菜单状态（0隐藏 1显示）',
    `is_enabled`  TINYINT(1)   default 1 comment '菜单状态（0停用 1正常）',
    `perms`       VARCHAR(100) DEFAULT NULL COMMENT '权限标识',
    `icon`        VARCHAR(100) DEFAULT '#' COMMENT '菜单图标',
    `create_by`   VARCHAR(64)  DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME COMMENT '创建时间',
    `update_by`   VARCHAR(64)  DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME COMMENT '更新时间',
    `remark`      VARCHAR(500) DEFAULT '' COMMENT '备注',
    primary key (`id`)
) ENGINE = innodb
  AUTO_INCREMENT = 2000 COMMENT = '菜单权限表';

-- ----------------------------
-- 用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role
(
    `uid` INT NOT NULL COMMENT '用户ID',
    `rid` INT NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`uid`, `rid`)
) engine = innodb comment = '用户和角色关联表';


drop table if exists `sys_role_menu`;
create table `sys_role_menu`
(
    `rid` INT NOT NULL COMMENT '角色ID',
    `mid` INT NOT NULL COMMENT '菜单ID',
    primary key (`rid`, `mid`)
) engine = innodb comment = '角色和菜单关联表';


-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
INSERT INTO `sys_role` (`id`, `name`, `role_key`, `sorted`, `data_scope`, `menu_check_strictly`, `is_enabled`, `is_deleted`,
                        `create_time`, `create_by`, `update_time`, `update_by`, `remark`)
VALUES (1, '超级管理员', 'admin', 0, 1, 1, 1, 0, sysdate(), 'admin', NULL, '', '超级管理员'),
       (2, '普通角色', 'common', 1, 2, 1, 1, 0, sysdate(), 'admin', NULL, '', '超级管理员');

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into sys_menu
values ('1', '系统管理', 1, '1', 'system', null, '', 0, 1, 'M', 1, 1, '', 'system', 'admin', sysdate(), '', null, '系统管理目录');
insert into sys_menu
values ('2', '系统监控', 1, '2', 'monitor', null, '', 0, 1, 'M', 1, 1, '', 'monitor', 'admin', sysdate(), '', null,
        '系统监控目录');
insert into sys_menu
values ('3', '系统工具', 1, '3', 'tool', null, '', 0, 1, 'M', 1, 1, '', 'tool', 'admin', sysdate(), '', null, '系统工具目录');
-- 二级菜单
insert into sys_menu
values ('100', '用户管理', '1', '1', 'user', 'system/user/index', '', 0, 1, 'C', 1, 1, 'system:user:list', 'user', 'admin',
        sysdate(), '', null, '用户管理菜单');
insert into sys_menu
values ('101', '角色管理', '1', '2', 'role', 'system/role/index', '', 0, 1, 'C', 1, 1, 'system:role:list', 'peoples',
        'admin', sysdate(), '', null, '角色管理菜单');
insert into sys_menu
values ('102', '菜单管理', '1', '3', 'menu', 'system/menu/index', '', 0, 1, 'C', 1, 1, 'system:menu:list', 'tree-table',
        'admin', sysdate(), '', null, '菜单管理菜单');
insert into sys_menu
values ('103', '部门管理', '1', '4', 'dept', 'system/dept/index', '', 0, 1, 'C', 1, 1, 'system:dept:list', 'tree', 'admin',
        sysdate(), '', null, '部门管理菜单');
insert into sys_menu
values ('104', '岗位管理', '1', '5', 'post', 'system/post/index', '', 0, 1, 'C', 1, 1, 'system:post:list', 'post', 'admin',
        sysdate(), '', null, '岗位管理菜单');
insert into sys_menu
values ('105', '字典管理', '1', '6', 'dict', 'system/dict/index', '', 0, 1, 'C', 1, 1, 'system:dict:list', 'dict', 'admin',
        sysdate(), '', null, '字典管理菜单');
insert into sys_menu
values ('106', '参数设置', '1', '7', 'config', 'system/config/index', '', 0, 1, 'C', 1, 1, 'system:config:list', 'edit',
        'admin', sysdate(), '', null, '参数设置菜单');
insert into sys_menu
values ('107', '通知公告', '1', '8', 'notice', 'system/notice/index', '', 0, 1, 'C', 1, 1, 'system:notice:list', 'message',
        'admin', sysdate(), '', null, '通知公告菜单');
insert into sys_menu
values ('108', '日志管理', '1', '9', 'log', '', '', 0, 1, 'M', 1, 1, '', 'log', 'admin', sysdate(), '', null, '日志管理菜单');
insert into sys_menu
values ('109', '在线用户', '2', '1', 'online', 'monitor/online/index', '', 0, 1, 'C', 1, 1, 'monitor:online:list', 'online',
        'admin', sysdate(), '', null, '在线用户菜单');
insert into sys_menu
values ('110', '定时任务', '2', '2', 'job', 'monitor/job/index', '', 0, 1, 'C', 1, 1, 'monitor:job:list', 'job', 'admin',
        sysdate(), '', null, '定时任务菜单');
insert into sys_menu
values ('111', '数据监控', '2', '3', 'druid', 'monitor/druid/index', '', 0, 1, 'C', 1, 1, 'monitor:druid:list', 'druid',
        'admin', sysdate(), '', null, '数据监控菜单');
insert into sys_menu
values ('112', '服务监控', '2', '4', 'server', 'monitor/server/index', '', 0, 1, 'C', 1, 1, 'monitor:server:list', 'server',
        'admin', sysdate(), '', null, '服务监控菜单');
insert into sys_menu
values ('113', '缓存监控', '2', '5', 'cache', 'monitor/cache/index', '', 0, 1, 'C', 1, 1, 'monitor:cache:list', 'redis',
        'admin', sysdate(), '', null, '缓存监控菜单');
insert into sys_menu
values ('114', '缓存列表', '2', '6', 'cacheList', 'monitor/cache/list', '', 0, 1, 'C', 1, 1, 'monitor:cache:list',
        'redis-list', 'admin', sysdate(), '', null, '缓存列表菜单');
insert into sys_menu
values ('115', '表单构建', '3', '1', 'build', 'tool/build/index', '', 0, 1, 'C', 1, 1, 'tool:build:list', 'build', 'admin',
        sysdate(), '', null, '表单构建菜单');
insert into sys_menu
values ('116', '代码生成', '3', '2', 'gen', 'tool/gen/index', '', 0, 1, 'C', 1, 1, 'tool:gen:list', 'code', 'admin',
        sysdate(), '', null, '代码生成菜单');
insert into sys_menu
values ('117', '系统接口', '3', '3', 'swagger', 'tool/swagger/index', '', 0, 1, 'C', 1, 1, 'tool:swagger:list', 'swagger',
        'admin', sysdate(), '', null, '系统接口菜单');
-- 三级菜单
insert into sys_menu
values ('500', '操作日志', '108', '1', 'operlog', 'monitor/operlog/index', '', 0, 1, 'C', 1, 1, 'monitor:operlog:list',
        'form', 'admin', sysdate(), '', null, '操作日志菜单');
insert into sys_menu
values ('501', '登录日志', '108', '2', 'logininfor', 'monitor/logininfor/index', '', 0, 1, 'C', 1, 1,
        'monitor:logininfor:list', 'logininfor', 'admin', sysdate(), '', null, '登录日志菜单');
-- 用户管理按钮
insert into sys_menu
values ('1000', '用户查询', '100', '1', '', '', '', 0, 1, 'F', 1, 1, 'system:user:query', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1001', '用户新增', '100', '2', '', '', '', 0, 1, 'F', 1, 1, 'system:user:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1002', '用户修改', '100', '3', '', '', '', 0, 1, 'F', 1, 1, 'system:user:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1003', '用户删除', '100', '4', '', '', '', 0, 1, 'F', 1, 1, 'system:user:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1004', '用户导出', '100', '5', '', '', '', 0, 1, 'F', 1, 1, 'system:user:export', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1005', '用户导入', '100', '6', '', '', '', 0, 1, 'F', 1, 1, 'system:user:import', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1006', '重置密码', '100', '7', '', '', '', 0, 1, 'F', 1, 1, 'system:user:resetPwd', '#', 'admin', sysdate(), '',
        null, '');
-- 角色管理按钮
insert into sys_menu
values ('1007', '角色查询', '101', '1', '', '', '', 0, 1, 'F', 1, 1, 'system:role:query', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1008', '角色新增', '101', '2', '', '', '', 0, 1, 'F', 1, 1, 'system:role:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1009', '角色修改', '101', '3', '', '', '', 0, 1, 'F', 1, 1, 'system:role:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1010', '角色删除', '101', '4', '', '', '', 0, 1, 'F', 1, 1, 'system:role:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1011', '角色导出', '101', '5', '', '', '', 0, 1, 'F', 1, 1, 'system:role:export', '#', 'admin', sysdate(), '',
        null, '');
-- 菜单管理按钮
insert into sys_menu
values ('1012', '菜单查询', '102', '1', '', '', '', 0, 1, 'F', 1, 1, 'system:menu:query', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1013', '菜单新增', '102', '2', '', '', '', 0, 1, 'F', 1, 1, 'system:menu:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1014', '菜单修改', '102', '3', '', '', '', 0, 1, 'F', 1, 1, 'system:menu:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1015', '菜单删除', '102', '4', '', '', '', 0, 1, 'F', 1, 1, 'system:menu:remove', '#', 'admin', sysdate(), '',
        null, '');
-- 部门管理按钮
insert into sys_menu
values ('1016', '部门查询', '103', '1', '', '', '', 0, 1, 'F', 1, 1, 'system:dept:query', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1017', '部门新增', '103', '2', '', '', '', 0, 1, 'F', 1, 1, 'system:dept:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1018', '部门修改', '103', '3', '', '', '', 0, 1, 'F', 1, 1, 'system:dept:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1019', '部门删除', '103', '4', '', '', '', 0, 1, 'F', 1, 1, 'system:dept:remove', '#', 'admin', sysdate(), '',
        null, '');
-- 岗位管理按钮
insert into sys_menu
values ('1020', '岗位查询', '104', '1', '', '', '', 0, 1, 'F', 1, 1, 'system:post:query', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1021', '岗位新增', '104', '2', '', '', '', 0, 1, 'F', 1, 1, 'system:post:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1022', '岗位修改', '104', '3', '', '', '', 0, 1, 'F', 1, 1, 'system:post:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1023', '岗位删除', '104', '4', '', '', '', 0, 1, 'F', 1, 1, 'system:post:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1024', '岗位导出', '104', '5', '', '', '', 0, 1, 'F', 1, 1, 'system:post:export', '#', 'admin', sysdate(), '',
        null, '');
-- 字典管理按钮
insert into sys_menu
values ('1025', '字典查询', '105', '1', '#', '', '', 0, 1, 'F', 1, 1, 'system:dict:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1026', '字典新增', '105', '2', '#', '', '', 0, 1, 'F', 1, 1, 'system:dict:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1027', '字典修改', '105', '3', '#', '', '', 0, 1, 'F', 1, 1, 'system:dict:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1028', '字典删除', '105', '4', '#', '', '', 0, 1, 'F', 1, 1, 'system:dict:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1029', '字典导出', '105', '5', '#', '', '', 0, 1, 'F', 1, 1, 'system:dict:export', '#', 'admin', sysdate(), '',
        null, '');
-- 参数设置按钮
insert into sys_menu
values ('1030', '参数查询', '106', '1', '#', '', '', 0, 1, 'F', 1, 1, 'system:config:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1031', '参数新增', '106', '2', '#', '', '', 0, 1, 'F', 1, 1, 'system:config:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1032', '参数修改', '106', '3', '#', '', '', 0, 1, 'F', 1, 1, 'system:config:edit', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1033', '参数删除', '106', '4', '#', '', '', 0, 1, 'F', 1, 1, 'system:config:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1034', '参数导出', '106', '5', '#', '', '', 0, 1, 'F', 1, 1, 'system:config:export', '#', 'admin', sysdate(), '',
        null, '');
-- 通知公告按钮
insert into sys_menu
values ('1035', '公告查询', '107', '1', '#', '', '', 0, 1, 'F', 1, 1, 'system:notice:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1036', '公告新增', '107', '2', '#', '', '', 0, 1, 'F', 1, 1, 'system:notice:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1037', '公告修改', '107', '3', '#', '', '', 0, 1, 'F', 1, 1, 'system:notice:edit', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1038', '公告删除', '107', '4', '#', '', '', 0, 1, 'F', 1, 1, 'system:notice:remove', '#', 'admin', sysdate(), '',
        null, '');
-- 操作日志按钮
insert into sys_menu
values ('1039', '操作查询', '500', '1', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:operlog:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1040', '操作删除', '500', '2', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:operlog:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1041', '日志导出', '500', '4', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:operlog:export', '#', 'admin', sysdate(), '',
        null, '');
-- 登录日志按钮
insert into sys_menu
values ('1042', '登录查询', '501', '1', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:logininfor:query', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1043', '登录删除', '501', '2', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:logininfor:remove', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1044', '日志导出', '501', '3', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:logininfor:export', '#', 'admin', sysdate(),
        '', null, '');
-- 在线用户按钮
insert into sys_menu
values ('1045', '在线查询', '109', '1', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:online:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1046', '批量强退', '109', '2', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:online:batchLogout', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1047', '单条强退', '109', '3', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:online:forceLogout', '#', 'admin', sysdate(),
        '', null, '');
-- 定时任务按钮
insert into sys_menu
values ('1048', '任务查询', '110', '1', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:job:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1049', '任务新增', '110', '2', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:job:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1050', '任务修改', '110', '3', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:job:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1051', '任务删除', '110', '4', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:job:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1052', '状态修改', '110', '5', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:job:changeStatus', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1053', '任务导出', '110', '7', '#', '', '', 0, 1, 'F', 1, 1, 'monitor:job:export', '#', 'admin', sysdate(), '',
        null, '');
-- 代码生成按钮
insert into sys_menu
values ('1054', '生成查询', '115', '1', '#', '', '', 0, 1, 'F', 1, 1, 'tool:gen:query', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1055', '生成修改', '115', '2', '#', '', '', 0, 1, 'F', 1, 1, 'tool:gen:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1056', '生成删除', '115', '3', '#', '', '', 0, 1, 'F', 1, 1, 'tool:gen:remove', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1057', '导入代码', '115', '2', '#', '', '', 0, 1, 'F', 1, 1, 'tool:gen:import', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1058', '预览代码', '115', '4', '#', '', '', 0, 1, 'F', 1, 1, 'tool:gen:preview', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1059', '生成代码', '115', '5', '#', '', '', 0, 1, 'F', 1, 1, 'tool:gen:code', '#', 'admin', sysdate(), '', null,
        '');


-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu
values ('2', '1'),
       ('2', '2'),
       ('2', '3'),
       ('2', '4'),
       ('2', '100'),
       ('2', '101'),
       ('2', '102'),
       ('2', '103'),
       ('2', '104'),
       ('2', '105'),
       ('2', '106'),
       ('2', '107'),
       ('2', '108'),
       ('2', '109'),
       ('2', '110'),
       ('2', '111'),
       ('2', '112'),
       ('2', '113'),
       ('2', '114'),
       ('2', '115'),
       ('2', '116'),
       ('2', '117'),
       ('2', '500'),
       ('2', '501'),
       ('2', '1000'),
       ('2', '1001'),
       ('2', '1002'),
       ('2', '1003'),
       ('2', '1004'),
       ('2', '1005'),
       ('2', '1006'),
       ('2', '1007'),
       ('2', '1008'),
       ('2', '1009'),
       ('2', '1010'),
       ('2', '1011'),
       ('2', '1012'),
       ('2', '1013'),
       ('2', '1014'),
       ('2', '1015'),
       ('2', '1016'),
       ('2', '1017'),
       ('2', '1018'),
       ('2', '1019'),
       ('2', '1020'),
       ('2', '1021'),
       ('2', '1022'),
       ('2', '1023'),
       ('2', '1024'),
       ('2', '1025'),
       ('2', '1026'),
       ('2', '1027'),
       ('2', '1028'),
       ('2', '1029'),
       ('2', '1030'),
       ('2', '1031'),
       ('2', '1032'),
       ('2', '1033'),
       ('2', '1034'),
       ('2', '1035'),
       ('2', '1036'),
       ('2', '1037'),
       ('2', '1038'),
       ('2', '1039'),
       ('2', '1040'),
       ('2', '1041'),
       ('2', '1042'),
       ('2', '1043'),
       ('2', '1044'),
       ('2', '1045'),
       ('2', '1046'),
       ('2', '1047'),
       ('2', '1048'),
       ('2', '1049'),
       ('2', '1050'),
       ('2', '1051'),
       ('2', '1052'),
       ('2', '1053'),
       ('2', '1054'),
       ('2', '1055'),
       ('2', '1056'),
       ('2', '1057'),
       ('2', '1058'),
       ('2', '1059');