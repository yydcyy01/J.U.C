package com.mmall.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author YYDCYY
 * @create 2020-02-01
 * ExecutorService = Executors.newSingleThreadExecutor();
 * fori 生成 10 个executorService.execute(new Runnable() {...}) 线程
 * // 多态, Executors 生成对象不同.
 * 结果 按序0-9 线程输出
 */
@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        /**
         * 放置 10 个任务,  
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task : {}", index);
                }
            });
        }
        executorService.shutdown(); // 关闭线程池, 不然不会关闭.
    }
}
