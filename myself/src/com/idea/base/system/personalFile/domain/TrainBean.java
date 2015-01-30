package com.idea.base.system.personalFile.domain;

import java.io.Serializable;

public class TrainBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String school;   //培训学校
	private String trainDesc;  //培训内容
	private String startDate;  //开始时间
	private String endDate;   //结束时间
	private String archiveId;  //档案编号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getTrainDesc() {
		return trainDesc;
	}
	public void setTrainDesc(String trainDesc) {
		this.trainDesc = trainDesc;
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
