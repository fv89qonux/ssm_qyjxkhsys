package com.xmjd.info.commons.util;

import java.util.Date;

public class TestAscii {
	private static String domain = "zfono";
	private static String hotel = "zunyue";
	private static String room = "8008";
	public static void main(String[] args) {
		String s = "zfono";// 字符串

//		char[] chars = s.toCharArray(); // 把字符中转换为字符数组

//		System.out.println("\n\n汉字 ASCII\n----------------------");

		//1.获得zfono hoteid roomid ASCII码
		long zfonol = getStrAscii(domain);
		zfonol+=getStrAscii(hotel);
		zfonol+=getStrAscii(room);



		//2.获得当前时间戳

		long currentTimeStamp = System.currentTimeMillis();
		System.out.println("currentTimeStamp : "+ currentTimeStamp);

		//3.获得一个随机时间戳
		long randomTimeStamp = getRandomTimeStamp();
		System.out.println("randomTimeStamp : "+ randomTimeStamp);

		//4.获得两个时间戳的和值
		long timeStamp = currentTimeStamp+randomTimeStamp;
		System.out.println("timeStamp : "+ timeStamp);


		//5.将两个值相加的结果转成16进制

		System.out.println("---------------");

		System.out.println("timeStamp+zfonol :" + (timeStamp+zfonol));

		System.out.println("sn : "+Long.toHexString(timeStamp+zfonol));
		System.out.println("sn : "+Long.toHexString(timeStamp+zfonol).toUpperCase());

		convertSn("1B24945136A56A0");

		System.out.println(getStrAscii(""));

	}

	public static String generateSN(){

		return "";
	}

	public static long getStrAscii(String str){
		char[] chars = str.toCharArray(); // 把字符中转换为字符数组
		long zfonol=0l;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {// 输出结果
			int ascii = (int) chars[i];
			sb.append(ascii);
			//zfonol+=(int) chars[i];
		}
		System.out.println(str + " : " + sb.toString());
		return Long.parseLong(sb.toString());
	}

	/**
	 * 随机一个double类型的数字,0.0 ~1.0之间，然后获得小数点后的一个12位的字符串，并将其转成long
	 *
	 */
	public static long getRandomTimeStamp(){

		String s = new Double(Math.random()).toString();
		return Long.parseLong(s.substring(2, 13));
	}
	
	/*
zfono : 122102111110111
zunyue : 122117110121117101
8008 : 56484856
currentTimeStamp : 1463375918216
randomTimeStamp : 25255101588
timeStamp : 1488631019804
---------------
timeStamp+zfonol :122240700919731872
sn : 1b24945136a56a0
sn : 1B24945136A56A0
	 */

	public static void convertSn(String sn) {



		long snl = Long.parseLong(sn, 16);
		System.out.println(snl);

		//


	}

}
