package com.idea.base.system.log.sysLog.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idea.base.core.dao.action.BaseAction;
import com.idea.base.system.log.sysLog.domain.SysLogBean;
import com.idea.base.system.log.sysLog.services.SysLogService;
import com.idea.base.system.param.util.ParamConstant;

/**
 * 系统日志查询
 * @author zhangyh
 *
 */
@Controller
@RequestMapping("/SysLogAction")
public class SysLogAction extends BaseAction{
	@Autowired
	private SysLogService service;
	@Autowired
	private ParamConstant param;
	/**
	 * 跳转到属性管理界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoSysLogList")
	public String gotoSysLogList(ModelMap map, HttpServletRequest request) {
		//获取分页的参数编号
		String pageParamId = param.getSYSTEM_PAGING_PARAMID();
		map.put("pageSize", service.queryParamById(request,pageParamId).get("pageSize"));
		return "base/system/log/sysLog/querySysLog";
	}
	/**
	 * 查询属性列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getSysLogJSON")
	public String getSysLogJSON(ModelMap map,SysLogBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getSysLogJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
