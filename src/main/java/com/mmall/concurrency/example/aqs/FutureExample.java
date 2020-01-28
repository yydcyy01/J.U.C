package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;


/**
 * @author YYDCYY
 * @create 2020-01-21
 * executorService , future 看看这个有啥方法.
 */
@Slf4j
public class FutureExample {
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
        String result = future.get(); //处理 ExecutionException
        log.info("result: {}", result);
    }
}
