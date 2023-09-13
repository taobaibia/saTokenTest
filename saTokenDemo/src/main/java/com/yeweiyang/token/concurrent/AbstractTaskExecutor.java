package com.yeweiyang.token.concurrent;

import com.google.common.collect.ImmutableMap;
import com.yeweiyang.token.config.EumeAndWeb.CommonCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static org.reflections.Reflections.log;

/**
 * 任务执行器
 *
 * @author Jay
 * @version V1.0
 * @Package com.yeweiyang.token.concurrent
 * @date 2023/8/9 15:47
 */
@Slf4j
@Component
public abstract class AbstractTaskExecutor {

    /**
     * 构建线程优先的线程池
     * <p>
     * 线程池默认是当核心线程数满了后，将任务添加到工作队列中，当工作队列满了之后，再创建线程直到达到最大线程数。
     *
     * <p>
     * 线程优先的线程池，就是在核心线程满了之后，继续创建线程，直到达到最大线程数之后，再把任务添加到工作队列中。
     *
     * <p>
     * 此方法默认设置核心线程数为 CPU 核数，最大线程数为 8倍 CPU 核数，空闲线程超过 5 分钟销毁，工作队列大小为 65536。
     *
     * @param poolName 线程池名称
     * @return ThreadPoolExecutor
     */
    public ThreadPoolExecutor buildThreadPoolExecutor(String poolName) {
        int coreSize = AbstractTaskExecutor.getCpuProcessors();
        int maxSize = coreSize * 8;
        ThreadPoolExecutor executor = buildThreadPoolExecutor(coreSize, maxSize, 5, TimeUnit.MINUTES, 1 << 16, poolName);
        AbstractTaskExecutor.displayThreadPoolStatus(executor, poolName);
        AbstractTaskExecutor.hookShutdownThreadPool(executor, poolName);
        ExecutorManager.registerThreadPoolExecutor(poolName, executor);
        return executor;
    }

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
    public abstract ThreadPoolExecutor buildThreadPoolExecutor(int corePoolSize,
                                                               int maximumPoolSize,
                                                               long keepAliveTime,
                                                               TimeUnit unit,
                                                               int workQueueSize,
                                                               String poolName);

