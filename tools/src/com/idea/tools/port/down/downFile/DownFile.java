/**
 * 
 */
package com.idea.tools.port.down.downFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public interface DownFile {
	
	/**
	 * 将文件下载到本地
	 * @param response
	 * @return
	 */
	public String downloadToHome(HttpServletRequest request,HttpServletResponse response);
}
