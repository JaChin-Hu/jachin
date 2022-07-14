package org.example.utils;

import com.jachin.common.utils.ThreadUtils;
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
    public void emailThreadPoolTest() {
        ExecutorService executorService = ThreadUtils.getEmailThreadPool();
        ExecutorService executorService2 = ThreadUtils.getEmailThreadPool();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executorService.execute(()-> {
                System.out.println(finalI +" "+ThreadUtils.getThreadName());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executorService2.execute(()-> {
                System.out.println(finalI +" "+ThreadUtils.getThreadName());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
