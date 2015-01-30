package com.idea.base.core.dao;

import java.util.List;

import com.idea.base.util.ConvertData;
import com.idea.tools.util.Util;

/**
 * 
 * 封装分页和排序参数及分页查询结果.(适用ibatis)
 * @author ht
 * @since 2009-3-9
 * @version 1.0 
 */
@SuppressWarnings("unchecked")
public class Page<T> {
	/**
	 * Logger for this class
	 */
//	private static final Logger logger = LoggerFactory.getLogger(Page.class);

	//每页多少行记录
	private int pageSize = 20;
	//当前第几页
	private int pageNo = 1;
	//记录总数
	private int totalRows = -1;
	//开始记录数
	private int startRow;
	//总页数
	private int totalPages;
	//记录集合
	
	private List resultList;
	
	public Page(){
	}
	
	public Page(int pageNo) {
		int v_endrownum   = pageNo * pageSize;
		this.startRow = v_endrownum - pageSize ;
		this.pageNo = pageNo;
	}
	
	public Page(int pageNo ,int pageSize) {
		int v_endrownum   = pageNo * pageSize;
		this.startRow = v_endrownum - pageSize;
		this.pageNo = pageNo;
		if(pageSize != 0)
			this.pageSize = pageSize;
	}
	public Page(String pageNo ,String pageSize) {
		this.pageNo = Util.toInteger(pageNo,1);
		int pSize = Util.toInteger(pageSize,0);
		int v_endrownum   = this.pageNo * pSize;
		this.startRow = v_endrownum - pSize;
		
		if(pSize != 0)
			this.pageSize = pSize;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	public String getJSONData(){
		return ConvertData.listToJSONString(resultList,totalRows);
	}
}
