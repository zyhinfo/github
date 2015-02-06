package com.idea.modules.upload.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.idea.base.util.ConvertData;
import com.idea.modules.upload.domain.ColParamBean;
import com.idea.modules.upload.domain.UploadBean;
import com.idea.modules.upload.domain.UploadThread;
import com.idea.modules.upload.init.InitUpload;
import com.idea.modules.upload.services.UploadService;
import com.idea.tools.factory.ToolsFactory;
import com.idea.tools.port.read.ReadBatchFile;
import com.idea.tools.port.read.ReadBean;
import com.idea.tools.port.upload.Upload;
import com.idea.tools.util.Util;

/**
 * 数据上传公共
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Controller
@RequestMapping("/UploadAction")
public class UploadAction extends BaseAction{
	@Autowired
	private UploadService service;
	@Autowired
	private ParamConstant param;
	/**
	 * 跳转数据上传界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoUpload")
	public String gotoUpload(ModelMap map,InitUpload upload, HttpServletRequest request) {
		if(upload == null || Util.isEmpty(upload.getImportTableName())){
			upload = new InitUpload();
			upload.setBackUrl("/DataSetAction.action?method=gotoDataSetList");
			upload.setEndUrl("/DataSetAction.action?method=gotoDataSetList");
			upload.setTemplateTableName("");
			upload.setImportTableName("");
		}
		map.put("upload", upload);
		return "modules/upload/upload";
	}
	
	/**
	 * 文件上传
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=upload")
	public String upload(ModelMap map, HttpServletRequest request) {
		UserInfo user = this.getUser(request);
		//获取文件上传参数编号
		String upParamId = param.getSYSTEM_UPLOAD_PARAMID();
		Map<String,String> paramMap = service.queryParamById(request,upParamId);
		Upload upload=ToolsFactory.newFileUpload(paramMap.get("path")+user.getUserId()+"/");
		Map<String,String> keyAndVal = upload.upload(request);
		for(Iterator it = keyAndVal.entrySet().iterator();it.hasNext();){
			Entry entry = (Entry)it.next();
			map.put(entry.getKey().toString(), entry.getValue().toString());
		}
		String uploadId = Util.dateToString("");
		keyAndVal.put("uploadId", uploadId);
		keyAndVal.put("userId", user.getUserId());
		keyAndVal.put("userName", user.getUserName());
		service.addUploadLog(keyAndVal);
		map.put("uploadId", uploadId);
		return "modules/upload/readFile";
	}
	
	/**
	 * 查询数据列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=readFile")
	public String readFile(ModelMap map, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		String filePath = request.getParameter("filePath");
		//获取上传参数
		String upParamId = param.getSYSTEM_UPLOAD_PARAMID();
		Map<String,String> paramMap = service.queryParamById(request,upParamId);
		try {
			ReadBean bean=new ReadBean();
			bean.setFilePath(filePath);
			bean.setRETURN_NUM(Util.toInteger(paramMap.get("readCount"),100));
			bean.setSplits(paramMap.get("textSplit"));
			ReadBatchFile read=ToolsFactory.newFileRead().getFileContent(bean);
			List<List<String>> list=read.readFile();
			read.closeAll();
			List<Map<String,String>> sysAttr=service.querySysAttr(request, "");
			String json = service.getTableJSON(list,sysAttr);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询数据列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=querySysAttr")
	public String querySysAttr(ModelMap map, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			List<Map<String,String>> list=service.querySysAttr(request, "");
			String json = ConvertData.listToJSONString(list,-1);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 生成数据表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=importDB")
	public ModelAndView importDB(ModelMap map,UploadBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		UserInfo user = this.getUser(request);
		String upParamId = param.getSYSTEM_UPLOAD_PARAMID();
		Map<String,String> paramMap = service.queryParamById(request,upParamId);
		try {
			//初始化数据记录
			Map<String,Object> data = Util.getNewMap();
			//上传日志 和数据集是相同的id
			data.put("instId", request.getParameter("uploadId"));
			data.put("instName", bean.getDataName());
			data.put("status", "0");//正在上传状态
			data.put("userId", user.getUserId());
			data.put("instTable", "DATA_INS_"+Util.dateToString(""));
			service.addDataIns(data);
			data.put("colParam", bean.getColParam());
			ColParamBean colBean = new ColParamBean(data,service.querySysAttr(request, ""));
			//创建数据存储表
			service.updateData(colBean.getCreateSql());
			data.put("insertSql", colBean.getInsertSql());
			data.put("readRowNum", colBean.getReadRowNum());
			data.put("types", colBean.getTypes());
			//调用线程导入数据
			
			data.put("filePath", bean.getFilePath());
			data.put("readCount", paramMap.get("readCount"));
			data.put("textSplit",paramMap.get("textSplit"));
			UploadThread export = new UploadThread(data,service);
			export.run();
			
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
}
