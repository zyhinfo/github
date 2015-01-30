package com.idea.base.core.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.idea.tools.util.Util;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private String defaultErrorView;
	public String getDefaultErrorView() {
		return defaultErrorView;
	}
	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}
	@SuppressWarnings("unchecked")
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		StringBuffer sb = new StringBuffer("");
		StackTraceElement[] element = ex.getStackTrace();
		for(int i=0 ;i<element.length;i++){
			if(Util.isStartsWith(element[i], "com.idea")){
				sb.append(element[i]+"\n");
			}
			if(i > 10) break;
		}
		Map model = Util.getNewMap();
		model.put("ex", ex.fillInStackTrace());
		model.put("error", sb.toString());
		return new ModelAndView(defaultErrorView, model);
	}
}