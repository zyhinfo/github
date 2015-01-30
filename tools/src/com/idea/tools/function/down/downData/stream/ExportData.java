/*******************************************************************************
 * file name:ExportData.java<br>
 * version: 1.0<br>
 * creator: zhangyh <br>
 * create date: Sep 6, 2012 <br>
 ******************************************************************************/

package com.idea.tools.function.down.downData.stream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.tools.zip.ZipOutputStream;

import com.idea.tools.function.dbf.DBFField;
import com.idea.tools.function.dbf.DBFWriter;
import com.idea.tools.util.StringSubUtils;

/**
 * @item DPP
 * @author zhangyh
 */
public class ExportData {
	private ZipOutputStream zipOut;
	private DataOutputStream outStream;
	private DBFWriter writer;
	private List<Map<String,Object>> data=null;   //要导出的数据
	private List<String> attrsName=null;	//导出字段
	private List<String> attrsDesc=null;	//导出字段描述
	private List<String> attrsLen=null;	//导出字段长度
	private int count=0;  //当前导出数据条数
	//用于判断 每多少条数据向文件输出一次
	private final int flushNum=100;  
	public ExportData(){}
	
	/**
	 * 
	 * @param attrsName 导出字段(为空全导出)
	 * @param attrDesc 导出字段描述
	 */
	public void setTitle(List<String> attrsDesc,List<String> attrsName
			,List<String> attrsLen){
		this.attrsName=attrsName;
		this.attrsDesc=attrsDesc;
		this.attrsLen=attrsLen;
	}
	
