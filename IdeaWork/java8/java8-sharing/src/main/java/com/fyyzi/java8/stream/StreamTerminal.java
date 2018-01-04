package com.fyyzi.java8.stream;

import com.fyyzi.java8.domain.Apple;
import com.fyyzi.java8.domain.AppleFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终端操作<br>
 *     1. 匹配<br>
 *         allMatch     全部匹配 时才返回 true
 *         anyMatch     只要有一个元素匹配 就返回 true
 *         noneMatch    Stream中没有匹配元素 时 返回 true
 *     2. 查找<br>
 *         findFirst    返回第一个
 *         findAny      返回随意一个(该项在使用paralleStream时比较容易体现出来)
 *     3. 组函数<br>
 *         min          根据条件获取最小值
 *         max          根据条件获取最大值
 *         count        统计数量
 *     4. 规约    reduce(T identity, BinaryOperator/reduce(BinaryOperator));<br>
 *         一定不为空的  返回对应对象
 *         若有可能为空则返回Optional<T>对象
 *         map - reduce 作为主要的数据搜索服务
 *     5. 收集    collect——将流转换为其他形式。接受一个Collector接口的实现，用于给Stream中元素做汇总方法；<br>
 *         转换<br>
 *             collect(Collectors.toList());        转换为List
 *             collect(Collectors.toSet());         转换为set
 *             toCollection(Collectors.toCollection(HashMap::new));     转换为HashMap
 *         统计<br>
 *             总数
 *             平均值
 *             求和
 *             最大值
 *             最小值
 *         分组<br>
 *             groupingBy()   可以实现多级分组
 *         分区<br>
 *             partitioningBy()     将对象分为A区和B区域    以boolean 作为区分
 *         组函数<br>
 *             summarizingXXXX()
 *         Join<br>
 *             连接
 *
 *
 *
 *
 *
 * @author 息阳
 * 2018/1/4 10:46
 * @version 1.0
 */
public class StreamTerminal {
    protected static StreamTerminal streamTerminal = new StreamTerminal();
    private static Logger logger = LoggerFactory.getLogger(StreamTerminal.class);
    List<Apple> apples = AppleFactory.getList();

    private Match match = new Match();
    private Find find = new Find();
    private FunctionGroup functionGroup = new FunctionGroup();
    private Reduce reduce = new Reduce();
    private Collect collect = new Collect();

    @Test
    public void terminal() {

//        match.match();
//        find.find();
//        functionGroup.functionGroup();
//        reduce.reduce();
        collect.collect();
    }
}

/**
 * 匹配<br>
 *     allMatch     全部匹配 时才返回 true
 *     anyMatch     只要有一个元素匹配 就返回 true
 *     noneMatch    Stream中没有匹配元素 时 返回 true
 */
class Match{
    private static Logger logger = LoggerFactory.getLogger(Match.class);
    private static List<Apple> apples = AppleFactory.getList();

    protected  void match() {
        boolean allMatch = apples.stream().allMatch(apple -> apple.getSize().equals(Apple.Size.SMALL));
        boolean anyMatch = apples.stream().anyMatch(apple -> apple.getSize().equals(Apple.Size.SMALL));
        boolean noneMatch = apples.stream().noneMatch(apple -> apple.getSize().equals(Apple.Size.NONE));

        logger.info(allMatch + "");
        logger.info(anyMatch + "");
        logger.info(noneMatch + "");
    }
}

/**
 * 查找<br>
 *     findFirst    返回第一个
 *     findAny      返回随意一个(该项在使用paralleStream时比较容易体现出来)
 */
class Find{
    private static Logger logger = LoggerFactory.getLogger(Find.class);
    private static List<Apple> apples = AppleFactory.getList();

    protected void find() {
        Optional<Apple> findFirst = apples.parallelStream().sorted((a1, a2) -> Double.compare(a1.getPirce(), a2.getPirce()))
                .findFirst();
        logger.info(findFirst.toString());

        Optional<Apple> findAny = apples.parallelStream().filter(apple -> apple.getSize().equals(Apple.Size.SMALL))
                .findAny();
        logger.info(findAny.toString());
    }
}

/**
 * 组函数<br>
 *     min          根据条件获取最小值
 *     max          根据条件获取最大值
 *     count        统计数量
 */
class FunctionGroup{
    private static Logger logger = LoggerFactory.getLogger(FunctionGroup.class);
    private static List<Apple> apples = AppleFactory.getList();

    protected void functionGroup() {

        Optional<Apple> max = apples.stream().max((a1, a2) -> Double.compare(a1.getWeight(), a2.getWeight()));
        Optional<Apple> min = apples.stream().min((a1, a2) -> Double.compare(a1.getWeight(), a2.getWeight()));
        long count = apples.stream().count();

        logger.info(max.toString());
        logger.info(min.toString());
        logger.info(count + "");
    }
}

