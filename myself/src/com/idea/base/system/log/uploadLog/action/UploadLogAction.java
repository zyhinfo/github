package com.idea.base.system.log.uploadLog.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idea.base.core.dao.action.BaseAction;
import com.idea.base.system.log.uploadLog.domain.UploadLogBean;
import com.idea.base.system.log.uploadLog.services.UploadLogService;
import com.idea.base.system.param.util.ParamConstant;
import com.idea.tools.factory.ToolsFactory;

/**
 * 上传日志
 * @author zhangyh
 *
 */
@Controller
@RequestMapping("/UploadLogAction")
public class UploadLogAction extends BaseAction{
	@Autowired
	private UploadLogService service;
	@Autowired
	private ParamConstant param;
	/**
	 * 跳转到上传日志列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoUploadLogList")
	public String gotoUploadLogList(ModelMap map, HttpServletRequest request) {
		//获取分页的参数编号
		String pageParamId = param.getSYSTEM_PAGING_PARAMID();
		map.put("pageSize", service.queryParamById(request,pageParamId).get("pageSize"));
		return "base/system/log/uploadLog/queryUploadLog";
	}
	/**
	 * 查询上传日志数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getUploadLogJSON")
	public String getUploadLogJSON(ModelMap map,UploadLogBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getUploadLogJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
