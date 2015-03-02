package com.idea.modules.down.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.core.dataSource.JdbcService;
import com.idea.tools.util.Util;

/**
 * 数据下载公共类
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Service
public class DownService extends BaseDao {
	@Autowired
	@Qualifier("JdbcService")
	private JdbcService jdbc;
	public JdbcService getJdbcService(){
		return jdbc;
	}
	public List queryDownFileList(String ids,String userId){
		Map<String,Object> param = Util.getNewMap();
		param.put("userId", userId);
		if(Util.isNotEmpty(ids)){
			param.put("idParam", ids);
		}
		return this.queryForList("Down.queryDownFileList",param);
	}
	public void updateDownLogStatus(String downId){
		this.update("Down.updateDownLogStatus",downId);
	}
	/**
	 * 拼接下载文件列表的表格
	 * @param list
	 * @return
	 */
	public Map<String,Object> getDownFileTable(List<Map<String,String>> list){
		StringBuffer sbTr = new StringBuffer("");
		StringBuffer sbProgress = new StringBuffer("");
		if(list != null && list.size() != 0) {
			int num = 0;
			for(Map<String,String> map:list){
				String downId = map.get("DOWN_ID");
				String isDown = map.get("IS_DOWN");
				String progress = map.get("DOWN_PROGRESS");
				String filePath = map.get("DOWN_FILE_PATH");
				//拼接下载列表的表格
				sbTr.append("<tr><td width='42%'>"+map.get("DOWN_DESC")+"</td>")
				      .append("<td width='42%' align='left'><span class='progress' id='"+downId+"' progress='' status=''></span></td>")
				      .append("<td width='16%' align='center' id='down"+downId+"'>");
				
				sbTr.append("</td></tr>");
				//拼接下载进度
				if(num != 0) sbProgress.append(",");
				sbProgress.append(downId+"!"+progress+"!"+isDown+"!"+filePath);
				num++;
			}
		}
		Map<String,Object> down = Util.getNewMap();
		down.put("downTr", sbTr.toString());
		down.put("downProgress", sbProgress.toString());
		return down;
	}
	/**
	 * 
	 * @param param
	 */
	public void updateDownProgress(Map<String,Object> param){
		this.update("Down.updateDownProgress",param);
	}
	
}
