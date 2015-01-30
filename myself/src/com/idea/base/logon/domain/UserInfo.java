package com.idea.base.logon.domain;

import java.util.List;
import java.util.Map;


/**
 * 登录
 * @author zhangyh
 *
 */
public class UserInfo{
	private String userId;    //用户编号
	private String userName;   //中文名称
	private String userDesc;
	private String logonName;   //登录名称
	private List<Map<String,String>> orgAndUserGroup=null;
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
	public List<Map<String, String>> getOrgAndUserGroup() {
		return orgAndUserGroup;
	}
	public void setOrgAndUserGroup(List<Map<String, String>> orgAndUserGroup) {
		this.orgAndUserGroup = orgAndUserGroup;
	}
}
