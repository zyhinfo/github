package com.idea.base.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import com.idea.tools.util.Util;

/**
 * 数据转换
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-7
 */
public class ConvertData {
	public static String toString(List<Map<String,Object>> param){
		int i=0; //当前是第几条
		Set<String> set=null; //传入参数中的所有key
		StringBuffer sb=new StringBuffer("[");
		for(Map<String,Object> map:param){
			int j=0;//当前是第几个属性
			//只在第一次循环时获取key
			if(i==0) set=map.keySet();
			else sb.append(",");
			sb.append("{");
			for(String s:set){
				Object obj = map.get(s);
				if(Util.isEmpty(obj)){
					continue;
				}
				if(j!=0) sb.append(",");
				if(Util.isEquals("ISCHECKED", s)){
					s=Util.toLowerCase(s);
					sb.append(s+":").append(obj.toString());
				}else{
					sb.append(s+":").append("'"+obj.toString()+"'");
				}
				//判断是否展开
				if(Util.isEquals(obj, "1")) sb.append(",isexpand:true");
				else sb.append(",isexpand:false");
				j++;
			}
			//设置菜单初始化为关闭状态
			sb.append("}");
			i++;
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * 注意日期类型应转成String
	 * @param list
	 * @param rowNum 总行数
	 * @return
	 */
	public static String listToJSONString(List list,int rowNum){
		if(list == null) return "{Rows:[],Total:0}";
		String jsonString = "";
		try{
			jsonString = JSONArray.fromObject(list).toString();
			if(rowNum == -1) rowNum = list.size();
		}catch(Exception e){
			jsonString = "[]";
			rowNum = 0;
		}
		String jsonStr = "{Rows:"+jsonString+",Total:"+rowNum+"}";
		System.out.println(jsonStr);
		return jsonStr;
	}
	public static String listToString(List list){
		if(list == null) return "[]";
		String jsonString = "";
		try{
			jsonString = JSONArray.fromObject(list).toString();
		}catch(Exception e){
			jsonString = "[]";
		}
		System.out.println(jsonString);
		return jsonString;
	}
	
}
