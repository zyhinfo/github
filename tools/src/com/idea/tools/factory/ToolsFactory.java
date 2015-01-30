/*******************************************************************************
 * file name:ToolsFactory.java<br>
 * version: 1.0<br>
 * creator: zhangyh <br>
 * create date: Sep 5, 2012 <br>
 ******************************************************************************/

package com.idea.tools.factory;

import com.idea.tools.function.down.downData.spool.SpoolDataImpl;
import com.idea.tools.function.down.downData.stream.DownDataImpl;
import com.idea.tools.function.down.downFile.DownFileImpl;
import com.idea.tools.function.exception.ExceptionDispose;
import com.idea.tools.function.image.OperateImage;
import com.idea.tools.function.read.FileRead;
import com.idea.tools.function.read.sqlldr.CreateSqlldr;
import com.idea.tools.function.upload.FileUpload;
import com.idea.tools.port.down.downData.spool.Spool;
import com.idea.tools.port.down.downData.stream.DownData;
import com.idea.tools.port.down.downFile.DownFile;
import com.idea.tools.port.image.Image;
import com.idea.tools.port.read.Read;
import com.idea.tools.port.read.Sqlldr;
import com.idea.tools.port.upload.Upload;

/**
 * @item DPP
 * @author zhangyh
 */
public class ToolsFactory {
	private static ExceptionDispose exceptionDispose=null;
	/**
	 * 文件下载
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @return
	 */
	public static DownFile newDownFile(String filePath,String fileName){
		return new DownFileImpl(filePath,fileName);
	}
	/**
	 * 文件下载
	 * @param fullPath 完整文件路径
	 * @return
	 */
	public static DownFile newDownFile(String fullPath){
		return new DownFileImpl(fullPath);
	}
	/**
	 * 数据下载
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @param fileType 文件类型(TXT、XLS、DBF)
	 * @return
	 */
	public static DownData newDownData(String filePath,String fileName,String fileType){
		return new DownDataImpl(filePath,fileName,fileType);
	}
	
	/**
	 * 数据下载
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @param fileType 文件类型(TXT、XLS、DBF)
	 * @param split TXT文件分割符
	 * @return
	 */
	public static DownData newDownData(String filePath,String fileName,String fileType,String split){
		return new DownDataImpl(filePath,fileName,fileType,split);
	}
	/**
	 * 数据上传
	 * @param path 文件要保存的路径
	 * @return
	 */
	public static Upload newFileUpload(String path){
		return new FileUpload(path);
	}
	
	/**
	 * 异常管理
	 * @return
	 */
	public static ExceptionDispose newExceptionDispose(){
		if(exceptionDispose==null){
			exceptionDispose=new ExceptionDispose();
		}
		return exceptionDispose;
	}
	
	/**
	 * 文件读取
	 * @return
	 */
	public static Read newFileRead(){
		return new FileRead();
	}
	
	
	/**
	 * 调用sqlldr
	 * @param filePath
	 * @param tableName
	 * @return
	 */
	public static Sqlldr newSqlldr(String filePath,String tableName){
		return new CreateSqlldr(filePath,tableName);
	}
	
	/**
	 * 调用spool
	 * @param path
	 * @param outFileName
	 * @param sql
	 * @return
	 */
	public static Spool newSpool(String path,String outFileName,String sql){
		return new SpoolDataImpl(path,outFileName,sql);
	}
	
	/**
	 * 图片处理
	 * @return
	 */
	public static Image newImage(){
		return new OperateImage();
	}
}
