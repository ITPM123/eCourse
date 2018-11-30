package com.scut.ecourse.util;


import java.util.UUID;

public class UuidUtil {
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

}
