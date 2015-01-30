package com.idea.base.system.usergroup.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.system.usergroup.domain.UserGroupRoleBean;
import com.idea.tools.util.Util;

/**
 * 用户组和角色关系维护
 * @author zhangyh
 */
@Service
public class UserGroupRoleService extends BaseDao{
	public String getUserGroupRoleJSON(UserGroupRoleBean bean){
		return this.queryForJSON("UserGroupRole.getUserGroupRoleJSON", bean);
	}
	
	public void updateUserGroupRole(String userGroupId,String ids){
		this.deleteData("T01_SYS_USERGROUP_ROLE", "USERGROUP_ID='"+userGroupId+"'",false);
		String[] roleIds = Util.split(ids, ",");
		if(roleIds != null){
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for(String roleId:roleIds){
				Map param = Util.getNewMap();
				param.put("userGroupId", userGroupId);
				param.put("roleId", roleId);
				list.add(param);
			}
			this.executeBatch("UserGroupRole.updateUserGroupRole", list);
		}
	}
	
}
