package com.fyyzi.java8.stream;

import com.fyyzi.java8.domain.Apple;
import com.fyyzi.java8.domain.AppleFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 映射：<br>
 *     map——接口Lambda，将元素转换成其他形式或提取信息。接受一个函数作为参数，该函数会被应用到每一个元素上，并将其映射成一个新元素。
 *     flatMap——接受一个函数作为参数，将流中的每一个值都换成另一个流，然后吧所有流连接成一个流。
 *
 * @author 息阳
 * Create in 22:54 2018/1/3
 * @version 1.0
 */
public class StreamMapping {

    private List<Apple> apples = AppleFactory.getList();
    private static final Logger logger = LoggerFactory.getLogger(StreamMapping.class);
    private static final StreamMapping streamMapping = new StreamMapping();

    /**
     * 演示map和flatMap
     */
    public void usedMapping() {

        apples.stream()
                .map(apple -> apple.getCollor().toUpperCase())
                .forEach((str) -> logger.info(str));

        logger.info("=================================================");

        apples.stream()
                .map(apple -> apple.getCollor())
                .forEach(s -> logger.info(s));

        logger.info("=================================================");

        apples.stream()
                .map(streamMapping::filterCharacter)
                .forEach(characterStream -> characterStream.
                        forEach(character -> logger.info(character + ""))
                );

        logger.info("=================================================");

        apples.stream()
                .flatMap(streamMapping::filterCharacter)
                .forEach(character -> logger.info(character + ""));

    }

    public Stream<Character> filterCharacter(Apple apple) {
        List<Character> list = new ArrayList<>();

        for (Character character : apple.getCollor().toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }

    /**
     * 演示list.add() 和 list.addAll()
     */
    public void listAddAndAddAll() {
        List<String> list = Arrays.asList("aa", "bb");

        List list1 = new ArrayList();
        list1.add("11");
        list1.add("22");
        list1.addAll(list);
        System.out.println(list1);
    }

}
