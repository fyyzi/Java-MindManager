package com.fyyzi.juc;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 * 闭锁：
 *
 * @author 息阳
 * 2018/1/29 10:47
 * @version 1.0
 */
public class Learning04CountDownLatch {

    private static final Logger logger = LoggerFactory.getLogger(Learning04CountDownLatch.class);

    @Test
    public void test01() {
        Instant start = Instant.now();
        final CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (this) {
                    try {
                        for (int j = 0; j < 5000; j++) {
                            if (j % 2 == 0) {
                                System.out.println(j);
                            }
                        }
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }

        Instant end = Instant.now();

        logger.info(Duration.between(start, end).toMillis() + "");

    }
}
