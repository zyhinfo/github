package com.idea.base.system.attr.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 系统属性管理
 * @author zhangyh
 *
 */
public class AttrBean extends BaseBean{

	private static final long serialVersionUID = 1L;
	private String attrId;
	private String attrName;
	private String attrDesc;
	private String attrType;
	private String attrLen;
	private String seqNum;
	private String status;
	public String getAttrId() {
		return attrId;
	}
	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	
	public String getAttrDesc() {
		return attrDesc;
	}
	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}
	public String getAttrType() {
		return attrType;
	}
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}
	public String getAttrLen() {
		return attrLen;
	}
	public void setAttrLen(String attrLen) {
		this.attrLen = attrLen;
	}
	public String getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
