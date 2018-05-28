package com.fyyzi.test.string;

public class Singleton {
    private Singleton() {
    }

    private static final Singleton SINGLETON = new Singleton();

    public static Singleton getInsatnce() {
        return SINGLETON;
    }
}

class Singleton2 {
    private Singleton2() {}
    private static Singleton2 single = null;

    public synchronized static Singleton2 getInsatnce() {
        if (single == null) {
            synchronized (Singleton2.class) {
                single = new Singleton2();
            }
        }
        return single;
    }
}