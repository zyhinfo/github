package com.idea.modules.down.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.idea.base.core.dao.action.BaseAction;
import com.idea.base.logon.domain.UserInfo;
import com.idea.base.system.param.util.ParamConstant;
import com.idea.modules.down.domain.DownThread;
import com.idea.modules.down.init.InitDown;
import com.idea.modules.down.services.DownService;
import com.idea.tools.factory.ToolsFactory;
import com.idea.tools.util.Util;

/**
 * 数据下载公共类
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Controller
@RequestMapping("/DownAction")
public class DownAction extends BaseAction{
	@Autowired
	private DownService service;
	@Autowired
	private ParamConstant param;
	
	/**
	 * 查询下载列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=queryDownFileList")
	public ModelAndView queryDownFileList(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		UserInfo user = this.getUser(request);
		try {
			String ids = request.getParameter("ids");
			//查询下载列表
			List list = service.queryDownFileList(ids,user.getUserId());
			Map<String,Object> param = service.getDownFileTable(list);
			//拼接下载列表tr
			modelAndView.addObject("downTr",param.get("downTr"));
			modelAndView.addObject("downProgress",param.get("downProgress"));
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 获取文件
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getDownFile")
	public String getDownFile(ModelMap map, HttpServletRequest request,HttpServletResponse response) {
		String downId = request.getParameter("downId");
		String filePath = request.getParameter("filePath");
		String downAgain = request.getParameter("downAgain");
		//修改下载状态
		if(!"yes".equals(downAgain)){
			service.updateDownLogStatus(downId);
		}
		ToolsFactory.newDownFile(filePath).downloadToHome(request,response);
		return null;
	}
	/**
	 * 
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=downDataSet")
	public ModelAndView downDataSet(ModelMap map, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String fileName=request.getParameter("fileName");
		String tableName = request.getParameter("tableName");
		UserInfo user = this.getUser(request);
		try{
			String downParamId = param.getSYSTEM_DOWNLOAD_PARAMID();
			Map<String,String> downParam = service.queryParamById(request,downParamId);
			String filePath = downParam.get("path");
			if (!filePath.endsWith("/") && !filePath.endsWith("\\")) {
				filePath += "/";
			}
			filePath+=user.getUserId()+"/";
			
			//设置参数对象
			Map<String,Object> param = Util.getNewMap();
			param.put("tableName", tableName);
			param.put("attrNames", "*");
			int sizeNum = (Integer)service.queryForObject("Down.downDataSetCount",param);
			//1.插入下载信息
			String downId = Util.dateToString("");
			Map<String,Object> data = Util.getNewMap();
			data.put("downId", downId);
			data.put("userId", user.getUserId());
			data.put("userName", user.getUserName());
			data.put("rowNum", sizeNum);
			data.put("fileName", fileName);
			data.put("downFilePath", filePath+fileName+".zip");
			service.insert("Down.insertDownLog", data);
			
			String sql = "select * from "+tableName;
			InitDown init = new InitDown(sql, filePath,sizeNum);
			init.setDownId(downId);
			init.setFileName(fileName);
			init.setFileType("TXT");
			
			init.setParam(param);
			DownThread thread = new DownThread(init,service);
			new Thread(thread).start();
			modelAndView.addObject("info", "ok");
		}catch(Exception e){
			modelAndView.addObject("info", "err");
		}
		
		return modelAndView;
	}
	
	
}
