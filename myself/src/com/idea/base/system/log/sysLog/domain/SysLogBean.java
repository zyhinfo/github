package com.idea.base.system.log.sysLog.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 系统日志查询
 * @author zhangyh
 *
 */
public class SysLogBean extends BaseBean{
	private static final long serialVersionUID = 1L;
	private String userName;
	private String logDesc;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLogDesc() {
		return logDesc;
	}
	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}
	
	
}
