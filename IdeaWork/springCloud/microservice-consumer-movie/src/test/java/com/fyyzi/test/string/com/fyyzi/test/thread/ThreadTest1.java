package com.fyyzi.test.string.com.fyyzi.test.thread;

/*
 *  无序的
 */
public class ThreadTest1 {

    private Integer j = 0;

    public static void main(String[] args) {
        ThreadTest1 threadTest = new ThreadTest1();
        Inc inc = threadTest.new Inc();
        Dec dec = threadTest.new Dec();

        for (int i = 0; i < 2; i++) {
            Thread t1 = new Thread(inc);
            Thread t2 = new Thread(dec);

            t1.start();
            t2.start();

        }
    }

    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "-inc:" + j);
    }

    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + "-dec:" + j);
    }

    class Inc implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1; i++) {
                inc();
            }
        }
    }

    class Dec implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1; i++) {
                dec();
            }
        }
    }
}

// -----------------------------
// 随手在写一个
class A {
    JManager jManager = new JManager();

    public static void main(String[] args) {
        new A().call();
    }

    private void call() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    while (true) {
                    jManager.accumulate();
//                    }
                }
            }).start();
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
//                            while (true) {
                            jManager.subact();
//                            }
                        }
                    }
            ).start();
        }
    }
}

class JManager {
    private int j = 0;

    public synchronized void subact() {
        j--;
        System.out.println(j);
    }

    public synchronized void accumulate() {
        j++;
        System.out.println(j);
    }
}
