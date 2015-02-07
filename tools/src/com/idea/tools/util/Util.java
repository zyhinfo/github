package com.idea.tools.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对基本的操作进行封装
 * @author zhangyh
 *
 */
public class Util {
	/**
	 * 判断字符串是否为空
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Object value){
		if(value == null || "".equals(value.toString())){
			return true;
		}
		return false;
	}
	public static String toString(Object value){
		if(value == null){
			return "";
		}
		return value.toString().trim();
	}
	public static Integer toInteger(Object value,int defaultCount){
		if(value != null){
			try{
				return Integer.parseInt(Util.toString(value));
			}catch(NumberFormatException e){}
		}
		return defaultCount;
	}
	public static int getSize(List list){
		if(list == null) return 0;
		return list.size();
	}
	/**
	 * 判断字符串是否为空
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(Object value){
		return !isEmpty(value);
	}
	/**
	 * obj 中是否包含str字符
	 * @param obj
	 * @param str
	 * @return
	 */
	public static boolean isHave(Object obj,String str){
		if(obj != null){
			if(obj.toString().indexOf(str)!=-1){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 截取字符串 <br>
	 * 截取从开始到结束字符的字符串
	 * @param obj 原字符串
	 * @param endStr 结束字符
	 * @return
	 */
	public static String substring(Object obj,String endStr){
		if(obj != null){
			String val=obj.toString();
			if(val.indexOf(endStr)!=-1){
				return val.substring(0,val.indexOf(endStr));
			}
		}
		return "";
	}
	public static int indexOf(Object obj,String str){
		if(obj != null){
			String val=obj.toString();
			if(val.indexOf(str)!=-1){
				return val.indexOf(str);
			}
		}
		return 0;
	}
	public static boolean isIndexOf(Object obj,String str){
		if(obj != null){
			if(obj.toString().indexOf(str)!=-1){
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断俩个字符是否相等
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static boolean isEquals(Object obj,String value2){
		if(obj != null && obj.toString().equals(value2)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否sou字符串是否以suffix结尾
	 * @param sou 原字符串
	 * @param suffix 要查找的字符串
	 * @return
	 */
	public static boolean isEndsWith(Object sou,String suffix){
		if(!isEmpty(sou) && sou.toString().endsWith(suffix)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否sou字符串是否以suffix开始
	 * @param sou 原字符串
	 * @param suffix 要查找的字符串
	 * @return
	 */
	public static boolean isStartsWith(Object sou,String suffix){
		if(!isEmpty(sou) && sou.toString().startsWith(suffix)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param sou 原字符串
	 * @param regex
	 * @return
	 */
	public static String[] split(Object sou,String regex){
		if(!isEmpty(sou)){
			return sou.toString().split(regex);
		}
		return null;
	}
	
	/**
	 *  将字符串转成大写
	 * @param str
	 * @return
	 */
	public static String toUpperCase(Object value){
		return toString(value).toUpperCase();
	}
	/**
	 *  将字符串转成小写
	 * @param str
	 * @return
	 */
	public static String toLowerCase(String str){
		if(isEmpty(str)) return "";
		return str.trim().toLowerCase();
	}
	public static String dateToString(String format){
		if(Util.isEmpty(format))format="yyyyMMddHHmmssSSS";
		SimpleDateFormat sd = new SimpleDateFormat(format);
		return sd.format(new Date());
		
	}
	public static Map<String,Object> getNewMap(){
		return new HashMap<String,Object>();
	}
	public static String cleanString(Object obj){
		if(isNotEmpty(obj)){
			return obj.toString().replaceAll(" ", "").replaceAll("\t","")
	    			.replaceAll("\n","").replaceAll("[.]0$","");
		}
		return "";
	}
	
}
