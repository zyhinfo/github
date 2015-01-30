package com.idea.base.system.param.services;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.core.dao.Page;
import com.idea.base.system.param.domain.ParamBean;
import com.idea.tools.util.Util;

/**
 * 系统参数管理
 * @author zhangyh
 */
@Service
public class ParamService extends BaseDao{
	public String getParamJSON(ParamBean bean){
		Page page = new Page(bean.getPage(),bean.getPagesize());
		page = this.browse("Param.getParamJSON", "Param.getParamJSONCount", page, bean);
		return page.getJSONData();
	}
	public void deleteParam(String id){
		this.deleteData("T02_DATA_PARAM", "PARAM_ID='"+id+"'",false);
		this.deleteData("T02_DATA_PARAM_DETAIL", "PARAM_ID='"+id+"'",false);
	}
	public void saveParamAdd(ParamBean bean){
		bean.setParamId(Util.dateToString(""));
		this.insert("Param.saveParamAdd", bean);
	}
	public void saveParamEdit(ParamBean bean){
		this.insert("Param.saveParamEdit", bean);
	}
	
}
