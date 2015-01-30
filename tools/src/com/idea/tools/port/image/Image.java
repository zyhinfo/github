package com.idea.tools.port.image;

import java.io.IOException;

public interface Image {
	/**
	 * 重置图形的边长大小
	 * @param srcImagePath 原图片路径
	 * @param toImagePath  生成图片路径
	 * @param width  生成图片宽度
	 * @param height  生成图片高度
	 * @throws IOException
	 */
	public void resizeImage(String srcImagePath, String toImagePath, int width,
			int height) throws IOException ;
}
