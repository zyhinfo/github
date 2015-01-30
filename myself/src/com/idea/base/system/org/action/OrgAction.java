package com.idea.base.system.org.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.idea.base.core.dao.action.BaseAction;
import com.idea.base.system.org.domain.OrgBean;
import com.idea.base.system.org.services.OrgService;
import com.idea.tools.util.Util;

/**
 * 机构管理
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Controller
@RequestMapping("/OrgAction")
public class OrgAction extends BaseAction{
	@Autowired
	private OrgService service;
	/**
	 * 
	 * 机构管理功能左侧机构树
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=queryLeftOrg")
	public void queryLeftOrg(ModelMap map, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		try {
			Map<String,Object> param=Util.getNewMap();
			String str=service.queryLeftOrg(param);
			PrintWriter out=response.getWriter();
			out.print(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 下拉框中的机构树
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=queryComboBoxOrg")
	public void queryComboBoxOrg(ModelMap map, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		try {
			Map<String,Object> param=Util.getNewMap();
			String str=service.queryComboBoxOrg(param);
			PrintWriter out=response.getWriter();
			out.print(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 进入菜单管理主界面
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=gotoOrgMain")
	public String gotoOrgMain(ModelMap map, HttpServletRequest request,HttpServletResponse response){
		
		return "base/system/org/orgMain";
	}
	/**
	 * 进入增加机构页面
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=gotoAddOrg")
	public String gotoAddOrg(ModelMap map, OrgBean bean, HttpServletRequest request,HttpServletResponse response){
		String orgId = bean.getOrgId();
		OrgBean orgBean = service.queryOrgById(orgId);
		map.put("ownOrgName", orgBean.getOrgName());
		map.put("ownOrgId", orgId);
		map.put("orgLev", (Util.toInteger(orgBean.getOrgLev(),0)+1)+"");
		return "base/system/org/orgAdd";
	}
	/**
	 * 新增菜单
	 * @param map
	 * @param bean
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=addOrg")
	public ModelAndView addOrg(ModelMap map, OrgBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			bean.setOrgId(Util.dateToString(""));
			service.addOrg(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	
	/**
	 * 进入修改菜单页面
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=gotoEditOrg")
	public String gotoEditOrg(ModelMap map, OrgBean bean, HttpServletRequest request,HttpServletResponse response){
		String orgId = bean.getOrgId();
		//查询菜单名称
		map.put("bean", service.queryOrgById(orgId));
		return "base/system/org/orgEdit";
	}
	/**
	 * 修改菜单
	 * @param map
	 * @param bean
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=editOrg")
	public ModelAndView editOrg(ModelMap map, OrgBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.editOrg(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 删除菜单
	 * @param map
	 * @param bean
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=deleteOrg")
	public ModelAndView deleteOrg(ModelMap map, OrgBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.deleteOrg(bean.getOrgId());
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
}
