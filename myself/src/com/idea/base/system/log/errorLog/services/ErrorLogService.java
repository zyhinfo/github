package com.idea.base.system.log.errorLog.services;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.core.dao.Page;
import com.idea.base.system.log.errorLog.domain.ErrorLogBean;
import com.idea.tools.util.Util;

/**
 * 错误日志查询
 * @author zhangyh
 */
@Service
public class ErrorLogService extends BaseDao{
	public String getErrorLogJSON(ErrorLogBean bean){
		Page page = new Page(bean.getPage(),bean.getPagesize());
		page = this.browse("ErrorLog.getErrorLogJSON", "ErrorLog.getErrorLogJSONCount", page, bean);
		return page.getJSONData();
	}
	public void addErrorLog(ErrorLogBean bean){
		bean.setErrorId(Util.dateToString(""));
		this.insert("ErrorLog.addErrorLog",bean);
	}
	public void deleteErrorLog(String id){
		this.deleteData("T01_SYS_ERROR_LOG", "ERROR_ID="+id, false);
	}
}
