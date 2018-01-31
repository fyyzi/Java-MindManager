package com.fyyzi.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * 一、创建执行线程的方式三：实现Callable接口。相较于实现Runnable接口的方式，这种方法可以有返回值，并且可以抛出异常。
 *
 * 二、执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果。FutureTask是Future接口的实现类。
 *
 * @author 息阳
 * 2018/1/29 16:38
 * @version 1.0
 */
public class Learning05Callable {

    @Test
    public void test(){
        DemoCallable demoCallable = new DemoCallable();

        // 1. 执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果。
        FutureTask<Integer> integerFutureTask = new FutureTask<>(demoCallable);
        new Thread(integerFutureTask).start();

        // 2. 接收线程运算结果
        try {
            // FutureTask也可用于闭锁
            Integer integer = integerFutureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }

}
class DemoCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0 ;
        for (int i = 0 ;i<=100;i++){
            sum += i;
        }
        return sum;
    }
}
