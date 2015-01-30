package com.idea.base.system.personalFile.action;

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
import com.idea.base.logon.domain.UserInfo;
import com.idea.base.system.param.util.ParamConstant;
import com.idea.base.system.personalFile.domain.EducationBean;
import com.idea.base.system.personalFile.domain.HolidayBean;
import com.idea.base.system.personalFile.domain.JobBean;
import com.idea.base.system.personalFile.domain.PersonalFileBean;
import com.idea.base.system.personalFile.domain.RewardBean;
import com.idea.base.system.personalFile.domain.TrainBean;
import com.idea.base.system.personalFile.services.PersonalFileService;
import com.idea.tools.factory.ToolsFactory;
import com.idea.tools.port.upload.Upload;
import com.idea.tools.util.Base64;
import com.idea.tools.util.Util;

/**
 * 个人档案管理
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Controller
@RequestMapping("/PersonalFileAction")
public class PersonalFileAction extends BaseAction{
	@Autowired
	private PersonalFileService service;
	@Autowired
	private ParamConstant param;
	
	/**
	 * 跳转到个人档案管理界面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=gotoPersonalFileList")
	public String gotoPersonalFileList(ModelMap map, HttpServletRequest request) {
		//获取分页的参数编号
		String pageParamId = param.getSYSTEM_PAGING_PARAMID();
		map.put("pageSize", service.queryParamById(request,pageParamId).get("pageSize"));
		return "base/system/personalFile/queryPersonalFile";
	}
	/**
	 * 显示档案主页
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=mainShowPersonalFile")
	public String mainShowPersonalFile(ModelMap map, HttpServletRequest request) {
		map.put("archiveId", request.getParameter("archiveId"));
		return "base/system/personalFile/mainShowPersonalFile";
	}
	/**
	 * 个人档案明细
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=showPersonalFile")
	public String showPersonalFile(ModelMap map, HttpServletRequest request) {
		Map<String,Object> param = Util.getNewMap();
		String archiveId=request.getParameter("archiveId");
		if(Util.isEmpty(archiveId)){
			if(map.containsKey("archiveId")){
				archiveId=(String)map.get("archiveId");
			}else{
				//根据用户编号查出档案编号archiveId
				String userId=this.getUser(request).getUserId();
				param.put("userId", userId);
				archiveId="";
			}
		}
		map.put("titId", request.getParameter("titId"));
		map.put("archiveInfo", service.getArchiveInfo(archiveId));
		map.put("educationList", service.getArchiveEducationList(archiveId));
		map.put("jobList", service.getArchiveJobList(archiveId));
		map.put("trainList", service.getArchiveTrainList(archiveId));
		map.put("rewardList", service.getArchiveRewardList(archiveId));
		map.put("holidayList", service.getArchiveHolidayList(archiveId));
		return "base/system/personalFile/showPersonalFile";
	}
	
	/**
	 * 查询档案列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=getPersonalFileJSON")
	public String getPersonalFileJSON(ModelMap map,PersonalFileBean bean, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			String json = service.getPersonalFileJSON(bean);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 档案基本信息新增保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=savePersonalFileAdd")
	public ModelAndView savePersonalFileAdd(ModelMap map, PersonalFileBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.savePersonalFileAdd(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	
	/**
	 * 档案基本信息修改保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=savePersonalFileEdit")
	public ModelAndView savePersonalFileEdit(ModelMap map, PersonalFileBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.savePersonalFileEdit(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	
	/**
	 * 档案基本信息新增保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveArchiveEducationAdd")
	public ModelAndView saveArchiveEducationAdd(ModelMap map, EducationBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveArchiveEducationAdd(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 档案基本信息修改保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveArchiveEducationEdit")
	public ModelAndView saveArchiveEducationEdit(ModelMap map, EducationBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveArchiveEducationEdit(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 档案工作经历信息新增保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveArchiveJobAdd")
	public ModelAndView saveArchiveJobAdd(ModelMap map, JobBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveArchiveJobAdd(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	
	/**
	 * 档案工作经历信息修改保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveArchiveJobEdit")
	public ModelAndView saveArchiveJobEdit(ModelMap map, JobBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveArchiveJobEdit(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	
	/**
	 * 档案培训经历信息新增保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveArchiveTrainAdd")
	public ModelAndView saveArchiveTrainAdd(ModelMap map, TrainBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveArchiveTrainAdd(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 档案教育经历信息修改保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveArchiveTrainEdit")
	public ModelAndView saveArchiveTrainEdit(ModelMap map, TrainBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveArchiveTrainEdit(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 档案奖罚信息新增保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveArchiveRewardAdd")
	public ModelAndView saveArchiveRewardAdd(ModelMap map, RewardBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveArchiveRewardAdd(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 档案奖罚信息修改保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveArchiveRewardEdit")
	public ModelAndView saveArchiveRewardEdit(ModelMap map, RewardBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveArchiveRewardEdit(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	
	/**
	 * 档案年度休假新增保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveArchiveHolidayAdd")
	public ModelAndView saveArchiveHolidayAdd(ModelMap map, HolidayBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveArchiveHolidayAdd(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 档案年度休假修改保存页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveArchiveHolidayEdit")
	public ModelAndView saveArchiveHolidayEdit(ModelMap map, HolidayBean bean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			service.saveArchiveHolidayEdit(bean);
			modelAndView.addObject("info", "ok");
		} catch (Exception e) {
			modelAndView.addObject("info", "err");
		}
		return modelAndView;
	}
	/**
	 * 
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=uploadImg")
	public String uploadImg(ModelMap map, HttpServletRequest request) {
		UserInfo user = this.getUser(request);
		//将图片保存到服务器
		//获取文件上传参数编号
		String upParamId = param.getSYSTEM_UPLOAD_PARAMID();
		Map<String,String> paramMap = service.queryParamById(request,upParamId);
		Upload upload=ToolsFactory.newFileUpload(paramMap.get("path")+user.getUserId()+"/");
		Map<String,String> keyAndVal = upload.upload(request);
		//将图片缩小
		String srcImagePath = keyAndVal.get("filePath");
		String archiveId = keyAndVal.get("archiveId");
		String fileType = srcImagePath.substring(srcImagePath.lastIndexOf(".")+1);
		String fileName = srcImagePath.substring(0,srcImagePath.lastIndexOf("."));
		//对上传文件名称进行修改
		int size = 120;//图片大小80*80
		String toImagePath = fileName+"_"+size+"."+fileType;
		try {
			ToolsFactory.newImage().resizeImage(srcImagePath, toImagePath, size, size);
			//将图片转成base64
			String photoBase64=Base64.imageToBase64(toImagePath);
			//保存到数据库中
			Map param = Util.getNewMap();
			param.put("archiveId", archiveId);
			param.put("photoBase64", photoBase64);
			service.saveImgBase64(param);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("archiveId", archiveId);
		return showPersonalFile(map, request);
	}
}
