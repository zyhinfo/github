package com.idea.tools.function.down.downData.spool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idea.tools.function.read.sqlldr.StreamGobbler;
import com.idea.tools.port.down.downData.spool.Spool;
import com.idea.tools.util.Util;

/**
 * 
 * 将数据库中的数据导出到服务器
 * @author zhangyh
 *
 */
public class SpoolDataImpl implements Spool {
	private Log logger = LogFactory.getLog(SpoolDataImpl.class);
	//执行sql命令需要的参数
	private String DB_NAME = ""; //数据库
	private String DB_USER_NAME = ""; //登录名称
	private String DB_USER_PASSWORD = ""; //密码
	
	private String path; //文件路径
	private String spoolFileName = ""; //spool文件
	private String outFileName;//文件名称
	private String sql;//导出语句
	public SpoolDataImpl(String path,String outFileName,String sql){
		this.path = path;
		this.outFileName = outFileName;
		this.sql = sql;
		
	}
	public String getPath(){
		return path;
	}
	public String getOutFileName(){
		return outFileName;
	}
	/**
	 * 创建spool文件
	 * @return
	 */
	public boolean createSpool(){
		
		if(Util.isNotEmpty(DB_NAME) &&Util.isNotEmpty(DB_USER_NAME) 
				&&Util.isNotEmpty(DB_USER_PASSWORD) && Util.isNotEmpty(path) 
				&& Util.isNotEmpty(sql)){
			String dateStr = Util.dateToString("");
			spoolFileName = dateStr+".spool";
			if(Util.isEmpty(outFileName)){
				outFileName = dateStr;
			}
			StringBuffer sb = new StringBuffer("");
			sb.append(" SET trimspool on \n")
			  .append(" SET linesize 1000 \n")
			  .append(" SET pagesize 0 \n")
			  .append(" SET newpage 1 \n")
			  .append(" SET heading off \n")
			  .append(" SET term off \n")
			  .append(" SET feedback off \n")
			  .append(" set echo off \n")
			  .append(" set newp none \n")
			  .append(" spool "+path+outFileName+"\n")
			  .append(" "+sql+"; \n")
			  .append(" spool off \n exit ");
			File Dir = new File(path);
			if (!Dir.exists()) {
				Dir.mkdirs();
			}
			try {
				FileWriter fw = new FileWriter(path+spoolFileName);
				fw.write(sb.toString());
				fw.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean exec(){
		if(createSpool()){
			try {
				String spool = "sqlplus " + DB_USER_NAME + "/" + DB_USER_PASSWORD +"@" +DB_NAME+ " @"+path+spoolFileName;
				logger.debug("spool=="+spool);
				Process process = Runtime.getRuntime().exec(spool);
				StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "Error");            
	            StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), "Output");
	            errorGobbler.start();
	            outputGobbler.start();
	        	//得到程序执行返回值, 0为success 
				process.waitFor();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 设置库名称、登录名称、登录密码
	 * @param dB_NAME
	 */
	public void setDB(String dB_NAME,String dB_USER_NAME,String dB_USER_PASSWORD) {
		this.DB_NAME = dB_NAME;
		this.DB_USER_NAME = dB_USER_NAME;
		this.DB_USER_PASSWORD = dB_USER_PASSWORD;
	}
}
