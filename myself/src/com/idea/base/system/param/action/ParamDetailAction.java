package com.idea.base.system.param.action;

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
import com.idea.base.system.param.domain.ParamDetailBean;
import com.idea.base.system.param.services.ParamDetailService;

/**
 * 系统参数明细管理
 * @author zhangyh
 *
 */
@Controller
@RequestMapping("/ParamDetailAction")
public class ParamDetailAction extends BaseAction{
	@Autowired
	private ParamDetailService service;
	
	/**
	 * 跳转到参数明细管理界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoParamDetailList")
	public String gotoParamDetailList(ModelMap map, HttpServletRequest request) {
		map.put("paramId", request.getParameter("paramId"));
		return "base/system/param/queryParamDetail";
	}
	/**
	 * 查询参数明细列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getParamDetailJSON")
	public String getParamDetailJSON(ModelMap map,ParamDetailBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getParamDetailJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 参数明细新增保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveParamDetailAdd")
	public ModelAndView saveParamDetailAdd(ModelMap map, ParamDetailBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveParamDetailAdd(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 参数明细修改页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveParamDetailEdit")
	public ModelAndView saveParamDetailEdit(ModelMap map, ParamDetailBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveParamDetailEdit(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 参数明细删除
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=deleteParamDetail")
	public ModelAndView deleteParamDetail(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String id = request.getParameter("id");
		try {
			service.deleteParamDetail(id);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
}
