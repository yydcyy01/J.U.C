package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;


/**
 * @author YYDCYY
 * @create 2020-01-21
 * future.get() 演示
 * executorService , future 看看这个有啥方法.
 */
@Slf4j
public class FutureExample {
    //创建线程
    static class MyCallable implements Callable<String> {
        public String call() throws Exception {
            log.info(" do somthing in callable ");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        log.info(" do somthing in callable ");
        Thread.sleep(1000); // 扔InterruptedException
        String result = future.get(); //处理 ExecutionException 现象 : 结束后应该阻塞在这里.
        log.info("result: {}", result);
        System.out.println("over");
    }
}
