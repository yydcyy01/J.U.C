package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author YYDCYY
 * @create 2020-01-28
 *
 * futureTask.get();
 */
@Slf4j
public class FutureTaskExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                log.info(" do somthing in callable ");
                Thread.sleep(5000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(1000); //InterruptedException
        String result = futureTask.get(); // ExecutionException
        log.info("result : {} ", result);

    }
}
