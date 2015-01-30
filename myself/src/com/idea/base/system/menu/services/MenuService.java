package com.idea.base.system.menu.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.system.menu.domain.MenuBean;
import com.idea.base.util.ConvertData;

/**
 * 
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Service
public class MenuService extends BaseDao{
	/**
	 * 菜单管理功能左侧菜单
	 * @param param
	 * @return
	 */
	public String queryLeftMenu(Map param){
		List list=this.queryForList("Menu.queryLeftMenu",param);
		String str=ConvertData.toString(list);
		log.debug("加载菜单管理功能左侧菜单:"+str);
		return str;
	}
	public String queryMenuNameById(String menuId){
		return (String)this.queryForObject("Menu.queryMenuNameById", menuId);
	}
	public void addMenu(MenuBean bean){
		this.update("Menu.addMenu", bean);
		
	}
	public MenuBean queryMenuById(String menuId){
		return (MenuBean)this.queryForObject("Menu.queryMenuById",menuId);
		
	}
	public void editMenu(MenuBean bean){
		this.update("Menu.editMenu",bean);
	}
	public void deleteMenu(String menuId){
		this.deleteData("T01_SYS_MENU", "MENU_ID='"+menuId+"'",false);
		this.deleteData("T01_SYS_ROLE_MENU", "MENU_ID='"+menuId+"'",false);
	}
}
