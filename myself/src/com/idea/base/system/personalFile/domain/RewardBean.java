package com.idea.base.system.personalFile.domain;

import java.io.Serializable;

public class RewardBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String jfReason;  //奖罚原因
	private String jfDetail;  //奖罚明细
	private String jfType;   //奖罚类型
	private String jfDate;   //奖罚日期
	private String archiveId;  //档案编号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getJfReason() {
		return jfReason;
	}
	public void setJfReason(String jfReason) {
		this.jfReason = jfReason;
	}
	public String getJfDetail() {
		return jfDetail;
	}
	public void setJfDetail(String jfDetail) {
		this.jfDetail = jfDetail;
	}
	public String getJfType() {
		return jfType;
	}
	public void setJfType(String jfType) {
		this.jfType = jfType;
	}
	public String getJfDate() {
		return jfDate;
	}
	public void setJfDate(String jfDate) {
		this.jfDate = jfDate;
	}
	public String getArchiveId() {
		return archiveId;
	}
	public void setArchiveId(String archiveId) {
		this.archiveId = archiveId;
	}
	
}
