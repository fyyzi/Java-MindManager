package com.fyyzi.java8.lambda;

import com.fyyzi.java8.domain.Apple;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.internal.Function;

import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用： 若Lambda体中的内容已经实现，则可以使用方法引用<br>
 *     一、 引用对象的实例方法<br>
 *         Consumer<String> consumer = logger::info;
 *         Supplier<Long> a = apple::getWeight;
 *     二、 引用静态方法<br>
 *         Comparator<Integer> compare = Integer::compare;
 *     三、 引用特定类型的任意对象的实例方法<br>
 *         BiPredicate<String,String> biPredicate = String::equals;
 *     四、 引用构造函数<br>
 *         BiFunction<String,Long,Apple> biFunction = Apple::new;
 *         需要调用的构造函数要与函数式接口类型保持一致！！！
 *     四＋、 数组的引用<br>
 *         Function<Integer, Object[]> function1 = Object[]::new;
 *
 *
 * 注意：<br>
 *     Lambda体中调用方法 的 参数列表与返回值类型 要与 函数式接口中抽象方法的函数类型和返回值类型保持一致；
 *     若Lambda参数列表中的第一个参数是实例方法的调用者，同时第二个参数是实例方法的参数时 ClassName::method;
 *
 * @author 息阳
 * 2018/1/2 9:14
 * @version 1.0
 */
public class MethodReference {

    private String outString = "这是一段打印测试语句。";
    private static final Logger logger = LoggerFactory.getLogger(MethodReference.class);
    private Apple apple = new Apple();

    /**
     * 一、 引用对象的实例方法<br>
     *     Object::instanceMethodName;
     */
    public void sample01() {
        // 例一
        Consumer<String> con = (x) -> logger.info(x);
        Consumer<String> consumer = logger::info;

        con.accept(outString);
        consumer.accept(outString);

        // 例二
        Supplier<String> supplier = () -> apple.getCollor();
        Supplier<Integer> a = apple::getWeight;

        logger.info(supplier.get());
        logger.info(a.get().toString());
    }

    /**
     * 二、 引用静态方法<br>
     *     Comparator<Integer> compare = Integer::compare;
     */
    public void sample02() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> compare = Integer::compare;

        logger.info(com.compare(21, 10) + "");
        logger.info(compare.compare(21, 21) + "");
    }

    /**
     * 三、 引用特定类型的任意对象的实例方法<br>
     *      BiPredicate<String,String> biPredicate = String::equals;
     */
   public void sample03(){
       BiPredicate<String,String> biPre = (x,y)->x.equals(y);
       BiPredicate<String,String> biPredicate = String::equals;

       logger.info("" + biPre.test("你","你"));
       logger.info("" + biPredicate.test("你","你"));
   }

    /**
     *  四、 引用构造函数<br>
     *      BiFunction<String,Long,Apple> biFunction = Apple::new;
     */
   public void sample04(){
       BiFunction<String,Integer,Apple> biFun = (x,y)->new Apple(x,y);
       BiFunction<String,Integer,Apple> appleBiFunction = Apple::new;
       Supplier<Apple> appleSupplier = Apple::new;
   }

    /**
     * 数组的引用<br>
     *     Function<Integer, Object[]> function1 = Object[]::new;
     */
   public void sample05() {
       Function<Integer, Object[]> func = (size) -> new Object[size];
       Function<Integer, Object[]> function1 = Object[]::new;

       Object[] objs = func.apply(10);
       Object[] objects = function1.apply(20);
       logger.info("" + objs.length);
       logger.info("" + objects.length);
   }

    public static void main(String[] args) {
        MethodReference methodReference = new MethodReference();
        logger.info("======================引用对象的实例方法======================");
        methodReference.sample01();
        logger.info("=========================引用静态方法=========================");
        methodReference.sample02();
        logger.info("===============引用特定类型的任意对象的实例方法===============");
        methodReference.sample03();
        logger.info("==========================引用构造器==========================");

        logger.info("==========================数组的引用==========================");
        methodReference.sample05();
    }
}
