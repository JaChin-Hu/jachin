

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
INSERT INTO `sys_role` (`id`, `name`, `role_key`, `sorted`, `data_scope`, `menu_check_strictly`, `is_enabled`,
                        `is_deleted`, `create_by`, `update_by`, `remark`)
VALUES (1, '超级管理员', 'admin', 0, 1, 1, 1, 0, 'admin', '', '超级管理员'),
       (2, '普通角色', 'common', 1, 2, 1, 1, 0, 'admin', '', '超级管理员');

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
INSERT INTO `sys_menu` VALUES(1, '系统管理', 0, 1, 'system',           NULL, '', 1, 1, 'M', 1, 1, '', 'system',   'ADMIN', '', sysdate(),  sysdate(), '系统管理目录');
INSERT INTO `sys_menu` VALUES(2, '系统监控', 0, 2, 'monitor',          NULL, '', 1, 1, 'M', 1, 1, '', 'monitor',  'admin', '', sysdate(),  sysdate(), '系统监控目录');
INSERT INTO `sys_menu` VALUES(3, '系统工具', 0, 3, 'tool',             NULL, '', 1, 1, 'M', 1, 1, '', 'tool',     'admin', '', sysdate(),  sysdate(), '系统工具目录');
INSERT INTO `sys_menu` VALUES(4, '博客官网', 0, 4, 'http://ruoyi.vip', NULL, '', 0, 1, 'M', 1, 1, '', 'guide',    'admin', '', sysdate(),  sysdate(), '博客官网地址');
-- 二级菜单
INSERT INTO `sys_menu` VALUES(100,  '用户管理', 1,   1, 'user',       'system/user/index',        '', 1, 1, 'C', 1, 1, 'system:user:list',        'user',          'admin', '', sysdate(), sysdate(), '用户管理菜单');
INSERT INTO `sys_menu` VALUES(101,  '角色管理', 1,   2, 'role',       'system/role/index',        '', 1, 1, 'C', 1, 1, 'system:role:list',        'peoples',       'admin', '', sysdate(), sysdate(), '角色管理菜单');
INSERT INTO `sys_menu` VALUES(102,  '菜单管理', 1,   3, 'menu',       'system/menu/index',        '', 1, 1, 'C', 1, 1, 'system:menu:list',        'tree-table',    'admin', '', sysdate(), sysdate(), '菜单管理菜单');
INSERT INTO `sys_menu` VALUES(103,  '部门管理', 1,   4, 'dept',       'system/dept/index',        '', 1, 1, 'C', 1, 1, 'system:dept:list',        'tree',          'admin', '', sysdate(), sysdate(), '部门管理菜单');
INSERT INTO `sys_menu` VALUES(104,  '岗位管理', 1,   5, 'post',       'system/post/index',        '', 1, 1, 'C', 1, 1, 'system:post:list',        'post',          'admin', '', sysdate(), sysdate(), '岗位管理菜单');
INSERT INTO `sys_menu` VALUES(105,  '字典管理', 1,   6, 'dict',       'system/dict/index',        '', 1, 1, 'C', 1, 1, 'system:dict:list',        'dict',          'admin', '', sysdate(), sysdate(), '字典管理菜单');
INSERT INTO `sys_menu` VALUES(106,  '参数设置', 1,   7, 'config',     'system/config/index',      '', 1, 1, 'C', 1, 1, 'system:config:list',      'edit',          'admin', '', sysdate(), sysdate(), '参数设置菜单');
INSERT INTO `sys_menu` VALUES(107,  '通知公告', 1,   8, 'notice',     'system/notice/index',      '', 1, 1, 'C', 1, 1, 'system:notice:list',      'message',       'admin', '', sysdate(), sysdate(), '通知公告菜单');
INSERT INTO `sys_menu` VALUES(108,  '日志管理', 1,   9, 'log',        '',                         '', 1, 1, 'M', 1, 1, '',                        'log',           'admin', '', sysdate(), sysdate(), '日志管理菜单');
INSERT INTO `sys_menu` VALUES(109,  '在线用户', 2,   1, 'online',     'monitor/online/index',     '', 1, 1, 'C', 1, 1, 'monitor:online:list',     'online',        'admin', '', sysdate(), sysdate(), '在线用户菜单');
INSERT INTO `sys_menu` VALUES(110,  '定时任务', 2,   2, 'job',        'monitor/job/index',        '', 1, 1, 'C', 1, 1, 'monitor:job:list',        'job',           'admin', '', sysdate(), sysdate(), '定时任务菜单');
INSERT INTO `sys_menu` VALUES(111,  '数据监控', 2,   3, 'druid',      'monitor/druid/index',      '', 1, 1, 'C', 1, 1, 'monitor:druid:list',      'druid',         'admin', '', sysdate(), sysdate(), '数据监控菜单');
INSERT INTO `sys_menu` VALUES(112,  '服务监控', 2,   4, 'server',     'monitor/server/index',     '', 1, 1, 'C', 1, 1, 'monitor:server:list',     'server',        'admin', '', sysdate(), sysdate(), '服务监控菜单');
INSERT INTO `sys_menu` VALUES(113,  '缓存监控', 2,   5, 'cache',      'monitor/cache/index',      '', 1, 1, 'C', 1, 1, 'monitor:cache:list',      'redis',         'admin', '', sysdate(), sysdate(), '缓存监控菜单');
INSERT INTO `sys_menu` VALUES(114,  '缓存列表', 2,   6, 'cacheList',  'monitor/cache/list',       '', 1, 1, 'C', 1, 1, 'monitor:cache:list',      'redis-list',    'admin', '', sysdate(), sysdate(), '缓存列表菜单');
INSERT INTO `sys_menu` VALUES(115,  '表单构建', 3,   1, 'build',      'tool/build/index',         '', 1, 1, 'C', 1, 1, 'tool:build:list',         'build',         'admin', '', sysdate(), sysdate(), '表单构建菜单');
INSERT INTO `sys_menu` VALUES(116,  '代码生成', 3,   2, 'gen',        'tool/gen/index',           '', 1, 1, 'C', 1, 1, 'tool:gen:list',           'code',          'admin', '', sysdate(), sysdate(), '代码生成菜单');
INSERT INTO `sys_menu` VALUES(117,  '系统接口', 3,   3, 'swagger',    'tool/swagger/index',       '', 1, 1, 'C', 1, 1, 'tool:swagger:list',       'swagger',       'admin', '', sysdate(), sysdate(), '系统接口菜单');
-- 三级菜单
INSERT INTO `sys_menu` VALUES(500,  '操作日志', 108, 1, 'operlog',    'monitor/operlog/index',    '', 1, 1, 'C', 1, 1, 'monitor:operlog:list',    'form',          'admin', '', sysdate(), sysdate(), '操作日志菜单');
INSERT INTO `sys_menu` VALUES(501,  '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 1, 'C', 1, 1, 'monitor:logininfor:list', 'logininfor',    'admin', '', sysdate(), sysdate(), '登录日志菜单');
-- 用户管理按钮
INSERT INTO `sys_menu` VALUES(1000, '用户查询', 100, 1,  '', '', '', 1, 1, 'F', 1, 1, 'system:user:query',          '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1001, '用户新增', 100, 2,  '', '', '', 1, 1, 'F', 1, 1, 'system:user:add',            '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1002, '用户修改', 100, 3,  '', '', '', 1, 1, 'F', 1, 1, 'system:user:edit',           '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1003, '用户删除', 100, 4,  '', '', '', 1, 1, 'F', 1, 1, 'system:user:remove',         '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1004, '用户导出', 100, 5,  '', '', '', 1, 1, 'F', 1, 1, 'system:user:export',         '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1005, '用户导入', 100, 6,  '', '', '', 1, 1, 'F', 1, 1, 'system:user:import',         '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1006, '重置密码', 100, 7,  '', '', '', 1, 1, 'F', 1, 1, 'system:user:resetPwd',       '#', 'admin', '', sysdate(), sysdate(), '');
-- 角色管理按钮
INSERT INTO `sys_menu` VALUES(1007, '角色查询', 101, 1,  '', '', '', 1, 1, 'F', 1, 1, 'system:role:query',          '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1008, '角色新增', 101, 2,  '', '', '', 1, 1, 'F', 1, 1, 'system:role:add',            '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1009, '角色修改', 101, 3,  '', '', '', 1, 1, 'F', 1, 1, 'system:role:edit',           '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1010, '角色删除', 101, 4,  '', '', '', 1, 1, 'F', 1, 1, 'system:role:remove',         '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1011, '角色导出', 101, 5,  '', '', '', 1, 1, 'F', 1, 1, 'system:role:export',         '#', 'admin', '', sysdate(), sysdate(), '');
-- 菜单管理按钮
INSERT INTO `sys_menu` VALUES(1012, '菜单查询', 102, 1,  '', '', '', 1, 1, 'F', 1, 1, 'system:menu:query',          '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1013, '菜单新增', 102, 2,  '', '', '', 1, 1, 'F', 1, 1, 'system:menu:add',            '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1014, '菜单修改', 102, 3,  '', '', '', 1, 1, 'F', 1, 1, 'system:menu:edit',           '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1015, '菜单删除', 102, 4,  '', '', '', 1, 1, 'F', 1, 1, 'system:menu:remove',         '#', 'admin', '', sysdate(), sysdate(), '');
-- 部门管理按钮
INSERT INTO `sys_menu` VALUES(1016, '部门查询', 103, 1,  '', '', '', 1, 1, 'F', 1, 1, 'system:dept:query',          '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1017, '部门新增', 103, 2,  '', '', '', 1, 1, 'F', 1, 1, 'system:dept:add',            '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1018, '部门修改', 103, 3,  '', '', '', 1, 1, 'F', 1, 1, 'system:dept:edit',           '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1019, '部门删除', 103, 4,  '', '', '', 1, 1, 'F', 1, 1, 'system:dept:remove',         '#', 'admin', '', sysdate(), sysdate(), '');
-- 岗位管理按钮
INSERT INTO `sys_menu` VALUES(1020, '岗位查询', 104, 1,  '', '', '', 1, 1, 'F', 1, 1, 'system:post:query',          '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1021, '岗位新增', 104, 2,  '', '', '', 1, 1, 'F', 1, 1, 'system:post:add',            '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1022, '岗位修改', 104, 3,  '', '', '', 1, 1, 'F', 1, 1, 'system:post:edit',           '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1023, '岗位删除', 104, 4,  '', '', '', 1, 1, 'F', 1, 1, 'system:post:remove',         '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1024, '岗位导出', 104, 5,  '', '', '', 1, 1, 'F', 1, 1, 'system:post:export',         '#', 'admin', '', sysdate(), sysdate(), '');
-- 字典管理按钮
INSERT INTO `sys_menu` VALUES(1025, '字典查询', 105, 1, '#', '', '', 1, 1, 'F', 1, 1, 'system:dict:query',          '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1026, '字典新增', 105, 2, '#', '', '', 1, 1, 'F', 1, 1, 'system:dict:add',            '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1027, '字典修改', 105, 3, '#', '', '', 1, 1, 'F', 1, 1, 'system:dict:edit',           '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1028, '字典删除', 105, 4, '#', '', '', 1, 1, 'F', 1, 1, 'system:dict:remove',         '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1029, '字典导出', 105, 5, '#', '', '', 1, 1, 'F', 1, 1, 'system:dict:export',         '#', 'admin', '', sysdate(), sysdate(), '');
-- 参数设置按钮
INSERT INTO `sys_menu` VALUES(1030, '参数查询', 106, 1, '#', '', '', 1, 1, 'F', 1, 1, 'system:config:query',        '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1031, '参数新增', 106, 2, '#', '', '', 1, 1, 'F', 1, 1, 'system:config:add',          '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1032, '参数修改', 106, 3, '#', '', '', 1, 1, 'F', 1, 1, 'system:config:edit',         '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1033, '参数删除', 106, 4, '#', '', '', 1, 1, 'F', 1, 1, 'system:config:remove',       '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1034, '参数导出', 106, 5, '#', '', '', 1, 1, 'F', 1, 1, 'system:config:export',       '#', 'admin', '', sysdate(), sysdate(), '');
-- 通知公告按钮
INSERT INTO `sys_menu` VALUES(1035, '公告查询', 107, 1, '#', '', '', 1, 1, 'F', 1, 1, 'system:notice:query',        '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1036, '公告新增', 107, 2, '#', '', '', 1, 1, 'F', 1, 1, 'system:notice:add',          '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1037, '公告修改', 107, 3, '#', '', '', 1, 1, 'F', 1, 1, 'system:notice:edit',         '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1038, '公告删除', 107, 4, '#', '', '', 1, 1, 'F', 1, 1, 'system:notice:remove',       '#', 'admin', '', sysdate(), sysdate(), '');
-- 操作日志按钮
INSERT INTO `sys_menu` VALUES(1039, '操作查询', 500, 1, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:operlog:query',      '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1040, '操作删除', 500, 2, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:operlog:remove',     '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1041, '日志导出', 500, 3, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:operlog:export',     '#', 'admin', '', sysdate(), sysdate(), '');
-- 登录日志按钮
INSERT INTO `sys_menu` VALUES(1042, '登录查询', 501, 1, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:logininfor:query',   '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1043, '登录删除', 501, 2, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:logininfor:remove',  '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1044, '日志导出', 501, 3, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:logininfor:export',  '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1045, '账户解锁', 501, 4, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:logininfor:unlock',  '#', 'admin', '', sysdate(), sysdate(), '');
-- 在线用户按钮
INSERT INTO `sys_menu` VALUES(1046, '在线查询', 109, 1, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:online:query',       '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1047, '批量强退', 109, 2, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:online:batchLogout', '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1048, '单条强退', 109, 3, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:online:forceLogout', '#', 'admin', '', sysdate(), sysdate(), '');
-- 定时任务按钮
INSERT INTO `sys_menu` VALUES(1049, '任务查询', 110, 1, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:job:query',          '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1050, '任务新增', 110, 2, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:job:add',            '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1051, '任务修改', 110, 3, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:job:edit',           '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1052, '任务删除', 110, 4, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:job:remove',         '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1053, '状态修改', 110, 5, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:job:changeStatus',   '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1054, '任务导出', 110, 6, '#', '', '', 1, 1, 'F', 1, 1, 'monitor:job:export',         '#', 'admin', '', sysdate(), sysdate(), '');
-- 代码生成按钮
INSERT INTO `sys_menu` VALUES(1055, '生成查询', 116, 1, '#', '', '', 1, 1, 'F', 1, 1, 'tool:gen:query',             '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1056, '生成修改', 116, 2, '#', '', '', 1, 1, 'F', 1, 1, 'tool:gen:edit',              '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1057, '生成删除', 116, 3, '#', '', '', 1, 1, 'F', 1, 1, 'tool:gen:remove',            '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1058, '导入代码', 116, 4, '#', '', '', 1, 1, 'F', 1, 1, 'tool:gen:import',            '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1059, '预览代码', 116, 5, '#', '', '', 1, 1, 'F', 1, 1, 'tool:gen:preview',           '#', 'admin', '', sysdate(), sysdate(), '');
INSERT INTO `sys_menu` VALUES(1060, '生成代码', 116, 6, '#', '', '', 1, 1, 'F', 1, 1, 'tool:gen:code',              '#', 'admin', '', sysdate(), sysdate(), '');

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 100),
       (2, 101),
       (2, 102),
       (2, 103),
       (2, 104),
       (2, 105),
       (2, 106),
       (2, 107),
       (2, 108),
       (2, 109),
       (2, 110),
       (2, 111),
       (2, 112),
       (2, 113),
       (2, 114),
       (2, 115),
       (2, 116),
       (2, 117),
       (2, 500),
       (2, 501),
       (2, 1000),
       (2, 1001),
       (2, 1002),
       (2, 1003),
       (2, 1004),
       (2, 1005),
       (2, 1006),
       (2, 1007),
       (2, 1008),
       (2, 1009),
       (2, 1010),
       (2, 1011),
       (2, 1012),
       (2, 1013),
       (2, 1014),
       (2, 1015),
       (2, 1016),
       (2, 1017),
       (2, 1018),
       (2, 1019),
       (2, 1020),
       (2, 1021),
       (2, 1022),
       (2, 1023),
       (2, 1024),
       (2, 1025),
       (2, 1026),
       (2, 1027),
       (2, 1028),
       (2, 1029),
       (2, 1030),
       (2, 1031),
       (2, 1032),
       (2, 1033),
       (2, 1034),
       (2, 1035),
       (2, 1036),
       (2, 1037),
       (2, 1038),
       (2, 1039),
       (2, 1040),
       (2, 1041),
       (2, 1042),
       (2, 1043),
       (2, 1044),
       (2, 1045),
       (2, 1046),
       (2, 1047),
       (2, 1048),
       (2, 1049),
       (2, 1050),
       (2, 1051),
       (2, 1052),
       (2, 1053),
       (2, 1054),
       (2, 1055),
       (2, 1056),
       (2, 1057),
       (2, 1058),
       (2, 1059);