package com.idea.base.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idea.base.core.dao.action.BaseAction;

/**
 * 对不合法的访问进行过滤
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-5
 */
public class LogonFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(LogonFilter.class);
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)req; 
		HttpServletResponse response = (HttpServletResponse)resp; 
		String fn = request.getParameter("method" );
		String url = request.getRequestURL().toString();
		
		if(-1 != url.indexOf("LogonAction")&&("logon".equals(fn)||"exit".equals(fn))){
			chain.doFilter(req, resp);
		}else{
			HttpSession session = request.getSession();
		    if (session.getAttribute(BaseAction.USER_INFO_KEY) != null) {
		        chain.doFilter(req, resp);
		    } else {
		    	RequestDispatcher dispatcher = request
		    			.getRequestDispatcher("/LogonAction.action?method=repeatLogon");
		    	dispatcher.forward(request, response);
		    }
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
