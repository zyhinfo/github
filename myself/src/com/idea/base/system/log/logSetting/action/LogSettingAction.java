package com.idea.base.system.log.logSetting.action;

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
import com.idea.base.system.log.logSetting.domain.LogSettingBean;
import com.idea.base.system.log.logSetting.services.LogSettingService;
import com.idea.base.system.param.util.ParamConstant;

/**
 * 系统日志设置
 * @author zhangyh
 *
 */
@Controller
@RequestMapping("/LogSettingAction")
public class LogSettingAction extends BaseAction{
	@Autowired
	private LogSettingService service;
	@Autowired
	private ParamConstant param;
	/**
	 * 跳转到属性管理界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoLogSettingList")
	public String gotoLogSettingList(ModelMap map, HttpServletRequest request) {
		//获取分页的参数编号
		String pageParamId = param.getSYSTEM_PAGING_PARAMID();
		map.put("pageSize", service.queryParamById(request,pageParamId).get("pageSize"));
		return "base/system/log/logSetting/queryLogSetting";
	}
	/**
	 * 查询属性列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getLogSettingJSON")
	public String getLogSettingJSON(ModelMap map,LogSettingBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getLogSettingJSON(bean);
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
	@RequestMapping(params = "method=saveLogSettingAdd")
	public ModelAndView saveLogSettingAdd(ModelMap map, LogSettingBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveLogSettingAdd(bean);
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
	@RequestMapping(params = "method=saveLogSettingEdit")
	public ModelAndView saveLogSettingEdit(ModelMap map, LogSettingBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveLogSettingEdit(bean);
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
	@RequestMapping(params = "method=deleteLogSetting")
	public ModelAndView deleteLogSetting(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String id = request.getParameter("id");
		try {
			service.deleteLogSetting(id);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
}
