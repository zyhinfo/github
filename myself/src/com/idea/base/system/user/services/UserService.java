package com.idea.base.system.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.core.dao.Page;
import com.idea.base.system.user.domain.UserBean;
import com.idea.base.util.ConvertData;
import com.idea.tools.util.Util;

/**
 * 角色管理
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Service
public class UserService extends BaseDao {
	public String getUserJSON(UserBean bean){
		Page page = new Page(bean.getPage(),bean.getPagesize());
		page = this.browse("User.getUserJSON", "User.getUserJSONCount", page, bean);
		return page.getJSONData();
	}
	public void deleteUser(String id){
		this.deleteData("T01_SYS_USER", "USER_ID='"+id+"'",false);
		this.deleteData("T01_SYS_USER_USERGROUP", "USER_ID='"+id+"'",false);
	}
	public void saveUserAdd(UserBean bean){
		bean.setUserId(Util.dateToString(""));
		this.insert("User.saveUserAdd", bean);
	}
	public void saveUserEdit(UserBean bean){
		this.insert("User.saveUserEdit", bean);
	}
	/**
	 * 角色管理中查询系统菜单
	 * @param param
	 * @return
	 */
	public String querySysMenu(Map<String,String> param){
		List list=this.queryForList("Role.querySysMenu",param);
		String str=ConvertData.toString(list);
		log.debug("角色管理中加载系统菜单:"+str);
		return str;
	}
	/**
	 * 更新角色菜单关系
	 * @param param
	 */
	public void updateRoleAndMenu(Map<String,String> param){
		String roleId = param.get("roleId");
		String[] ids = Util.split(param.get("ids"), ",");
		//删除原有数据
		this.deleteData("T01_SYS_ROLE_MENU", "ROLE_ID='"+roleId+"'",false);
		if(ids != null){
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for(String id : ids){
				Map map = Util.getNewMap();
				map.put("roleId", roleId);
				map.put("menuId", id);
				list.add(map);
			}
			this.executeBatch("Role.updateRoleAndMenu", list);
		}
		
	}
}
