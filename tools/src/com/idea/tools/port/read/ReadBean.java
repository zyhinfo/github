package com.idea.tools.port.read;


public class ReadBean {
	private String filePath;//文件绝对路径
	private String splits="";//txt文件分隔符
	private String step;//是否为设置字段
	
	private int startRowNum=0;//开始行
	private int rowNum;
	private int RETURN_NUM=100;//每次读取的条数
	private int colNum=0;//读取文件列数
	
	public int getColNum() {
		return colNum;
	}
	/**
	 * 读取文件列数
	 * @param colNum
	 */
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	private String[] types=null;
	private int[] length=null;
	private int[] seqNum=null;
	
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	public int[] getLength() {
		return length;
	}
	public void setLength(int[] length) {
		this.length = length;
	}
	public int[] getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(int[] seqNum) {
		this.seqNum = seqNum;
	}
	public int getStartRowNum() {
		return startRowNum;
	}
	/**
	 * 开始读取行
	 * @param startRowNum
	 */
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	
	
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getRETURN_NUM() {
		return RETURN_NUM;
	}
	/**
	 * 每次读取的条数
	 * @param rETURN_NUM
	 */
	public void setRETURN_NUM(int rETURN_NUM) {
		RETURN_NUM = rETURN_NUM;
	}
	
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 文件绝对路径
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getSplits() {
		return splits;
	}
	/**
	 * 多个分割符 以 /
	 * @param split
	 */
	public void setSplits(String splits) {
		this.splits = splits;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}

}
