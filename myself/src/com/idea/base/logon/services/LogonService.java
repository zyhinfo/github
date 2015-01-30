package com.idea.base.logon.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.logon.domain.LogonBean;
import com.idea.base.logon.domain.UserInfo;
import com.idea.base.util.ConvertData;
import com.idea.tools.util.Util;

/**
 * 
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-5
 */
@Service
public class LogonService extends BaseDao{
	/**
	 * 系统主界面中左侧菜单
	 * @param param
	 * @return
	 */
	public String queryLeftMenu(HttpServletRequest request,UserInfo user){
		Map<String,Object> param=Util.getNewMap();
		if(!"1".equals(user.getUserId())){//非admin用户
			String menuIds="";
			List<Map<String,String>> orgAndUserGroup = user.getOrgAndUserGroup();
			if(orgAndUserGroup != null && orgAndUserGroup.size() > 0){
				for(Map<String,String> map : orgAndUserGroup){
					if(Util.isNotEmpty(menuIds)) menuIds+=",";
					menuIds+=this.queryMenuByUserGroupId(request, map.get("USERGROUP_ID"));
				}
			}
			if(Util.isEmpty(menuIds)) menuIds="''";
			param.put("menuIds", menuIds);
		}
		List list=this.queryForList("Logon.queryLeftMenu",param);
		String str=ConvertData.toString(list);
		log.debug("加载系统主界面中左侧菜单:"+str);
		return str;
	}
	public UserInfo queryUser(LogonBean bean){
		UserInfo user= (UserInfo)this.queryForObject("Logon.queryUser",bean);
		if(user != null){
			user.setOrgAndUserGroup(this.queryForList("Logon.queryOrgAndUserGroup", user.getUserId()));
		}
		return user;
	}
	public List queryDownFileList(String ids,String userId){
		Map<String,Object> param = Util.getNewMap();
		param.put("userId", userId);
		if(Util.isNotEmpty(ids)){
			param.put("idParam", ids);
		}
		return this.queryForList("Logon.queryDownFileList",param);
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
	
}
