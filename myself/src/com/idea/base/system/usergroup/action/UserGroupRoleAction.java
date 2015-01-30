package com.idea.base.system.usergroup.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.idea.base.core.dao.action.BaseAction;
import com.idea.base.system.usergroup.domain.UserGroupRoleBean;
import com.idea.base.system.usergroup.services.UserGroupRoleService;

/**
 * 用户组和角色关系维护
 * @author zhangyh
 *
 */
@Controller
@RequestMapping("/UserGroupRoleAction")
public class UserGroupRoleAction extends BaseAction{
	@Autowired
	private UserGroupRoleService service;
	
	/**
	 * 跳转到用户组角色管理界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoUserGroupRoleList")
	public String gotoUserGroupRoleList(ModelMap map, HttpServletRequest request) {
		map.put("userGroupId", request.getParameter("userGroupId"));
		return "base/system/usergroup/queryUserGroupRole";
	}
	/**
	 * 查询用户组角色列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getUserGroupRoleJSON")
	public String getUserGroupRoleJSON(ModelMap map,UserGroupRoleBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getUserGroupRoleJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 用户组角色修改保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=updateUserGroupRole")
	public ModelAndView updateUserGroupRole(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String userGroupId = request.getParameter("userGroupId");
		String ids = request.getParameter("ids");
		try {
			service.updateUserGroupRole(userGroupId,ids);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	
	
}
