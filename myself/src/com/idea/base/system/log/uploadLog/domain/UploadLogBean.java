package com.idea.base.system.log.uploadLog.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 上传日志
 * @author zhangyh
 *
 */
public class UploadLogBean extends BaseBean{
	private static final long serialVersionUID = 1L;
	private String uploadDesc;
	private String userName;
	public String getUploadDesc() {
		return uploadDesc;
	}
	public void setUploadDesc(String uploadDesc) {
		this.uploadDesc = uploadDesc;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
