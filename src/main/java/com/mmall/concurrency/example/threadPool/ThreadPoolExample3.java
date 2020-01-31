package com.mmall.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author YYDCYY
 * @create 2020-02-01
 * ScheduledExecutorService =  Executors.newScheduledThreadPool(1); 演示  这个传参不是很明白, 待补充.
 * executorService.schedule(new Runnable() { ...}, 延迟时间, 单位);
 * 使用 Timer 实现相同定时执行线程任务效果.
 * 结果 延迟 2 秒输出线程打印内容.
 */
@Slf4j
public class ThreadPoolExample3 {
    public static void main(String[] args) {
        /**
         * 放置 10 个任务,  
         */
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        /*for (int i = 0; i < 10; i++) {
            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    log.warn("schedule run");
                }
            }, 100, TimeUnit.MICROSECONDS); //
        }*/ // 没搞懂, 咋一下子全输出了 10 个

        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
        }, 3, TimeUnit.SECONDS); // 停顿 3 秒, 完成线程


        // 使用 Timer 实现相同效果
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
        }, new Date(), 2 * 1000); // 隔 2 秒钟执行线程.

        //executorService.shutdown(); // 关闭线程池, 不然不会关闭.
    }
}
