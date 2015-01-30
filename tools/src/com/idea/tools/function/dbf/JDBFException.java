/**
 * Copyright By 2007 TeamSun Co. Ltd.  
 * All right reserved. 
 */
package com.idea.tools.function.dbf;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * CPNAIS
 * 
 * @author chenzg
 * @since Aug 1, 2007
 * @version 1.0
 */
public class JDBFException extends Exception {
	private Throwable detail;
	private static final long serialVersionUID = 1079752281227294498L;

	public JDBFException(String s) {
		this(s, null);
	}

	public JDBFException(Throwable throwable) {
		this(throwable.getMessage(), throwable);
	}

	public JDBFException(String s, Throwable throwable) {
		super(s);
		detail = throwable;
	}

	public String getMessage() {
		if (detail == null)
			return super.getMessage();
		else
			return super.getMessage();
	}

	public void printStackTrace(PrintStream printstream) {
		if (detail == null) {
			super.printStackTrace(printstream);
			return;
		} else {
			PrintStream printstream1 = printstream;
			printstream1.println(this);
			detail.printStackTrace(printstream);

			return;
		}
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintWriter printwriter) {
		if (detail == null) {
			super.printStackTrace(printwriter);
			return;
		} else {
			PrintWriter printwriter1 = printwriter;
			printwriter1.println(this);
			detail.printStackTrace(printwriter);
			return;
		}
	}

}
