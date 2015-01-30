package com.idea.tools.function.read.read;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.idea.tools.function.read.util.UtilMethod;
import com.idea.tools.port.read.ReadBatchFile;
import com.idea.tools.port.read.ReadBean;
import com.idea.tools.util.Util;

/**
 * @author zhangyh
 * 分批次读取XLS
 */
public class FileReadXls implements ReadBatchFile{
	private ReadBean bean=null;
	private InputStream is = null;
	private HSSFWorkbook workbook = null;
	private HSSFSheet sheets=null;
	private int rows=0;
	private int columns=0;
	private int curLinNum=0;
	private int RETURN_NUM=100;//每次读取的条数
	public FileReadXls(ReadBean bean){
		this.bean=bean;
		this.RETURN_NUM=bean.getRETURN_NUM();
		String filePath = bean.getFilePath();
		try{
			is = new FileInputStream(filePath);
			workbook = new HSSFWorkbook(is);
			sheets = workbook.getSheetAt(0);
			rows = sheets.getLastRowNum();
			if(sheets.getRow(0)!=null){
				columns=sheets.getRow(0).getLastCellNum();
			}
		}catch(Exception e){}
	}
	
	/**
	 * 文件读取
	 */
	public List<List<String>> readFile() {
		List<List<String>> lists = new ArrayList<List<String>>();
		int rownum=0;
		try {
			// 读出Excel中的数据
			while (true) {
				List<String> list = new ArrayList<String>(columns);
				if(RETURN_NUM==rownum++||++curLinNum>rows){
					break;
				}
				if ((curLinNum++) < bean.getStartRowNum()-1) {
					continue;
				}
				HSSFRow rowStr = sheets.getRow(curLinNum-1);
				if(rowStr==null) break;
				for (int column = 0; column < columns; column++) {
					HSSFCell cell = rowStr.getCell(column);
					String cellVal = UtilMethod.convertCell(cell);
					list.add(Util.cleanString(cellVal));
				}
				if(list.size() > 0){
					lists.add(list);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {}
		return lists;
	}
	/**
	 * 关闭输出流
	 */
	public void closeAll(){
		try {
			if (is != null)
				is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
