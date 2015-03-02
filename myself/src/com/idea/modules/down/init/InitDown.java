package com.idea.modules.down.init;


public class InitDown {
	/**
	 * 数据下载参数
	 * @param sql 要下载数据的sql
	 * @param downPath  下载路径
	 * @param downAllCount 下载总条数
	 */
	public InitDown(String sql,String downPath,int downAllCount){
		this.downPath = downPath;
		this.sql = sql;
		this.downAllCount = downAllCount;
	}
	private String downId;  
	private String downPath;  //下载路径
	private String sql;
	private int downAllCount;
	
	private String fileName;  //文件名称
	private String fileType;  //文件类型XLS ,TXT, DBF
	private Object param;
	
	public String getDownId() {
		return downId;
	}
	
	public void setDownId(String downId) {
		this.downId = downId;
	}

	public String getDownPath() {
		return downPath;
	}
	

	public String getSql() {
		return sql;
	}

	public int getDownAllCount() {
		return downAllCount;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
	public Object getParam() {
		return param;
	}
	public void setParam(Object param) {
		this.param = param;
	}
}
