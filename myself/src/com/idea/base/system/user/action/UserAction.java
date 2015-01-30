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
import com.idea.base.core.encrypt.Encrypt;
import com.idea.base.system.param.util.ParamConstant;
import com.idea.base.system.user.domain.UserBean;
import com.idea.base.system.user.services.UserService;

/**
 * 用户管理
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Controller
@RequestMapping("/UserAction")
public class UserAction extends BaseAction{
	@Autowired
	private UserService service;
	@Autowired
	private ParamConstant param;
	/**
	 * 跳转到用户管理界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoUserList")
	public String gotoUserList(ModelMap map, HttpServletRequest request) {
		//获取分页的参数编号
		String pageParamId = param.getSYSTEM_PAGING_PARAMID();
		map.put("pageSize", service.queryParamById(request,pageParamId).get("pageSize"));
		return "base/system/user/queryUser";
	}
	/**
	 * 查询用户列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getUserJSON")
	public String getUserJSON(ModelMap map,UserBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getUserJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 用户新增保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveUserAdd")
	public ModelAndView saveUserAdd(ModelMap map, UserBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			bean.setPassword(Encrypt.encrypt(bean.getPassword()));
			service.saveUserAdd(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 用户修改页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveUserEdit")
	public ModelAndView saveUserEdit(ModelMap map, UserBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveUserEdit(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 用户删除
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=deleteUser")
	public ModelAndView deleteUser(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String id = request.getParameter("id");
		try {
			service.deleteUser(id);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	
}
