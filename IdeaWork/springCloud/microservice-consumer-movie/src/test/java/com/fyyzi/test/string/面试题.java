package com.fyyzi.test.string;

import java.io.Serializable;

public class 面试题 {

    public static void main(String[] args) {
        // 八种基本数据类型名称及对应的封装类 以及大小
        八种基本数据类型();

        equals方法举例();

        // Integer 可以保存null int不可以
        Integer的创建();

        请说出作用域public_private_protected_以及不写时的区别();


        Outer outer = new Outer();

        testString();

    }

    private static void testString() {
        String s = "Hello ";
        int i = s.hashCode();
        s = s + "world";
        int i1 = s.hashCode();

        System.out.println(i);
        System.out.println(i1);
    }

    private static void 请说出作用域public_private_protected_以及不写时的区别() {
        String title = "作用域\t\t当前类\t同一package\t子类\t其他package";
        String pub = "public\t\t\t√\t\t√\t\t√\t\t\t√";
        String pro = "protected\t\t√\t\t√\t\t√\t\t\t×";
        String pub3 = "default\t\t\t√\t\t√\t\t×\t\t\t×";
        String pub4 = "private\t\t\t√\t\t×\t\t×\t\t\t×";

        System.out.println(title);
        System.out.println(pub);
        System.out.println(pro);
        System.out.println(pub3);
        System.out.println(pub4);
    }

    private static void Integer的创建() {
        Integer integer = null;
        integer = new Integer(100);
        System.out.println(integer);
    }

    private static void equals方法举例() {
        String a = "a";
        boolean equals = "a".equals(a);
        System.out.println(equals);
    }

    private static void 八种基本数据类型() {
        System.out.println("byte\tByte\t\t" + Byte.MAX_VALUE);
        System.out.println("short\tShort\t\t" + Short.MAX_VALUE);
        System.out.println("char\tCharacter\t" + Character.MAX_CODE_POINT);
        System.out.println("int\t\tInteger\t\t" + Integer.MAX_VALUE);
        System.out.println("long\tLong\t\t" + Long.MAX_VALUE);
        System.out.println("float\tFloat\t\t" + Float.MAX_VALUE);
        System.out.println("double\tDouble\t\t" + Double.MAX_VALUE);
        System.out.println("boolean\tBoolean\t\t" + Boolean.TRUE + "/" + Boolean.FALSE);
    }

    public void aaa() {
    }

    public String aaa(String a) {
        return "a";
    }

}

class Outer {
    static int out = 0;

    static class Inner1 {
        void test() {
            System.out.println(out);
        }
    }
}

