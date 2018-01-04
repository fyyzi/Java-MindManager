package com.fyyzi.java8.stream;

import com.fyyzi.java8.domain.Apple;
import com.fyyzi.java8.domain.AppleFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *     中间操作<br>
 *        1. 筛选与切片： filter、limit、skip(n)、distinct
 *        2. 映射：map()、flatMap()
 *        3. 排序：sorted()、sorted(Compartor compartor)
 *
 * @author 息阳
 * 2018/1/4 10:46
 * @version 1.0
 */
public class StreamIntermediate {

    /**
     * 2. Mapping
     */
    private static StreamFilterLimitSkinDistinct streamFilterLimitSkinDistinct = new StreamFilterLimitSkinDistinct();
    private static StreamMapping streamMapping = new StreamMapping();
    private static StreamSorted streamSorted = new StreamSorted();

    public void intermediate() {
        // 筛选与切片
        streamFilterLimitSkinDistinct.intermediateOperation();

        // 映射
        streamMapping.usedMapping();

        // 排序
        streamSorted.sorted();
    }

}

/**
 * 切片与筛选<br>
 *     1. filter    过滤    接口Lambda（从流中排除某学元素）   Stream转换语句，原有Stream对象不改变，返回一个新的Stream对象
 *     2. limit     截断    停止迭代
 *     3. skip(n)   跳过    返回一个扔掉了前n个元素的溜，若元素不足n则返回一个空流。  可以与limit互补
 *     4. distinct  筛选    去重，通过hashCode() 和 equals() 去重
 */
class StreamFilterLimitSkinDistinct{

    protected static StreamFilterLimitSkinDistinct streamFilterLimitSkinDistinct = new StreamFilterLimitSkinDistinct();
    private static final Logger logger = LoggerFactory.getLogger(StreamFilterLimitSkinDistinct.class);
    protected static List<Apple> apples = AppleFactory.getList();

    /**
     * 中间操作：<br> 位执行终止操作前不会执行任何操作
     *     1. filter    过滤    接口Lambda（从流中排除某学元素）   Stream转换语句，原有Stream对象不改变，返回一个新的Stream对象
     *     2. limit     截断    停止迭代
     *     3. skip(n)   跳过    返回一个扔掉了前n个元素的溜，若元素不足n则返回一个空流。  可以与limit互补
     *     4. distinct  筛选    去重，通过hashCode() 和 equals() 去重
     *
     */
    public void intermediateOperation() {
        Stream<Apple> appleStream = apples.stream()
                // filter 过滤
                .filter(apple -> apple.getWeight() > 150)
                // skip（n）  跳过前n个元素
                .skip(0)
                // limit 截断
                .limit(20)
                // 去重 通过hashCode() && equals()
                .distinct();

        // 终止操作： 一次性执行全部内容，即“惰性求值” 或 “延迟加载”
        appleStream.forEach(apple -> logger.info(apple.toString()));
    }
}

/**
 * 映射：<br>
 *     map——接口Lambda，将元素转换成其他形式或提取信息。接受一个函数作为参数，该函数会被应用到每一个元素上，并将其映射成一个新元素。
 *     flatMap——接受一个函数作为参数，将流中的每一个值都换成另一个流，然后吧所有流连接成一个流。
 *
 * @author 息阳
 * Create in 22:54 2018/1/3
 * @version 1.0
 */
class StreamMapping extends StreamFilterLimitSkinDistinct {

    protected static final Logger logger = LoggerFactory.getLogger(StreamMapping.class);
    protected static StreamMapping streamMapping = new StreamMapping();

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

    private Stream<Character> filterCharacter(Apple apple) {
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

/**
 * Stream排序<br>
 *     sorted()————自然排序
 *     sorted(Compartor compartor) ———— 定制排序
 *
 *     注意： 使用自然排序的对象必须实现Comparable接口
 *           若对象未实现该接口，则需要使用定制排序
 *
 * @author 息阳
 * Create in 0:02 2018/1/4
 * @version 1.0
 */
class StreamSorted extends StreamMapping {
    private List<Apple> appleList = AppleFactory.getList();
    private static final Logger logger = LoggerFactory.getLogger(StreamSorted.class);
    protected static StreamSorted streamSorted = new StreamSorted();

    public void sorted() {

        List<Integer> integerList = Arrays.asList(4, 2, 1, 3, 5, 4);
        integerList.stream()
                .sorted()
                .forEach(integer -> logger.info(integer + ""));

        appleList.stream()
                .sorted((a1,a2)->{
                    if (a1.getWeight().equals(a2.getWeight())){
                        return a1.getCollor().compareTo(a2.getCollor());
                    }else{
                        return a1.getWeight().compareTo(a2.getWeight());
                    }
                })
                .forEach(apple -> logger.info(apple.toString()));
    }
}
