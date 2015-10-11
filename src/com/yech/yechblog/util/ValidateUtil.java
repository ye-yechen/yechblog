package com.yech.yechblog.util;

import java.util.Collection;

/**
 * 校验工具类
 * @author Administrator
 *
 */
public class ValidateUtil {
	
	/**
	 * 判断字符串是否有效
	 * @param src
	 * @return
	 */
	public static boolean isValidate(String src){
		if(src == null || "".equals(src.trim())){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断集合的有效性
	 * @param collection
	 * @return
	 */
	public static boolean isValidate(@SuppressWarnings("rawtypes") Collection collection){
		if(collection == null || collection.isEmpty()){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断数组是否有效
	 * @param arr
	 * @return
	 */
	public static boolean isValidate(Object[] arr){
		if(arr == null || arr.length == 0){
			return false;
		}
		return true;
	}
}
