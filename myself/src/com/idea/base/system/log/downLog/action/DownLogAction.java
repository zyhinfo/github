package com.idea.base.system.log.downLog.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idea.base.core.dao.action.BaseAction;
import com.idea.base.system.log.downLog.domain.DownLogBean;
import com.idea.base.system.log.downLog.services.DownLogService;
import com.idea.base.system.param.util.ParamConstant;

/**
 * 下载日志
 * @author zhangyh
 *
 */
@Controller
@RequestMapping("/DownLogAction")
public class DownLogAction extends BaseAction{
	@Autowired
	private DownLogService service;
	@Autowired
	private ParamConstant param;
	/**
	 * 跳转到下载日志列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoDownLogList")
	public String gotoDownLogList(ModelMap map, HttpServletRequest request) {
		//获取分页的参数编号
		String pageParamId = param.getSYSTEM_PAGING_PARAMID();
		map.put("pageSize", service.queryParamById(request,pageParamId).get("pageSize"));
		return "base/system/log/downLog/queryDownLog";
	}
	/**
	 * 查询下载日志数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getDownLogJSON")
	public String getDownLogJSON(ModelMap map,DownLogBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getDownLogJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
