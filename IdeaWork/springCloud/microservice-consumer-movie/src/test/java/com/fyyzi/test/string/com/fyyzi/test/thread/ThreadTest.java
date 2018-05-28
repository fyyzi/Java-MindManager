package com.fyyzi.test.string.com.fyyzi.test.thread;

public class ThreadTest {

    public static void main(String[] args) {

        StringBuilder a = new StringBuilder("b");
        for (int i = 0; i < 10000000; i++) {
            a.append(i);
        }
        System.out.println(a.length());
        System.out.println(76543211 - 7654320);
    }


}



