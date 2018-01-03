package com.fyyzi.java8.stream;

import com.fyyzi.java8.domain.Apple;
import com.fyyzi.java8.domain.AppleFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

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
public class StreamSorted {
    private List<Apple> appleList = AppleFactory.getList();
    private static final Logger logger = LoggerFactory.getLogger(StreamSorted.class);

    @Test
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
