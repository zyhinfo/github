package com.idea.base.system.org.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 机构管理
 * @author zhangyh
 *
 */
public class OrgBean extends BaseBean{
	private String orgId;
	private String orgName;
	private String ownOrgId;
	private String ownOrgName;
	private String orgLev;
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOwnOrgId() {
		return ownOrgId;
	}
	public void setOwnOrgId(String ownOrgId) {
		this.ownOrgId = ownOrgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgLev() {
		return orgLev;
	}
	public void setOrgLev(String orgLev) {
		this.orgLev = orgLev;
	}
	public String getOwnOrgName() {
		return ownOrgName;
	}
	public void setOwnOrgName(String ownOrgName) {
		this.ownOrgName = ownOrgName;
	}
	
}
