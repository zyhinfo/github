package com.idea.apps.dataSet.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.idea.apps.dataSet.domain.DataSetBean;
import com.idea.apps.dataSet.services.DataSetService;
import com.idea.base.core.dao.action.BaseAction;
import com.idea.base.system.param.util.ParamConstant;

/**
 * 数据管理
 * @author zhangyh
 *
 */
@Controller
@RequestMapping("/DataSetAction")
public class DataSetAction extends BaseAction{
	@Autowired
	private DataSetService service;
	@Autowired
	private ParamConstant param;
	
	/**
	 * 跳转到数据管理界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoDataSetList")
	public String gotoParamList(ModelMap map, HttpServletRequest request) {
		//获取分页的参数编号
		String pageParamId = param.getSYSTEM_PAGING_PARAMID();
		map.put("pageSize", service.queryParamById(request,pageParamId).get("pageSize"));
		return "apps/dataSet/queryDataSet";
	}
	/**
	 * 查询数据列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getDataSetJSON")
	public String getDataSetJSON(ModelMap map,DataSetBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getDataSetJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 数据修改页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveDataSetEdit")
	public ModelAndView saveDataSetEdit(ModelMap map, DataSetBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveDataSetEdit(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 数据删除
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=deleteDataSet")
	public ModelAndView deleteDataSet(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String id = request.getParameter("id");
		String tableName = request.getParameter("tableName");
		try {
			service.deleteDataSet(id);
			service.dropTable(tableName, true);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
}
