package com.idea.base.system.menu.domain;

import com.idea.base.core.dao.domain.BaseBean;

/**
 * 菜单管理
 * @author zhangyh
 *
 */
public class MenuBean extends BaseBean{
	private static final long serialVersionUID = 1L;
	private String menuId;  //菜单编号
	private String menuName;  //菜单名称
	private String menuDesc;  //菜单描述
	private String linkUrl;   //菜单链接
	private String menuIcon; 
	private String seqNum; 
	private String ownMenuId;
	private String ownMenuName;
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getOwnMenuId() {
		return ownMenuId;
	}
	public void setOwnMenuId(String ownMenuId) {
		this.ownMenuId = ownMenuId;
	}
	public String getOwnMenuName() {
		return ownMenuName;
	}
	public void setOwnMenuName(String ownMenuName) {
		this.ownMenuName = ownMenuName;
	}
	public String getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	
	
}
