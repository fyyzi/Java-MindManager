package com.fyyzi.java8.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1. lambda表达式可以让代码更清晰
 *
 * @author 息阳
 * Create in 12:50 2018/1/1
 * @version 1.0
 */
public class LambdaThread {

    private final static Logger logger = LoggerFactory.getLogger(LambdaThread.class);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info(Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> {
            logger.info(Thread.currentThread().getName());
        }).start();


        Thread.currentThread().join();
    }
}
