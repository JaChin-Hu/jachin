package com.jachin.common.utils;

import java.util.UUID;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 15:01
 */
public class CommonUtils {
    public static String getRandomCode() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 6);
    }
}
