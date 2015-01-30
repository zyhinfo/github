package com.idea.tools.port.read;

import java.io.IOException;


/**
 * @author zhangyh
 *
 */
public interface Read {
	/**
	 * 得到文件所有行
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public ReadBatchFile getFileContent(ReadBean bean);
	
}
