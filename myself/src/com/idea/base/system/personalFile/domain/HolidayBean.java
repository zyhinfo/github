package com.idea.base.system.personalFile.domain;

import java.io.Serializable;

public class HolidayBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String reason;   //原因
	private String isType;   //类型
	private String startDate;  //开始时间
	private String endDate;   //结束时间
	private String archiveId;  //档案编号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getIsType() {
		return isType;
	}
	public void setIsType(String isType) {
		this.isType = isType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getArchiveId() {
		return archiveId;
	}
	public void setArchiveId(String archiveId) {
		this.archiveId = archiveId;
	}
	
}
