package com.idea.modules.upload.domain;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.idea.tools.util.Util;
/**
 * 建表语句，插入语句，字段类型数组
 * @author zhangyh
 *
 */
public class ColParamBean {
	private String colParam;
	private String tableName;
	private StringBuffer createSql=new StringBuffer("");  //创建表语句
	private StringBuffer insertSql=new StringBuffer("");  //插入表语句
	private int[] types = null;                           //插入字段类型
	private List<Integer> readRowNum = new ArrayList<Integer>();   //读取列编号
	public ColParamBean(Map<String,Object> data,List<Map<String,String>> attrList){
		this.colParam = Util.toString(data.get("colParam"));
		this.tableName = Util.toString(data.get("instTable"));
		initData(attrList);
	}
	public void initData(List<Map<String,String>> attrList){
		createSql.append("create table "+tableName+"(ID VARCHAR2(30)");
		insertSql.append("insert into "+tableName+"(ID");
		//1,NAME!2,ADDR_NAME
		String[] attrs = Util.split(colParam,"!");
		String str="";
		
		types = new int[attrs.length+1];
		for(int i=0;i<types.length;i++){
			types[i]=Types.VARCHAR;
			if(i==0)str="?";
			else str+=",?";
		}
		
		
		
		for(String attr:attrs){
			String[] col= Util.split(attr, ",");
			readRowNum.add(Util.toInteger(col[0],-1));
			for(Map<String,String> map: attrList){
				if(map.containsValue(col[1])){
					createSql.append(","+map.get("ATTR_NAME")+" VARCHAR2("+Util.toInteger(map.get("ATTR_LEN"), 200)+")");
					insertSql.append(","+map.get("ATTR_NAME"));
				}
			}
		}
		
		
		createSql.append(")");
		insertSql.append(") values("+str+")");
	}
	public String getCreateSql(){
		return createSql.toString();
	}
	public String getInsertSql(){
		return insertSql.toString();
	}
	public int[] getTypes(){
		return types;
	}
	public List<Integer> getReadRowNum(){
		return readRowNum;
	}
	
}
