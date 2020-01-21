package com.mmall.concurrency.example.atomic;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author YYDCYY
 * @create 2020-01-21
 */
@Slf4j
public class FutureExample {
    static class MyCallable implements Callable<String> {
        public String call() throws Exception {

            log.info(" do somthing in callable ");
            return null;
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

    }
}
