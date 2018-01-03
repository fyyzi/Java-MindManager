package com.fyyzi.java8.domain;

import java.util.Arrays;
import java.util.List;

/**
 * @author 息阳
 * 2018/1/2 14:34
 * @version 1.0
 */
public class AppleFactory {

    public static List<Apple> getList() {
        Apple[] apples = {
                new Apple("green", 150)
                , new Apple("yellow", 1000)
                , new Apple("green", 170)
                , new Apple("red", 200)
                , new Apple("blue", 2000)
                , new Apple("yellow", 1000)
                , new Apple("yellow", 1100)
                , new Apple("yellow", 900)
                , new Apple("yellow", 1000)
                , new Apple("yellow", 1000)
        };
        List<Apple> list = Arrays.asList(apples);
        return list;
    }

}
