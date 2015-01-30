package com.idea.base.system.log.downLog.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 下载日志
 * @author zhangyh
 *
 */
public class DownLogBean extends BaseBean{
	private static final long serialVersionUID = 1L;
	private String downDesc;
	public String getDownDesc() {
		return downDesc;
	}
	public void setDownDesc(String downDesc) {
		this.downDesc = downDesc;
	}
	
	
}
