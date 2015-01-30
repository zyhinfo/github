package com.idea.tools.function.read.sqlldr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StreamGobbler extends Thread{
	private Log logger = LogFactory.getLog(StreamGobbler.class);
	InputStream is;
	String type;
	public StreamGobbler(InputStream is, String type) {
		this.is = is;
		this.type = type;
	}
	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (type.equals("Error"))
					logger.error(line);
				else
					logger.debug(line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
