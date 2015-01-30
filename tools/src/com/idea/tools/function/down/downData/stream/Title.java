package com.idea.tools.function.down.downData.stream;

import java.io.Serializable;

/**
 * 导出数据设置标题列
 * @author zhangyh
 *
 */
public class Title implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String attrName; //属性名称
	private String titleDesc; //标题描述
	private String attrLen; //属性长度
	
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getTitleDesc() {
		return titleDesc;
	}
	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}
	public String getAttrLen() {
		return attrLen;
	}
	public void setAttrLen(String attrLen) {
		this.attrLen = attrLen;
	}
	
}
