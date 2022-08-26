package com.jachin.common.utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/03/25 21:11
 */
public class ThreadUtils {
    private ThreadUtils() {
    }

    private static final Logger log = Logger.getLogger("ThreadUtils");
    private static final RejectedExecutionHandler DEFAULT_HANDLER = new ThreadPoolExecutor.AbortPolicy();
    private static final int DEFAULT_CAPACITY = 1024;
    private static final int SHUTDOWN_TIMEOUT = 120;
    private static final int SHUTDOWN_NUMBER = 1000;
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(
                nThreads,
                nThreads,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(DEFAULT_CAPACITY),
                new DefaultThreadFactory(),
                DEFAULT_HANDLER);
    }

    public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(
                nThreads,
                nThreads,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(DEFAULT_CAPACITY),
                threadFactory,
                DEFAULT_HANDLER);
    }

    public static ExecutorService newSingleThreadExecutor() {
        return new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(DEFAULT_CAPACITY),
                new DefaultThreadFactory(),
                DEFAULT_HANDLER
        );
    }

    public static ThreadPoolExecutor getEmailThreadPool() {
        return EmailThreadPool.EXECUTOR;
    }


    private static class EmailThreadPool {
        private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
                PROCESSORS,
                PROCESSORS,
                10L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                new DefaultThreadFactory("email"),
                new ThreadPoolExecutor.AbortPolicy()
        );

        static {
            EXECUTOR.allowsCoreThreadTimeOut();
            Runtime.getRuntime().addShutdownHook(new ShutdownHook("Email线程池", () -> shutdownThreadPool(EXECUTOR)));
        }
    }


    private static class ShutdownHook extends Thread {
        private volatile boolean hasShutdown = false;
        private final Runnable r;

        private ShutdownHook(String name, Runnable r) {
            super("JVM退出钩子(" + name + ")");
            this.r = r;
        }

        @Override
        public void run() {
            synchronized (this) {
                log.info(getName() + " starting.... ");
                if (!this.hasShutdown) {
                    this.hasShutdown = true;
                    long beginTime = System.currentTimeMillis();
                    try {
                        this.r.run();
                    } catch (Exception e) {
                        log.info(getName() + " error: " + e.getMessage());
                    }
                    long consumingTimeTotal = System.currentTimeMillis() - beginTime;
                    log.info(getName() + "  耗时(ms): " + consumingTimeTotal);
                }
            }
        }
    }

    public static void shutdownThreadPool(ExecutorService threadPool) {
        if (threadPool == null || threadPool.isTerminated() || threadPool.isShutdown()) {
            return;
        }
        try {
            threadPool.shutdown();   //拒绝接受新任务
        } catch (SecurityException | NullPointerException e) {
            return;
        }
        try {
            // 等待 60 s，等待线程池中的任务完成执行
            if (!threadPool.awaitTermination(SHUTDOWN_TIMEOUT, TimeUnit.SECONDS)) {
                // 调用 shutdownNow 取消正在执行的任务
                threadPool.shutdownNow();
                // 再次等待 60 s，如果还未结束，可以再次尝试，或则直接放弃
                if (!threadPool.awaitTermination(SHUTDOWN_TIMEOUT, TimeUnit.SECONDS)) {
                    log.info("线程池任务未正常执行结束");
                }
            }
        } catch (InterruptedException ie) {
            // 捕获异常，重新调用 shutdownNow
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
        // 任然没有关闭，循环关闭1000次，每次等待10毫秒
        if (!threadPool.isTerminated()) {
            try {
                for (int i = 0; i < SHUTDOWN_NUMBER; i++) {
                    if (threadPool.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                        break;
                    }
                    threadPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.info(e.getMessage());
            }
        }
    }

    /**
     * 打印线程异常信息
     */
    public static void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?> future) {
            try {
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null) {
            log.severe(t.getMessage());
        }
    }

    public static Thread getThread() {
        return Thread.currentThread();
    }

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger THREAD_NUMBER = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            group = Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
        }

        DefaultThreadFactory(String name) {
            group = Thread.currentThread().getThreadGroup();
            namePrefix = name + "-" + POOL_NUMBER.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + THREAD_NUMBER.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
