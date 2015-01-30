package com.idea.base.system.log.errorLog.action;

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
import com.idea.base.logon.domain.UserInfo;
import com.idea.base.system.log.errorLog.domain.ErrorLogBean;
import com.idea.base.system.log.errorLog.services.ErrorLogService;
import com.idea.base.system.param.util.ParamConstant;

/**
 * 错误日志查询
 * @author zhangyh
 *
 */
@Controller
@RequestMapping("/ErrorLogAction")
public class ErrorLogAction extends BaseAction{
	@Autowired
	private ErrorLogService service;
	@Autowired
	private ParamConstant param;
	/**
	 * 跳转到错误日志查询
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoErrorLogList")
	public String gotoErrorLogList(ModelMap map, HttpServletRequest request) {
		//获取分页的参数编号
		String pageParamId = param.getSYSTEM_PAGING_PARAMID();
		map.put("pageSize", service.queryParamById(request,pageParamId).get("pageSize"));
		return "base/system/log/errorLog/queryErrorLog";
	}
	/**
	 * 查询错误日志查询
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getErrorLogJSON")
	public String getErrorLogJSON(ModelMap map,ErrorLogBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getErrorLogJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 错误日志修改
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=addErrorLog")
	public ModelAndView addErrorLog(ModelMap map, ErrorLogBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			UserInfo user = this.getUser(request);
			bean.setUserId(user.getUserId());
			bean.setUserName(user.getUserName());
			service.addErrorLog(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 错误日志删除
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=deleteErrorLog")
	public ModelAndView deleteErrorLog(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String id = request.getParameter("id");
		try {
			service.deleteErrorLog(id);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
}
