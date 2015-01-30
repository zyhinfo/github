package com.idea.base.system.log.uploadLog.services;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.core.dao.Page;
import com.idea.base.system.log.uploadLog.domain.UploadLogBean;

/**
 * 上传日志
 * @author zhangyh
 */
@Service
public class UploadLogService extends BaseDao{
	public String getUploadLogJSON(UploadLogBean bean){
		Page page = new Page(bean.getPage(),bean.getPagesize());
		page = this.browse("UploadLog.getUploadLogJSON", "UploadLog.getUploadLogJSONCount", page, bean);
		return page.getJSONData();
	}
	
}
