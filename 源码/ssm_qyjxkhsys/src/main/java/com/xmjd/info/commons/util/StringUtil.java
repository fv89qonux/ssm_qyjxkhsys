package com.xmjd.info.commons.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringUtils;

/**
 * @author xiongchuan
 * @ClassName: StringUtil
 * @Description:
 * @date 2016年1月8日 上午10:14:24
 */
public class StringUtil extends StringUtils {

    /**
     * 用StringBuffer拼接字符串
     */
    public static String concat(String... items) {
        assert null != items;
        StringBuffer stringBuffer = new StringBuffer();
        for (String each : items) {
            stringBuffer.append(each);
        }
        return stringBuffer.toString();
    }

    public static String[] split(String string, String regex) {
        if (null == string) {
            return new String[0];
        }
        return string.split(regex);
    }

    public static String alignRight(Object obj, int width, String c) {
        if (null == obj) {
            return null;
        }
        String str = obj.toString();
        int length = str.length();
        if (length >= width) {
            return str;
        }
        return new StringBuilder().append(dup(c, width - length)).append(str).toString();
    }

    public static String dup(String cs, int num) {
        if (null == cs || num <= 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(cs.length() * num);
        for (int i = 0; i < num; i++) {
            stringBuilder.append(cs);
        }
        return stringBuilder.toString();
    }

    public static String toString(Object object, String valueWhenNull) {
        return null == object ? valueWhenNull : object.toString();
    }

    public static String toString(Object object) {
        return toString(object, null);
    }

    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();//32位加密 
            //return buf.toString().substring(8, 24);//16位的加密     
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean startWith(String string, String prefix) {
        return null != string && null != prefix && string.startsWith(prefix);
    }

    public static Boolean startWithIgnoreCase(String string, String prefix) {
        return null != string && null != prefix && string.toUpperCase().startsWith(prefix.toUpperCase());
    }

    public static String uuid() {
        return UUIDUtil.genRandomUUID().replace("-", "");
    }

    /**
     * @param content
     * @param beginIndex
     * @param endIndex   不是长度,为负数时表示从尾部开始截取
     */
    public static String substring(String content, Integer beginIndex, Integer endIndex) {
        if (endIndex < 0) {
            int len = content.length();
            return content.substring(len - beginIndex - (-endIndex), len - beginIndex);
        } else {
            return content.substring(beginIndex, endIndex);
        }
    }

    public static String upperFirst(CharSequence s) {
        if (null == s)
            return null;
        int len = s.length();
        if (len == 0)
            return "";
        char c = s.charAt(0);
        if (Character.isUpperCase(c))
            return s.toString();
        return new StringBuilder(len).append(Character.toUpperCase(c)).append(s.subSequence(1, len)).toString();
    }

    public static String toHexString(byte[] datas) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < datas.length; i++) {
            String hex = Integer.toHexString(datas[i] & 0xFF);
            if (hex.length() <= 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 返回第一个非空的值
     */
    public static String coalesce(String... strs) {
        for (String each : strs) {
            if (!isEmpty(each)) {
                return each;
            }
        }
        return null;
    }

    public static Boolean equals(String str1, String str2) {
        return (null == str1 && null == str2) || (null != str1 && str1.equals(str2));
    }

    public static Boolean equals(Object str1, Object str2) {
        return (null != str1 && null != str2) && equals(toString(str1), toString(str2));
    }

    public static Boolean equalsIgnoreCase(String str1, String str2) {
        return (null == str1 && null == str2) || (null != str1 && str1.equalsIgnoreCase(str2));
    }

    public static Boolean endWith(String string, String str) {
        return null != string && null != str && string.toLowerCase().endsWith(str.toLowerCase());
    }

    /**
     * new String("abc".getBytes("utf-8"), "ISO8859-1"))
     */
    public static String utf8ToIso88591(String input) {
        try {
            return new String(input.getBytes("utf-8"), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String escapeNull(String string) {
        if (null == string || string.trim().isEmpty() || "null".equalsIgnoreCase(string.trim())) {
            return null;
        } else {
            return string.trim();
        }
    }

    public static Integer length(String value) {
        return null == value ? 0 : value.length();
    }

    public static Boolean isAllEmpty(String[] array) {
        if (null == array || array.length < 1) {
            return true;
        } else {
            for (String each : array) {
                if (!isEmpty(each)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static String truncation(String s, int length) {
        if (StringUtil.isEmpty(s)) {
            return "";
        }
        s = s.replaceAll("<[^>]*>", "");
        if (s.length() > length) {
            s = s.substring(0, length) + "...";
        }
        return s;
    }

    /**
     * 复制字符串
     *
     * @param cs  字符串
     * @param num 数量
     * @return 新字符串
     */
    public static String dup(CharSequence cs, int num) {
        if (isEmpty(cs) || num <= 0)
            return "";
        StringBuilder sb = new StringBuilder(cs.length() * num);
        for (int i = 0; i < num; i++)
            sb.append(cs);
        return sb.toString();
    }

    /**
     * 保证字符串为一固定长度。超过长度，切除右侧字符，否则右侧填补字符。
     *
     * @param string 字符串
     * @param width  长度
     * @param c      补字符
     * @return 修饰后的字符串
     */
    public static String cutLeft(String string, int width, char c) {
        if (null == string)
            return null;
        int len = string.length();
        if (len == width)
            return string;
        if (len < width)
            return string + dup(c + "", width - len);
        return string.substring(0, width);
    }

    /**
     * @param string
     * @param width
     * @param suffix
     */
    public static String cutLeft(String string, int width, String suffix) {
        if (StringUtil.length(string) <= width) {
            return string;
        }
        return string.substring(0, width) + suffix;
    }

    public static String convert(String utfString) {
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;
        while ((i = utfString.indexOf("\\u", pos)) != -1) {
            sb.append(utfString.substring(pos, i));
            if (i + 5 < utfString.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(utfString.substring(i + 2, i + 6), 16));
            }
        }
        return sb.toString();
    }

    public static String trim(String string) {
        return null == string ? null : string.trim();
    }

    public static String toUpperCase(String string) {
        return null == string ? null : string.toUpperCase();
    }

    public static String emptyWhenNull(String string) {
        return null == string ? "" : string;
    }

    public static String maxLength(String string, Integer length) {
        if (null == string || string.length() <= length) {
            return string;
        } else {
            return string.substring(0, length);
        }
    }

    /**
     * 判断是否包含
     */
    public static Boolean containsIgnoreCase(String string, String str) {
        return null != string && null != str && string.toUpperCase().contains(str.toUpperCase());
    }

    public static Boolean contains(String string, String str) {
        return null != string && null != str && string.contains(str);
    }

    public static Boolean isBlank(String str) {
        return isEmpty(str) || str.trim().isEmpty();
    }

    /**
     * 判断一个字符串是否仅由数字字母和点组成
     */
    public static Boolean isLetterDigitDot(String string) {
        if (StringUtil.isEmpty(string)) {
            return false;
        }

        char[] chars = string.toCharArray();
        for (char c : chars) {
            if (Character.isLetter(c) || Character.isDigit(c) || '.' == c) {
                //
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取指定字符串中以begin开始以end结束的所有子字符串
     *
     * @param s     字符串
     * @param begin 开始字符串
     * @param end   结束字符串
     * @return 子字符串集合
     */
    public static String[] substringsBetweens(String s, String begin, String end) {
        List<String> list = new ArrayList<String>();
        if (begin.equals(end) && s.indexOf(begin) >= 0) {
            list.add(begin);
        }
        for (int index = s.indexOf(begin); index >= 0; index = s.indexOf(begin,
                index + 1)) {
            if (end.length() == 0) {
                list.add(s.substring(index));
            } else {
                int endIndex = s.indexOf(end, index + 1);
                if (endIndex > index) {
                    list.add(s.substring(index, endIndex + end.length()));
                } else if (endIndex < 0) {
                    break;
                }
            }
        }
        return list.toArray(new String[0]);
    }

    public static String removeCDATA(String value) {
        if (StringUtil.isEmpty(value)) {
            return value;
        }
        value = value.trim();
        if (value.startsWith("<![CDATA[") && value.endsWith("]]>")) {
            return value.substring(9, value.length() - 3);
        }
        return null;
    }

    /**
     * 特殊字符判断方法
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {
        if(StringUtil.isEmpty(str)){
            return str;
        }
        String regEx = "[~^&\\<>/]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("*").trim();
    }
}