package com.idea.apps.dataSet.services;

import org.springframework.stereotype.Service;

import com.idea.apps.dataSet.domain.DataSetBean;
import com.idea.base.core.dao.BaseDao;
import com.idea.tools.util.Util;

/**
 * 数据管理
 * @author zhangyh
 */
@Service
public class DataSetService extends BaseDao{
	public String getDataSetJSON(DataSetBean bean){
		return this.queryForJSON("DataSet.getDataSetJSON", bean);
	}
	public void deleteDataSet(String id){
		this.deleteData("T02_DATA_INSTANCE", "INST_ID='"+id+"'",false);
	}
	public void saveDataSetAdd(DataSetBean bean){
		bean.setInstId(Util.dateToString(""));
		this.insert("DataSet.saveDataSetAdd", bean);
	}
	public void saveDataSetEdit(DataSetBean bean){
		this.insert("DataSet.saveDataSetEdit", bean);
	}
	
}
