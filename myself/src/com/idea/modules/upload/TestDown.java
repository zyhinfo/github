package com.idea.modules.upload;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TestDown {
	public static void main(String[] args){
		URL url = null;
		try {
			url = new URL("http://172.16.1.33/newfile/e358beb5367341929614332e1a390dfb.apk");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		URLConnection httpConnection = null;
		try {
			// 建立连接
			httpConnection = url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 设置ua
		httpConnection.setRequestProperty("User-Agent","NetFox");
		// 设置断点续传的开始位置 从100k开始
		httpConnection.setRequestProperty("RANGE","bytes=102400-");
		// 获得输入流
		InputStream input = null;
		try {
			input = httpConnection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 可以从多少字节开始写的文件类
		RandomAccessFile oSavedFile = null;
		try {
			oSavedFile = new RandomAccessFile("d://down.apk","rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 从第100K开始写
		long nPos = 102400;
        // 定位文件指针到 nPos 位置
        try {
        	// 文件指针移动到那个位置
			//oSavedFile.seek(nPos);
		}catch(IOException e){e.printStackTrace();}
		byte[] b = new byte[1024];int nRead;
        // 从输入流中读入字节流，然后写到文件中
        try {
            // int n =0;
            while((nRead=input.read(b,0,1024)) > 0){
            oSavedFile.write(b,0,nRead);
            /**
             * 第一次的时设置n的值到100的时候断开
             * if(100 == n){
             *     break;
             * }
             */
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}