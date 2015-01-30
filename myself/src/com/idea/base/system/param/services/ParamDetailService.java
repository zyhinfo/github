package com.idea.base.system.param.services;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.system.param.domain.ParamDetailBean;
import com.idea.tools.util.Util;

/**
 * 系统参数明细管理
 * @author zhangyh
 */
@Service
public class ParamDetailService extends BaseDao{
	public String getParamDetailJSON(ParamDetailBean bean){
		return this.queryForJSON("ParamDetail.getParamDetailJSON", bean);
	}
	public void deleteParamDetail(String id){
		this.deleteData("T02_DATA_PARAM_DETAIL", "DETAIL_ID='"+id+"'",false);
	}
	public void saveParamDetailAdd(ParamDetailBean bean){
		bean.setDetailId(Util.dateToString(""));
		this.insert("ParamDetail.saveParamDetailAdd", bean);
	}
	public void saveParamDetailEdit(ParamDetailBean bean){
		this.insert("ParamDetail.saveParamDetailEdit", bean);
	}
	
}
