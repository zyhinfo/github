package com.idea.base.system.user.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 角色管理
 * @author zhangyh
 *
 */
public class UserBean extends BaseBean{
	private String userId;
	private String userName;
	private String userDesc;
	private String logonName;
	private String password;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getLogonName() {
		return logonName;
	}
	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
