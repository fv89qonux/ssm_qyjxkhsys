package com.xmjd.info.commons.util;

import java.util.UUID;

public class UUIDUtil {

    public static String genRandomUUID() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString();
    }
}
