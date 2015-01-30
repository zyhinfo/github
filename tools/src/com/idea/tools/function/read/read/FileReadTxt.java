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
public class FileReadTxt implements ReadBatchFile {
	private ReadBean bean = null;
	private int RETURN_NUM = 1000;// 每次读取的条数
	private BufferedReader lineReader = null;
	private String filePath = "";
	private String split = "";
	private int curLinNum = 0;
	
	//temp param
	private String secondRow = "";

	public FileReadTxt(ReadBean bean) {
		this.bean = bean;
		this.RETURN_NUM = bean.getRETURN_NUM();
		filePath = bean.getFilePath();
		try {
			lineReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath),
					com.idea.tools.util.UtilMethod.getCode(filePath)));
		} catch (Exception e) {
		}
	}
	/**
	 * 文件读取
	 */
	public List<List<String>> readFile() {
		List<List<String>> lists = new ArrayList<List<String>>();
		try {
			while (true) {
				String str = lineReader.readLine();
				if ((curLinNum++) < bean.getStartRowNum()) {
					str = lineReader.readLine();
				}
				//自动设置分隔符 只执行一次
				if (Util.isNotEmpty(str) && Util.isEmpty(split)) {
					secondRow = lineReader.readLine();
					String splits = bean.getSplits();
					if(Util.isNotEmpty(splits)){
						String[] splitChar = Util.split(splits,"/");
						for(int i=0;i<splitChar.length;i++){
							int firstNum = Util.split(str,splitChar[i]).length;
							if(Util.isNotEmpty(secondRow)){
								int secondNum = Util.split(secondRow+" ",splitChar[i]).length;
								if(firstNum != 1 && firstNum == secondNum){
									split = splitChar[i];
									break;
								}
							}else{
								if(firstNum != 1){
									split = splitChar[i];
									break;
								}
							}
						}
					}
				}
				List<String> list = splitForList(str);
				if (list != null && list.size() > 0) {
					lists.add(list);
					if(Util.isNotEmpty(secondRow)){
						List<String> secondList = splitForList(secondRow);
						if (secondList != null && secondList.size() > 0){
							lists.add(secondList);
						}
						secondRow = "";
					}
				}else{
					break;
				}
				if (lists.size() == RETURN_NUM) break;
			}
		} catch (Exception e) {
			lists.clear();
			e.printStackTrace();
		} finally {
		}
		return lists;
	}
	public List<String> splitForList(String str){
		if(Util.isNotEmpty(str)){
			List<String> list = new ArrayList<String>();
			if (Util.isEmpty(split)) split = "\t";
			String[] cols = (str+" ").split(split);
			for (int i = 0; i < cols.length; i++) {
				list.add(cols[i]);
			}
			return list;
		}
		return null;
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
