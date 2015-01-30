insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140729123743', '20140731113148', '用户管理', '用户管理', '/UserAction.action?method=gotoUserList', '2', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('1', '0', '系统菜单', '系统菜单', null, '1', 'style/ligerUI/ligerUI/skins/icons/home.gif', '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140728023728', '20140731113148', '用户组管理', '用户组管理', '/UserGroupAction.action?method=gotoUserGroupList', '5', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('3050', '20140731135118', '系统属性管理', '系统属性管理', '/AttrAction.action?method=gotoAttrList', '1', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140729122650', '3000', '日志管理', '日志管理', null, '4', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140729122709', '20140729122650', '系统日志', '系统日志', '/SysLogAction.action?method=gotoSysLogList', '1', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140729122722', '20140729122650', '上传日志', '上传日志', '/UploadLogAction.action?method=gotoUploadLogList', '2', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140729122731', '20140729122650', '下载日志', '下载日志', '/DownLogAction.action?method=gotoDownLogList', '3', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140727124449', '20140731113148', '角色管理', '角色管理', '/RoleAction.action?method=gotoRoleList', '1', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140731135118', '3000', '系统环境设置', '系统环境设置', null, '2', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140731113148', '3000', '权限管理', '权限管理', null, '3', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140729094340', '20140731113148', '机构管理', '机构管理', '/OrgAction.action?method=gotoOrgMain', '3', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140812173421', '20140729122650', '错误日志', '错误日志', '/ErrorLogAction.action?method=gotoErrorLogList', '4', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140801162519', '20140731135118', '系统日志配置', '维护需要记录系统日志的URL', '/LogSettingAction.action?method=gotoLogSettingList', null, null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140814172320', '1', '数据加工', '数据加工', null, '2', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140814172346', '20140814172320', '数据上传', '数据上传', '/UploadAction.action?method=gotoUpload', '1', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140814172426', '20140814172320', '数据管理', '数据管理', '/DataSetAction.action?method=gotoDataSetList', '2', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('3000', '1', '系统管理', '系统管理', null, '1', 'style/ligerUI/ligerUI/skins/icons/config.gif', '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('3060', '20140731135118', '系统参数管理', '系统参数管理', '/ParamAction.action?method=gotoParamList', '2', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('3070', '20140731113148', '菜单管理', '菜单管理', '/MenuAction.action?method=gotoMenuMain', '4', null, '1');
commit;
