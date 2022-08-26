package com.jachin.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/24 11:49
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class ResultTests {
    @Test
    public void okTest() {
        Result ok = Result.ok();
        assert ok.get("TIMESTAMP") != null;
        System.out.println(ok);
    }
}
