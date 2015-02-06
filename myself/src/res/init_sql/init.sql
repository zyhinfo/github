
/* dataManage  Drop Tables */

DROP TABLE T02_DATA_ATTR;
DROP TABLE T02_DATA_INSTANCE;




/* Create Tables */

CREATE TABLE T02_DATA_ATTR
(
	ATTR_ID VARCHAR2(20),
	ATTR_NAME VARCHAR2(200),
	ATTR_DESC VARCHAR2(200),
	ATTR_TYPE VARCHAR2(20),
	ATTR_LEN VARCHAR2(20),
	SEQ_NUM NUMBER,
	STATUS VARCHAR2(2)
);


CREATE TABLE T02_DATA_INSTANCE
(
	INST_ID VARCHAR2(20),
	INST_NAME VARCHAR2(200),
	INST_TABLE VARCHAR2(200),
	ROW_NUM VARCHAR2(20),
	CREATE_DATE DATE,
	USER_ID VARCHAR2(20),
	STATUS VARCHAR2(2)
);



/* Comments */

COMMENT ON TABLE T02_DATA_ATTR IS '数据属性表';
COMMENT ON COLUMN T02_DATA_ATTR.ATTR_ID IS '编号';
COMMENT ON COLUMN T02_DATA_ATTR.ATTR_NAME IS '属性名称';
COMMENT ON COLUMN T02_DATA_ATTR.ATTR_DESC IS '属性描述';
COMMENT ON COLUMN T02_DATA_ATTR.ATTR_TYPE IS '属性类型';
COMMENT ON COLUMN T02_DATA_ATTR.ATTR_LEN IS '属性长度';
COMMENT ON COLUMN T02_DATA_ATTR.SEQ_NUM IS '排序';
COMMENT ON COLUMN T02_DATA_ATTR.STATUS IS '状态';
COMMENT ON TABLE T02_DATA_INSTANCE IS '数据实例表';
COMMENT ON COLUMN T02_DATA_INSTANCE.INST_ID IS '编号';
COMMENT ON COLUMN T02_DATA_INSTANCE.INST_NAME IS '实例名称';
COMMENT ON COLUMN T02_DATA_INSTANCE.INST_TABLE IS '数据表';
COMMENT ON COLUMN T02_DATA_INSTANCE.ROW_NUM IS '数据量';
COMMENT ON COLUMN T02_DATA_INSTANCE.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN T02_DATA_INSTANCE.USER_ID IS '用户编号';
COMMENT ON COLUMN T02_DATA_INSTANCE.STATUS IS '状态';




/*logManage  Drop Tables */

DROP TABLE T01_SYS_DOWN_LOG;
DROP TABLE T01_SYS_ERROR_LOG;
DROP TABLE T01_SYS_LOG;
DROP TABLE T01_SYS_LOG_SETTING;
DROP TABLE T01_SYS_UPLOAD_LOG;




/* Create Tables */

CREATE TABLE T01_SYS_DOWN_LOG
(
	DOWN_ID VARCHAR2(20),
	DOWN_DESC VARCHAR2(200),
	DOWN_FILE_PATH VARCHAR2(200),
	DOWN_PROGRESS VARCHAR2(20),
	FILE_SIZE VARCHAR2(20),
	FILE_ROWNUM VARCHAR2(20),
	-- 0=no
	-- 1=yes
	IS_DOWN VARCHAR2(2),
	USER_ID VARCHAR2(20),
	USER_NAME VARCHAR2(200),
	START_DATE DATE,
	END_DATE DATE
);


CREATE TABLE T01_SYS_ERROR_LOG
(
	ERROR_ID VARCHAR2(20),
	MENU_NAME VARCHAR2(200),
	ERROR_SUMMARY VARCHAR2(200),
	ERROR_DESCRIPTION VARCHAR2(2000),
	USER_ID VARCHAR2(20),
	USER_NAME VARCHAR2(200),
	CREATE_DATE DATE
);


CREATE TABLE T01_SYS_LOG
(
	LOG_ID VARCHAR2(20),
	USER_ID VARCHAR2(20),
	USER_NAME VARCHAR2(20),
	LOG_DESC VARCHAR2(200),
	OPERATOR_TIME DATE
);


CREATE TABLE T01_SYS_LOG_SETTING
(
	LINK_ID VARCHAR2(20),
	LINK_DESC VARCHAR2(200),
	LINK_URL VARCHAR2(200)
);


CREATE TABLE T01_SYS_UPLOAD_LOG
(
	UPLOAD_ID VARCHAR2(20),
	UPLOAD_DESC VARCHAR2(200),
	UPLOAD_FILE_PATH VARCHAR2(200),
	-- -1:上传失败
	-- 0：准备上传，
	-- 1：正在上传进度为10%
	-- 2：正在上传进度为20%
	-- 10：进度100%
	UPLOAD_PROGRESS VARCHAR2(20),
	FILE_SIZE VARCHAR2(20),
	FILE_ROWNUM VARCHAR2(20),
	ERROR_ROWNUM VARCHAR2(20),
	USER_ID VARCHAR2(20),
	START_DATE DATE,
	END_DATE DATE,
	USER_NAME VARCHAR2(200),
	IS_TITLE VARCHAR2(1)
);



