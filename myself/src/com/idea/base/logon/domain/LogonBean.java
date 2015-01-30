package com.idea.base.logon.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 登录
 * @author zhangyh
 *
 */
public class LogonBean extends BaseBean{
	private static final long serialVersionUID = 1L;

	private String logonName;
	private String password;
	
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
