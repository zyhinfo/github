/*******************************************************************************
 * file name:DownFileImpl.java<br>
 * version: 1.0<br>
 * creator: zhangyh <br>
 * create date: Sep 5, 2012 <br>
 ******************************************************************************/

package com.idea.tools.function.down.downFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.idea.tools.port.down.downFile.DownFile;

/**
 * @item DPP
 * @author zhangyh
 * 将文件从服务器拷贝到客户端
 */
public class DownFileImpl implements DownFile{
	private String fileName="";
	private String fullPath="";
	/**
	 * @param filePath 下载路径 d:/
	 * @param fileName 下载文件 t.txt
	 */
	public DownFileImpl(String filePath,String fileName){
		this.fileName=fileName;
		fullPath=filePath+fileName;
	}
	/**
	 * @param fullPath 下载路径 d:/test.zip
	 */
	public DownFileImpl(String fullPath){
		this.fullPath=fullPath;
		fileName=fullPath.substring(fullPath.lastIndexOf("/")+1);
	} 
	/**
	 * 将文件下载到本地
	 * @param request
	 * @param response
	 * @return
	 */
	public String downloadToHome(HttpServletRequest request,HttpServletResponse response){
		File file = new File(fullPath);
		if (file.exists()) {
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			final String userAgent = request.getHeader("USER-AGENT");
			try {
				String finalFileName = null;
	            if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器
	                finalFileName = URLEncoder.encode(fileName,"UTF8");
	            }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
	                finalFileName = new String(fileName.getBytes(), "ISO8859-1");
	            }else{
	                finalFileName = URLEncoder.encode(fileName,"UTF8");//其他浏览器
	            }
				response.setHeader("Content-disposition", "attachment; filename="+finalFileName);
			} catch (UnsupportedEncodingException e1) {
				//logger.error("编码转换失败",e1);
				e1.printStackTrace();
			}
			
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[4096];
				int bytesRead;
				int count = 0;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
					if (count % 100 == 0)
						bos.flush();
					try {
						Thread.sleep(25);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			} catch (final IOException e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					bis = null;
				}
				if (bos != null) {
					try {
						bos.flush();
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					bos = null;
				}
			}
			return "下载文件大小[" + file.length() / 1024 + "]k";
		} else {
			return "文件[" + file.getAbsolutePath() + "]不存在";
		}
	}
	
}
