package com.idea.base.system.user.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.core.dao.Page;
import com.idea.base.system.user.domain.UserDetailBean;
import com.idea.base.util.ConvertData;
import com.idea.tools.util.Util;

/**
 * 用户明细管理(用户、机构、用户组关系维护)
 * @author zhangyh
 */
@Service
public class UserDetailService extends BaseDao{
	public String getUserDetailJSON(UserDetailBean bean){
		return this.queryForJSON("UserDetail.getUserDetailJSON", bean);
	}
	public void deleteUserDetail(String id){
		this.deleteData("T01_SYS_USER_USERGROUP", "USER_USERGROUP_ID='"+id+"'",false);
	}
	public void saveUserDetailAdd(UserDetailBean bean){
		bean.setUserUserGroupId(Util.dateToString(""));
		this.insert("UserDetail.saveUserDetailAdd", bean);
	}
	
	public String getUserGroupJSON(){
		List list = this.queryForList("UserDetail.getUserGroupJSON");
		return ConvertData.listToString(list);
	}
	public String getOrgJSON(UserDetailBean bean){
		Page page = new Page(bean.getPage(),bean.getPagesize());
		page = this.browse("UserDetail.getOrgJSON", "UserDetail.getOrgJSONCount", page, bean);
		return page.getJSONData();
	}
}
