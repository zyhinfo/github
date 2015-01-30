package com.idea.modules.upload.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 数据上传公共
 * @author zhangyh
 *
 */
public class UploadBean extends BaseBean{
	private static final long serialVersionUID = 1L;
	private String dataName;
	private String colParam;
	private String filePath;
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getColParam() {
		return colParam;
	}
	public void setColParam(String colParam) {
		this.colParam = colParam;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
