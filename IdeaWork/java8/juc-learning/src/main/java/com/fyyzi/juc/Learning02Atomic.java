package com.fyyzi.juc;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/**
 * 二、原子变量：jdk1.5以后java.util.concurrent.atomic包下提供了常用的原子变量：<br>
 * 1. valatile 保证内存可见性<br>
 * <p>
 * 2. CAS(Compare-And-Swap) 算法保证数据的原子性<br>
 * CAS算法是硬件对于并发操作共享数据的支持
 * CAS包括了三个操作数：<br>
 * 内存值	V
 * 预估值	A
 * 更新至	B
 * 当且仅当V == A 时，V = B，否则，将不做任何操作。
 *
 * @author 息阳
 * 2018/1/24 10:21
 * @version 1.0
 */
public class Learning02Atomic {

    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                int expectedValue = cas.getValue();
                boolean b = cas.compareAndSet(expectedValue, (int) (Math.random() * 10));
                if (b == false) {
                    System.out.println(b);
                }
            }).start();
        }
    }
}

class CompareAndSwap {

    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            this.value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}
