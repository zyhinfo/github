/*******************************************************************************
 * file name:DownDataFunction.java<br>
 * version: 1.0<br>
 * creator: zhangyh <br>
 * create date: Sep 5, 2012 <br>
 ******************************************************************************/

package com.idea.tools.function.down.downData.stream;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.idea.tools.function.dbf.DBFWriter;
import com.idea.tools.port.down.downData.stream.DownData;

/**
 * @item DPP
 * @author zhangyh 下载数据
 */
public class DownDataImpl implements DownData {
	private String fileName = ""; // 文件名称 name
	private String fileType = ""; // 文件类型 .txt
	private String filePath = ""; // 文件路径
	private String fileSplit = ""; //TXT文件分割符
	private String downFilePath = ""; // 最终下载路径
	private String entryFileName = ""; // 完整文件名 name.txt
	
	private FileOutputStream out = null;
	private ZipOutputStream zipOut = null;
	private DataOutputStream outStream = null;
	private final ExportData exportData = new ExportData();
	private DBFWriter writer = null;
	private int count = 0;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getFileName() {
		return fileName + ".zip";
	}

	/**
	 * 创建数据导出对象
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @param fileType 文件类型
	 */
	public DownDataImpl(String filePath, String fileName, String fileType) {
		createObjectInit(filePath, fileName, fileType, "");
	}
	
	/**
	 * 创建数据导出对象
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @param fileType 文件类型
	 * @param split TXT文件分割符默认tab
	 */
	public DownDataImpl(String filePath, String fileName, String fileType, String split) {
		createObjectInit(filePath, fileName, fileType, split);
	}
	/**
	 * 对象实例化时调用
	 * @param filePath
	 * @param fileName
	 * @param fileType
	 * @param split
	 */
	public void createObjectInit(String filePath, String fileName, String fileType, String split){
		if ("XLS".equals(fileType.toUpperCase())) {
			this.fileType = ".csv";
		} else if ("DBF".equals(fileType.toUpperCase())) {
			this.fileType = ".dbf";
		} else {
			this.fileType = ".txt";
		}
		if (!filePath.endsWith("/") && !filePath.endsWith("\\")) {
			filePath += "/";
		}
		this.fileName = fileName;
		this.filePath = filePath;
		if(split != null)this.fileSplit = split;	
		File Dir = new File(filePath);
		if (!Dir.exists()) {
			Dir.mkdirs();
		}
		initDataDown();
	}
	
	/**
	 * 下载数据初始化方法
	 */
	public void initDataDown() {
		downFilePath = filePath + fileName + ".zip";
		entryFileName = "newFile" + fileType;
		try {
			out = new FileOutputStream(downFilePath);
			zipOut = new ZipOutputStream(out);
			ZipEntry e = new ZipEntry(entryFileName);
			zipOut.putNextEntry(e);
			if (".dbf".equals(fileType)) {
				outStream = new DataOutputStream(zipOut);
				writer = new DBFWriter(-1);
				exportData.setOutStream(outStream);
				exportData.setWriter(writer);
			} else {
				exportData.setZipOut(zipOut);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在服务器上创建文件
	 * 
	 * @param params
	 * @return
	 */
	public void downloadToServer(List<Map<String, Object>> data) {
		if (data != null && data.size() != 0 && out != null) {
			count += data.size();
			try {
				exportData.setData(data);
				exportData.startDown(fileType, fileSplit);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 设置标题显示字段和中文名称
	 * HashMap 对象不要求输出的先后顺序
	 * LinkedHashMap 按照放入的顺序导出
	 * @param title 
	 */
	public void setTitle(List<Title> list){
		List<String> attrName=new ArrayList<String>();
		List<String> attrDesc=new ArrayList<String>();
		List<String> attrLen=new ArrayList<String>();
		for(Title title : list){
			attrName.add(title.getAttrName());
			attrDesc.add(title.getTitleDesc());
			attrLen.add(title.getAttrLen());
		}
		exportData.setTitle(attrDesc, attrName,attrLen);
	}
	/**
	 * 设置标题显示字段和中文名称
	 * @param attrDesc
	 * @param attrName
	 * @param attrLen
	 */
	public void setTitle(String attrDesc,String attrName,String attrLen){
		exportData.setTitle(attrDesc, attrName,attrLen);
	}
	/**
	 * 关闭流
	 */
	public void closeAll() {
		try {
//			if (outStream != null) {
//				writer.writeEnd(outStream);
//				outStream.close();
//			}
			
			if (zipOut != null) {
				// zipOut.flush();
				// zipOut.closeEntry();
				zipOut.close();
			}
			if (out != null)
				out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
