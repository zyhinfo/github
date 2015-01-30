/*******************************************************************************
 * file name:UtilMethod.java<br>
 * version: 1.0<br>
 * creator: zhangyh <br>
 * create date: Sep 10, 2012 <br>
 ******************************************************************************/

package com.idea.tools.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

/**
 * @item DPP 方法
 * @author zhangyh
 */
public class UtilMethod {
	/**
	 * 判断当前是否连接网络
	 * 
	 * @return
	 */
	public static boolean isNetWorking() {
		URL url = null;
		try {
			url = new URL("http://www.baidu.com/");
			InputStream in = url.openStream();
			in.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * 
	 * @Description 读取txt文件编码
	 * @author zhangyh
	 * @param {filePath:文件路径}
	 * @return
	 * @version 1.0
	 */
	public static String getCode(String filePath) {
		FileInputStream fis = null;
		String txtCode = "";
		try {
			fis = new FileInputStream(filePath);
			int a = fis.read();
			int b = fis.read();
			if (a == 0xFF && b == 0xFE) {
				txtCode = "Unicode";
			} else if (a == 0xFE && b == 0xFF) {
				txtCode = "UTF-16BE";
			} else if (a == 0xEF && b == 0xBB) {
				txtCode = "UTF-8";
			} else {
				txtCode = "GBK";
			}
			if(a == 229 && b == 167) txtCode = "UTF-8";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("=============" + txtCode);
		return txtCode;
	}
	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public static String getRemoteHost(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		return ip.startsWith("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

}
