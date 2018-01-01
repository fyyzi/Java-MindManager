package com.fyyzi.java8.lambda;

import com.fyyzi.java8.domain.Apple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 通过对苹果颜色及重量筛选举例说明lambda表达式的应用场景<br>
 *     1. 对绿色进行筛选，新建方法findGreenApple，传入一个List；
 *     2. 需求发生改变，需要筛选红色，这时重写将原有方法进行改写，多传入一个参数color；
 *     3. 需求增加，需要增加判断重量,策略模式<br>
 *         1. 增加内部接口，接口的作用是对质量进行判断，返回一个Boolean值
 *         2. 定义内部类（实现内部接口）
 *         3. 创建新方法，适用策略模式进行判断
 *         4. 也可以在使用时定义内部类
 *     4.  适用lambda表达式 (在接口前添加 @FunctionalInterface 告诉编译器在这是一个 防止编译报错)

 *
 * @author 息阳
 * Create in 2:04 2018/1/1
 * @version 1.0
 */
public class FilterApple {

    private final static Logger logger = LoggerFactory.getLogger(FilterApple.class);

    /**
     * 演示环境
     */
    private static int filterVersion = 5;

    /**
     * 3.1 增加内部接口，接口的作用是对质量进行判断，返回一个Boolean值
     */
    @FunctionalInterface
    interface AppleFilter{
        boolean filter(Apple apple);
    }

    /**
     * 3.2 定义内部类（实现内部接口）
     */
    public class GreenAnd160WeightFilter implements AppleFilter{
        @Override
        public boolean filter(Apple apple) {
            return apple.getCollor().equals("green") && apple.getWeight() >= 160;
        }
    }

    /**
     * 3.3 适用策略模式进行判断
     *
     * @param apples
     * @param appleFilter
     * @return
     */
    public List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    /**
     * 1. 对绿色进行筛选，新建方法findGreenApple，传入一个List；
     *
     * @param apples
     * @return
     */
    public List<Apple> findGreenApple(List<Apple> apples) {

        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if ("green".equals(apple.getCollor())) {
                list.add(apple);
            }
        }

        return list;
    }

    /**
     * 2. 需求发生改变，需要筛选红色，这时重写将原有方法进行改写，多传入一个参数color；
     *
     * @param apples
     * @param color
     * @return
     */
    public List<Apple> findAppleByColor(List<Apple> apples, String color) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (color.equals(apple.getCollor())) {
                list.add(apple);
            }
        }
        return list;
    }


    public static void main(String[] args) {
        Apple[] apples = {new Apple("green", 150), new Apple("yellow", 120), new Apple("green", 170)};
        List<Apple> list = Arrays.asList(apples);
        FilterApple filterApple = new FilterApple();

        if (filterVersion == 1){
            List<Apple> greenApples = filterApple.findGreenApple(list);
            assert greenApples.size() == 2;
            logger.info(greenApples.toString());
        }else if (filterVersion == 2){
            List<Apple> redApples = filterApple.findAppleByColor(list, "red");
            logger.info(redApples.toString());
        }else if (filterVersion == 3){
            List<Apple> result = filterApple.findApple(list, filterApple.new GreenAnd160WeightFilter());
            logger.info(result.toString());
        }else if (filterVersion == 4){
            List<Apple> yellowApples = filterApple.findApple(list, new AppleFilter() {
                @Override
                public boolean filter(Apple apple) {
                    return "yellow".equals(apple.getCollor());
                }
            });
            logger.info(yellowApples.toString());
        }else if (filterVersion == 5){
            List<Apple> lambdaResult = filterApple.findApple(list, (Apple apple) -> {
                return "green".equals(apple.getCollor());
            });
            logger.info(lambdaResult.toString());
        }
    }
}
