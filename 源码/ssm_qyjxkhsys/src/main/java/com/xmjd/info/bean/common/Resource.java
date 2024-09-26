package com.xmjd.info.bean.common;

/**
 * 通用的资源对象,包含：菜单、按钮
 * Created by admin on 2021/7/11.
 */
public class Resource {

    /**
     * 菜单
     */
    public static final int TYPE_MENU = 1;

    /**
     * 按钮
     */
    public static final int TYPE_BUTTON = 2;

    //主键ID
    private String id;

    //菜单名称
    private String name;

    //菜单唯一标识
    private String code;

    //父菜单ID
    private String pid;

    //菜单类型，1-菜单，1-按钮
    private int type;

    //菜单的图标
    private String icon;

    //class样式
    private String clazz;

    //菜单等级，当type=1时有用
    private int level = 0;

    //排序字段
    private int sn = 0;

    //菜单地址
    private String url;

    //按钮的方法，包含()
    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }
}
