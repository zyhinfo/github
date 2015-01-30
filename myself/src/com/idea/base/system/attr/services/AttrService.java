package com.idea.base.system.attr.services;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.system.attr.domain.AttrBean;
import com.idea.tools.util.Util;

/**
 * 系统属性管理
 * @author zhangyh
 */
@Service
public class AttrService extends BaseDao{
	public String getAttrJSON(AttrBean bean){
		return this.queryForJSON("Attr.getAttrJSON", bean);
	}
	public void deleteAttr(String id){
		this.deleteData("T02_DATA_ATTR", "ATTR_ID='"+id+"'",false);
	}
	public void saveAttrAdd(AttrBean bean){
		bean.setAttrId(Util.dateToString(""));
		this.insert("Attr.saveAttrAdd", bean);
	}
	public void saveAttrEdit(AttrBean bean){
		this.insert("Attr.saveAttrEdit", bean);
	}
	
}
