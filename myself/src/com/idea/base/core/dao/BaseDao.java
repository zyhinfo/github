package com.idea.base.core.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idea.base.core.dataSource.IBatisBaseDao;
import com.idea.tools.util.Util;

/**
 * 对数据源进行封装
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
public class BaseDao extends IBatisBaseDao{
	public Log log = LogFactory.getLog(this.getClass());
	public void insertData(String sql){
		this.insert("BaseDao.insertData",sql);
	}
	public void deleteData(String sql){
		this.delete("BaseDao.deleteData",sql);
	}
	public void updateData(String sql){
		this.update("BaseDao.updateData",sql);
	}
	
	
	/**
	 * 判断表是否存在
	 * @param tableName
	 * @return
	 */
	public boolean isExistsTable(String tableName){
		if(Util.isEmpty(tableName) || Util.isEquals(queryForObject("BaseDao.isExistsTable",
				Util.toUpperCase(tableName)),"0")){
			return false;
		}
		return true;
	}
	
	/**
	 * 查询表中的数据量
	 * @param tableName
	 * @param whereStr
	 * @param checkTableExists 检查表是否存在true:yes,false:no
	 * @return
	 */
	public int queryTableCount(String tableName,String whereStr,boolean checkTableExists){
		if(Util.isNotEmpty(tableName)){
			if(!checkTableExists || isExistsTable(tableName)){
				if(Util.isNotEmpty(whereStr)){
					whereStr=" where "+whereStr;
				}
				Object obj = queryForObject("BaseDao.queryTableCount",tableName+whereStr);
				return Integer.parseInt(obj.toString());
			}
		}
		return 0;
	}
	/**
	 * 删除表中数据
	 * @param tableName
	 * @param whereStr
	 * @param checkTableExists 检查表是否存在true:检查,false:不检查
	 */
	public void deleteData(String tableName,String whereStr,boolean checkTableExists){
		if(Util.isNotEmpty(tableName)){
			if(!checkTableExists || isExistsTable(tableName)){
				if(Util.isNotEmpty(whereStr)){
					whereStr=" where "+whereStr;
					deleteData("delete "+tableName+whereStr);
				}else{
					updateData("truncate table "+tableName);
				}
				
			}
		}
	}
	/**
	 * 删除表
	 * @param tableName 要删除的表名
	 * @param checkTableExists 检查表是否存在true:检查,false:不检查
	 */
	public void dropTable(String tableName,boolean checkTableExists){
		if(Util.isNotEmpty(tableName)){
			if(!checkTableExists || isExistsTable(tableName)){
				this.update("BaseDao.dropTable",tableName);
			}
		}
	}
	
	 //////////////////////////功能方法 ///////////////////////////
	 
	/**
	 * 获取参数的map对象(唯一方法)
	 * @param paramId
	 * @return
	 */
	public Map<String,String> queryParamById(HttpServletRequest request,String paramId){
		String key = "paramId_"+paramId;
		Map<String,String> param = null;
		ServletContext context = null;
		if(request != null){
			//从系统内存中获取
			context = request.getSession().getServletContext();
			param = (Map<String,String>)context.getAttribute(key);
			if(param != null && param.size() != 0){
				return param;
			}
		}
		param = this.queryForMap("BaseDao.queryParamById",paramId, "DETAIL_KEY", "DETAIL_VALUE");
		context.setAttribute(key, param);
		return param;
	}
	
	/**
	 * 获取菜单id(唯一方法)
	 * @param paramId
	 * @return
	 */
	public String queryMenuByUserGroupId(HttpServletRequest request,String userGroupId){
		String key = "userGroupId_"+userGroupId;
		String str="";
		ServletContext context = null;
		if(request != null){
			//从系统内存中获取
			context = request.getSession().getServletContext();
			str = (String)context.getAttribute(key);
			if(Util.isNotEmpty(str)){
				return str;
			}
		}
		str = (String)this.queryForObject("BaseDao.queryMenuByUserGroupId",userGroupId);
		context.setAttribute(key, str);
		return str;
	}
	
	/**
	 * 查询系统属性
	 * @param request
	 * @param joinTable
	 * @return
	 */
	public List<Map<String,String>> querySysAttr(HttpServletRequest request,String joinTable){
		if(isExistsTable(joinTable)){
			return this.queryForList("BaseDao.querySysAttrByTab", Util.toUpperCase(joinTable));
		}else{
			String key = "sysAttr";
			List<Map<String,String>> str= null;
			ServletContext context = null;
			if(request != null){
				//从系统内存中获取
				context = request.getSession().getServletContext();
				str = (List<Map<String,String>>)context.getAttribute(key);
				if(Util.getSize(str) > 0){
					return str;
				}
			}
			str = (List<Map<String,String>>)this.queryForList("BaseDao.querySysAttr");
			context.setAttribute(key, str);
			return str;
		}
		
	}
	/**
	 * 查询并缓存
	 * @param request
	 * @param joinTable
	 * @return
	 */
	public List<Map<String,String>> queryTableCache(String statementName,HttpServletRequest request){
		List<Map<String,String>> str= null;
		ServletContext context = null;
		if(request != null){
			//从系统内存中获取
			context = request.getSession().getServletContext();
			str = (List<Map<String,String>>)context.getAttribute(statementName);
			if(Util.getSize(str) > 0){
				return str;
			}
		}
		str = (List<Map<String,String>>)this.queryForList(statementName);
		context.setAttribute(statementName, str);
		return str;
	}
}
