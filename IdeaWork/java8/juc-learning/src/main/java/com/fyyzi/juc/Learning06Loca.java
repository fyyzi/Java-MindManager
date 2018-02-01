package com.fyyzi.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一、用于解决多线程的方式有三种：<br>
 *     synchronized：（隐式锁）<br>
 *         1. 同步代码块
 *         2. 同步方法
 *     JDK1.5以后<br>
 *         3. 同步锁Lock<br>
 *             注意：这是一个显式锁，需要通过lock()方法上锁，通过unlock()方法释放锁
 *
 * @author 息阳
 * 2018/2/1 8:54
 * @version 1.0
 */
public class Learning06Loca {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(ticket,"1").start();
        new Thread(ticket,"2").start();
        new Thread(ticket,"3").start();
    }

}

class Ticket implements Runnable{
    private int tick = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (tick > 0 ){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t完成售票，余票为：" + --tick);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
