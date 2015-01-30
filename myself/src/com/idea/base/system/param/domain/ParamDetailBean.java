package com.idea.base.system.param.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 系统参数明细管理
 * @author zhangyh
 *
 */
public class ParamDetailBean extends BaseBean{
	private String detailId;
	private String paramId;
	private String detailName;
	private String detailKey;
	private String detailValue;
	
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public String getDetailName() {
		return detailName;
	}
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
	public String getDetailKey() {
		return detailKey;
	}
	public void setDetailKey(String detailKey) {
		this.detailKey = detailKey;
	}
	public String getDetailValue() {
		return detailValue;
	}
	public void setDetailValue(String detailValue) {
		this.detailValue = detailValue;
	}
	
}
