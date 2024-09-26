package com.xmjd.info.commons.constant;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.reloading.ReloadingStrategy;

import java.io.InputStream;

/**
 * redis的各个key，必须从此处调用。
 * Created by admin on 2021/7/7.
 */
public class RedisConstant {

    protected static PropertiesConfiguration config = new PropertiesConfiguration();

    private static String PREFIX = null;

    static {
        try {
            InputStream is = RedisConstant.class.getClassLoader().getResourceAsStream("config.properties");
            ReloadingStrategy r = new FileChangedReloadingStrategy();
            config.setReloadingStrategy(r);
            config.load(is);
            PREFIX = config.getString("redisPrefix");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 最新订单的key，redis 通用前缀 + 新订单标志　+ 酒店ID（调用时拼接）
     */
    public static final String THE_NEW_ORDERS = PREFIX + "_THE_NEW_ORDERS_";

    /**
     * 系统配置项
     */
    public static final String SYS_CONFIG = PREFIX + "_SYS_CONFIG_";
}
