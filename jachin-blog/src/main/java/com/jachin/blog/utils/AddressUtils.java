package com.jachin.blog.utils;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/17 15:09
 */
public class AddressUtils {
    private static final String URL_PREFIX = "http://ip-api.com/json/";
    private static final String URL_SUFFIX = "?lang=zh-CN";

    public static String getRealAddressByIp(String ip) {
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        return "XX XX";
    }
}
