package com.biz.common;

import java.util.UUID;

public class UUIDUtils {
    public UUIDUtils() {
    }

    public static String genratorCode() {
        UUID ui = UUID.randomUUID();
        return ui.toString().replaceAll("-", "");
    }


}
