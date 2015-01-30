package com.idea.base.core.dao.domain;

import java.io.Serializable;


/**
 * 基础bean
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-5
 */
public class BaseBean implements Serializable{
	private static final long serialVersionUID = 3200343115799078903L;
	private String page;
	private String pagesize;
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPagesize() {
		return pagesize;
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	
}