/* Comments */

COMMENT ON TABLE T01_SYS_DOWN_LOG IS '系统下载日志';
COMMENT ON COLUMN T01_SYS_DOWN_LOG.DOWN_ID IS '下载编号';
COMMENT ON COLUMN T01_SYS_DOWN_LOG.DOWN_DESC IS '下载描述';
COMMENT ON COLUMN T01_SYS_DOWN_LOG.DOWN_FILE_PATH IS '下载文件路径';
COMMENT ON COLUMN T01_SYS_DOWN_LOG.DOWN_PROGRESS IS '下载进度';
COMMENT ON COLUMN T01_SYS_DOWN_LOG.FILE_SIZE IS '文件大小';
COMMENT ON COLUMN T01_SYS_DOWN_LOG.FILE_ROWNUM IS '文件行数';
COMMENT ON COLUMN T01_SYS_DOWN_LOG.IS_DOWN IS '是否下载(0=no,1=yes) : 0=no1=yes';
COMMENT ON COLUMN T01_SYS_DOWN_LOG.USER_ID IS '用户编号';
COMMENT ON COLUMN T01_SYS_DOWN_LOG.USER_NAME IS '用户名';
COMMENT ON COLUMN T01_SYS_DOWN_LOG.START_DATE IS '开始时间';
COMMENT ON COLUMN T01_SYS_DOWN_LOG.END_DATE IS '结束时间';
COMMENT ON TABLE T01_SYS_ERROR_LOG IS '系统错误日志';
COMMENT ON COLUMN T01_SYS_ERROR_LOG.ERROR_ID IS '错误编号';
COMMENT ON COLUMN T01_SYS_ERROR_LOG.MENU_NAME IS '模块名称';
COMMENT ON COLUMN T01_SYS_ERROR_LOG.ERROR_SUMMARY IS '概括信息';
COMMENT ON COLUMN T01_SYS_ERROR_LOG.ERROR_DESCRIPTION IS '错误明细信息';
COMMENT ON COLUMN T01_SYS_ERROR_LOG.USER_ID IS '用户编号';
COMMENT ON COLUMN T01_SYS_ERROR_LOG.USER_NAME IS '用户名称';
COMMENT ON COLUMN T01_SYS_ERROR_LOG.CREATE_DATE IS '创建时间';
COMMENT ON TABLE T01_SYS_LOG IS 'T01_SYS_LOG';
COMMENT ON COLUMN T01_SYS_LOG.LOG_ID IS '日志编号';
COMMENT ON COLUMN T01_SYS_LOG.USER_ID IS '用户编号';
COMMENT ON COLUMN T01_SYS_LOG.USER_NAME IS '用户名称';
COMMENT ON COLUMN T01_SYS_LOG.LOG_DESC IS '日志描述';
COMMENT ON COLUMN T01_SYS_LOG.OPERATOR_TIME IS '操作时间';
COMMENT ON TABLE T01_SYS_LOG_SETTING IS '系统日志设置';
COMMENT ON COLUMN T01_SYS_LOG_SETTING.LINK_ID IS '编号';
COMMENT ON COLUMN T01_SYS_LOG_SETTING.LINK_DESC IS '描述';
COMMENT ON COLUMN T01_SYS_LOG_SETTING.LINK_URL IS '链接';
COMMENT ON TABLE T01_SYS_UPLOAD_LOG IS '系统上传日志';
COMMENT ON COLUMN T01_SYS_UPLOAD_LOG.UPLOAD_ID IS '上传编号';
COMMENT ON COLUMN T01_SYS_UPLOAD_LOG.UPLOAD_DESC IS '上传描述';
COMMENT ON COLUMN T01_SYS_UPLOAD_LOG.UPLOAD_FILE_PATH IS '文件完整路径';
COMMENT ON COLUMN T01_SYS_UPLOAD_LOG.UPLOAD_PROGRESS IS '上传进度 : -1:上传失败
0：准备上传，
1：正在上传进度为10%
2：正在上传进度为20%
10：进度100%';
COMMENT ON COLUMN T01_SYS_UPLOAD_LOG.FILE_SIZE IS '文件大小';
COMMENT ON COLUMN T01_SYS_UPLOAD_LOG.FILE_ROWNUM IS '文件行数';
COMMENT ON COLUMN T01_SYS_UPLOAD_LOG.ERROR_ROWNUM IS '错误行数';
COMMENT ON COLUMN T01_SYS_UPLOAD_LOG.USER_ID IS '用户编号';
COMMENT ON COLUMN T01_SYS_UPLOAD_LOG.START_DATE IS '开始时间';
COMMENT ON COLUMN T01_SYS_UPLOAD_LOG.END_DATE IS '结束时间';
COMMENT ON COLUMN T01_SYS_UPLOAD_LOG.USER_NAME IS '用户名';
COMMENT ON COLUMN T01_SYS_UPLOAD_LOG.IS_TITLE IS '是否存在标题';




