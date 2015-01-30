package com.idea.base.system.log.errorLog.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 错误日志查询
 * @author zhangyh
 *
 */
public class ErrorLogBean extends BaseBean{
	private static final long serialVersionUID = 1L;
	private String errorId;
	private String menuName;
	private String errorSummary;
	private String errorDesc;
	private String userId;
	private String userName;
	public String getErrorId() {
		return errorId;
	}
	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getErrorSummary() {
		return errorSummary;
	}
	public void setErrorSummary(String errorSummary) {
		this.errorSummary = errorSummary;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
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
	
}
