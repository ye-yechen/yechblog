package com.yech.yechblog.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 根据IP地址获取详细的地域信息
 * 调用淘宝的接口 http://ip.taobao.com/service/getIpInfo.php?ip=
 */
public class AddressUtil {
	/**
	 * @param encoding
	 * 服务器端请求编码。如GBK,UTF-8等
	 */
	public static String getAddresses(String ip, String encodingString)
			throws UnsupportedEncodingException {
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php?ip=";
		String returnStr = getResult(urlStr, ip, encodingString);
		if (returnStr != null) {
			// 处理返回的省市区信息
			System.out.println(returnStr);
			//{"code":0,"data":{"country":"\u4e2d\u56fd","country_id":"CN",
			//"area":"\u534e\u4e1c","area_id":"300000",
			//"region":"\u5c71\u4e1c\u7701","region_id":"370000",
			//"city":"\u9752\u5c9b\u5e02","city_id":"370200","county":"",
			//"county_id":"-1","isp":"\u963f\u91cc\u4e91","isp_id":"1000323",
			//"ip":"114.215.92.22"}}
			String[] temp = returnStr.split(",");
			if (temp.length < 3) {
				return null;// 无效IP，局域网测试
			}
			StringBuffer addr = new StringBuffer();
			//得到unicode码 例如 \u9752\u5c9b\u5e02
			String country = (temp[1].split(":"))[2].replaceAll("\"", "");
			addr.append(decodeUnicode(country));//国家 
			String province = (temp[5].split(":"))[1].replaceAll("\"", "");
			addr.append(decodeUnicode(province));// 省份
			String city = (temp[7].split(":"))[1].replaceAll("\"", "");
			addr.append(decodeUnicode(city));// 市
			return addr.toString();
		}
		return null;
	}

	/**
	 * @param urlStr 请求的地址
	 * @param encoding 服务器端请求编码。如GBK,UTF-8等
	 */
	private static String getResult(String urlStr, String ip, String encoding) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr+ip);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
			// ,以BufferedReader流来读取
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
		return null;
	}

	/**
	 * unicode 转换成 中文
	 */
	private static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}
}