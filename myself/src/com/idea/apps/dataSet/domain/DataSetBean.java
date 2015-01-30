package com.idea.apps.dataSet.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 数据管理
 * @author zhangyh
 *
 */
public class DataSetBean extends BaseBean{

	private static final long serialVersionUID = 1L;
	private String instId;
	private String instName;
	private String instTable;
	private String rowNum;
	private String userId;
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public String getInstTable() {
		return instTable;
	}
	public void setInstTable(String instTable) {
		this.instTable = instTable;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
