package com.idea.base.system.log.downLog.services;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.core.dao.Page;
import com.idea.base.system.log.downLog.domain.DownLogBean;

/**
 * 下载日志
 * @author zhangyh
 */
@Service
public class DownLogService extends BaseDao{
	public String getDownLogJSON(DownLogBean bean){
		Page page = new Page(bean.getPage(),bean.getPagesize());
		page = this.browse("DownLog.getDownLogJSON", "DownLog.getDownLogJSONCount", page, bean);
		return page.getJSONData();
	}
}
