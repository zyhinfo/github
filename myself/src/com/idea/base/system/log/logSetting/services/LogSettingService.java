package com.idea.base.system.log.logSetting.services;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.core.dao.Page;
import com.idea.base.system.log.logSetting.domain.LogSettingBean;
import com.idea.tools.util.Util;

/**
 * 系统日志设置
 * @author zhangyh
 */
@Service
public class LogSettingService extends BaseDao{
	public String getLogSettingJSON(LogSettingBean bean){
		Page page = new Page(bean.getPage(),bean.getPagesize());
		page = this.browse("LogSetting.getLogSettingJSON", "LogSetting.getLogSettingJSONCount", page, bean);
		return page.getJSONData();
	}
	public void deleteLogSetting(String id){
		this.deleteData("T01_SYS_LOG_SETTING", "LINK_ID='"+id+"'",false);
	}
	public void saveLogSettingAdd(LogSettingBean bean){
		bean.setLinkId(Util.dateToString(""));
		this.insert("LogSetting.saveLogSettingAdd", bean);
	}
	public void saveLogSettingEdit(LogSettingBean bean){
		this.insert("LogSetting.saveLogSettingEdit", bean);
	}
	
}
