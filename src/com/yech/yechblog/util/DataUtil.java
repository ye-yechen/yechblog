package com.yech.yechblog.util;

import java.security.MessageDigest;

/**
 * 数据工具类
 * @author Administrator
 *
 */
public class DataUtil {
	
	/**
	 * 使用 MD5 算法进行加密
	 * @param src
	 * @return
	 */
	public static String md5(String src){
		try {
			StringBuffer buffer = new StringBuffer();
			char[] chars = 
				{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			byte[] bytes = src.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] targ = md.digest(bytes);//targ 为16个字节长度
			
			for(byte b : targ){
				//一个字节8位，右移4位取高4位
				buffer.append(chars[(b>>4) & 0x0F]);
				//取低4位
				buffer.append(chars[b & 0x0F]);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
