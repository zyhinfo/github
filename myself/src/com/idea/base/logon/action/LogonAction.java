package com.idea.base.logon.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idea.base.core.dao.action.BaseAction;
import com.idea.base.core.encrypt.Encrypt;
import com.idea.base.logon.domain.LogonBean;
import com.idea.base.logon.domain.UserInfo;
import com.idea.base.logon.services.LogonService;
import com.idea.tools.util.Util;

/**
 * 
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-5
 */
@Controller
@RequestMapping("/LogonAction")
public class LogonAction extends BaseAction{
	
	@Autowired
	private LogonService service;
	/**
	 * 验证用户信息进入系统
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=logon")
	public String logon(ModelMap map,LogonBean bean, HttpServletRequest request) {
		if(bean != null && Util.isNotEmpty(bean.getPassword())){
			//对用户密码进行加密
			bean.setPassword(Encrypt.encrypt(bean.getPassword()));
			UserInfo user = service.queryUser(bean);
			if(user != null){
				setUser(request, user);
				return main(map, request);
			}
		}
		return "base/logon/index";
	}
	
	/**
	 * 提示重新登录信息
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=repeatLogon")
	public String repeatLogon(ModelMap map, HttpServletRequest request) {
		return "base/logon/msg";
	}
	
	
	/**
	 * 进入系统主界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=main")
	public String main(ModelMap map, HttpServletRequest request) {
		UserInfo user = this.getUser(request);
		map.put("userDesc", user.getUserName());
		return "base/logon/main";
	}
	
	/**
	 * 得到系统左侧菜单
	 * 根据用户权限查询
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=queryLeftMenu")
	public void queryLeftMenu(ModelMap map, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		try {
			UserInfo user = this.getUser(request);
			String str=service.queryLeftMenu(request,user);
			PrintWriter out=response.getWriter();
			out.print(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 加载欢迎界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=welcome")
	public String welcome(ModelMap map, HttpServletRequest request) {
		return "base/logon/welcome";
	}
	/**
	 * 退出系统主界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=exit")
	public String exit(ModelMap map, HttpServletRequest request) {
		setUser(request, null);
		return logon(map, null, request);
	}
	
}
