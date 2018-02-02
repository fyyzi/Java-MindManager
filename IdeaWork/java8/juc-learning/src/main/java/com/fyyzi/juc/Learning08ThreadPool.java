package com.fyyzi.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 一、线程池：提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁线程的额外开销，提高了响应的速度。<br>
 *     Interface Executor ： 维护现成的使用和根接口
 *
 * 二、线程池的体系结构：<br>
 *     java.util.concurrent.Executor            负责线程的使用与调度的根接口<br>
 *         |-- ExecutorService                  负责线程池的主要接口<br>
 *             |-- ThreadPoolExecutor           线程池的实现类
 *             |-- ScheduledExecutorService     负责现成调度的子接口<br>
 *                 |-- ScheduledPoolExecutorService     继承了ThreadPoolExecutor  同时 实现了 ScheduleExecutorService
 *
 * 三、工具类：Executors<br>
 *     newFixedThreadPool(int nThreads)  ： 创建固定大小的线程池
 *     newCachedThreadPool()        ： 缓存线程池，线程池的数量是不固定的，可以根据需求自动的更改数量
 *     newSingleThreadExecutor()    ： 创建单个的线程池，线程池中只有一个现成
 *
 *     newScheduledThreadPool(int corePoolSize)     ： 创建固定大小的线程池，返回ScheduledThreadPool，可以延时或定时的执行任务。
 *
 * 四、线程池的使用：<br>
 *     1. 创建线程池
 *     2. 向线程池里添加任务
 *     3. 以平和的方式关闭线程池
 *
 *
 *
 * @author 息阳
 * 2018/2/1 17:06
 * @version 1.0
 */
public class Learning08ThreadPool {

    private int i = 0;

    public static void main(String[] args) {
        DemoThreadPool demoThreadPool = new DemoThreadPool();
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 3. 为线程池分配任务
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            executorService.submit(demoThreadPool);
            Future<Integer> submit = executorService.submit(() -> {
                int sum = 0;
                for (int j = 0; j < 100; j++) {
                    sum += j;
                }
                return sum;
            });
            futures.add(submit);
        }
        // 2. 关闭线程池
        executorService.shutdown();

        for (Future<Integer> future:futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
class DemoThreadPool implements Runnable{

    private int i = 0;

    @Override
    public void run() {
        while (i<=100){
            System.out.println(Thread.currentThread().getName() + ":" + i++);
        }
    }
}
