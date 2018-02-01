package com.fyyzi.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 息阳
 * 2018/2/1 13:16
 * @version 1.0
 */
public class DemoProducerAndConsumerForLock {
    public static void main(String[] args) {
        Clerk cleck = new CleckForLock();
        Producer producer = new Producer(cleck);
        Consumer consumer = new Consumer(cleck);
        new Thread(producer, "生产者A").start();
        new Thread(consumer, "消费者B").start();
        new Thread(producer, "生产者C").start();
        new Thread(consumer, "消费者D").start();
    }
}

class CleckForLock extends Clerk {
    private int product = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    @Override
    public void get() {
        lock.lock();
        try {
            while (product >= 1) {
                System.out.println("产品已满！");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName() + " : " + ++product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    /**
     * 卖货
     */
    public void sale() {
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println("缺货！");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + --product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
