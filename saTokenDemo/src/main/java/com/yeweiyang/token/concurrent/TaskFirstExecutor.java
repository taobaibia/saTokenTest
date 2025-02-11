package com.yeweiyang.token.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
//import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;


/**
 * @author Jay
 * @version V1.0
 * @Package com.yeweiyang.token.concurrent
 * @date 2023/8/9 15:46
 */
@Component
@Slf4j
public class TaskFirstExecutor extends AbstractTaskExecutor{


    /**
     * 构建线程优先的线程池
     * <p>
     * 线程池默认是当核心线程数满了后，将任务添加到工作队列中，当工作队列满了之后，再创建线程直到达到最大线程数。
     *
     * <p>
     * 线程优先的线程池，就是在核心线程满了之后，继续创建线程，直到达到最大线程数之后，再把任务添加到工作队列中。
     *
     * @param corePoolSize    核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   空闲线程的空闲时间
     * @param unit            时间单位
     * @param workQueueSize   工作队列容量大小
     * @param poolName        线程池名称
     * @return ThreadPoolExecutor
     */
    @Override
    public ThreadPoolExecutor buildThreadPoolExecutor(int corePoolSize,
                                                      int maximumPoolSize,
                                                      long keepAliveTime,
                                                      TimeUnit unit,
                                                      int workQueueSize,
                                                      String poolName) {
        // 自定义队列，优先开启更多线程，而不是放入队列
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(workQueueSize) {


            private static final long serialVersionUID = 7706702175352240913L;

            @Override
            public boolean offer(@NotNull Runnable o) {
                // 造成队列已满的假象
                return false;
            }
        };

        // 当线程达到 maximumPoolSize 时会触发拒绝策略，此时将任务 put 到队列中
        RejectedExecutionHandler rejectedExecutionHandler = (runnable, executor) -> {
            try {
                // 任务拒绝时，通过 offer 放入队列
                queue.put(runnable);
            } catch (InterruptedException e) {
                log.warn("{} Queue offer interrupted. ", poolName, e);
                Thread.currentThread().interrupt();
            }
        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize,
                keepAliveTime, unit,
                queue,
                new ThreadFactoryBuilder()
                        .setNameFormat(poolName + "-%d")
                        .setUncaughtExceptionHandler((Thread thread, Throwable throwable) -> log.error("{} catching the uncaught exception, ThreadName: [{}]", poolName, thread.toString(), throwable))
                        .build(),
                rejectedExecutionHandler
        );

        executor.allowCoreThreadTimeOut(true);

        return executor;
    }
}
