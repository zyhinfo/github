package com.idea.modules.upload.domain;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.object.BatchSqlUpdate;

import com.idea.base.core.threadPool.BaseThread;
import com.idea.modules.upload.services.UploadService;
import com.idea.tools.factory.ToolsFactory;
import com.idea.tools.port.read.ReadBatchFile;
import com.idea.tools.port.read.ReadBean;
import com.idea.tools.util.Util;

public class UploadThread extends BaseThread{
	Map<String,Object> param=null;
	UploadService dao = null;
	String insertSql = "";
	List<Integer> readRowNum = null;
	int[] types = null;
	int isTitle = 0;  //是否标题  0=没有，1有
	public UploadThread(Map<String,Object> param,UploadService dao){
		this.param = param;
		insertSql = Util.toString(param.get("insertSql"));
		readRowNum = (List<Integer>)param.get("readRowNum");
		types = (int[])param.get("types");
		this.dao = dao;
	}
	public void run() {
		// TODO Auto-generated method stub
		try{
			super.run();
			String filePath = Util.toString(param.get("filePath"));
			if(Util.isNotEmpty(filePath)){
				//将文件中的数据导入数据库
				ReadBean bean=new ReadBean();
				bean.setFilePath(filePath);
				bean.setRETURN_NUM(Util.toInteger(param.get("readCount"),100));
				bean.setSplits(Util.toString(param.get("textSplit")));
				ReadBatchFile read=ToolsFactory.newFileRead().getFileContent(bean);
				
				DataSource ds = dao.getJdbcService().getDataSource();
				BatchSqlUpdate bsu = new BatchSqlUpdate(ds, insertSql);
				bsu.setBatchSize(100);
				bsu.setTypes(types);
				//修改数据状态为上传成功
				int rowNum = 0;
				while(true){
					List<List<String>> list=read.readFile();
					if(list.size()==0) break;
					for(List<String> rows:list){
						if(rowNum == 0 && isTitle(rows)) {
							isTitle = 1;
							continue;
						}
						Object[] obj= new Object[types.length];
						obj[0]=++rowNum;
						for(int i=0;i<readRowNum.size();i++){
							obj[i+1]=rows.get(readRowNum.get(i)-1);
						}
						bsu.update(obj);
						bsu.flush();
					}
				}
				read.closeAll();
				param.put("rowNum", rowNum);
			}
			param.put("status", "1");
		}catch(Exception e){
			e.printStackTrace();
			//修改数据状态为上传失败
			param.put("status", "-1");
		}
		//修改数据状态
		dao.update("Upload.updateDataInsStatus",param);
		param.put("isTitle", isTitle);
		dao.updateUploadLog(param);
	}
	public boolean isTitle(List<String> firstData){
		boolean fals = false;
		for(String s:firstData){
			if (Util.isIndexOf(s, "姓名")) {
				fals = true;
			}else if (Util.isIndexOf(s, "地址")) {
				fals = true;
			}else if (Util.isIndexOf(s, "年龄")) {
				fals = true;
			}else if (Util.isIndexOf(s, "电话")) {
				fals = true;
			}else if (Util.isIndexOf(s, "手机号")) {
				fals = true;
			}else if (Util.isIndexOf(s, "邮编")) {
				fals = true;
			}
		}
		return fals;
	}
}
