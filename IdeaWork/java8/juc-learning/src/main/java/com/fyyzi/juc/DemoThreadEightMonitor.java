package com.fyyzi.juc;

/**
 *
 * 线程八锁<br>
 *		①两个普通同步方法，两个线程，标准打印？ // one two
 *		②新增Thread.sleep()给getOne(),标准打印？ // one two
 *		③新增普通方法getThree()，打印？  //three one two
 *		④两个普通同步方法，两个Number对象，打印？ // two one
 *		⑤修改getOne()为静态方法，一个Number对象，打印？ // two one
 *		⑥修改两个方法均均为静态同步方法，一个Number对象？ // one two
 *		⑦一个静态同步方法，一个非静态同步方法，两个Number对象？ // two one
 *		⑧两个静态同步方法，两个Number对象？ //one two
 *
 * 结论：<br>
 *     1. 非静态方法的锁默认为this，静态方法的锁为Class类
 *     2. 某一时刻内只能有一个线程持有锁（相同锁）
 *     3. 静态同步方法和非静态同步方法使用过的锁不一样，因此不会互相产生影响
 * @author 息阳
 * 2018/2/1 15:56
 * @version 1.0
 */
public class DemoThreadEightMonitor {
    public static void main(String[] args) {
        DemoNumber number = new DemoNumber();
        DemoNumber number2 = new DemoNumber();
        new Thread(()->{
            number.getOne();
        }).start();
        new Thread(()->{
            number2.getTwo();
//            number.getTwo();
        }).start();
        /*new Thread(()->{
            number.getThree();
        }).start();*/
    }
}
class DemoNumber{
    public static synchronized void getOne(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public static synchronized  void getTwo(){
        System.out.println("two");
    }

    public void getThree() {
        System.out.println("three");
    }
}