    /**
     * 批量提交异步任务，执行失败可抛出异常或返回异常编码即可 <br>
     * <p>
     * 需注意提交的异步任务无法控制事务，一般需容忍产生一些垃圾数据的情况下才能使用异步任务，异步任务执行失败将抛出异常，主线程可回滚事务.
     * <p>
     * 异步任务失败后，将取消剩余的任务执行.
     *
     * @param tasks             将任务转化为 AsyncTask 批量提交
     * @param executor          线程池，需自行根据业务场景创建相应的线程池
     * @return 返回执行结果
     */
    public <P, T> List<AsyncTaskResult<P, T>> batchExecuteAsync( List<AsyncTask<P, T>> tasks,  ThreadPoolExecutor executor) {
        if (CollectionUtils.isEmpty(tasks)) {
            return Collections.emptyList();
        }

        int size = tasks.size();

        List<Callable<AsyncTaskResult<P, T>>> callables = tasks.stream().map(t -> (Callable<AsyncTaskResult<P, T>>) () -> {
            AsyncTaskResult<P, T> asyncTaskResult = new AsyncTaskResult<>();
            asyncTaskResult.setAsyncTask(t);
            try {
                T r = t.doExecute();

                log.debug("[>>Executor<<] Async task execute success. ThreadName: [{}], SubTaskName: [{}]",
                        Thread.currentThread().getName(), t.taskName());
                asyncTaskResult.setCode(CommonCodeEnum.SUCCESS.getCode());
                asyncTaskResult.setResult(r);
            } catch (Throwable e) {
                log.error("[>>Executor<<] Async task execute error. ThreadName: [{}], SubTaskName: [{}], exception: {}",
                        Thread.currentThread().getName(), t.taskName(), e.getMessage());
                asyncTaskResult.setCode(CommonCodeEnum.EXCEPTION.getCode());
                asyncTaskResult.setThrowable(e);
            }
            return asyncTaskResult;
        }).collect(Collectors.toList());

        CompletionService<AsyncTaskResult<P, T>> cs = new ExecutorCompletionService<>(executor, new LinkedBlockingQueue<>(size));
        List<Future<AsyncTaskResult<P, T>>> futures = new ArrayList<>(size);
        log.info("[>>Executor<<] Start async tasks, TaskSize: [{}]", size);

        for (Callable<AsyncTaskResult<P, T>> task : callables) {
            futures.add(cs.submit(task));
        }

        List<AsyncTaskResult<P, T>> resultList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            try {
                Future<AsyncTaskResult<P, T>> future = cs.poll(6, TimeUnit.MINUTES);
                if (future != null) {
                    AsyncTaskResult<P, T> asyncTaskResult = future.get();
                    resultList.add(asyncTaskResult);
                    log.debug("[>>Executor<<] Async task [{}] execute success, result: {}", i, asyncTaskResult);
                } else {
                    cancelTask(futures);
                    log.error("[>>Executor<<] Async task [{}] execute timeout, then cancel other tasks.", i);
                }
            } catch (InterruptedException e) {
                cancelTask(futures);
                Thread.currentThread().interrupt(); // 重置中断标识
                log.error("[>>Executor<<] Async task [{}] were interrupted.", i);
            } catch (Throwable throwable) {
                cancelTask(futures);
                log.error("[>>Executor<<] Async task [{}] throw exception.", i, throwable);
            }
        }
        log.info("[>>Executor<<] Finish async tasks,TaskSize: [{}]", size);
        return resultList;
    }

    /**
     * 根据一定周期输出线程池的状态
     *
     * @param threadPool     线程池
     * @param threadPoolName 线程池名称
     */
    public static void displayThreadPoolStatus(ThreadPoolExecutor threadPool, String threadPoolName) {
        displayThreadPoolStatus(threadPool, threadPoolName, RandomUtils.nextInt(60, 600), TimeUnit.SECONDS);
    }

    /**
     * 根据一定周期输出线程池的状态
     *
     * @param threadPool     线程池
     * @param threadPoolName 线程池名称
     * @param period         周期
     * @param unit           时间单位
     */
    public static void displayThreadPoolStatus(ThreadPoolExecutor threadPool, String threadPoolName, long period, TimeUnit unit) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            String payload = "[>>ExecutorStatus<<] ThreadPool Name: [{}], Pool Status: [shutdown={}, Terminated={}], Pool Thread Size: {}, Largest Pool Size: {}, Active Thread Count: {}, Task Count: {}, Tasks Completed: {}, Tasks in Queue: {}";
            Object[] params = new Object[]{threadPoolName,
                    threadPool.isShutdown(), threadPool.isTerminated(), // 线程是否被终止
                    threadPool.getPoolSize(), // 线程池线程数量
                    threadPool.getLargestPoolSize(), // 线程最大达到的数量
                    threadPool.getActiveCount(), // 工作线程数
                    threadPool.getTaskCount(), // 总任务数
                    threadPool.getCompletedTaskCount(), // 已完成的任务数
                    threadPool.getQueue().size()};

            if (threadPool.getQueue().remainingCapacity() < 64) {
                log.warn(payload, params);
            } else {
                log.info(payload, params);
            }
        }, 0, period, unit);
    }

    /**
     * 添加Hook在Jvm关闭时优雅的关闭线程池
     *
     * @param threadPool     线程池
     * @param threadPoolName 线程池名称
     */
    public static void hookShutdownThreadPool(ExecutorService threadPool, String threadPoolName) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("[>>ExecutorShutdown<<] Start to shutdown the thead pool: [{}]", threadPoolName);
            // 使新任务无法提交
            threadPool.shutdown();
            try {
                // 等待未完成任务结束
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    threadPool.shutdownNow(); // 取消当前执行的任务
                    log.warn("[>>ExecutorShutdown<<] Interrupt the worker, which may cause some task inconsistent. Please check the biz logs.");

                    // 等待任务取消的响应
                    if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                        log.error("[>>ExecutorShutdown<<] Thread pool can't be shutdown even with interrupting worker threads, which may cause some task inconsistent. Please check the biz logs.");
                    }
                }
            } catch (InterruptedException ie) {
                // 重新取消当前线程进行中断
                threadPool.shutdownNow();
                log.error("[>>ExecutorShutdown<<] The current server thread is interrupted when it is trying to stop the worker threads. This may leave an inconsistent state. Please check the biz logs.");

                // 保留中断状态
                Thread.currentThread().interrupt();
            }

            log.info("[>>ExecutorShutdown<<] Finally shutdown the thead pool: [{}]", threadPoolName);
        }));
    }

    /**
     * 获取返回CPU核数
     *
     * @return 返回CPU核数，默认为8
     */
    public static int getCpuProcessors() {
        return Runtime.getRuntime() != null && Runtime.getRuntime().availableProcessors() > 0 ?
                Runtime.getRuntime().availableProcessors() : 8;
    }

    private static <T> void cancelTask(List<Future<T>> futures) {
        for (Future<T> future : futures) {
            if (!future.isDone()) {
                future.cancel(true);
            }
        }
    }

    public static class ExecutorManager {

        private static final ConcurrentHashMap<String, ThreadPoolExecutor> EXECUTORS = new ConcurrentHashMap<>(8);

        /**
         * 向管理器注册线程池
         *
         * @param threadPoolName 线程池名称
         * @param executor       ThreadPoolExecutor
         */
        public static void registerThreadPoolExecutor(String threadPoolName, ThreadPoolExecutor executor) {
            EXECUTORS.put(threadPoolName, executor);
        }

        /**
         * 根据名称获取线程池
         *
         * @param threadPoolName 线程池名称
         */
        public static ThreadPoolExecutor getThreadPoolExecutor(String threadPoolName) {
            return EXECUTORS.get(threadPoolName);
        }

        /**
         * 获取所有已注册的线程池
         *
         * @return ThreadPoolExecutor
         */
        public static Map<String, ThreadPoolExecutor> getAllThreadPoolExecutor() {
            return ImmutableMap.copyOf(EXECUTORS);
        }

        /**
         * 根据名称移除已注册的线程池
         *
         * @param threadPoolName 线程池名称
         */
        public static void removeThreadPoolExecutor(String threadPoolName) {
            EXECUTORS.remove(threadPoolName);
        }
    }
}
