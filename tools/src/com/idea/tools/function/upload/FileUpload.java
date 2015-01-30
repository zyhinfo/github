package com.idea.tools.function.upload;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.idea.tools.port.upload.Upload;

/**
 * @author yanghl
 * @since Nov 22, 2010
 * @version 2.0 
 */
public class FileUpload implements Upload{
	private String FILE_UPLOAD_PATH = "";
	
	/**
	 * 上传文件
	 * @param request(包含上传文件信息)
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> upload(HttpServletRequest request) {
		HashMap<String,String> paramMap = new HashMap<String,String>();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(5*1024);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding(request.getCharacterEncoding());
			List<FileItem> fileItems = upload.parseRequest(request);
			Pattern p = Pattern.compile(".+\\\\(.+)$");
			for(FileItem item:fileItems){
				if (!item.isFormField()) {
					String name = item.getName();
					long size = item.getSize();
					if ((name == null || name.equals("")) && size == 0) continue;
					Matcher m = p.matcher(name);
					try {
						if (m.find()) {
							name = m.group(1);
						}
						String fileType = name.substring(name.lastIndexOf(".")+1);
						String fileName = name.substring(0,name.lastIndexOf("."));
						//对上传文件名称进行修改
						long rannum=System.currentTimeMillis()%100000;
						name = fileName+"_"+rannum+"."+fileType;
						
						paramMap.put("fileName", name);
						paramMap.put("fileType", fileType.toUpperCase());
						File file = new File(FILE_UPLOAD_PATH);
						if(!file.exists()) file.mkdirs();
						item.write(new File(FILE_UPLOAD_PATH + name));
						paramMap.put("filePath", FILE_UPLOAD_PATH + name);
						paramMap.put("fileSize", (size / 1024 + 1)+""); //kb
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					try {
						paramMap.put(item.getFieldName(), new String(item.getString().getBytes("iso8859-1"),"utf-8"));
					} catch (UnsupportedEncodingException e) {}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return paramMap;
	}
	public FileUpload(String filePath){
		this.FILE_UPLOAD_PATH = filePath;
	}
}
