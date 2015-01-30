package com.idea.base.system.user.action;

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
import com.idea.base.system.user.domain.UserDetailBean;
import com.idea.base.system.user.services.UserDetailService;

/**
 * 用户明细管理(用户、机构、用户组关系维护)
 * @author zhangyh
 *
 */
@Controller
@RequestMapping("/UserDetailAction")
public class UserDetailAction extends BaseAction{
	@Autowired
	private UserDetailService service;
	
	/**
	 * 跳转到用户、机构、用户组关系维护管理界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoUserDetailList")
	public String gotoUserDetailList(ModelMap map, HttpServletRequest request) {
		map.put("userId", request.getParameter("userId"));
		return "base/system/user/queryUserDetail";
	}
	/**
	 * 查询用户、机构、用户组关系列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getUserDetailJSON")
	public String getUserDetailJSON(ModelMap map,UserDetailBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getUserDetailJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 新增用户、机构、用户组关系 机构查询
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getOrgJSON")
	public String getOrgJSON(ModelMap map,UserDetailBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getOrgJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 新增用户、机构、用户组关系 用户组查询
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getUserGroupJSON")
	public String getUserGroupJSON(ModelMap map, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getUserGroupJSON();
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 用户、机构、用户组关系新增
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveUserDetailAdd")
	public ModelAndView saveUserDetailAdd(ModelMap map,UserDetailBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			service.saveUserDetailAdd(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 用户、机构、用户组关系删除
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=deleteUserDetail")
	public ModelAndView deleteUserDetail(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String id = request.getParameter("id");
		try {
			service.deleteUserDetail(id);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	
}
