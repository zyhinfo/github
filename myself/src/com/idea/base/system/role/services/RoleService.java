package com.idea.base.system.role.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.core.dao.Page;
import com.idea.base.system.role.domain.RoleBean;
import com.idea.base.util.ConvertData;
import com.idea.tools.util.Util;

/**
 * 角色管理
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Service
public class RoleService extends BaseDao {
	public String getRoleJSON(RoleBean bean){
		Page page = new Page(bean.getPage(),bean.getPagesize());
		page = this.browse("Role.getRoleJSON", "Role.getRoleJSONCount", page, bean);
		return page.getJSONData();
	}
	public void deleteRole(String id){
		this.deleteData("T01_SYS_ROLE", "ROLE_ID='"+id+"'",false);
		this.deleteData("T01_SYS_ROLE_MENU", "ROLE_ID='"+id+"'",false);
		this.deleteData("T01_SYS_USERGROUP_ROLE", "ROLE_ID='"+id+"'",false);
	}
	public void saveRoleAdd(RoleBean bean){
		bean.setRoleId(Util.dateToString(""));
		this.insert("Role.saveRoleAdd", bean);
	}
	public void saveRoleEdit(RoleBean bean){
		this.insert("Role.saveRoleEdit", bean);
	}
	/**
	 * 角色管理中查询系统菜单
	 * @param param
	 * @return
	 */
	public String querySysMenu(Map<String,Object> param){
		List list=this.queryForList("Role.querySysMenu",param);
		String str=ConvertData.toString(list);
		log.debug("角色管理中加载系统菜单:"+str);
		return str;
	}
	/**
	 * 更新角色菜单关系
	 * @param param
	 */
	public void updateRoleAndMenu(Map<String,Object> param){
		String roleId = Util.toString(param.get("roleId"));
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
