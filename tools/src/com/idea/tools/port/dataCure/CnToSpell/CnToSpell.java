package com.idea.tools.port.dataCure.CnToSpell;

/** 
 *汉字转化为拼音
 */ 
public interface  CnToSpell {
	/** 
     * 获取汉字串拼音首字母，英文字符不变 
     * @param chinese 汉字串 
     * @return 汉语拼音首字母 
     */ 
    public String cnFirstSpell(String chinese);

    /** 
     * 获取汉字串拼音，英文字符不变 
     * @param chinese 汉字串 
     * @return 汉语拼音 
     */ 
    public String cnSpell(String chinese);

}
