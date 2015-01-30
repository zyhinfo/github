package com.idea.tools.port.read;

public interface Sqlldr {
	/**
	 * 执行导入
	 * @return
	 */
	public boolean exec();
	/**
	 * 上传生成文件存放路径
	 * @param path
	 */
	public void setPath(String path);
	
	/**
	 * 入库字段名称
	 * 以逗号分割
	 * @param attrNames
	 */
	public void setAttrNames(String attrNames);
	/**
	 * 默认制表符 分割
	 * @param split
	 */
	public void setSplit(String split);
	/**
	 * 默认TRUNCATE  <br>
	 * 可选值  <br>
	 * TRUNCATE : 删除旧记录，替换成新装载的记录 <br>
	 * INSERT : 在数据装载开始时要求表为空  <br>
	 * APPEND : 在表中追加新记录  <br>
	 * REPLACE : 删除旧记录，替换成新装载的记录  <br>
	 * @param type
	 */
	public void setType(String type);
	/**
	 * 跳过行号
	 * @param skip
	 */
	public void setSkip(int skip);
	/**
	 * 设置数据库信息
	 * @param dB_NAME
	 * @param dB_USER_NAME
	 * @param dB_USER_PASSWORD
	 */
	public void setDB(String dB_NAME,String dB_USER_NAME,String dB_USER_PASSWORD);
	public void setTableName(String tableName);
	/**
	 * 获取错误数据
	 * @return
	 */
	public String getSQLLDER_BAD();
	/**
	 * 获取执行日志
	 * @return
	 */
	public String getSQLLDER_LOG();
	public String getPath();
	/**
	 * 获取执行CTL
	 * @return
	 */
	public String getSQLLDER_CTL();
	public String getFileName();
	
	public void setFlag(boolean flag);
}
