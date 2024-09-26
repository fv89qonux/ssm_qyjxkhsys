package com.xmjd.info.bean.system;

import java.io.Serializable;

/**
 * 系统配置
 * Created by admin on 2021/7/16.
 */
public class SysConfig implements Serializable {

    public static final String YIGEYUE = "YIGEYUE";
    public static final String CHIDAO = "CHIDAO";
    public static final String LINSHIQIANTUI = "LINSHIQIANTUI";
    public static final String BINGJIA = "BINGJIA";
    public static final String SHIJIA = "SHIJIA";
    public static final String ZAOTUI = "ZAOTUI";
    public static final String YEWUPILOU = "YEWUPILOU";

    //配置的唯一标识
    private String code;

    //配置的值
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