/**
 *     规约    reduce(T identity, BinaryOperator/reduce(BinaryOperator));<br>
 *       一定不为空的  返回对应对象
 *       若有可能为空则返回Optional<T>对象
 *                            map - reduce 作为主要的数据搜索服务
 */
class Reduce{

    private static Logger logger = LoggerFactory.getLogger(Reduce.class);
    private static List<Apple> apples = AppleFactory.getList();

    protected void reduce() {
        integerReduce();
        appleReduce();
    }

    /**
     * 一定不为空的  返回对应对象
     */
    private void integerReduce(){
        List<Integer> integerList = AppleFactory.getIntegerList();
        Integer reduce = integerList.stream()
                .reduce(0, (x,y)->x+y);
        logger.info(reduce + "");
    }

    /**
     * 有可能为空的 返回Optional<T>对象
     */
    private void appleReduce() {
        Optional<Double> applePriceReduce = apples.stream()
                .map(apple -> apple.getPirce())
                .reduce(Double::sum);
        logger.info(applePriceReduce + "");
    }
}


class Collect{
    private static Logger logger = LoggerFactory.getLogger(Collect.class);
    private static List<Apple> apples = AppleFactory.getList();

    protected void collect() {
        collectToList();
        logger.info("====================");
        collectToSer();
        logger.info("====================");
        collectTocollection();
        collectOther();
        collectGroupingBy();
        join();
    }

    /**
     * 转换为List
     */
    private void collectToList(){
        List<String> collectToList = apples.stream()
                .map(apple -> apple.getCollor())
                .collect(Collectors.toList());
        collectToList.stream()
                .forEach(s -> logger.info(s.toString()));
    }

    /**
     * 转换为Set
     */
    private void collectToSer(){
        apples.stream()
                .map(apple -> apple.getCollor())
                .collect(Collectors.toSet())

                .forEach(s -> logger.info(s.toString()));
    }

    /**
     * 转换为任意
     */
    private void collectTocollection(){
        HashSet<String> collectTocollection = apples.stream()
                .map(Apple::getCollor)
                .collect(Collectors.toCollection(HashSet::new));
        logger.info(collectTocollection.toString());
    }

    /**
     * 统计
     */
    private void collectOther(){
        // 总数
        Long collect = apples.stream()
                .map(Apple::getCollor)
                .collect(Collectors.counting());
        logger.info(collect.toString());

        // 平均值
        Double collect1 = apples.stream()
                .collect(Collectors.averagingDouble(Apple::getWeight));
        logger.info(collect+"");

        // 求和
        DoubleSummaryStatistics collectToSum = apples.stream()
                .collect(Collectors.summarizingDouble(Apple::getPirce));
        logger.info(collectToSum + "");

        // 最大值
        Optional<Apple> collectToMax = apples.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getPirce(), e2.getPirce())));
        logger.info(collectToMax.toString());

        // 最小值
        Optional<Apple> collectToMin = apples.stream()
                .collect(Collectors.maxBy((e1, e2) -> -Double.compare(e1.getPirce(), e2.getPirce())));
        logger.info(collectToMin.get().toString());
    }

    /**
     * 分组 及 多级分组
     */
    private void collectGroupingBy(){
        Map<Apple.Size, List<Apple>> collect = apples.stream()
                .collect(Collectors.groupingBy(Apple::getSize));

        Map<Apple.Size, Map<String, List<Apple>>> collect1 = apples.stream()
                .collect(Collectors.groupingBy(Apple::getSize, Collectors.groupingBy((Apple apple)->{
                    if (apple.getWeight()<=100){
                        return "小苹果";
                    }else if (apple.getWeight()<=500){
                        return "大苹果";
                    }else{
                        return "巨无霸苹果";
                    }
                })));
    }

    /**
     * 分区
     */
    private void partitioning(){
        Map<Boolean, List<Apple>> collect = apples.stream()
                .collect(Collectors.partitioningBy(apple -> apple.getWeight() > 5));
    }

    /**
     * 组函数
     */
    private void functionGroup(){
        DoubleSummaryStatistics summary = apples.stream()
                .collect(Collectors.summarizingDouble(Apple::getPirce));
        logger.info("" + summary.getAverage());
        logger.info("" + summary.getMax());

    }

    /**
     * 连接
     */
    private void join(){
        String collect = apples.stream()
                .map(Apple::getCollor)
                .collect(Collectors.joining());
        logger.info(collect);

        logger.info(apples.stream().map(Apple::getCollor).collect(Collectors.joining(",")));
    }
}



