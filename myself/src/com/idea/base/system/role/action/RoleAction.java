package com.idea.base.system.role.action;

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
import com.idea.base.system.param.util.ParamConstant;
import com.idea.base.system.role.domain.RoleBean;
import com.idea.base.system.role.services.RoleService;
import com.idea.tools.util.Util;

/**
 * 角色管理
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Controller
@RequestMapping("/RoleAction")
public class RoleAction extends BaseAction{
	@Autowired
	private RoleService service;
	@Autowired
	private ParamConstant param;
	/**
	 * 跳转到角色管理界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoRoleList")
	public String gotoRoleList(ModelMap map, HttpServletRequest request) {
		//获取分页的参数编号
		String pageParamId = param.getSYSTEM_PAGING_PARAMID();
		map.put("pageSize", service.queryParamById(request,pageParamId).get("pageSize"));
		return "base/system/role/queryRole";
	}
	/**
	 * 查询角色列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getRoleJSON")
	public String getRoleJSON(ModelMap map,RoleBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getRoleJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 角色新增保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveRoleAdd")
	public ModelAndView saveRoleAdd(ModelMap map, RoleBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveRoleAdd(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 角色修改页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveRoleEdit")
	public ModelAndView saveRoleEdit(ModelMap map, RoleBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveRoleEdit(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 角色删除
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=deleteRole")
	public ModelAndView deleteRole(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String id = request.getParameter("id");
		try {
			service.deleteRole(id);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	
	/**
	 * 得到系统左侧菜单
	 * 根据用户权限查询
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=querySysMenu")
	public void querySysMenu(ModelMap map, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		try {
			Map<String,Object> param=Util.getNewMap();
			param.put("roleId", request.getParameter("roleId"));
			String str=service.querySysMenu(param);
			PrintWriter out=response.getWriter();
			out.print(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改角色和菜单对应关系
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=updateRoleAndMenu")
	public ModelAndView updateRoleAndMenu(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String ids = request.getParameter("ids");
		String roleId = request.getParameter("roleId");
		Map<String,Object> param=Util.getNewMap();
		param.put("roleId", roleId);
		param.put("ids", ids);
		try {
			service.updateRoleAndMenu(param);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
}
