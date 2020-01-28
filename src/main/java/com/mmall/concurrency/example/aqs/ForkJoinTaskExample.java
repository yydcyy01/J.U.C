package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;
import sun.awt.DefaultMouseInfoPeer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author YYDCYY
 * @create 2020-01-28
 *  大任务拆分成小任务.
 *  工作中涉及不多, 算法思想.工作窃取算法: 大任务切分为小子任务. 为减少线程竞争, 把任务放在队列中.
 *  局限性 : ①任务只能使用 fork , join 方法. ②不应执行 I/O 操作 ③  不能抛出检查异常
 *  使用时看不懂多看这两个例子.
 */
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer> {
    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    // 计算 1到 100. 简单运算模拟复杂任务
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= threshold;
        //如果任务足够小才计算任务
        if (canCompute) {
            for (int i = start; i <= end; i ++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start, middle);
            ForkJoinTaskExample rightTask = new ForkJoinTaskExample(middle + 1, end);

            // 执行子任务
            leftTask.fork();// 调用 fork 执行子任务
            rightTask.fork();

            // 等待任务执行结束合并其结果
            int leftResult = leftTask.join(); // 合并结果.
            int rightResult = rightTask.join();

        // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //生成一个计算任务，计算1+2+3+4
        ForkJoinTaskExample task = new ForkJoinTaskExample(1, 100);
        //执行一个任务 result 这个命名清晰易懂.
        Future<Integer> result = forkJoinPool.submit(task);

        try {
            log.info("resutl : {}" , result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
