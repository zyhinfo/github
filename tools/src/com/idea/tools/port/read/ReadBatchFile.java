/**
 * 
 */
package com.idea.tools.port.read;

import java.util.List;


/**
 * @author zhangyh
 * 批量读取
 */
public interface ReadBatchFile {
	/**
	 * 读取文件
	 * @return
	 */
	public List<List<String>> readFile();
	
	/**
	 * 释放资源
	 */
	public void closeAll();
}
