package com.jachin.common.utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/03/25 21:11
 */
public class ThreadUtils {
    private static final int SHUTDOWN_TIMEOUT = 60;
    private static final int SHUTDOWN_NUMBER = 1000;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    public static ThreadPoolExecutor getEmailThreadPool() {
        return EmailThreadPool.EXECUTOR;
    }

    private static class IThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);  // 线程池数量
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);  // 线程数量
        private final String threadTag;

        public IThreadFactory(String threadTag) {
            group = Thread.currentThread().getThreadGroup();
            this.threadTag = "pool-" + POOL_NUMBER.getAndIncrement() + "-" + threadTag + "-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(group, r, threadTag + threadNumber.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        }
    }

    private static class EmailThreadPool {
        private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
                CPU_COUNT,
                CPU_COUNT,
                10L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                new IThreadFactory("email"),
                new ThreadPoolExecutor.AbortPolicy()
        );

        static {
            EXECUTOR.allowsCoreThreadTimeOut();
            Runtime.getRuntime().addShutdownHook(new ShutdownHook("Email线程池", () -> {
                shutdownThreadPool(EXECUTOR);
            }));
        }
    }

    private static class CpuThreadPool {
        private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
                CPU_COUNT,
                CPU_COUNT,
                30L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                new IThreadFactory("email"),
                new ThreadPoolExecutor.AbortPolicy()
        );

        static {
            EXECUTOR.allowCoreThreadTimeOut(true);
            Runtime.getRuntime().addShutdownHook(new ShutdownHook("Email线程池", () -> {
                shutdownThreadPool(EXECUTOR);
            }));
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
                System.out.println(getName() + " starting.... ");
                if (!this.hasShutdown) {
                    this.hasShutdown = true;
                    long beginTime = System.currentTimeMillis();
                    try {
                        this.r.run();
                    } catch (Exception e) {
                        System.out.println(getName() + " error: " + e.getMessage());
                    }
                    long consumingTimeTotal = System.currentTimeMillis() - beginTime;
                    System.out.println(getName() + "  耗时(ms): " + consumingTimeTotal);
                }
            }
        }
    }

    private static void shutdownThreadPool(ExecutorService threadPool) {
        if (threadPool == null || threadPool.isTerminated()) {
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
                    System.err.println("线程池任务未正常执行结束");
                }
            }
        } catch (InterruptedException ie) {
            // 捕获异常，重新调用 shutdownNow
            threadPool.shutdownNow();
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
            } catch (Throwable e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static Thread getThread() {
        return Thread.currentThread();
    }

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
