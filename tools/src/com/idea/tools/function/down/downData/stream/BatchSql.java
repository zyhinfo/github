package com.idea.tools.function.down.downData.stream;


/**
 * 分批次查询SQL语句
 * @item DPP
 * @author zhangyh
 */
public class BatchSql {
	
	private int batchSize=1000;//每次批量导出1000条数据
	private int count=-1;
	private int downSize=0;;
	
	private boolean status=true; 
	
	private String headSql="";
	private String endSql=" and rownum_ <= ";
	public BatchSql(String sql){
		headSql="select * from ( select row_.*, rownum rownum_ from ( "+sql
		+") row_ ) where rownum_ > ";
	}
	public int getBatchSize() {
		return batchSize;
	}
	/**
	 * 设置每次从数据库中查询出的条数
	 * 默认1000
	 * @param batchSize
	 */
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	/**
	 * 
	 * @Description 分批次查询语句 
	 * @author zhangyh
	 * @return
	 * @version 1.0
	 */
	public String getBatchSql(){
		if(status){
			count++;
			return headSql+(count * batchSize)+endSql+((count+1) * batchSize);
		}
		return "";
	}
	/**
	 * 
	 * @Description 设置查询条数判断是否继续循环 
	 * @author zhangyh
	 * @param size
	 * @version 1.0
	 */
	public void setStatus(int size){
		downSize+=size;
		//查询出来的总条数 少于 设置的每次返回条数 循环结束
		if(size < batchSize){
			status=false;
		}
	}
	/**
	 * 
	 * @Description 当前状态true继续false停止 
	 * @author zhangyh
	 * @return
	 * @version 1.0
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * 
	 * @Description 当前下载总条数 
	 * @author zhangyh
	 * @return
	 * @version 1.0
	 */
	public int getCountNow(){
		return downSize;
	}
}
