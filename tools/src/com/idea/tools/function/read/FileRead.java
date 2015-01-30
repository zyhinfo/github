package com.idea.tools.function.read;

import com.idea.tools.function.read.read.FileReadCSV;
import com.idea.tools.function.read.read.FileReadDbf;
import com.idea.tools.function.read.read.FileReadTxt;
import com.idea.tools.function.read.read.FileReadXls;
import com.idea.tools.function.read.read.FileReadXlsx;
import com.idea.tools.port.read.Read;
import com.idea.tools.port.read.ReadBatchFile;
import com.idea.tools.port.read.ReadBean;

/**
 * @item DPP
 * @author zhangyh
 */
public class FileRead implements Read{
	
	
	/**
	 * 读取文件所有行
	 * @param map 参数
	 * start_num 开始行
	 * reciprocal_num 倒数第几行结束
	 */
	public ReadBatchFile getFileContent(ReadBean bean){
		String filePath=bean.getFilePath();
		if(filePath.toUpperCase().endsWith("TXT")
				||filePath.trim().toUpperCase().endsWith("DAT")){
			return new FileReadTxt(bean);
		}if(filePath.trim().toUpperCase().endsWith("CSV")){
			return new FileReadCSV(bean);
		}else if(filePath.toUpperCase().endsWith("XLSX")){
			return new FileReadXlsx(bean);
		}else if(filePath.toUpperCase().endsWith("XLS") || filePath.trim().toUpperCase().endsWith("ET")){
			return new FileReadXls(bean);
		}else if(filePath.toUpperCase().endsWith("DBF")){
			return new FileReadDbf(bean);
		}
		
		return null;
	}
	
}