/*paramManage   Drop Tables */

DROP TABLE T02_DATA_PARAM_DETAIL;
DROP TABLE T02_DATA_PARAM;




/* Create Tables */

CREATE TABLE T02_DATA_PARAM
(
	PARAM_ID VARCHAR2(20) NOT NULL,
	PARAM_NAME VARCHAR2(200),
	UPDATE_DATE DATE,
	CREATE_DATE DATE
);


CREATE TABLE T02_DATA_PARAM_DETAIL
(
	DETAIL_ID VARCHAR2(20) NOT NULL,
	PARAM_ID VARCHAR2(20) NOT NULL,
	DETAIL_NAME VARCHAR2(200),
	DETAIL_KEY VARCHAR2(20),
	DETAIL_VALUE VARCHAR2(200)
);



/* Comments */

COMMENT ON TABLE T02_DATA_PARAM IS '参数列表';
COMMENT ON COLUMN T02_DATA_PARAM.PARAM_ID IS '参数编号';
COMMENT ON COLUMN T02_DATA_PARAM.PARAM_NAME IS '参数名称';
COMMENT ON COLUMN T02_DATA_PARAM.UPDATE_DATE IS '更新时间';
COMMENT ON COLUMN T02_DATA_PARAM.CREATE_DATE IS '创建时间';
COMMENT ON TABLE T02_DATA_PARAM_DETAIL IS '参数明细表';
COMMENT ON COLUMN T02_DATA_PARAM_DETAIL.DETAIL_ID IS '明细编号';
COMMENT ON COLUMN T02_DATA_PARAM_DETAIL.PARAM_ID IS '参数编号';
COMMENT ON COLUMN T02_DATA_PARAM_DETAIL.DETAIL_NAME IS '名称';
COMMENT ON COLUMN T02_DATA_PARAM_DETAIL.DETAIL_KEY IS 'DETAIL_KEY';
COMMENT ON COLUMN T02_DATA_PARAM_DETAIL.DETAIL_VALUE IS 'DETAIL_VALUE';



/* personalFile   Drop Tables */

DROP TABLE T03_ARCHIVE_EDUCATION;
DROP TABLE T03_ARCHIVE_HOLIDAY;
DROP TABLE T03_ARCHIVE_JOB;
DROP TABLE T03_ARCHIVE_REWARD;
DROP TABLE T03_ARCHIVE_TRAIN;
DROP TABLE T03_ARCHIVE_INFO;




/* Create Tables */

CREATE TABLE T03_ARCHIVE_EDUCATION
(
	ID VARCHAR2(20),
	SCHOOL VARCHAR2(200),
	START_DATE DATE,
	END_DATE DATE,
	PROFESSION VARCHAR2(20),
	EDUCATION VARCHAR2(20),
	DEGREE_NAME VARCHAR2(20),
	ARCHIVE_ID VARCHAR2(20) NOT NULL
);


CREATE TABLE T03_ARCHIVE_HOLIDAY
(
	ID VARCHAR2(20),
	REASON VARCHAR2(2000),
	-- 1_事假；2_病假；3_年假；4_其他
	IS_TYPE VARCHAR2(20),
	START_DATE DATE,
	END_DATE DATE,
	ARCHIVE_ID VARCHAR2(20) NOT NULL
);


CREATE TABLE T03_ARCHIVE_INFO
(
	ARCHIVE_ID VARCHAR2(20) NOT NULL,
	USER_ID VARCHAR2(20),
	USER_NAME VARCHAR2(200),
	ORG_ID VARCHAR2(20),
	POSITION_NAME VARCHAR2(20),
	BIRTHDAY DATE,
	SEX VARCHAR2(2),
	NATONAL_NAME VARCHAR2(20),
	ZZMM VARCHAR2(20),
	TEL VARCHAR2(20),
	HOMETEL VARCHAR2(20),
	HOME_ADDR VARCHAR2(200),
	-- Base64编码
	PHOTO CLOB,
	-- 0_无考核;1_20%;2_30%;3_50%;根据情况调整
	KHBZ VARCHAR2(2),
	-- OCP,PMP,系统集成等
	JOB_TITLE VARCHAR2(20),
	-- 0_在职；1_离职
	STATE VARCHAR2(2)
);


CREATE TABLE T03_ARCHIVE_JOB
(
	ID VARCHAR2(20),
	COMPANY_NAME VARCHAR2(200),
	COMPANY_ADDR VARCHAR2(200),
	ADD_DATE DATE,
	LEAVE_DATE DATE,
	POSITION VARCHAR2(20),
	JOB_DES VARCHAR2(2000),
	LEAVE_REASON VARCHAR2(200),
	ARCHIVE_ID VARCHAR2(20) NOT NULL
);


