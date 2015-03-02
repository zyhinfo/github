package com.idea.modules.down.domain;

import java.util.List;
import java.util.Map;

import com.idea.base.core.threadPool.BaseThread;
import com.idea.modules.down.init.InitDown;
import com.idea.modules.down.services.DownService;
import com.idea.tools.factory.ToolsFactory;
import com.idea.tools.function.down.downData.stream.BatchSql;
import com.idea.tools.port.down.downData.stream.DownData;
import com.idea.tools.util.Util;

public class DownThread extends BaseThread {
	private InitDown init;
	private DownService dao;
	private BatchSql batchSql = null;
	private int sizeNum = 0;
	private int plan = -1;
	public DownThread(InitDown init,DownService dao){
		this.init = init;
		this.dao = dao;
		batchSql = new BatchSql(init.getSql());
		sizeNum = init.getDownAllCount();
	}
	public void run() {
		// TODO Auto-generated method stub
		DownData down=ToolsFactory.newDownData(init.getDownPath(), init.getFileName(), init.getFileType());
		Map<String, Object> param = Util.getNewMap();
		param.put("downId", init.getDownId());
		//down.setTitle(title);
		while (batchSql.isStatus()) {
			// 分批次查询SQL语句
			List data = dao.getJdbcService().getJdbcTemplate().queryForList(batchSql.getBatchSql());
			down.downloadToServer(data);
			batchSql.setStatus(data.size());
			int countNow = batchSql.getCountNow();
			if (sizeNum != 0 && (countNow * 100) / sizeNum != plan) {
				plan = (countNow * 100) / sizeNum;
				if (plan > 100) plan = 100;
				param.put("progress", plan);
				dao.updateDownProgress(param);
			}
		}
		down.closeAll();
	}
	
}
