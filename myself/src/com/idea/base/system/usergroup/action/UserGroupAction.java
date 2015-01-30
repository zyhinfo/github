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
import com.idea.base.system.usergroup.domain.UserGroupBean;
import com.idea.base.system.usergroup.services.UserGroupService;

/**
 * 用户组管理
 * @author zhangyh
 *
 */
@Controller
@RequestMapping("/UserGroupAction")
public class UserGroupAction extends BaseAction{
	@Autowired
	private UserGroupService service;
	
	/**
	 * 跳转到用户组管理界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoUserGroupList")
	public String gotoUserGroupList(ModelMap map, HttpServletRequest request) {
		return "base/system/usergroup/queryUserGroup";
	}
	/**
	 * 查询用户组列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getUserGroupJSON")
	public String getUserGroupJSON(ModelMap map,UserGroupBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getUserGroupJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 用户组新增保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveUserGroupAdd")
	public ModelAndView saveUserGroupAdd(ModelMap map, UserGroupBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveUserGroupAdd(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 用户组修改页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveUserGroupEdit")
	public ModelAndView saveUserGroupEdit(ModelMap map, UserGroupBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveUserGroupEdit(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 用户组删除
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=deleteUserGroup")
	public ModelAndView deleteUserGroup(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String id = request.getParameter("id");
		try {
			service.deleteUserGroup(id);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
}