CREATE TABLE T03_ARCHIVE_REWARD
(
	ID VARCHAR2(20),
	JF_REASON VARCHAR2(200),
	JF_DETAIL VARCHAR2(2000),
	-- 1_奖励；2_处罚
	JF_TYPE VARCHAR2(20),
	JF_DATE DATE,
	ARCHIVE_ID VARCHAR2(20) NOT NULL
);


CREATE TABLE T03_ARCHIVE_TRAIN
(
	ID VARCHAR2(20),
	SCHOOL VARCHAR2(200),
	TRAIN_DESC VARCHAR2(2000),
	START_DATE DATE,
	END_DATE DATE,
	ARCHIVE_ID VARCHAR2(20) NOT NULL
);



/* Comments */

COMMENT ON TABLE T03_ARCHIVE_EDUCATION IS '教育经历表';
COMMENT ON COLUMN T03_ARCHIVE_EDUCATION.ID IS '编号';
COMMENT ON COLUMN T03_ARCHIVE_EDUCATION.SCHOOL IS '培训学校';
COMMENT ON COLUMN T03_ARCHIVE_EDUCATION.START_DATE IS '入学时间';
COMMENT ON COLUMN T03_ARCHIVE_EDUCATION.END_DATE IS '毕业时间';
COMMENT ON COLUMN T03_ARCHIVE_EDUCATION.PROFESSION IS '专业';
COMMENT ON COLUMN T03_ARCHIVE_EDUCATION.EDUCATION IS '学历';
COMMENT ON COLUMN T03_ARCHIVE_EDUCATION.DEGREE_NAME IS '学位';
COMMENT ON COLUMN T03_ARCHIVE_EDUCATION.ARCHIVE_ID IS '档案编号';
COMMENT ON TABLE T03_ARCHIVE_HOLIDAY IS '年度休假情况表';
COMMENT ON COLUMN T03_ARCHIVE_HOLIDAY.ID IS '编号';
COMMENT ON COLUMN T03_ARCHIVE_HOLIDAY.REASON IS '休假原因';
COMMENT ON COLUMN T03_ARCHIVE_HOLIDAY.IS_TYPE IS '休假类型 : 1_事假；2_病假；3_年假；4_其他';
COMMENT ON COLUMN T03_ARCHIVE_HOLIDAY.START_DATE IS '开始时间';
COMMENT ON COLUMN T03_ARCHIVE_HOLIDAY.END_DATE IS '结束时间';
COMMENT ON COLUMN T03_ARCHIVE_HOLIDAY.ARCHIVE_ID IS '档案编号';
COMMENT ON TABLE T03_ARCHIVE_INFO IS '档案基本信息表';
COMMENT ON COLUMN T03_ARCHIVE_INFO.ARCHIVE_ID IS '档案编号';
COMMENT ON COLUMN T03_ARCHIVE_INFO.USER_ID IS '用户编号';
COMMENT ON COLUMN T03_ARCHIVE_INFO.USER_NAME IS '用户名称';
COMMENT ON COLUMN T03_ARCHIVE_INFO.ORG_ID IS '部门编号';
COMMENT ON COLUMN T03_ARCHIVE_INFO.POSITION_NAME IS '职位';
COMMENT ON COLUMN T03_ARCHIVE_INFO.BIRTHDAY IS '出生日期';
COMMENT ON COLUMN T03_ARCHIVE_INFO.SEX IS '性别';
COMMENT ON COLUMN T03_ARCHIVE_INFO.NATONAL_NAME IS '民族';
COMMENT ON COLUMN T03_ARCHIVE_INFO.ZZMM IS '政治面貌';
COMMENT ON COLUMN T03_ARCHIVE_INFO.TEL IS '个人电话';
COMMENT ON COLUMN T03_ARCHIVE_INFO.HOMETEL IS '家庭电话';
COMMENT ON COLUMN T03_ARCHIVE_INFO.HOME_ADDR IS '家庭地址';
COMMENT ON COLUMN T03_ARCHIVE_INFO.PHOTO IS '照片 : Base64编码';
COMMENT ON COLUMN T03_ARCHIVE_INFO.KHBZ IS '考核标志 : 0_无考核;1_20%;2_30%;3_50%;根据情况调整';
COMMENT ON COLUMN T03_ARCHIVE_INFO.JOB_TITLE IS '职称 : OCP,PMP,系统集成等';
COMMENT ON COLUMN T03_ARCHIVE_INFO.STATE IS '状态 : 0_在职；1_离职';
COMMENT ON TABLE T03_ARCHIVE_JOB IS '工作经历表';
COMMENT ON COLUMN T03_ARCHIVE_JOB.ID IS '编号';
COMMENT ON COLUMN T03_ARCHIVE_JOB.COMPANY_NAME IS '公司名称';
COMMENT ON COLUMN T03_ARCHIVE_JOB.COMPANY_ADDR IS '公司地址';
COMMENT ON COLUMN T03_ARCHIVE_JOB.ADD_DATE IS '入职时间';
COMMENT ON COLUMN T03_ARCHIVE_JOB.LEAVE_DATE IS '离职时间';
COMMENT ON COLUMN T03_ARCHIVE_JOB.POSITION IS '职位';
COMMENT ON COLUMN T03_ARCHIVE_JOB.JOB_DES IS '工作职责';
COMMENT ON COLUMN T03_ARCHIVE_JOB.LEAVE_REASON IS '离职原因';
COMMENT ON COLUMN T03_ARCHIVE_JOB.ARCHIVE_ID IS '档案编号';
COMMENT ON TABLE T03_ARCHIVE_REWARD IS '奖罚情况表';
COMMENT ON COLUMN T03_ARCHIVE_REWARD.ID IS '编号';
COMMENT ON COLUMN T03_ARCHIVE_REWARD.JF_REASON IS '奖罚原因';
COMMENT ON COLUMN T03_ARCHIVE_REWARD.JF_DETAIL IS '奖罚明细';
COMMENT ON COLUMN T03_ARCHIVE_REWARD.JF_TYPE IS '奖罚分类 : 1_奖励；2_处罚';
COMMENT ON COLUMN T03_ARCHIVE_REWARD.JF_DATE IS '奖罚时间';
COMMENT ON COLUMN T03_ARCHIVE_REWARD.ARCHIVE_ID IS '档案编号';
COMMENT ON TABLE T03_ARCHIVE_TRAIN IS '培训经历表';
COMMENT ON COLUMN T03_ARCHIVE_TRAIN.ID IS '编号';
COMMENT ON COLUMN T03_ARCHIVE_TRAIN.SCHOOL IS '培训学校';
COMMENT ON COLUMN T03_ARCHIVE_TRAIN.TRAIN_DESC IS '培训内容';
COMMENT ON COLUMN T03_ARCHIVE_TRAIN.START_DATE IS '入学时间';
COMMENT ON COLUMN T03_ARCHIVE_TRAIN.END_DATE IS '毕业时间';
COMMENT ON COLUMN T03_ARCHIVE_TRAIN.ARCHIVE_ID IS '档案编号';




