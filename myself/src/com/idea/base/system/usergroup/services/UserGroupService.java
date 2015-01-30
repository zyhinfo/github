package com.idea.base.system.usergroup.services;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.system.usergroup.domain.UserGroupBean;
import com.idea.tools.util.Util;

/**
 * 用户组管理
 * @author zhangyh
 */
@Service
public class UserGroupService extends BaseDao{
	public String getUserGroupJSON(UserGroupBean bean){
		return this.queryForJSON("UserGroup.getUserGroupJSON", bean);
	}
	public void deleteUserGroup(String id){
		this.deleteData("T01_SYS_USERGROUP", "USERGROUP_ID='"+id+"'",false);
		this.deleteData("T01_SYS_USERGROUP_ROLE", "USERGROUP_ID='"+id+"'",false);
		this.deleteData("T01_SYS_USER_USERGROUP", "USERGROUP_ID='"+id+"'",false);
	}
	public void saveUserGroupAdd(UserGroupBean bean){
		bean.setUserGroupId(Util.dateToString(""));
		this.insert("UserGroup.saveUserGroupAdd", bean);
	}
	public void saveUserGroupEdit(UserGroupBean bean){
		this.insert("UserGroup.saveUserGroupEdit", bean);
	}
	
}
