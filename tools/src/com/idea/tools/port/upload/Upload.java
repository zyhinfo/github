/**
 * 
 */
package com.idea.tools.port.upload;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangyh
 *
 */
public interface Upload {
	/**
	 * 上传文件
	 * @param request(包含上传文件信息)
	 */
	public Map<String,String> upload(HttpServletRequest request);
}
