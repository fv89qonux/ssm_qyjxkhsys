package com.xmjd.info.commons.util;

import org.apache.commons.lang3.RandomUtils;

/**
 * 随机码生成器
 * @author zhaojun
 *         2015年12月2日 上午11:41:30
 */
public class RandomCodeGenerator {

    public static String randomHex(int n) {

        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int c = RandomUtils.nextInt(0, 36);
            randomBuilder.append(chars.charAt(c));
        }
        return randomBuilder.toString();
    }

    public static String randomDecimal(int n) {
        String chars = "0123456789";
        StringBuilder randomBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int c = RandomUtils.nextInt(0, 10);
            randomBuilder.append(chars.charAt(c));
        }
        return randomBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(randomHex(5));
    }
}
