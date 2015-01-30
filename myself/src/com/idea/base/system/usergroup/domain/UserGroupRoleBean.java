package com.idea.base.system.usergroup.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 用户组和角色关系维护
 * @author zhangyh
 *
 */
public class UserGroupRoleBean extends BaseBean{
	private String userGroupId;
	private String roleId;
	public String getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
