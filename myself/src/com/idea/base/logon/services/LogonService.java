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
			if(Util.isNotEmpty(user.getUserGroupId())&&Util.isNotEmpty(user.getOrgId())){
				if(Util.isNotEmpty(menuIds)) menuIds+=",";
				menuIds+=this.queryMenuByUserGroupId(request, user.getUserGroupId());
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
		return user;
	}
	
}
