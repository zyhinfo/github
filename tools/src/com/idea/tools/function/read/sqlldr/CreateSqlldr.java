package com.idea.tools.function.read.sqlldr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idea.tools.port.read.Sqlldr;

/**
 * 创建sqlldr 的ctl文件
 * 
 * @author zhangyh
 * 
 */
public class CreateSqlldr implements Sqlldr {
	private Log logger = LogFactory.getLog(CreateSqlldr.class);
	// 执行sql命令需要的参数
	private String DB_NAME = ""; // 数据库
	private String DB_USER_NAME = ""; // 登录名称
	private String DB_USER_PASSWORD = ""; // 密码

	private String path = ""; // 生成文件存放路径
	private String tableName = ""; // 数据库表名称
	private String filePath = ""; // 要入库的文件路径
	private String fileName = ""; // 文件名称
	private String attrNames = ""; // 要入库的字段(addr,name,post)
	private String split = "\t"; // 文件分割符
	private String type = "TRUNCATE"; // 处理类型(TRUNCATE,INSERT,APPEND,REPLACE)
	private String SQLLDER_CTL = ""; // ctl文件名称
	private String SQLLDER_BAD = ""; // 错误数据文件名称
	private String SQLLDER_LOG = ""; // 日志文件名称
	private int skip = 0;
	private boolean flag = true;// 组织机构地址维护

	/**
	 * 
	 * @param filePath
	 *            要上传 的数据文件
	 */
	public CreateSqlldr(String filePath, String tableName) {
		// 数据文件的路径
		this.filePath = filePath;
		// 要导入的表名称
		this.tableName = tableName;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		fileName = sf.format(new Date());
		// ctl控制文件名称
		SQLLDER_CTL = fileName + ".ctl";
		// 错误文件名称
		SQLLDER_BAD = fileName + ".bad";
		// 日志文件名称
		SQLLDER_LOG = fileName + ".log";
	}

	/**
	 * 执行sqlldr 程序
	 * 
	 * @return
	 */
	public boolean exec() {
		try {
			if (isNotNull(DB_NAME) && isNotNull(DB_USER_NAME)
					&& isNotNull(DB_USER_PASSWORD) && createCtl()) {
				String sqlldr = "sqlldr " + DB_USER_NAME + "/"
						+ DB_USER_PASSWORD + "@" + DB_NAME + " control=" + path
						+ SQLLDER_CTL + " bad=" + path + SQLLDER_BAD + " log="
						+ path + SQLLDER_LOG + " rows=10000 bindsize=8192000 "
						+ " readsize=8192000 errors=-1 ";
				logger.info("==" + sqlldr);
				Process process = Runtime.getRuntime().exec(sqlldr);
				logger.info("==sqlldr end==");
				StreamGobbler errorGobbler = new StreamGobbler(
						process.getErrorStream(), "Error");
				StreamGobbler outputGobbler = new StreamGobbler(
						process.getInputStream(), "Output");
				errorGobbler.start();
				outputGobbler.start();
				// 得到程序执行返回值, 0为success
				process.waitFor();
				// 创建成功文件
				File file = new File(path + SQLLDER_BAD + "_true");
				file.createNewFile();
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 创建失败文件
		File file = new File(path + SQLLDER_BAD + "_false");
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	/**
	 * 生成ctl文件内容
	 * 
	 * @return
	 */
	public String ctlStr() {
		if ("tab".equals(split)) {
			split = "	";
		}
		String ctlStr = "OPTIONS(skip=" + skip + ") " + "load data "
				+ "CHARACTERSET ZHS16GBK infile '" + filePath + "' " + type
				+ " into table " + tableName + " fields terminated by \""
				+ split + "\" ";
		if (flag) {
			ctlStr = ctlStr + " Optionally enclosed by '\"'";
		}

		ctlStr = ctlStr + " TRAILING NULLCOLS(" + attrNames + ")";
		return ctlStr;
	}

	/**
	 * 创建sqlldr ctl文件
	 */
	public boolean createCtl() {
		logger.info("==sqlldr path=" + path + "|tableName=" + tableName
				+ "|filePath=" + filePath + "|attrNames=" + attrNames);
		if (isNotNull(path) && isNotNull(tableName) && isNotNull(filePath)
				&& isNotNull(attrNames)) {
			File Dir = new File(path);
			if (!Dir.exists()) {
				Dir.mkdirs();
			}
			try {
				FileWriter fw = new FileWriter(path + SQLLDER_CTL);
				fw.write(ctlStr());
				fw.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public String getSQLLDER_BAD() {
		return SQLLDER_BAD;
	}

	public String getSQLLDER_LOG() {
		return SQLLDER_LOG;
	}

	public String getSQLLDER_CTL() {
		return SQLLDER_CTL;
	}

	public String getFileName() {
		return fileName;
	}

	public String getPath() {
		return this.path;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * 设置导出过程中生成文件的存放路径
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		if (isNotNull(path))
			this.path = path + fileName + "/";
	}

	/**
	 * 设置导入字段
	 * 
	 * @param attrNames
	 */
	public void setAttrNames(String attrNames) {
		this.attrNames = attrNames;
	}

	/**
	 * 设置分隔符 空格不能作为分割符
	 * 
	 * @param split
	 */
	public void setSplit(String split) {
		if (isNotNull(split)) {
			this.split = split;
		} else {
			this.split = "\t";
		}
	}

	/**
	 * 设置操作分类
	 * 
	 * @param type
	 */
	public void setType(String type) {
		if (isNotNull(type)) {
			type = type.toUpperCase();
			if ("REPLACE".equals(type) || "APPEND".equals(type)
					|| "INSERT".equals(type) || "TRUNCATE".equals(type)) {
				this.type = type;
			}
		}
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	/**
	 * 设置库名称、登录名称、登录密码
	 * 
	 * @param dB_NAME
	 */
	public void setDB(String dB_NAME, String dB_USER_NAME,
			String dB_USER_PASSWORD) {
		this.DB_NAME = dB_NAME;
		this.DB_USER_NAME = dB_USER_NAME;
		this.DB_USER_PASSWORD = dB_USER_PASSWORD;
	}

	/**
	 * 判断是否不为空
	 * 
	 * @param val
	 * @return
	 */
	public boolean isNotNull(String val) {
		if (val == null || "".equals(val.trim())) {
			return false;
		}
		return true;
	}
}
