package com.idea.tools.function.read.read;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.idea.tools.port.read.ReadBatchFile;
import com.idea.tools.port.read.ReadBean;
import com.idea.tools.util.Util;

/**
 * @author zhangyh 
 * 分批次读取TXT和CSV
 */
public class FileReadCSV implements ReadBatchFile {
	private ReadBean bean = null;
	private int RETURN_NUM = 1000;// 每次读取的条数
	private BufferedReader lineReader = null;
	private String filePath = "";
	private String split = ",";
	private int length = 0;
	private int curLinNum = 0;

	public FileReadCSV(ReadBean bean) {
		this.bean = bean;
		this.RETURN_NUM = bean.getRETURN_NUM();
		filePath = bean.getFilePath();

		try {
			lineReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath),
					com.idea.tools.util.UtilMethod.getCode(filePath)));
		} catch (Exception e) {}
	}
	/**
	 * 文件读取
	 */
	public List<List<String>> readFile() {
		List<List<String>> lists = new ArrayList<List<String>>();
		try {
			while (true) {
				List<String> list = new ArrayList<String>(length);
				String str = lineReader.readLine();
				if (Util.isEmpty(str)) break;
				if ((curLinNum++) < bean.getStartRowNum()) {
					str = lineReader.readLine();
				}
				if (!str.endsWith(split)) str += split;
				for(String col: str.split(split)){
					list.add(col);
				}
				if (list.size() > 0) {
					lists.add(list);
				}
				if (lists.size() == RETURN_NUM) {
					break;
				}
			}
			} catch (Exception e) {
				lists.clear();
				e.printStackTrace();
			} finally {
		}
		return lists;
	}
	
	/**
	 * 关闭输出流
	 */
	public void closeAll() {
		try {
			if (lineReader != null)
				lineReader.close();
		} catch (Exception e) {
		}
	}
	
}
