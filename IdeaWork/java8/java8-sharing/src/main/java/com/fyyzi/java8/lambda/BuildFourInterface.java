package com.fyyzi.java8.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * JDK 1.8 的四大内置接口：<br>
 *     Consumer<T> : 消费型
 *     Supplier<T> : 供给型
 *     Function<T,R> : 函数型
 *     Predicate<T> : 断言型
 *
 *
 * @author 息阳
 * Create in 21:49 2018/1/1
 * @version 1.0
 */
public class BuildFourInterface {

    private static final Logger logger = LoggerFactory.getLogger(BuildFourInterface.class);

    private static int version = 4;


    /**
     * 需求
     *
     * @param money
     * @param consumer 消费型接口 Consumer<T>
     */
    public void happy(BigDecimal money, Consumer<BigDecimal> consumer) {
        consumer.accept(money);
    }

    public void testConsumer() {
        happy(new BigDecimal(10000.00), (m) -> logger.info("消费模式，消费价格为：" + m + "元"));
    }

    // -------------------------------------------------------------

    /**
     * 需求： 产生指定个数的整数，并放入List集合中
     *
     * @param size     所需产生数组元素个数
     * @param supplier 供给型接口 Supplier<T>
     * @return
     */
    public List<Integer> getNumber(int size, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Integer number = supplier.get();
            list.add(number);
        }

        return list;
    }

    public void testSupplier() {
        List<Integer> number = getNumber(10, () -> (int) (Math.random() * 100));
        logger.info(number.toString());
    }

    // -------------------------------------------------------------

    /**
     * 需求： 用于处理字符串
     *
     * @param str      传入需要处理的字符串
     * @param function 函数型接口 Function<T,R>
     * @return
     */
    public String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    public void testFunction() {
        String s = strHandler("\t\t\t 函数型接口字符串trim测试   ", (str) -> str.trim());
        logger.info(s);
        String s1 = strHandler(s, (str) -> str.substring(0, 3));
        logger.info(s1);
    }

    // -------------------------------------------------------------

    /**
     * 需求： 将满足条件的字符串放入集合中
     *
     * @param stringList
     * @param predicate  Predicate<T> 断言型接口
     * @return
     */
    public List<String> filterStr(List<String> stringList, Predicate<String> predicate) {
        List<String> list = new ArrayList<>();
        for (String str : stringList) {
            if (predicate.test(str)) {
                list.add(str);
            }
        }
        return list;

    }

    public void testPredicate() {
        List<String> list = Arrays.asList("Hello", "Lmbda", "Predicate");
        List<String> list1 = filterStr(list, (str) -> str.length() >= 3);
        logger.info(list1.toString());
    }


    public static void main(String[] args) {
        BuildFourInterface buildFourInterface = new BuildFourInterface();
        if (version == 1) {
            buildFourInterface.testConsumer();
        } else if (version == 2) {
            buildFourInterface.testSupplier();
        } else if (version == 3) {
            buildFourInterface.testFunction();
        } else if (version == 4) {
            buildFourInterface.testPredicate();
        }
    }


}
