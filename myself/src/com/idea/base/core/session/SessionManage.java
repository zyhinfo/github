package com.idea.base.core.session;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * session管理
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-5
 */
public class SessionManage {
	private static final Logger logger = LoggerFactory
			.getLogger(SessionManage.class);
	// 保存sessionID和userName 的映射
	public static Map<String,String> hUserName = new ConcurrentHashMap<String,String>();
	 // 集合对象，保存session 对象的引用
	public static Map<String,HttpSession> htsession = new ConcurrentHashMap<String,HttpSession>();
	// 保存用户操作员代码与 SESSION ID 的关联
	public static Map<String,String> mpOper = new ConcurrentHashMap<String,String>(); 
	/*
	 * isOnline-用于判断用户是否在线 @param session HttpSession-登录的用户名称 @return
	 * boolean-该用户是否在线的标志
	 */
	public static boolean isOnline(HttpSession session,String logonName) {
		boolean flag = false;
		if (hUserName.containsValue(logonName)) {
			flag = true;
		} else {
			flag = false;
			hUserName.put(session.getId(), logonName);
			htsession.put(session.getId(), session);
	    }
		return flag;
	}
	/*
	 * isOnline-用于判断用户是否在线 @param session HttpSession-登录的用户名称 @return
	 * boolean-该用户是否在线的标志
	 */
	public static boolean doRemove(HttpSession session,String sUserName) {
		boolean flag = false;
		if (hUserName.containsValue(sUserName)) {
			flag = true;
			Iterator iter = hUserName.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				if (((String) val).equals(sUserName)) {
					hUserName.remove(key);
					if(htsession.get(key)!=null&&(!session.getId().equals(key))){
						htsession.get(key).invalidate();
					}					
					htsession.remove(key);
				}
			}
		}
		return flag;
	}
	/**
	 * 是否将session保存至MAP中
	 * @param session
	 * @param sUserName
	 * @return
	 */
	public static boolean isAddSessionToMap(HttpSession session,String sUserName) {
		boolean flag = false;
		if (hUserName.containsValue(sUserName)) {
			flag = true;
		} else {
			flag = false;
			hUserName.put(session.getId(), sUserName);
			htsession.put(session.getId(), session);
			}
		return flag;
	}	
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}
	public void sessionDestroyed(HttpSessionEvent se) {
		hUserName.remove(se.getSession().getId());
        htsession.remove(se.getSession().getId());
        
		String sessID = se.getSession().getId();
		logger.debug("用户会话ID："  + sessID );
		String operID = mpOper.remove(sessID);
		/*if( operID != null ){
			SysUserInfo userInfo = new SysUserInfo();
	        userInfo.setOid(operID);
	        String serverId = MonitorService.getServerId();
	        userInfo.setServerId(serverId);
	        userInfo.setOnlineFlag("0");
	        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(
	                                        se.getSession().getServletContext());
	        ICommonServiceJdbc serviceJdbc = (ICommonServiceJdbc)wac.getBean("commonServiceJdbc");
	        serviceJdbc.saveUserInfo(userInfo);
		}else{
			logger.debug("没有获得用户操作员号，注销失败");
		}*/
		 logger.debug("用户会话已过期");
	}
}
