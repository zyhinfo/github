package com.idea.tools.port.down.downData.spool;

public interface Spool {
	public boolean exec();
	public void setDB(String dB_NAME,String dB_USER_NAME,String dB_USER_PASSWORD);
	/**
	 * 文件路径
	 * @return
	 */
	public String getPath();
	/**
	 * 输出文件名称
	 * @return
	 */
	public String getOutFileName();
}
