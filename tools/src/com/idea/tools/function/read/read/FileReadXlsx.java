package com.idea.tools.function.read.read;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.idea.tools.function.read.util.UtilMethod;
import com.idea.tools.port.read.ReadBatchFile;
import com.idea.tools.port.read.ReadBean;
import com.idea.tools.util.Util;

/**
 * @author zhangyh
 * 分批次读取XLSX
 */
public class FileReadXlsx implements ReadBatchFile{
	private ReadBean bean=null;
	private InputStream is = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheets=null;
	private int rows=0;
	private int columns=0;
	private int curLinNum=0;//当前所读行
	private int RETURN_NUM=100;//每次读取的条数
	public FileReadXlsx(ReadBean bean){
		this.bean=bean;
		this.RETURN_NUM=bean.getRETURN_NUM();
		String filePath = bean.getFilePath();
		try{
			is = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(OPCPackage.open(filePath));
			sheets = workbook.getSheetAt(0);
			rows = sheets.getLastRowNum()+1;
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
				if(rownum++>RETURN_NUM||++curLinNum>rows){
					break;
				}
				if ((curLinNum++) < bean.getStartRowNum()-1) {
					continue;
				}
				XSSFRow rowStr=sheets.getRow(curLinNum-1);
				if(rowStr==null) break;
				for (int column = 0; column < columns; column++) {
					XSSFCell cell=rowStr.getCell(column);
					String str=UtilMethod.convertCell(cell);
					list.add(Util.cleanString(str));
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
