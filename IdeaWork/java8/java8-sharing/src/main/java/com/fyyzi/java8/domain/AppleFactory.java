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
                new Apple("green", 150, 3.00, Apple.Size.SMALL)
                , new Apple("yellow", 1000, 4.03, Apple.Size.BIG)
                , new Apple("green", 170, 5.05, Apple.Size.SMALL)
                , new Apple("red", 200, 6.02, Apple.Size.SMALL)
                , new Apple("blue", 2000, 7.05, Apple.Size.SMALL)
                , new Apple("yellow", 1000, 8.01, Apple.Size.BIG)
                , new Apple("yellow", 1100, 9.01, Apple.Size.BIG)
                , new Apple("yellow", 900, 1.04, Apple.Size.BIG)
                , new Apple("yellow", 1000, 5.01, Apple.Size.BIG)
                , new Apple("yellow", 1000, 6.04, Apple.Size.BIG)
                , new Apple("purple", 50, 6.04, Apple.Size.SMALL)
        };
        List<Apple> list = Arrays.asList(apples);
        return list;
    }

}
