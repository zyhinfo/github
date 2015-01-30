package com.idea.modules.upload.domain;

import java.util.Map;

import com.idea.base.core.dataSource.IBatisBaseDao;
import com.idea.base.core.threadPool.BaseThread;

public class ExportThread extends BaseThread{
	Map<String,Object> param=null;
	IBatisBaseDao dao = null;
	public ExportThread(Map<String,Object> param,IBatisBaseDao dao){
		this.param = param;
		this.dao = dao;
	}
	public void run() {
		// TODO Auto-generated method stub
		try{
			super.run();
			//将文件中的数据导入数据库
			
			//修改数据状态为上传成功
			param.put("status", "1");
		}catch(Exception e){
			//修改数据状态为上传失败
			param.put("status", "-1");
		}
		//修改数据状态
		dao.update("Upload.updateDataInsStatus",param);
	}
}
