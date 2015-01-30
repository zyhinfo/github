package com.idea.modules.upload.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.idea.base.core.dao.BaseDao;
import com.idea.base.util.ConvertData;
import com.idea.tools.util.Util;

/**
 * 数据上传公共
 * @version: 1.0
 * @author : zhangyh
 * @date   : 2013-6-8
 */
@Service
public class UploadService extends BaseDao {
	private String ATTR_COL="COL_";
	public void addUploadLog(Map<String,String> param){
		this.insert("Upload.addUploadLog", param);
	}
	
	public String getTableJSON(List<List<String>> rows,List<Map<String,String>> sysAttr){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		if(rows.size() > 0){
			Map<String,Object> map = null;
			List<String> row1 = rows.get(0);
			for(int i=0;i<row1.size();i++){
				map = Util.getNewMap();
				map.put(ATTR_COL+1, (i+1)+"");
				String str = "";
				for(int j=1;j<rows.size();j++){
					List<String> row = rows.get(j);
					String val = Util.toString(row.get(i));
					if(Util.isEmpty(val)) continue;
					if(Util.isNotEmpty(str)){
						str+=","+val;
						break;
					}else{
						str=val;
					}
				}
				map.put(ATTR_COL+2, str);
				map.put("ATTR_NAME", "");
				list.add(map);
			}
		}
		return ConvertData.listToJSONString(list,-1);
	}
	public void addDataIns(Map<String,Object> data){
		this.insert("Upload.addDataIns",data);
	}
}
