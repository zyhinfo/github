package com.idea.tools.function.read.read;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.idea.tools.function.dbf.DBFReader;
import com.idea.tools.port.read.ReadBatchFile;
import com.idea.tools.port.read.ReadBean;
import com.idea.tools.util.Util;


/**
 * @author zhangyh
 * 分批次读取DBF
 */
public class FileReadDbf implements ReadBatchFile{
	private ReadBean bean=null;
	private InputStream inputStream = null;
	private DBFReader dbfreader = null;
	private int curLinNum = 0;
	private int columns = 0;
	Map<String,String> reMap=null;
	private int RETURN_NUM=100;//每次读取的条数
	public FileReadDbf(ReadBean bean){
		this.bean=bean;
		this.RETURN_NUM=bean.getRETURN_NUM();
		String filePath = bean.getFilePath();
		try{
			inputStream  = new FileInputStream(filePath);
			dbfreader = new DBFReader(inputStream);
			columns = dbfreader.getFieldCount();
		}catch(Exception e){}
	}
	/**
	 * 文件读取
	 */
	public List<List<String>> readFile() {
		List<List<String>> lists = new ArrayList<List<String>>();
		try {
			Object[] rowObjects;
			int rownum=0;
			while(dbfreader!=null&&(rowObjects = dbfreader.nextRecord()) != null) {
				if ((curLinNum++) < bean.getStartRowNum()) {
					continue;
				}
				List<String> list = new ArrayList<String>(columns);
				//按指定导入顺序导入
				for( int b=0; b<columns; b++) {
			    	list.add(Util.cleanString(rowObjects[b]));
				}
				if(list.size() > 0) lists.add(list);
				if(RETURN_NUM==++rownum){
					break;
				}
			}
		} catch (Exception e) {
			lists.clear();
			e.printStackTrace();
		} finally {}
		return lists;
	}
	/**
	 * 关闭输出流
	 */
	public void closeAll() {
		try {
			if(inputStream != null) inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
