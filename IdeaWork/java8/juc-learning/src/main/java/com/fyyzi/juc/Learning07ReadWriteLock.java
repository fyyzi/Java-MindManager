package com.fyyzi.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock 读写锁<br>
 *     读写互斥
 *
 * @author 息阳
 * 2018/2/1 15:37
 * @version 1.0
 */
public class Learning07ReadWriteLock {

    public static void main(String[] args) {
        DemoReadWriteLock demo = new DemoReadWriteLock();
        new Thread(() -> {
            demo.set((int) (Math.random() * 101));
        }, "Write:").start();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                demo.get();
            }).start();
        }
    }
}

class DemoReadWriteLock {
    private int number = 0;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void get() {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " : " + number);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void set(int number) {
        readWriteLock.writeLock().lock();
        try {
            this.number = number;
            System.out.println(Thread.currentThread().getName());
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
