package com.idea.base.system.user.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 用户明细管理(用户、机构、用户组关系维护)
 * @author zhangyh
 *
 */
public class UserDetailBean extends BaseBean{
	private String userId;
	private String userGroupId;
	private String orgId;
	private String userUserGroupId;
	private String orgName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUserUserGroupId() {
		return userUserGroupId;
	}
	public void setUserUserGroupId(String userUserGroupId) {
		this.userUserGroupId = userUserGroupId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
