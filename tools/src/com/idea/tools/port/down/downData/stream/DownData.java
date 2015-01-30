/**
 * 
 */
package com.idea.tools.port.down.downData.stream;

import java.util.List;
import java.util.Map;

import com.idea.tools.function.down.downData.stream.Title;

/**
 * @author zhangyh
 *
 */
public interface DownData {
	
	/**
	 * 导出数据    在服务器上生成文件 <br>
	 * List 中可以放HashMap 和 LinkedHashMap <br>
	 * HashMap 不要求输出的先后顺序 <br>
	 * LinkedHashMap 按照放入的顺序导出 <br>
	 * @param data
	 * 
	 */
	public void downloadToServer(List<Map<String,Object>> data);
	
	/**
	 * 关闭流
	 */
	public void closeAll();
	/**
	 * 返回文件路径
	 * @return
	 */
	public String getFilePath();
	/**
	 * 返回文件名称
	 * @return
	 */
	public String getFileName();
	/**
	 * 返回下载条数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 设置标题显示字段和中文名称 <br>
	 * HashMap 对象不要求输出的先后顺序 <br>
	 * LinkedHashMap 按照放入的顺序导出 <br>
	 * @param title 
	 */
	public void setTitle(List<Title> title);
	public void setTitle(String attrDesc,String attrName,String attrLen);
}
