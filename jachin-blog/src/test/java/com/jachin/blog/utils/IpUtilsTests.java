package com.jachin.blog.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/26 15:33
 */
@SpringBootTest
class IpUtilsTests {
    @Test
    void getRealAddressByIpTest() {
        String address = IpUtils.getRealAddressByIp("125.69.189.242");
        System.out.println(address);
    }
}
