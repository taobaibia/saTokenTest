package com.yeweiyang.token.serivice.serviceImpl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeweiyang.token.mapper.JayMapper;
import com.yeweiyang.token.pojo.copy.Jay;
import com.yeweiyang.token.serivice.RedisService;
import com.yeweiyang.token.utils.RedisUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static cn.hutool.core.thread.ThreadUtil.sleep;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.serivice.serviceImpl
 * @date 2021/12/17 1:42 下午
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private JayMapper jayMapper;
    @Autowired
    private RedisUtils redisUtils;

    /*放入redis缓存*/
    @Override
    public List<Jay> RedisCacheSave() {

        List<Jay> jays = jayMapper.selectList(new QueryWrapper<>());

        redisUtils.setList("jays", jays);
        List jays1 = redisUtils.getList("jays");
        System.out.println(jays1);
        System.out.println(redisUtils.get("name"));
        System.out.println(redisUtils.getList("address"));
        return null;
    }

    public static void main(String[] args) {
//        createScheduledThreadPool();
//        createThreadPool();
        List<Jay> jayArrayList = new ArrayList<>();
        List<Jay> jayArrayResp = new ArrayList<>();
        Jay jay = new Jay();
        Jay jay1 = new Jay();
        jay1.setId(1L);
        Jay jay2 = new Jay();
        jay2.setId(2L);
        Jay jay3 = new Jay();
        jay3.setId(3L);
        jayArrayList.add(jay1);
        jayArrayList.add(jay2);
        jayArrayList.add(jay3);
        for (Jay jaya : jayArrayList) {
            jaya.setName("lalala");
            BeanUtils.copyProperties(jaya,jay);
            jayArrayResp.add(jay);
        }
        System.out.println(jayArrayResp);



    }

    private static void createScheduledThreadPool() {
        final BlockingQueue queue = new ArrayBlockingQueue<Runnable>(200);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(40, 40, 0L, TimeUnit.MILLISECONDS, queue);
        // by default (unfortunately) the ThreadPoolExecutor will throw an exception
        // when you submit the 201st job, to have it block you do:
        threadPool.setRejectedExecutionHandler(new RejectedExecutionHandler() {

            @SneakyThrows
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // this will block if the queue is full
                executor.getQueue().put(r);
            }
        });
    }

    private static void createThreadPool() {
        ExecutorService executorService = new ThreadPoolExecutor(20, 40, 1, TimeUnit.MINUTES,
                        new ArrayBlockingQueue<>(500, true), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 100; i++) {
            final int index = i;
            executorService.execute(() -> {
                System.out.println(DateUtil.now() + " " + Thread.currentThread().getName() + " " + index);
//                sleep(2000);
            });
        }
    }


}
