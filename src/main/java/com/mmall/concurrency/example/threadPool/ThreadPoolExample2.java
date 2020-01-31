package com.mmall.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author YYDCYY
 * @create 2020-02-01
 * Executors.newCachedThreadPool 演示
 */
@Slf4j
public class ThreadPoolExample2 {
    public static void main(String[] args) {
        /**
         * 放置 10 个任务,  
         */
        ExecutorService executorService = Executors.newFixedThreadPool(3);
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
