package com.idea.base.core.dao.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.idea.base.logon.domain.UserInfo;


/**
 * 
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Component
public class BaseAction {
	public static final String USER_INFO_KEY="Certificate";
	protected static Log log =null ;
	public BaseAction() {
		super();
	    log = LogFactory.getLog(this.getClass());
	}
	
    protected UserInfo getUser(HttpServletRequest request) {
    	UserInfo user = (UserInfo)request.getSession().getAttribute(USER_INFO_KEY);
        if(user != null){
        	return user;
        }
        return null;
       
    }
    protected void setUser(HttpServletRequest request,UserInfo user) {
    	HttpSession session = request.getSession();
    	session.removeAttribute(USER_INFO_KEY);
        if(user != null){
        	session.setAttribute(USER_INFO_KEY, user);
        }
    }
}

