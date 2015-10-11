package com.yech.yechblog.util;

/**
 * 字符串工具类
 * @author Administrator
 *
 */
public class StringUtil {
	
	/**
	 * 字符串拆分成string数组
	 * @param str
	 * @return
	 */
	public static String[] str2Arr(String str,String tag){
		if(ValidateUtil.isValidate(str)){
			return str.split(tag);
		}
		return null;
	}

	/**
	 * 判断在 values 数组中是否含有value字符串
	 * @param values
	 * @param value
	 * @return
	 */
	public static boolean contains(String[] values, String value) {
		if(ValidateUtil.isValidate(values)){
			for(String s : values){
				if(s.equals(value)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 将数组转为字符串，使用 , 分隔
	 * @param value
	 * @return
	 */
	public static String arr2Str(Object[] value) {
		String tmp = "";
		if(ValidateUtil.isValidate(value)){
			for(Object s : value){
				tmp = tmp + s + ",";
			}
			return tmp.substring(0,tmp.length()-1);
		}
		return tmp;
	}
}