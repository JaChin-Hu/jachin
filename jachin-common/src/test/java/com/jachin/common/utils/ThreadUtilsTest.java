package com.jachin.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.concurrent.ExecutorService;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/03/25 22:22
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class ThreadUtilsTest {

    @Test
    public void test() {
        ExecutorService executor = ThreadUtils.newFixedThreadPool(3);
        ExecutorService executorService = ThreadUtils.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            executor.execute(()-> System.out.println(ThreadUtils.getThreadName()));
            executorService.execute(()->System.out.println(ThreadUtils.getThreadName()));
        }
        executor.shutdown();
        executorService.shutdown();
    }
}
