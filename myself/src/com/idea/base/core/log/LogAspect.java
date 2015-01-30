package com.idea.base.core.log;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.ui.ModelMap;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.core.dao.action.BaseAction;
import com.idea.base.logon.domain.UserInfo;
import com.idea.tools.util.Util;

/**
 * 功能日志
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-5
 */
@Aspect
public class LogAspect extends BaseDao{
	private Map<String,String> linkUrlAndDesc=null;
	@Pointcut("execution(* com.idea..*Action.*(..))")
	public void PointcutSave() {}

	/**
	 * 
	 * @param joinPoint
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@AfterReturning(pointcut = "PointcutSave()", returning = "retVal")
	public Object saveLog(JoinPoint joinPoint, Object retVal) {
		Object[] args = joinPoint.getArgs();
		HttpServletRequest request = null;
		ModelMap modelMap = null;
		for (Object object : args) {
			if (object instanceof ModelMap) {
				modelMap = (ModelMap) object;
			}
			if (object instanceof HttpServletRequest) {
				request = (HttpServletRequest) object;
				break;
			}
		}
		try {
			if (request == null) {
				return retVal;
			} else {
				if(linkUrlAndDesc == null){
					linkUrlAndDesc=this.queryForMap("Log.queryLinkUrlAndDesc", null, "LINK_URL", "LINK_DESC");
				}
				String methodName = Util.toString(request.getParameter("method"));
				UserInfo user = (UserInfo)request.getSession().getAttribute(
						BaseAction.USER_INFO_KEY);
				if(user != null){
					String actionUrl=request.getRequestURI()+"?method="+methodName;
					if(linkUrlAndDesc.containsKey(actionUrl)){
						Map<String,Object> param = Util.getNewMap();
						param.put("logId", Util.dateToString(""));
						param.put("userId", user.getUserId());
						param.put("userName", user.getUserName());
						param.put("logDesc", linkUrlAndDesc.get(actionUrl));
						this.insert("Log.insertSysLog", param);
					}
					log.debug("===="+actionUrl);
				}
				
			}
		} catch (Throwable e) {
			log.error("", e);
		}
		return retVal;
	}
}
