package com.idea.base.system.usergroup.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 用户组管理管理
 * @author zhangyh
 *
 */
public class UserGroupBean extends BaseBean{
	private String userGroupId;
	private String userGroupName;
	private String userGroupDesc;
	public String getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
	public String getUserGroupDesc() {
		return userGroupDesc;
	}
	public void setUserGroupDesc(String userGroupDesc) {
		this.userGroupDesc = userGroupDesc;
	}
	
}
