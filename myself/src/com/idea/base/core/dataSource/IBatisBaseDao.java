package com.idea.base.core.dataSource;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.idea.base.core.dao.Page;
import com.idea.base.util.ConvertData;

/**
 * dataSource
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-5
 */
public class IBatisBaseDao {
	
	@Autowired
	@Qualifier("sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}
	//新增数据
	public void insert(String statementName){
		this.getSqlMapClientTemplate().insert(statementName);
	}
	public void insert(String statementName,Object parameterObject){
		this.getSqlMapClientTemplate().insert(statementName,parameterObject);
	}
	//删除数据
	public int delete(String statementName){
		return this.getSqlMapClientTemplate().delete(statementName);
	}
	public int delete(String statementName,Object parameterObject){
		return this.getSqlMapClientTemplate().delete(statementName, parameterObject);
	}
	
	//修改数据
	public int update(String statementName){
		return this.getSqlMapClientTemplate().update(statementName);
	}
	public int update(String statementName,Object parameterObject){
		return this.getSqlMapClientTemplate().update(statementName, parameterObject);
	}
	
	//查询一个对象
	public Object queryForObject(String statementName){
		return this.getSqlMapClientTemplate().queryForObject(statementName);
	}
	public Object queryForObject(String statementName,Object parameterObject){
		return this.getSqlMapClientTemplate().queryForObject(statementName,parameterObject);
	}
	//查询一个map对象
	public Map queryForMap(String statementName,Object parameterObject,String keyProperty,String valueProperty){
		return this.getSqlMapClientTemplate().queryForMap(statementName, parameterObject, keyProperty, valueProperty);
	}
	
	//查询多个对象
	public List queryForList(String statementName){
		return this.getSqlMapClientTemplate().queryForList(statementName);
	}
	public List queryForList(String statementName,Object parameterObject){
		return this.getSqlMapClientTemplate().queryForList(statementName,parameterObject);
	}
	public String queryForJSON(String statementName,Object parameterObject){
		List list = this.getSqlMapClientTemplate().queryForList(statementName,parameterObject);
		return ConvertData.listToJSONString(list,-1);
	}
	/**
	 * 分页查询数据
	 * @param nameSpace
	 * @param nameSpaceCount
	 * @param page
	 * @param parameterObject
	 * @return
	 */
	public <T> Page<T> browse(String nameSpace, String nameSpaceCount,
			Page<T> page, Object parameterObject) {
		//获取表中的总条数
		if (page.getTotalRows() == -1) {
			page.setTotalRows((Integer) (getSqlMapClientTemplate()
					.queryForObject(nameSpaceCount, parameterObject)));
		}
		//计算总页数
		page.setTotalPages((page.getTotalRows() % page.getPageSize() == 0) ? (page
				.getTotalRows() / page.getPageSize())
				: (page.getTotalRows() / page.getPageSize() + 1));
		
		if (page.getTotalRows() >= 1) {
			page.setResultList(getSqlMapClientTemplate().queryForList(
					nameSpace, parameterObject, page.getStartRow(),
					page.getPageSize()));
		}
		return page;
	}
	
	/**
	 * 批量
	 * 
	 * @param nameSpace
	 * @param parameterObjects
	 * @return
	 */
	public int executeBatch(final String nameSpace, final List parameterObjects) {
		int ret = (Integer) this.getSqlMapClientTemplate().execute(
				new SqlMapClientCallback() {
					public Object doInSqlMapClient(SqlMapExecutor executor)
							throws SQLException {
						executor.startBatch();
						for (Object parameterObject : parameterObjects) {
							executor.update(nameSpace, parameterObject);
						}
						return executor.executeBatch();
					}
				});
		return ret;
	}
}
