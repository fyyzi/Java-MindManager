package com.fyyzi.java8.lambda;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式的基础语法：<br>
 *     JDK 1.8 中引入新的操作符 “->” 该操作符称为箭头操作符 或 Lambda操作符，Lambda操作符将表达式拆分为两部分：<br>
 *         左侧： Lambda表达式的参数列表 （即：接口中抽象方法的参数列表）
 *         右侧： Lambda表达式所需要的功能（Lambda体）即 抽象方法的实现类
 *
 *     语法格式：<br>
 *         1. 无参数、无返回值：<br>
 *             Runnable runnable = () -> logger.info("Hello Lembda!");
 *         2. 有一个参数，无返回值：（若只有一个参数，则可以不写括号）<br>
 *             Consumer<String> consumer = (x) -> logger.info(x);
 *         3. 有两个以上的参数、有返回值、并且Lambda体中有多条语句：<br>
 *             Comparator<Integer> tComparator = (x, y) -> {logger.info("函数式接口"); return Integer.compare(x, y);};
 *             若只有一条语句，则大括号和return 可以省略：<br>
 *                 Comparator<Integer> tComparator = (x, y) -> Integer.compare(x, y);
 *         4. Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器会通过上下文推断出相应的数据类型，即“类型推断”。
 *     基本原则是：能省则省
 *
 * Lambda表达式需要函数式接口的支持:<br>
 *     若接口中只有一个抽象方法的接口，则称为函数式接口。  可以用@FunctionalInterface对函数式接口进行编译判断
 *
 *
 *
 * @author 息阳
 * Create in 13:12 2018/1/1
 * @version 1.0
 */
public class LambdaExpression {

    private final static Logger logger = LoggerFactory.getLogger(LambdaExpression.class);

    public void example01(){
        Runnable runnable = () -> logger.info("Hello Lembda!");
    }

    public void example02(){
        Consumer<String> consumer = (x) -> logger.info(x);
        consumer.accept("Lembda Example 01 ");
    }

    public void example03(){
        Comparator<Integer> tComparator = (x, y) -> {logger.info("函数式接口"); return Integer.compare(x, y);};
    }

    public void example04(){
        Comparator<Integer> comparator = (Integer x, Integer y) -> Integer.compare(x, y);
    }


    public static void main(String[] args) {

        LambdaExpression lambdaExpression = new LambdaExpression();
        lambdaExpression.example04();
    }
}
