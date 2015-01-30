package com.idea.base.system.attr.action;

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
import com.idea.base.system.attr.domain.AttrBean;
import com.idea.base.system.attr.services.AttrService;

/**
 * 系统属性管理
 * @author zhangyh
 *
 */
@Controller
@RequestMapping("/AttrAction")
public class AttrAction extends BaseAction{
	@Autowired
	private AttrService service;
	
	/**
	 * 跳转到属性管理界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoAttrList")
	public String gotoParamList(ModelMap map, HttpServletRequest request) {
		return "base/system/attr/queryAttr";
	}
	/**
	 * 查询属性列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getAttrJSON")
	public String getAttrJSON(ModelMap map,AttrBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getAttrJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 属性新增保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveAttrAdd")
	public ModelAndView saveAttrAdd(ModelMap map, AttrBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveAttrAdd(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 属性修改页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveAttrEdit")
	public ModelAndView saveAttrEdit(ModelMap map, AttrBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveAttrEdit(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 属性删除
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=deleteAttr")
	public ModelAndView deleteAttr(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String id = request.getParameter("id");
		try {
			service.deleteAttr(id);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
}
