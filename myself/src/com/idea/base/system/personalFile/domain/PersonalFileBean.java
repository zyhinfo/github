package com.idea.base.system.personalFile.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 个人档案管理
 * @author zhangyh
 *
 */
public class PersonalFileBean extends BaseBean{
	private static final long serialVersionUID = 1L;
	private String archiveId;  //编号
	private String userId;    //用户编号
	private String userName;   //用户名称
	private String orgId;     //机构编号
	private String positionName;  //职位
	private String birthday;   //出生日期
	private String sex;    //性别
	private String tel;    //电话
	private String homeTel;   //家庭电话
	private String homeAddr;  //家庭地址
	private String photo;  //照片
	private String jobTitle;   //职称
	private String natonalName;   //民族
	private String zzmm;   //政治面貌
	private String state;    //状态
	public String getArchiveId() {
		return archiveId;
	}
	public void setArchiveId(String archiveId) {
		this.archiveId = archiveId;
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHomeTel() {
		return homeTel;
	}
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}
	public String getHomeAddr() {
		return homeAddr;
	}
	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getNatonalName() {
		return natonalName;
	}
	public void setNatonalName(String natonalName) {
		this.natonalName = natonalName;
	}
	
	public String getZzmm() {
		return zzmm;
	}
	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