	/**
	 * 设置标题显示字段和中文名称
	 * @param attrDesc
	 * @param attrName
	 * @param attrLen
	 */
	public void setTitle(String attrDesc,String attrName,String attrLen){
		if(this.attrsName == null){
			this.attrsName=new ArrayList<String>();
			this.attrsDesc=new ArrayList<String>();
			this.attrsLen=new ArrayList<String>();
		}
		this.attrsName.add(attrName);
		this.attrsDesc.add(attrDesc);
		this.attrsLen.add(attrLen);
	}
	/**
	 * 
	 * 下载数据
	 * @param fileType 文件类型
	 * @param splitStr txt分割符
	 */
	public void startDown(String fileType,String splitStr){
		exportAttrs();
		try{
			if(".csv".equals(fileType)){
				this.exportDataToCsvFile();
			}else if(".txt".equals(fileType)){
				this.exportDataToTxtFile(splitStr);
			}else if(".dbf".equals(fileType)){
				this.exportDataToDbfFile();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 导出TXT
	 * @param splitStr导出数据的分隔符
	 * 默认制表符
	 */
	public void exportDataToTxtFile(String splitStr) throws IOException{
		StringBuffer sb=new StringBuffer("");
		Map<String,Object> params=null;
		if(splitStr==null||"".equals(splitStr)){
			splitStr="\t";
		}
		if(attrsDesc!=null&&count==0){
			for(int i=0;i<attrsDesc.size();i++){
				if(i==attrsDesc.size()-1){
					sb.append(attrsDesc.get(i)).append("\r\n");
				}else{
					sb.append(attrsDesc.get(i)).append(splitStr);
				}
			}
		}
		for(int i=0;i<data.size();i++){
			params=data.get(i);
			Object obj=null;
			for(int j=0;j<attrsName.size();j++){
				obj=params.get(attrsName.get(j));
				if(j==attrsName.size()-1){
					sb.append(obj==null || obj.toString().length()==0 ? "" : obj).append("\r\n");
				}else{
					sb.append(obj==null || obj.toString().length()==0 ? "" : obj).append(splitStr);
				}
			}
			if(i==data.size()-1){
				byte[] b = sb.toString().getBytes();
				zipOut.write(b);
				zipOut.flush();
			}else if((i+1) % 19 == 0){
				byte[] b = sb.toString().getBytes();
				zipOut.write(b);
				sb=new StringBuffer("");
			}
			if(count % flushNum == 0)
				zipOut.flush();
			count++;
		}
	}
	/**
	 * 导出DBF
	 */
	public void exportDataToDbfFile() throws IOException{
		Map<String,Object> params=null;
		Object[] obj=null;
		DBFField[] fields = new DBFField[attrsName.size()];
		if(count==0){//设置title
			if(attrsDesc!=null){
				for(int i=0;i<attrsName.size();i++){
					fields[i] = new DBFField();
					String name = attrsDesc.get(i);
					if(StringSubUtils.length(name) > 10){
						name = StringSubUtils.substring(name, 10, "");
					}
					fields[i].setName(name);
					fields[i].setDataType(DBFField.FIELD_TYPE_C);
					int length=200; //默认长度
					if(attrsLen!=null){
						length = Integer.valueOf(attrsLen.get(i));
					}
					fields[i].setFieldLength(length);
				}
			}else if (attrsDesc==null){
				for(int i=0;i<attrsName.size();i++){
					fields[i] = new DBFField();
					fields[i].setName("字段_"+(i+1));
					fields[i].setDataType(DBFField.FIELD_TYPE_C);
					fields[i].setFieldLength(200);
				}
			}
			writer.setFields(fields);
		}
		writer.writeHeader(outStream);
		for(int i=0;i<data.size();i++){
			params=data.get(i);
			obj= new Object[attrsName.size()];
			Object val=null;
			for(int j=0;j<attrsName.size();j++){
				val= params.get(attrsName.get(j));
				obj[j] = val==null ? "" : val+"";
			}
			writer.addRecord(obj,outStream);
			count++;
		}
		
	}
	/**
	 * 导出CSV
	 */
	public void exportDataToCsvFile() throws IOException{
		StringBuffer sb=new StringBuffer("");
		if(attrsDesc!=null&&count==0){
			for(int i=0;i<attrsDesc.size();i++){
				sb.append("\"").append(attrsDesc.get(i)).append("\",");
				if(i==attrsDesc.size()-1){
					sb.append("\n");
				}
			}
		}
		Map<String,Object> params=null;
		for(int i=0;i<data.size();i++){
			params=data.get(i);
			Object obj=null;
			for(int j=0;j<attrsName.size();j++){
				obj=handleData(params.get(attrsName.get(j)));
				sb.append("\"").append(obj==null || obj.toString().length()==0 ? "" : obj).append("\",");
				if(j==attrsName.size()-1){
					sb.append("\n");
				}
			}
			if(i==data.size()-1){
				byte[] b = sb.toString().getBytes();
				zipOut.write(b);
				zipOut.flush();
			}else if((i+1) % 19 == 0){
				byte[] b = sb.toString().getBytes();
				zipOut.write(b);
				sb=new StringBuffer("");
			}
			if(count % flushNum == 0)
				zipOut.flush();
			count++;
		}
	}
	/**
	 * 设置导出字段
	 * @param splitStr 分隔符
	 * @return
	 */
	public void exportAttrs(){
		if(attrsName==null){
			Map<String,Object> attr=data.get(0);
			attrsName=new ArrayList<String>();
			for(String s:attr.keySet()){
				attrsName.add(s);
			}
		}
	}
	/**
	 * 数据处理
	 * @param obj
	 * @return
	 */
	public Object handleData(Object obj){
		if(obj!=null&&!"".equals(obj)){
			String str=obj.toString();
			Pattern p=null;
			//对1-1类型的文本进行处理
			if(str.indexOf("-")!=-1){
				p=Pattern.compile("^[0-9]+-[0-9]+$");
				if(p.matcher(str).find()){
					return "'"+str;
				}
			}
			//对纯数字增加"'"
			if(str.length()>7){
				if(str.indexOf(".")!=-1){
					p=Pattern.compile("^[0-9]*.[0-9]*$");
				}else{
					p=Pattern.compile("^[0-9]*$");
				}
				if(p.matcher(str).find()){
					return "'"+str;
				}
			}
		}
		
		return obj;
	}
	
	public List<Map<String, Object>> getData() {
		return data;
	}
	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
	public List<String> getAttrsName() {
		return attrsName;
	}
	public void setAttrsName(List<String> attrsName) {
		if(this.attrsName==null)
		this.attrsName = attrsName;
	}
	public List<String> getAttrsDesc() {
		return attrsDesc;
	}
	public void setAttrsDesc(List<String> attrsDesc) {
		if(this.attrsDesc==null)
		this.attrsDesc = attrsDesc;
	}
	public void setZipOut(ZipOutputStream zipOut) {
		this.zipOut = zipOut;
	}
	public void setOutStream(DataOutputStream outStream) {
		this.outStream = outStream;
	}
	public void setWriter(DBFWriter writer) {
		this.writer = writer;
	}
	
}
