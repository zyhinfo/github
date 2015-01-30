package com.idea.base.system.personalFile.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.core.dao.Page;
import com.idea.base.system.personalFile.domain.EducationBean;
import com.idea.base.system.personalFile.domain.HolidayBean;
import com.idea.base.system.personalFile.domain.JobBean;
import com.idea.base.system.personalFile.domain.PersonalFileBean;
import com.idea.base.system.personalFile.domain.RewardBean;
import com.idea.base.system.personalFile.domain.TrainBean;
import com.idea.tools.util.Util;

/**
 * 个人档案管理
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Service
public class PersonalFileService extends BaseDao {
	public String getPersonalFileJSON(PersonalFileBean bean){
		Page page = new Page(bean.getPage(),bean.getPagesize());
		page = this.browse("PersonalFile.getPersonalFileJSON", "PersonalFile.getPersonalFileJSONCount", page, bean);
		return page.getJSONData();
	}
	
	
	public Map getArchiveInfo(String archiveId){
		return (Map)this.queryForObject("PersonalFile.getArchiveInfo", archiveId);
	}
	public List getArchiveEducationList(String archiveId){
		return this.queryForList("PersonalFile.getArchiveEducationList",archiveId);
	}
	public List getArchiveJobList(String archiveId){
		return this.queryForList("PersonalFile.getArchiveJobList",archiveId);
	}
	public List getArchiveTrainList(String archiveId){
		return this.queryForList("PersonalFile.getArchiveTrainList",archiveId);
	}
	public List getArchiveRewardList(String archiveId){
		return this.queryForList("PersonalFile.getArchiveRewardList",archiveId);
	}
	public List getArchiveHolidayList(String archiveId){
		return this.queryForList("PersonalFile.getArchiveHolidayList",archiveId);
	}
	
	public void savePersonalFileAdd(PersonalFileBean bean){
		bean.setArchiveId(Util.dateToString(""));
		this.insert("PersonalFile.savePersonalFileAdd", bean);
	}
	public void saveArchiveEducationAdd(EducationBean bean){
		bean.setId(Util.dateToString(""));
		this.insert("PersonalFile.saveArchiveEducationAdd",bean);
	}
	
	public void saveArchiveJobAdd(JobBean bean){
		bean.setId(Util.dateToString(""));
		this.insert("PersonalFile.saveArchiveJobAdd",bean);
	}
	public void saveArchiveTrainAdd(TrainBean bean){
		bean.setId(Util.dateToString(""));
		this.insert("PersonalFile.saveArchiveTrainAdd",bean);
	}
	public void saveArchiveRewardAdd(RewardBean bean){
		bean.setId(Util.dateToString(""));
		this.insert("PersonalFile.saveArchiveRewardAdd",bean);
	}
	public void saveArchiveHolidayAdd(HolidayBean bean){
		bean.setId(Util.dateToString(""));
		this.insert("PersonalFile.saveArchiveHolidayAdd",bean);
	}
	
	public void savePersonalFileEdit(PersonalFileBean bean){
		this.update("PersonalFile.savePersonalFileEdit", bean);
	}
	public void saveArchiveEducationEdit(EducationBean bean){
		this.update("PersonalFile.saveArchiveEducationEdit",bean);
	}
	public void saveArchiveJobEdit(JobBean bean){
		this.update("PersonalFile.saveArchiveJobEdit",bean);
	}
	public void saveArchiveTrainEdit(TrainBean bean){
		this.update("PersonalFile.saveArchiveTrainEdit",bean);
	}
	public void saveArchiveRewardEdit(RewardBean bean){
		this.update("PersonalFile.saveArchiveRewardEdit",bean);
	}
	public void saveArchiveHolidayEdit(HolidayBean bean){
		this.update("PersonalFile.saveArchiveHolidayEdit",bean);
	}
	public void saveImgBase64(Map<String,String> param){
		this.update("PersonalFile.saveImgBase64",param);
	}
}
