package com.fyyzi.juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 注: pool.schedule 方法 传入 Runnable 和 Callable 返回结果不同的原因是：<br>
 *     使用 Callable时for循环里面的打印语句需要返回值,因此有一个等待的过程
 *     而Runnable是5个线程同时执行的，因此回体现出一起打印
 *
 * @author 息阳
 * 2018/2/2 16:21
 * @version 1.0
 */
public class Learning09ScheduledThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        System.out.println("action");
        for (int i = 0; i < 10; i++) {
            ScheduledFuture<Integer> future = pool.schedule(() -> {
                int num = new Random().nextInt(100);
                System.out.println(Thread.currentThread().getName() + " : " + num);
                return num;
            }, 3, TimeUnit.SECONDS);
            System.out.println(future.get());
        }
        pool.shutdown();
    }


}
