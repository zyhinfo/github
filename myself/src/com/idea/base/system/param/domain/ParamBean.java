package com.idea.base.system.param.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 系统参数管理
 * @author zhangyh
 *
 */
public class ParamBean extends BaseBean{
	private String paramId;
	private String paramName;
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
}
