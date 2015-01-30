/*******************************************************************************
 * file name:BaseException.java<br>
 * version: 1.0<br>
 * creator: zhangyh <br>
 * create date: Sep 9, 2012 <br>
 ******************************************************************************/

package com.idea.tools.function.exception;



/**
 * @item DPP
 * 异常处理
 * @author zhangyh
 */
public class ExceptionDispose{
	
	/**
	 * 异常处理
	 * 发邮件
	 */
	public void dispose(Exception e){
		dispose(e,Type.TO_EMAIL);
	}
	/**
	 * 异常处理
	 */
	public void dispose(Exception e,int type){
		if(Type.TO_EMAIL==type){
			toMailException(e);
		}
	}
	
	/**
	 * 将系统异常发送到邮箱
	 * @param e
	 */
	public void toMailException(Exception e){
		StringBuffer sb=new StringBuffer();
		sb.append(e.toString()).append("\n");
		for(StackTraceElement s:e.getStackTrace()){
			sb.append(s).append("\n");
		}
		String subject=e.getMessage()==null ? "错误" : e.getMessage();
		String content=sb.toString();
		//ToolsFactory.newMailMessages().sendMail(ImportantProperty.toAddr, subject, content);
	}
}
