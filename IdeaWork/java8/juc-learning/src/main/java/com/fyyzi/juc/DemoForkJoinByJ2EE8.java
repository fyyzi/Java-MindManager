package com.fyyzi.juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * JDK1.8 新特性 fork/join
 *
 * @author 息阳
 * 2018/2/2 16:48
 * @version 1.0
 */
public class DemoForkJoinByJ2EE8 {

    private static final Logger logger = LoggerFactory.getLogger(DemoForkJoinByJ2EE8.class);

    public static void main(String[] args) {

        Instant start = Instant.now();

        long sum = LongStream.rangeClosed(0L, 1000000000000L)
                .parallel()
                .reduce(0L, Long::sum);
        logger.info("计算结果为：{}" , sum);

        Instant end = Instant.now();
        long millis = Duration.between(start, end).toMillis();
        logger.info("累计耗费时间为：{}" , millis);

    }

}
