package com.jachin.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/31 10:46
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TempTest {
    @Test
    public void temp() {
        Runtime runtime = Runtime.getRuntime();
        Map<String, Object> map = new HashMap<>(16);
        map.put("version", Runtime.version());
        map.put("runtime", runtime);
        map.put("processors", runtime.availableProcessors());
        map.put("freeMemory", runtime.freeMemory() / (1024 * 1024) + "MB");
        map.put("maxMemory", runtime.maxMemory() / (1024 * 1024) + "MB");
        map.put("totalMemory", runtime.totalMemory() / (1024 * 1024) + "MB");

        System.out.println(map);
    }
}
