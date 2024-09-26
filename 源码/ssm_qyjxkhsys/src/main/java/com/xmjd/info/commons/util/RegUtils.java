package com.xmjd.info.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 各种正则表达验证
 * Created by admin on 2021/7/8.
 */
public class RegUtils {

    /**
     * 判断是否为手机号码
     * @param phone 手机
     * @return 结果
     */
    public static boolean isPhone(String phone) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
