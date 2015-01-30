package com.idea.base.system.log.sysLog.services;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.core.dao.Page;
import com.idea.base.system.log.sysLog.domain.SysLogBean;

/**
 * 系统日志查询
 * @author zhangyh
 */
@Service
public class SysLogService extends BaseDao{
	public String getSysLogJSON(SysLogBean bean){
		Page page = new Page(bean.getPage(),bean.getPagesize());
		page = this.browse("SysLog.getSysLogJSON", "SysLog.getSysLogJSONCount", page, bean);
		return page.getJSONData();
	}
	
}
