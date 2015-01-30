package com.idea.base.system.org.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.system.org.domain.OrgBean;
import com.idea.base.util.ConvertData;

/**
 * 机构管理
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Service
public class OrgService extends BaseDao{
	/**
	 * 机构管理功能左侧机构树
	 * @param param
	 * @return
	 */
	public String queryLeftOrg(Map param){
		List list=this.queryForList("Org.queryLeftOrg",param);
		String str=ConvertData.toString(list);
		log.debug("加载机构管理功能左侧机构树:"+str);
		return str;
	}
	
	/**
	 * 下拉框中的机构树
	 * @param param
	 * @return
	 */
	public String queryComboBoxOrg(Map param){
		List list=this.queryForList("Org.queryComboBoxOrg",param);
		String str=ConvertData.toString(list);
		log.debug("加载下拉框中的机构树:"+str);
		return str;
	}
	
	public void addOrg(OrgBean bean){
		this.update("Org.addOrg", bean);
		
	}
	public OrgBean queryOrgById(String OrgId){
		return (OrgBean)this.queryForObject("Org.queryOrgById",OrgId);
		
	}
	public void editOrg(OrgBean bean){
		this.update("Org.editOrg",bean);
	}
	public void deleteOrg(String orgId){
		String whereStr = "(SELECT ORG_ID FROM T01_SYS_ORG START WITH ORG_ID = '"
				+ orgId+"' CONNECT BY PRIOR ORG_ID = OWN_ORG_ID)";
		this.deleteData("T01_SYS_ORG", "ORG_ID IN "+whereStr,false);
		this.deleteData("T01_SYS_USER_USERGROUP", "ORG_ID='"+orgId+"'",false);
	}
}
