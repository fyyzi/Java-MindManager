package com.fyyzi.juc;

/**
 * 一、Volatile关键字：当多个线程操作共享数据的时候，可以保证内存中的数据可见。<br>
 *     相较于synchronized是一种较为轻量级的同步策略。
 *
 *     注意: <br>
 *         1. volatile 不具有“互斥性”
 *         2. volatile 不能保证变量的“原子性”
 *
 * @author 息阳
 * @version 1.0
 * @date 2018年1月23日
 */
public class LearningVolatile {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();

        while (true) {
            if (threadDemo.isFlag()) {
                System.out.println("--------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {

    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag=" + flag);
    }

    public boolean isFlag() {
        return flag;
    }
}