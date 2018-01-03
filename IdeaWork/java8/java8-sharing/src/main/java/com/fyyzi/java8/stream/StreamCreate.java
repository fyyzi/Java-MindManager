package com.fyyzi.java8.stream;

import com.fyyzi.java8.domain.Apple;
import com.fyyzi.java8.domain.AppleFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream的创建方式：<br>
 *     1. 通过Collection接口获取Stream;
 *     2. 通过Arrays的静态方法stream(xxx)获取;
 *     3. 通过Stream累中的静态方法of(T... values)获取;
 *     4. 无限流<br>
 *         4.1 迭代
 *         4.2 生成
 *
 * @author 息阳
 * Create in 22:31 2018/1/3
 * @version 1.0
 */
public class StreamCreate {

    private final static Logger logger = LoggerFactory.getLogger(StreamApi.class);
    private final static List<Apple> LIST = AppleFactory.getList();

    /**
     * 创建Stream 的四种方式
     */
    @SuppressWarnings("Duplicates")
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

}
