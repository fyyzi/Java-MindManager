package com.fyyzi.java8.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.OptionalLong;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 并行操作     Fork/Join框架： 就是在必要的情况下，将一个大任务进行拆分（fork），拆分为若干各小任务（拆到不可拆为止）
 *                              在将小任务运算结果进行（join）汇总。
 *              Fork/Join框架采用工作窃取模式（work-stealing）
 *
 * @author 息阳
 * 2018/1/5 9:29
 * @version 1.0
 */
public class ForkJoin {

    private static final Logger logger = LoggerFactory.getLogger(ForkJoin.class);

    private static Long aLong = 1*10000*10000L;

    /**
     * 并行流方式
     */
    public void forkJoinStream(){
        Instant startTime = Instant.now();
        OptionalLong reduce = LongStream.range(0L, aLong +1)
                .parallel()
                .reduce(Long::sum);
        Instant endTime = Instant.now();
        logger.info("计算结果{}",reduce);
        logger.info("计算消耗时间为{}", Duration.between(startTime,endTime).toMillis());

    }

    /**
     * forkJoin方式
     */
    public void forkJoin() {
        Instant startTime = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0L, aLong);
        Long invoke = pool.invoke(task);

        Instant endTime = Instant.now();
        logger.info("计算结果为{}",invoke);
        logger.info("计算消耗时间为{}", Duration.between(startTime,endTime).toMillis());
    }
}

class ForkJoinCalculate extends RecursiveTask<Long> {

    private static final long serialVersionUID = 3040163410595058098L;

    private Long start;
    private Long end;

    private static final Long THRESHOLD = 50000000L;

    public ForkJoinCalculate(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        Long length = end - start;

        if (length < THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            Long middle = (start + end) / 2;

            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();

            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();

            return left.join() + right.join();
        }

    }

}
