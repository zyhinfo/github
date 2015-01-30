package com.idea.base.system.log.logSetting.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 系统日志设置
 * @author zhangyh
 *
 */
public class LogSettingBean extends BaseBean{
	private static final long serialVersionUID = 1L;
	private String linkId;
	private String linkDesc;
	private String linkUrl;
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getLinkDesc() {
		return linkDesc;
	}
	public void setLinkDesc(String linkDesc) {
		this.linkDesc = linkDesc;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	
}
