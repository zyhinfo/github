package com.idea.base.system.param.util;import org.springframework.beans.factory.annotation.Value;import org.springframework.stereotype.Service;/** * 常量参数 * @author zhangyh * */@Servicepublic class ParamConstant {	@Value("${system.log.paramId}")	public String SYSTEM_LOG_PARAMID;   //系统日志参数编号	@Value("${system.paging.paramId}")	public String SYSTEM_PAGING_PARAMID;   //分页参数编号	@Value("${system.download.paramId}")	public String SYSTEM_DOWNLOAD_PARAMID;   //系统文件下载参数编号	@Value("${system.upload.paramId}")	public String SYSTEM_UPLOAD_PARAMID;   //系统文件上传参数编号	public String getSYSTEM_LOG_PARAMID() {		return SYSTEM_LOG_PARAMID;	}	public void setSYSTEM_LOG_PARAMID(String sYSTEM_LOG_PARAMID) {		SYSTEM_LOG_PARAMID = sYSTEM_LOG_PARAMID;	}	public String getSYSTEM_PAGING_PARAMID() {		return SYSTEM_PAGING_PARAMID;	}	public void setSYSTEM_PAGING_PARAMID(String sYSTEM_PAGING_PARAMID) {		SYSTEM_PAGING_PARAMID = sYSTEM_PAGING_PARAMID;	}	public String getSYSTEM_DOWNLOAD_PARAMID() {		return SYSTEM_DOWNLOAD_PARAMID;	}	public void setSYSTEM_DOWNLOAD_PARAMID(String sYSTEM_DOWNLOAD_PARAMID) {		SYSTEM_DOWNLOAD_PARAMID = sYSTEM_DOWNLOAD_PARAMID;	}	public String getSYSTEM_UPLOAD_PARAMID() {		return SYSTEM_UPLOAD_PARAMID;	}	public void setSYSTEM_UPLOAD_PARAMID(String sYSTEM_UPLOAD_PARAMID) {		SYSTEM_UPLOAD_PARAMID = sYSTEM_UPLOAD_PARAMID;	}			}