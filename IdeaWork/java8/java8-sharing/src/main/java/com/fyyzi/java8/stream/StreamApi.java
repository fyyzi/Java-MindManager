package com.fyyzi.java8.stream;

import com.fyyzi.java8.domain.Apple;
import com.fyyzi.java8.domain.AppleFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream 的操作步骤：<br>
 *     1. 创建Stream<br>
 *         stream(); // 串行
 *         parallelStream(); // 并行
 *
 *     2. 中间操作<br>
 *         1. 筛选与切片  filter、limit、skip(n)、distinct
 *     3. 终止操作（终端操作）
 *
 * @author 息阳
 * 2018/1/2 13:31
 * @version 1.0
 */
public class StreamApi {

    private final static Logger logger = LoggerFactory.getLogger(StreamApi.class);
    private final static List<Apple> LIST = AppleFactory.getList();

    /**
     * 创建Stream
     */
    public void createStream() {
        // 1. 通过Collection接口获取Stream
        Collection collection = new ArrayList();
        Stream stream1 = collection.stream();

        // 2. 通过Arrays的静态方法stream(xxx)获取;
        Apple[] apples = new Apple[10];
        Stream<Apple> stream2 = Arrays.stream(apples);

        // 3. 通过Stream累中的静态方法of(T... values)获取
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        // 4. 无限流 4.1 迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        // 4.2 生成
        Stream<String> generate = Stream.generate(() -> Math.random() + "");
    }

    /**
     * 中间操作：<br> 位执行终止操作前不会执行任何操作
     *     1. filter    过滤    接口Lambda
     *     2. limit     截断
     *     3. skip(n)   跳过
     *     4. distinct  筛选
     *
     */
    @Test
    public void intermediateOperation() {

        Stream<Apple> appleStream = LIST.stream()
                // filter 过滤
                .filter(apple -> apple.getWeight() > 150)
                // limit 截断
                .limit(2);

        // 终止操作： 一次性执行全部内容，即“惰性求值” 或 “延迟加载”
        appleStream.forEach(apple -> logger.info(apple.toString()));
    }
}
