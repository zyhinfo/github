package com.idea.tools.util;

import java.io.UnsupportedEncodingException;

/**
 * JSBDM
 * @author yanghl
 * @since Mar 31, 2011
 * @version 1.0 
 */
public class StringSubUtils {

	/** 
	 * 截取一段字符的长度(汉、日、韩文字符长度为2),不区分中英文,如果数字不正好，则少取一个字符位 
	 * @param str 原始字符串 
	 * @param specialCharsLength 截取长度(汉、日、韩文字符长度为2) 
	 * @return 
	 */
	public static String trim(String str, int specialCharsLength) {
		if (str == null || "".equals(str) || specialCharsLength < 1) {
			return "";
		}
		char[] chars = str.toCharArray();
		int charsLength = getCharsLength(chars, specialCharsLength);
		return new String(chars, 0, charsLength);
	}

	/** 
	 * 获取一段字符的长度，输入长度中汉、日、韩文字符长度为2，输出长度中所有字符均长度为1 
	 * @param chars 一段字符 
	 * @param specialCharsLength 输入长度，汉、日、韩文字符长度为2 
	 * @return 输出长度，所有字符均长度为1 
	 */
	private static int getCharsLength(char[] chars, int specialCharsLength) {
		int count = 0;
		int normalCharsLength = 0;
		for (int i = 0; i < chars.length; i++) {
			int specialCharLength = getSpecialCharLength(chars[i]);
			if (count <= specialCharsLength - specialCharLength) {
				count += specialCharLength;
				normalCharsLength++;
			} else {
				break;
			}
		}
		return normalCharsLength;
	}

	/** 
	 * 获取字符长度：汉、日、韩文字符长度为2，ASCII码等字符长度为1 
	 * @param c 字符 
	 * @return 字符长度 
	 */
	private static int getSpecialCharLength(char c) {
		if (isLetter(c)) {
			return 1;
		} else {
			return 2;
		}
	}

	/** 
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符） 
	 * @param char c, 需要判断的字符 
	 * @return boolean, 返回true,Ascill字符 
	 */
	private static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/** 
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1 
	 * @param s 需要得到长度的字符串 
	 * @return i得到的字符串长度 
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/** 
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位 
	 * @param  origin 原始字符串 
	 * @param len 截取长度(一个汉字长度按2算的) 
	 * @param c 后缀            
	 * @return 返回的字符串 
	 */
	public static String substring(String origin, int len, String c) {
		if (origin == null || origin.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > length(origin)) {
			return origin + c;
		}
		try {
			System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);
			int count = 0;
			for (int i = 0; i < len; i++) {
				int value = (int) strByte[i];
				if (value < 0) {
					count++;
				}
			}
			if (count % 2 != 0) {
				len = (len == 1) ? ++len : --len;
			}
			return new String(strByte, 0, len, "GBK") + c;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
