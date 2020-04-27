package com.bf.dt.util;

import java.util.UUID;

public class UUIDUtil {
    public UUIDUtil() {
    }

    public static String getUUID() {
        String str = UUID.randomUUID().toString();
        str = str.replaceAll("-", "");
        return str;
    }

    public static String formatUUID(String str) {
        return str.replaceAll("\\-", "");
    }
}
