/*******************************************************************************
 * file name:Test.java<br>
 * version: 1.0<br>
 * creator: zhangyh <br>
 * create date: Sep 11, 2012 <br>
 ******************************************************************************/

package com.idea.tools.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idea.tools.factory.ToolsFactory;
import com.idea.tools.function.down.downData.stream.BatchSql;
import com.idea.tools.function.exception.Type;
import com.idea.tools.port.down.downData.spool.Spool;
import com.idea.tools.port.down.downData.stream.DownData;
import com.idea.tools.port.read.ReadBatchFile;
import com.idea.tools.port.read.ReadBean;
import com.idea.tools.port.read.Sqlldr;
import com.idea.tools.port.upload.Upload;

/**
 * @item DPP
 * @author zhangyh
 */
public class Test {
	/**
	 * 准备数据
	 * @return
	 */
	public static List<Map<String,Object>> initData(){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> par=null;
		for(int i=0;i<100;i++){
			par=new LinkedHashMap<String, Object>();
			par.put("NAME", "张彦辉"+i);
			par.put("ADDR", "地址"+i);
			par.put("AGE", "24"+i);
			par.put("POST", "100100"+i);
			par.put("DIST", "233233"+i);
			par.put("TEL", "18611912666"+i);
			list.add(par);
		}
		return list;
	}
	
	/**
	 * 数据下载1
	 */
	public static void test2_1() {
		DownData down=ToolsFactory.newDownData("d:/zyh/", "test", "TXT");
		List data=initData();
//		
//		List<String> attrName=new ArrayList<String>();
//		List<String> attrDesc=new ArrayList<String>();
//		attrName.add("NAME");
//		attrName.add("AGE");
//		attrDesc.add("姓名");
//		attrDesc.add("年龄");
		//标题设置
//		down.setTitle(attrName, attrDesc);
		
		down.downloadToServer(data);
		down.closeAll();
	}
	/**
	 * 数据下载2
	 */
	public static void test2(HttpServletRequest request,HttpServletResponse response) {
		DownData down=ToolsFactory.newDownData("d:/zyh/", "test", "TXT");
		//第一种写法
		downloadToServerBatch("sql");
		//第二种写法
		down.downloadToServer(new ArrayList<Map<String,Object>>());
		down.downloadToServer(new ArrayList<Map<String,Object>>());
		down.closeAll();
		//将数据下载到客户端
		ToolsFactory.newDownFile(down.getFilePath(),down.getFileName()).downloadToHome(request,response);
	}
	/**
	 * 异常管理
	 */
	public static void test3() {
		try{
			int num = 1 / 0;
		}catch(Exception e){
			ToolsFactory.newExceptionDispose().dispose(e,Type.TO_EMAIL);//指定异常处理方式
			ToolsFactory.newExceptionDispose().dispose(e);//
		}
	}
	
	/**
	 * 文件上传 
	 * 
	 */
	public static void test5(HttpServletRequest request) {
		Upload upload=ToolsFactory.newFileUpload("D:/upload/jspost/dataproc/upload/");
		Map param = upload.upload(request);
//		//得到文件参数
//		for(Iterator it = param.entrySet().iterator();it.hasNext();){
//			Entry entry = (Entry)it.next();
//			System.out.println(entry.getKey().toString()+"="+ entry.getValue().toString()+";");
//		}
	}
	
	/**
	 * 将数据保存到服务器上
	 */
	public static void test7(){
		List<Map<String,Object>> list=initData();
		long start=new Date().getTime();
		DownData down=ToolsFactory.newDownData("d:/zyh/", "test", "TXT");
		for(int i=0;i<1;i++){
			down.downloadToServer(list);
		}
		down.closeAll();
		long end=new Date().getTime();
		System.out.println("数据导出用时【"+(end-start)/1000+"】秒");
	}
	/**
	 * 线程下载
	 */
	public static void test8(){
		
	}
	/**
	 * 数据导出建议使用这种写法
	 * @param sql
	 */
	public static void downloadToServerBatch(String sql){
		DownData downData=ToolsFactory.newDownData("d:/zyh/", "test", "TXT");
		BatchSql batchSql=new BatchSql(sql);
		//设置每次从数据库中查询出的条数，默认1000
		batchSql.setBatchSize(100);
		while(batchSql.isStatus()){
			//分批次查询SQL语句
			//List data=dao.query(batchSql.getBatchSql());
			List data=initData();
			downData.downloadToServer(data);
			batchSql.setStatus(data.size());
		}
		downData.closeAll();
	}
	/**
	 * 读取文件
	 */
	public static void test9(){
		ReadBean bean=new ReadBean();
		bean.setFilePath("D://test//newFile2.dbf");
		bean.setRETURN_NUM(2);
//		bean.setIsCon("2");
		bean.setColNum(3);
		ReadBatchFile read=ToolsFactory.newFileRead().getFileContent(bean);
		int i=1;
		while(true){
			List<List<String>> list=read.readFile();
			if(list.size()==0) break;
			for(List<String> list1:list){
				System.out.print(i+"---");
				for(String s:list1){
					System.out.print(s+"	");
				}
				System.out.println();
				i++;
			}
		}
		read.closeAll();
		
	}
	/**
	 * 数据下载
	 */
	public static void test22() {
		DownData down=ToolsFactory.newDownData("d:/zyh/", "test", "DBF");
		List data=initData();
		down.downloadToServer(data);
		down.closeAll();
	}
	
	/**
	 * 调用sqlldr
	 * @param list
	 */
	public static void test25(){
		Sqlldr ldr = ToolsFactory.newSqlldr("D:/media/test.txt", "TB_ADR_BLDGRSDNS_NEW");
		ldr.setDB("10.154.0.226:1521/sdtestdb", "sdnais_test", "sdnais_test");
		ldr.setPath("D:/media/jspost/");
		ldr.setAttrNames("ID,RSDNBLDG_ID,DIST_CD,RSDNBLDG_NAME");
//		ldr.setSkip(1);
//		ldr.setType("INSERT");
		
//		ldr.setSplit(",");
		System.out.println(ldr.exec());
	}
	public static void test26(){
		String sql = "select org_cd||'|'||org_name from t05_int_org";
		Spool spool = ToolsFactory.newSpool("/media/jspost/", "t.txt", sql);
		spool.setDB("10.154.0.226:15211/sdtestdb", "sdnais_test", "sdnais_test");
		spool.exec();
	}
	public static void main(String[] args) {
		
		
	}
	


	
	
	 

}
