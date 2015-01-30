/**
 * Copyright By 2007 TeamSun Co. Ltd.  
 * All right reserved. 
 */
package com.idea.tools.function.dbf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JDBField {
	@SuppressWarnings("unused")
	private String name;  		//字段名
	private char type;    		//字段类型
	private int length;   		//字段长度
	private int decimalCount;   //待定
	
	public JDBField(String s, char c, int i, int j) throws JDBFException {
		if (s.length() > 10)
			throw new JDBFException(
					"The field name is more than 10 characters long: " + s);
		if (c != 'C' && c != 'N' && c != 'L' && c != 'D' && c != 'F')
			throw new JDBFException("The field type is not a valid. Got: " + c);
		if (i < 1)
			throw new JDBFException("The field length should be a positive integer. Got: " + i);
		if (c == 'C' && i >= 254)
			throw new JDBFException("The field length should be less than 254 characters for character fields. Got: "+ i);
		if (c == 'N' && i >= 21)
			throw new JDBFException("The field length should be less than 21 digits for numeric fields. Got: "+ i);
		if (c == 'L' && i != 1)
			throw new JDBFException("The field length should be 1 characater for logical fields. Got: "+ i);
		if (c == 'D' && i != 8)
			throw new JDBFException("The field length should be 8 characaters for date fields. Got: "+ i);
		if (c == 'F' && i >= 21)
			throw new JDBFException("The field length should be less than 21 digits for floating point fields. Got: "+ i);
		if (j < 0)
			throw new JDBFException("The field decimal count should not be a negative integer. Got: "+ j);
		if ((c == 'C' || c == 'L' || c == 'D') && j != 0)
			throw new JDBFException("The field decimal count should be 0 for character, logical, and date fields. Got: "+ j);
		if (j > i - 1) {
			throw new JDBFException("The field decimal count should be less than the length - 1. Got: "	+ j);
		} else {
			name = s;
			type = c;
			length = i;
			decimalCount = j;
			return;
		}
	}

	public String getName() {
		return name;
	}

	public char getType() {
		return type;
	}

	public int getLength() {
		return length;
	}

	public int getDecimalCount() {
		return decimalCount;
	}

	public String format(Object obj) throws JDBFException {
		//字符串类型
		if (type == 'C' || type == 'N') {
			if (obj == null)
				obj = "";
			if (obj instanceof String) {
				String s = (String) obj;
				if (s.length() > getLength())
					throw new JDBFException("'" + obj + "' is longer than "	+ getLength() + " characters.");
				StringBuffer stringbuffer = new StringBuffer(getLength() - s.length());
				for (int i = 0; i < getLength() - s.length(); i++)
					stringbuffer.append(' ');
					//stringbuffer.append("");

				return s + stringbuffer;
			} else {
				throw new JDBFException("Expected a String, got "
						+ obj.getClass() + ".");
			}
		}
		//bool类型
		if (type == 'L') {
			if (obj == null)
				obj = new Boolean(false);
			if (obj instanceof Boolean) {
				Boolean boolean1 = (Boolean) obj;
				return boolean1.booleanValue() ? "Y" : "N";
			} else {
				throw new JDBFException("Expected a Boolean, got " + obj.getClass() + ".");
			}
		}
		//日期类型
		if (type == 'D') {
			if (obj == null)
				obj = new Date();
			if (obj instanceof Date) {
				Date date = (Date) obj;
				SimpleDateFormat simpledateformat = new SimpleDateFormat(
						"yyyyMMdd");
				return simpledateformat.format(date);
			} else {
				throw new JDBFException("Expected a Date, got "
						+ obj.getClass() + ".");
			}
		} else {
			throw new JDBFException("Unrecognized JDBFField type: " + type);
		}
	}

	public Object parse(String s) throws JDBFException {
		s = s.trim();
		if (type == 'N' || type == 'F') {
			if (s.equals(""))
				s = "0";
			try {
				if (getDecimalCount() == 0)
					return new Long(s);
				else
					return new Double(s);
			} catch (NumberFormatException numberformatexception) {
				throw new JDBFException(numberformatexception);
			}
		}
		if (type == 'C')
			return s;
		if (type == 'L') {
			if (s.equals("Y") || s.equals("y") || s.equals("T")	|| s.equals("t"))
				return new Boolean(true);
			if (s.equals("N") || s.equals("n") || s.equals("F")	|| s.equals("f"))
				return new Boolean(false);
			else
				throw new JDBFException(
						"Unrecognized value for logical field: " + s);
		}
		if (type == 'D') {
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
			try {
				if ("".equals(s))
					return null;
				else
					return simpledateformat.parse(s);
			} catch (ParseException parseexception) {
				throw new JDBFException(parseexception);
			}
		} else {
			throw new JDBFException("Unrecognized JDBFField type: " + type);
		}
	}

	public String toString() {
		return name;
	}
}
