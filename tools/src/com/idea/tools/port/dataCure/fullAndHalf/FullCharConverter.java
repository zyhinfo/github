package com.idea.tools.port.dataCure.fullAndHalf;

import java.io.UnsupportedEncodingException;

public interface FullCharConverter {
	/**
	 * 全角转半角的 转换函数
	 * @param QJstr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String fullToHalfChange(String QJstr)
			throws UnsupportedEncodingException;
	/**
	 * 半角转全角
	 * @param QJstr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String halfToFullchange(String QJstr)
			throws UnsupportedEncodingException;
}
