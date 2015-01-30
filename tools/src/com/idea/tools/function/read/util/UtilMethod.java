/*******************************************************************************
 * file name:UtilMethod.java<br>
 * version: 1.0<br>
 * creator: zhangyh <br>
 * create date: Sep 10, 2012 <br>
 ******************************************************************************/

package com.idea.tools.function.read.util;

import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 * @item DPP
 * 方法
 * @author zhangyh
 */
public class UtilMethod {
	
	/**
	 * 解析EXCEL CELL格式(xlsx)
	 * @param cell
	 * @return
	*/
	public static String convertCell(XSSFCell cell) {
		NumberFormat formater = NumberFormat.getInstance();
		formater.setGroupingUsed(false);
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_NUMERIC:
				cellValue = formater.format(cell.getNumericCellValue());
				break;
			case XSSFCell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				cellValue = cell.getStringCellValue();
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN:
				cellValue = Boolean.valueOf(cell.getBooleanCellValue()).toString();
				break;
			case XSSFCell.CELL_TYPE_ERROR:
				cellValue = String.valueOf(cell.getErrorCellValue());
				break;
			default:
				cellValue = "";
		}
		return cellValue.trim();
	}
	/**
	 * 解析EXCEL CELL格式(xls)
	 * @param cell
	 * @return
	*/
	public static String convertCell(HSSFCell cell) {
		NumberFormat formater = NumberFormat.getInstance();
		formater.setGroupingUsed(false);
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				cellValue = formater.format(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				cellValue = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				cellValue = Boolean.valueOf(cell.getBooleanCellValue()).toString();
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				cellValue = String.valueOf(cell.getErrorCellValue());
				break;
			default:
				cellValue = "";
		}
		return cellValue.trim();
	}
	
	/**
	 * 判断是否是日期
	 * @param dateValue
	 * @return
	 */
	public static boolean isDate(String dateValue){
		if(dateValue!=null&&!"".equals(dateValue)){
			Pattern p=Pattern.compile("^1[0-9]{3}-[0-1][0-9]-[0-1][0-9]$");
			Matcher m=p.matcher(dateValue);
			if(!m.find()){
				return false;
			}
		}
		return true;
	}
	public static boolean isTitle(List<String> row){
		int num = 0;
		for(String str: row){
			if(num < 3 && str.indexOf("姓名") != -1){
				num++;
			}else if(num < 3 && str.indexOf("地址") != -1){
				num++;
			}else if(num < 3 && str.indexOf("邮编") != -1){
				num++;
			}else if(num < 3 && str.indexOf("邮政编码") != -1){
				num++;
			}else if(num < 3 && str.indexOf("电话") != -1){
				num++;
			}else if(num < 3 && str.indexOf("手机") != -1){
				num++;
			}else if(num < 3 && str.indexOf("身份证号") != -1){
				num++;
			}
		}
		return num > 0;
	}
}
