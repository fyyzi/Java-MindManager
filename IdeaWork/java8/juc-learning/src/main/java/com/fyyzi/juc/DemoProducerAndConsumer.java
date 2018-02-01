package com.fyyzi.juc;

/**
 * 生产者消费者模式
 *
 * @author 息阳
 * 2018/2/1 10:00
 * @version 1.0
 */
public class DemoProducerAndConsumer {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer producer = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(producer,"生产者A").start();
        new Thread(consumer,"消费者B").start();

        new Thread(producer,"生产者C").start();
        new Thread(consumer,"消费者D").start();
    }
}

/**
 * 职员类
 */
class Clerk {

    private int product = 0;

    /**
     * 进货
     */
    public synchronized void get() {
        // 为避免虚假唤醒问题，应该总是使用while来判断wait执行条件
        while (product >= 1) {
            System.out.println("产品已满！无法添加");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + ++product);
        this.notifyAll();
    }

    /**
     * 卖货
     */
    public synchronized void sale() {
        while (product <= 0) {
            System.out.println("缺货！");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + --product);
        this.notifyAll();
    }
}

/**
 * 生产者
 */
class Producer implements Runnable{
    private Clerk clerk;

    public Producer(Clerk clerk){
        super();
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0 ;i < 20;i++){
            clerk.get();
        }
    }
}

/**
 * 消费者
 */
class  Consumer implements Runnable{

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        super();
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0 ;i<20;i++){
            clerk.sale();
        }
    }
}