/*sysManage   Drop Tables */

DROP TABLE T01_SYS_ROLE_MENU;
DROP TABLE T01_SYS_MENU;
DROP TABLE T01_SYS_USER_USERGROUP;
DROP TABLE T01_SYS_ORG;
DROP TABLE T01_SYS_USERGROUP_ROLE;
DROP TABLE T01_SYS_ROLE;
DROP TABLE T01_SYS_USER;
DROP TABLE T01_SYS_USERGROUP;




/* Create Tables */

CREATE TABLE T01_SYS_MENU
(
	MENU_ID VARCHAR2(20) NOT NULL,
	OWN_MENU_ID VARCHAR2(20),
	MENU_NAME VARCHAR2(200),
	MENU_DESC VARCHAR2(200),
	LINK_URL VARCHAR2(200),
	MENU_SEQ VARCHAR2(2),
	MENU_ICON VARCHAR2(200),
	STATUS VARCHAR2(2)
);


CREATE TABLE T01_SYS_ORG
(
	ORG_ID VARCHAR2(20) NOT NULL,
	ORG_NAME VARCHAR2(200) NOT NULL,
	OWN_ORG_ID VARCHAR2(20),
	ORG_LEV VARCHAR2(2)
);


CREATE TABLE T01_SYS_ROLE
(
	ROLE_ID VARCHAR2(20) NOT NULL,
	ROLE_NAME VARCHAR2(200),
	ROLE_DESC VARCHAR2(200)
);


CREATE TABLE T01_SYS_ROLE_MENU
(
	MENU_ID VARCHAR2(20) NOT NULL,
	ROLE_ID VARCHAR2(20) NOT NULL
);


CREATE TABLE T01_SYS_USER
(
	USER_ID VARCHAR2(20) NOT NULL,
	USER_NAME VARCHAR2(200),
	USER_DESC VARCHAR2(200),
	LOGON_NAME VARCHAR2(20),
	PASSWORD VARCHAR2(20)
);


CREATE TABLE T01_SYS_USERGROUP
(
	USERGROUP_ID VARCHAR2(20) NOT NULL,
	USERGROUP_NAME VARCHAR2(200),
	USERGROUP_DESC VARCHAR2(200)
);


CREATE TABLE T01_SYS_USERGROUP_ROLE
(
	USERGROUP_ID VARCHAR2(20) NOT NULL,
	ROLE_ID VARCHAR2(20) NOT NULL
);


CREATE TABLE T01_SYS_USER_USERGROUP
(
	USER_USERGROUP_ID VARCHAR2(20) NOT NULL,
	ORG_ID VARCHAR2(20) NOT NULL,
	USER_ID VARCHAR2(20) NOT NULL,
	USERGROUP_ID VARCHAR2(20) NOT NULL
);



/* Comments */

