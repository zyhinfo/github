package com.idea.base.system.role.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 角色管理
 * @author zhangyh
 *
 */
public class RoleBean extends BaseBean{
	private String roleId;
	private String roleName;
	private String roleDesc;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	
}
