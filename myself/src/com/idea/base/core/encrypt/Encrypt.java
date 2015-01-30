package com.idea.base.core.encrypt;import java.util.Map;import com.idea.tools.util.Util;public class Encrypt {	/**	 * 初始化字符顺序	 * 	 * @return	 */	public static Map<String, Object> initParam() {		int start = 32;//开始字符		int end = 126;//结束字符		int[] notLike ={92,34};//去除字符		int charVal=11;//打乱字符顺序，charVal取值范围(0-(end-start))		Map<String, Object> param = Util.getNewMap();		//调整字符对应关系		for (int i = start; i <= end; i++) {			//对charVal的取值进行限制			if(charVal < 0 ) charVal = 0;			else if(charVal > end-start) charVal = 0;			//去除字符			for(int num:notLike){				if(num==i)i++;				if(num==end-charVal)charVal++;			}			//System.out.println((char) i+"----"+i+","+(char) (end-charVal)+"----"+(end-charVal));			param.put((char) i + "", (char) (end - charVal) + "");			charVal++;		}		return param;	}	/**	 * 对字符进行加密	 * 	 * @param str	 * @return	 */	public static String encrypt(String string) {		StringBuffer sb = new StringBuffer("");		Map<String, Object> param = initParam();		char[] ch = string.toCharArray();		for (int i = 0; i < ch.length; i++) {			if (param.containsKey(ch[i] + "")) {				sb.append(param.get(ch[i] + ""));			}		}		return sb.toString();	}	/**	 * 对字符解密	 * 	 * @param codes	 * @return	 */	public static String decipher(String codes) {		StringBuffer sb = new StringBuffer("");		Map<String, Object> param = initParam();		char[] ch = codes.toCharArray();		for (int i = 0; i < ch.length; i++) {			if (param.containsValue(ch[i]+"")) {				for (String key : param.keySet()) {					if ((param.get(key)).equals(ch[i]+"")) {						sb.append(key);						break;					}				}			} else {				System.out.println("=========代码不存在============");			}		}		return sb.toString();	}	public static void main(String[] args) {		String str="xy,JEMDZ_]Za]";		System.out.println(decipher(str));		/*		String model = encrypt(str);		System.out.println("加密：   "+model);		//model="xy,JEMDZ_]Za]ez~3y}~/!+#$(Z_]Za]c``ez~3y}~/!+#$(Z_]Za]c``Sc^af1%'";		String model2=decipher(model);		System.out.println("解密：   "+model2);		System.out.println("原字符和解密后字符比较：   "+str.equals(model2));		*/	}}