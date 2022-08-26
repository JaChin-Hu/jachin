package com.jachin.blog.config;

import com.jachin.blog.utils.AsyncManager;
import com.jachin.common.utils.ThreadUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PreDestroy;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/26 14:14
 */
@Configuration
public class ThreadPoolConfig {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolConfig.class);
    private static final int CORE_POOL_SIZE = 50;

    private static final int MAX_POOL_SIZE = 200;

    private static final int QUEUE_CAPACITY = 1000;

    private static final int KEEP_ALIVE_SECONDS = 300;

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    /**
     * 执行周期性或定时任务
     */
    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(CORE_POOL_SIZE,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build(),
                new ThreadPoolExecutor.CallerRunsPolicy()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                ThreadUtils.printException(r, t);
            }
        };
    }

    @PreDestroy
    private void shutdownAsyncManager() {
        try {
            logger.info("====关闭后台任务任务线程池====");
            AsyncManager.me().shutdown();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
