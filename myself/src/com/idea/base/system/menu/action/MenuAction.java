package com.idea.base.system.menu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.idea.base.core.dao.action.BaseAction;
import com.idea.base.system.menu.domain.MenuBean;
import com.idea.base.system.menu.services.MenuService;
import com.idea.tools.util.Util;

/**
 * 菜单管理
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Controller
@RequestMapping("/MenuAction")
public class MenuAction extends BaseAction{
	@Autowired
	private MenuService service;
	/**
	 * 
	 * 菜单管理功能左侧菜单
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=queryLeftMenu")
	public void queryLeftMenu(ModelMap map, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		try {
			Map<String,Object> param=Util.getNewMap();
			String str=service.queryLeftMenu(param);
			PrintWriter out=response.getWriter();
			out.print(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 进入菜单管理主界面
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=gotoMenuMain")
	public String gotoMenuMain(ModelMap map, HttpServletRequest request,HttpServletResponse response){
		
		return "base/system/menu/menuMain";
	}
	/**
	 * 进入增加菜单页面
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=gotoAddMenu")
	public String gotoAddMenu(ModelMap map, MenuBean bean, HttpServletRequest request,HttpServletResponse response){
		String menuId = bean.getMenuId();
		//查询菜单名称
		map.put("ownMenuName", service.queryMenuNameById(menuId));
		map.put("ownMenuId", menuId);
		return "base/system/menu/menuAdd";
	}
	/**
	 * 新增菜单
	 * @param map
	 * @param bean
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=addMenu")
	public ModelAndView addMenu(ModelMap map, MenuBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			bean.setMenuId(Util.dateToString(""));
			service.addMenu(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	
	/**
	 * 进入修改菜单页面
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=gotoEditMenu")
	public String gotoEditMenu(ModelMap map, MenuBean bean, HttpServletRequest request,HttpServletResponse response){
		String menuId = bean.getMenuId();
		//查询菜单名称
		map.put("bean", service.queryMenuById(menuId));
		return "base/system/menu/menuEdit";
	}
	/**
	 * 修改菜单
	 * @param map
	 * @param bean
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=editMenu")
	public ModelAndView editMenu(ModelMap map, MenuBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.editMenu(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 删除菜单
	 * @param map
	 * @param bean
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=deleteMenu")
	public ModelAndView deleteMenu(ModelMap map, MenuBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.deleteMenu(bean.getMenuId());
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
}