COMMENT ON TABLE T01_SYS_MENU IS '菜单表';
COMMENT ON COLUMN T01_SYS_MENU.MENU_ID IS '菜单编号';
COMMENT ON COLUMN T01_SYS_MENU.OWN_MENU_ID IS '上级菜单';
COMMENT ON COLUMN T01_SYS_MENU.MENU_NAME IS '菜单名称';
COMMENT ON COLUMN T01_SYS_MENU.MENU_DESC IS '菜单描述';
COMMENT ON COLUMN T01_SYS_MENU.LINK_URL IS '菜单链接';
COMMENT ON COLUMN T01_SYS_MENU.MENU_SEQ IS '显示顺序';
COMMENT ON COLUMN T01_SYS_MENU.MENU_ICON IS '图片链接';
COMMENT ON COLUMN T01_SYS_MENU.STATUS IS '状态';
COMMENT ON TABLE T01_SYS_ORG IS '机构表';
COMMENT ON COLUMN T01_SYS_ORG.ORG_ID IS '机构号';
COMMENT ON COLUMN T01_SYS_ORG.ORG_NAME IS '机构名称';
COMMENT ON COLUMN T01_SYS_ORG.OWN_ORG_ID IS '上级机构号';
COMMENT ON COLUMN T01_SYS_ORG.ORG_LEV IS '机构级别';
COMMENT ON TABLE T01_SYS_ROLE IS '角色表';
COMMENT ON COLUMN T01_SYS_ROLE.ROLE_ID IS '角色编号';
COMMENT ON COLUMN T01_SYS_ROLE.ROLE_NAME IS '角色名称';
COMMENT ON COLUMN T01_SYS_ROLE.ROLE_DESC IS '角色描述';
COMMENT ON TABLE T01_SYS_ROLE_MENU IS 'T01_SYS_ROLE_MENU';
COMMENT ON COLUMN T01_SYS_ROLE_MENU.MENU_ID IS '菜单编号';
COMMENT ON COLUMN T01_SYS_ROLE_MENU.ROLE_ID IS '角色编号';
COMMENT ON TABLE T01_SYS_USER IS '用户表';
COMMENT ON COLUMN T01_SYS_USER.USER_ID IS '用户编号';
COMMENT ON COLUMN T01_SYS_USER.USER_NAME IS '用户名称';
COMMENT ON COLUMN T01_SYS_USER.USER_DESC IS '用户描述';
COMMENT ON COLUMN T01_SYS_USER.LOGON_NAME IS '登录名';
COMMENT ON COLUMN T01_SYS_USER.PASSWORD IS '密码';
COMMENT ON TABLE T01_SYS_USERGROUP IS '用户组表';
COMMENT ON COLUMN T01_SYS_USERGROUP.USERGROUP_ID IS '用户组编号';
COMMENT ON COLUMN T01_SYS_USERGROUP.USERGROUP_NAME IS '用户组名称';
COMMENT ON COLUMN T01_SYS_USERGROUP.USERGROUP_DESC IS '用户组描述';
COMMENT ON TABLE T01_SYS_USERGROUP_ROLE IS '用户组和角色对应关系';
COMMENT ON COLUMN T01_SYS_USERGROUP_ROLE.USERGROUP_ID IS '用户组编号';
COMMENT ON COLUMN T01_SYS_USERGROUP_ROLE.ROLE_ID IS '角色编号';
COMMENT ON TABLE T01_SYS_USER_USERGROUP IS '用户和用户组关系表';
COMMENT ON COLUMN T01_SYS_USER_USERGROUP.USER_USERGROUP_ID IS '用户和用户组关系编号';
COMMENT ON COLUMN T01_SYS_USER_USERGROUP.ORG_ID IS '机构号';
COMMENT ON COLUMN T01_SYS_USER_USERGROUP.USER_ID IS '用户编号';
COMMENT ON COLUMN T01_SYS_USER_USERGROUP.USERGROUP_ID IS '用户组编号';


insert into T01_SYS_USER(USER_ID,USER_NAME,USER_DESC,LOGON_NAME,PASSWORD) VALUES('1','超级管理员','超级管理员','admin','c');
insert into T03_ARCHIVE_INFO(archive_id,user_id,user_name) values('1','1','超级管理员');
commit;

insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140801214712', '系统属性修改', '/myself/AttrAction.action?method=saveAttrEdit');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140801211855', '系统属性新增', '/myself/AttrAction.action?method=saveAttrAdd');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140801222039', '用户新增', '/myself/UserAction.action?method=saveUserAdd');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140801222059', '用户修改', '/myself/UserAction.action?method=saveUserEdit');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803114429', '用户组新增', '/myself/UserGroupAction.action?method=saveUserGroupAdd');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803114523', '用户组修改', '/myself/UserGroupAction.action?method=saveUserGroupEdit');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803114641', '用户组和角色关系修改', '/myself/UserGroupRoleAction.action?method=updateUserGroupRole');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803114945', '角色新增', '/myself/RoleAction.action?method=saveRoleAdd');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803115504', '角色修改', '/myself/RoleAction.action?method=saveRoleEdit');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803115616', '菜单新增', '/myself/MenuAction.action?method=addMenu');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803115646', '菜单修改', '/myself/MenuAction.action?method=editMenu');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803120757', '菜单删除', '/myself/MenuAction.action?method=deleteMenu');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803120840', '角色删除', '/myself/RoleAction.action?method=deleteRole');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803121229', '系统属性删除', '/myself/AttrAction.action?method=deleteAttr');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803121316', '用户删除', '/myself/UserAction.action?method=deleteUser');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803121546', '用户组删除', '/myself/UserGroupAction.action?method=deleteUserGroup');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803121657', '机构新增', '/myself/OrgAction.action?method=addOrg');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803121731', '机构修改', '/myself/OrgAction.action?method=editOrg');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803121839', '机构删除', '/myself/OrgAction.action?method=deleteOrg');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803124406', '角色和菜单关系修改', '/myself/RoleAction.action?method=updateRoleAndMenu');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803124651', '用户、机构和用户组关系新增', '/myself/UserDetailAction.action?method=saveUserDetailAdd');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803124734', '用户、机构和用户组关系删除', '/myself/UserDetailAction.action?method=deleteUserDetail');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803125004', '系统参数新增', '/myself/ParamAction.action?method=saveParamAdd');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803125027', '系统参数修改', '/myself/ParamAction.action?method=saveParamEdit');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803125058', '系统参数删除', '/myself/ParamAction.action?method=deleteParam');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803125222', '系统参数明细新增', '/myself/ParamDetailAction.action?method=saveParamDetailAdd');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803125254', '系统参数明细修改', '/myself/ParamDetailAction.action?method=saveParamDetailEdit');
insert into T01_SYS_LOG_SETTING (link_id, link_desc, link_url)
values ('20140803125327', '系统参数明细删除', '/myself/ParamDetailAction.action?method=deleteParamDetail');
commit;

insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140729123743', '20140731113148', '用户管理', '一个用户对应多个用户组', '/UserAction.action?method=gotoUserList', '5', 'style/ligerUI/ligerUI/skins/icons/memeber.gif', '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('1', '0', '系统菜单', '系统菜单', null, '1', 'style/ligerUI/ligerUI/skins/icons/home.gif', '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140728023728', '20140731113148', '用户组管理', '一个用户组对应多个角色', '/UserGroupAction.action?method=gotoUserGroupList', '4', 'style/ligerUI/ligerUI/skins/icons/customers.gif', '1');
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
values ('20140727124449', '20140731113148', '角色管理', '一个角色对应多个菜单', '/RoleAction.action?method=gotoRoleList', '3', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140731135118', '3000', '系统环境设置', '系统环境设置描述', null, '1', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140731113148', '3000', '权限管理', '权限管理描述', null, '3', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140729094340', '20140731113148', '部门管理', '部门管理描述', '/OrgAction.action?method=gotoOrgMain', '1', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140812173421', '20140729122650', '错误日志', '错误日志', '/ErrorLogAction.action?method=gotoErrorLogList', '4', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140801162519', '20140731135118', '系统日志配置', '维护需要记录系统日志的URL', '/LogSettingAction.action?method=gotoLogSettingList', null, null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140814172320', '1', '数据加工', '数据加工', null, '2', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20150108103929934', '20150108103737118', '文件管理', '文件管理描述', '/', '1', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20140814172426', '20140814172320', '数据管理', '数据管理', '/DataSetAction.action?method=gotoDataSetList', '2', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20141011161951828', '1', '数据分析', '数据分析', null, '3', 'style/ligerUI/ligerUI/skins/icons/process.gif', '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20150105102141984', '20150108103737118', '档案管理', '档案管理描述', '/PersonalFileAction.action?method=gotoPersonalFileList', '6', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('20150108103737118', '3000', '系统维护', '系统维护描述', '/', '2', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('3000', '1', '系统管理', '系统管理描述', null, '1', 'style/ligerUI/ligerUI/skins/icons/settings.gif', '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('3060', '20140731135118', '系统参数管理', '系统参数管理描述', '/ParamAction.action?method=gotoParamList', '2', null, '1');
insert into T01_SYS_MENU (menu_id, own_menu_id, menu_name, menu_desc, link_url, menu_seq, menu_icon, status)
values ('3070', '20140731113148', '菜单管理', '菜单管理', '/MenuAction.action?method=gotoMenuMain', '2', null, '1');
commit;


insert into T01_SYS_ORG (org_id, org_name, own_org_id, org_lev)
values ('20150104180132241', 'IDSP', '20150104174500803', '3');
insert into T01_SYS_ORG (org_id, org_name, own_org_id, org_lev)
values ('20150104174500803', '爱迪特信息科技有限公司', '1', '2');
insert into T01_SYS_ORG (org_id, org_name, own_org_id, org_lev)
values ('20150104174533402', '迪尔玛国际营销（北京）有限公司', '1', '2');
insert into T01_SYS_ORG (org_id, org_name, own_org_id, org_lev)
values ('20150108104223636', 'DBA', '20150104174533402', '3');
insert into T01_SYS_ORG (org_id, org_name, own_org_id, org_lev)
values ('1', '系统部门', '0', '1');
commit;

insert into T02_DATA_ATTR (attr_id, attr_name, attr_desc, attr_type, attr_len, seq_num, status)
values ('20140716172039', 'ADDR_NAME', '地址', 'VARCHAR', '200', 4, null);
insert into T02_DATA_ATTR (attr_id, attr_name, attr_desc, attr_type, attr_len, seq_num, status)
values ('20140716172417', 'CARD', '证件号码', 'VARCHAR', '20', 6, null);
insert into T02_DATA_ATTR (attr_id, attr_name, attr_desc, attr_type, attr_len, seq_num, status)
values ('20140716172818', 'ID', '数据编号', 'NUMBER', '20', 1, null);
insert into T02_DATA_ATTR (attr_id, attr_name, attr_desc, attr_type, attr_len, seq_num, status)
values ('20140801104132', 'POST_CD', '邮编', 'NUMBER', '20', 7, null);
insert into T02_DATA_ATTR (attr_id, attr_name, attr_desc, attr_type, attr_len, seq_num, status)
values ('20140801104203', 'DIST_CD', '行政区划', 'NUMBER', '20', 8, null);
insert into T02_DATA_ATTR (attr_id, attr_name, attr_desc, attr_type, attr_len, seq_num, status)
values ('20140801104248', 'NAME', '姓名', 'VARCHAR', '20', 9, null);
insert into T02_DATA_ATTR (attr_id, attr_name, attr_desc, attr_type, attr_len, seq_num, status)
values ('20140801104328', 'ORG_NAME', '机构名称', 'VARCHAR', '200', 3, null);
insert into T02_DATA_ATTR (attr_id, attr_name, attr_desc, attr_type, attr_len, seq_num, status)
values ('20140801104403', 'TEL', '电话', 'VARCHAR', '20', 5, null);
commit;

insert into T02_DATA_PARAM (param_id, param_name, update_date, create_date)
values ('20140714152350', '上传', to_date('12-11-2014 14:46:52', 'dd-mm-yyyy hh24:mi:ss'), to_date('14-07-2014 15:23:50', 'dd-mm-yyyy hh24:mi:ss'));
insert into T02_DATA_PARAM (param_id, param_name, update_date, create_date)
values ('20140718140610', '日志', to_date('18-07-2014 14:07:51', 'dd-mm-yyyy hh24:mi:ss'), to_date('18-07-2014 14:06:11', 'dd-mm-yyyy hh24:mi:ss'));
insert into T02_DATA_PARAM (param_id, param_name, update_date, create_date)
values ('20140714152428', '数据库链接', to_date('14-07-2014 15:24:29', 'dd-mm-yyyy hh24:mi:ss'), to_date('14-07-2014 15:24:29', 'dd-mm-yyyy hh24:mi:ss'));
insert into T02_DATA_PARAM (param_id, param_name, update_date, create_date)
values ('20140718140423', '下载', to_date('28-07-2014 10:49:58', 'dd-mm-yyyy hh24:mi:ss'), to_date('18-07-2014 14:04:23', 'dd-mm-yyyy hh24:mi:ss'));
insert into T02_DATA_PARAM (param_id, param_name, update_date, create_date)
values ('20140718140738', '页面分页', to_date('18-07-2014 14:07:38', 'dd-mm-yyyy hh24:mi:ss'), to_date('18-07-2014 14:07:38', 'dd-mm-yyyy hh24:mi:ss'));
commit;


insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140714152446', '20140714152428', '用户名', 'USERNAME', 'orcl');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140718140003', '20140714152428', '端口', 'PORT', '1521');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140714152501', '20140714152428', '密码', 'PASSWORD', '123456');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140718140335', '20140714152350', '上传路径', 'path', '/Users/zhangyh/media/idea/upload/');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140718135936', '20140714152428', '地址', 'IP', '10.154.5.43');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140718140444', '20140718140423', '下载路径', 'path', '/media/idea/down');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140718140640', '20140718140610', '是否记录上传日志', 'isUploadLog', 'true');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140718140852', '20140718140738', '每页显示条数', 'pageSize', '20');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140718142055', '20140718140610', '是否记录下载日志', 'isDownLog', 'true');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140718142153', '20140718140610', '是否记录系统日志', 'isSysLog', 'true');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140814173742', '20140718140610', '是否记录错误日志', 'isErrorLog', 'true');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140807163119', '20140718140423', '同时处理任务数', 'size', '10');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140807163149', '20140714152350', '每次读取文件条数', 'readCount', '500');
insert into T02_DATA_PARAM_DETAIL (detail_id, param_id, detail_name, detail_key, detail_value)
values ('20140821230755585', '20140714152350', '文本分隔符(多个以"/"分割)', 'textSplit', '\t/,/;');
commit;