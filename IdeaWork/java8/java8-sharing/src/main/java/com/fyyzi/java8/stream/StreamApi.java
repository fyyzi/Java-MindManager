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
 *         1. 筛选与切片： filter、limit、skip(n)、distinct
 *         2. 映射：map()、flatMap()
 *         3. 排序：sorted()、sorted(Compartor compartor)
 *
 *     3. 终止操作（终端操作）
 *
 * @author 息阳
 * 2018/1/2 13:31
 * @version 1.0
 */
public class StreamApi  {
    private static StreamCreate streamCreate = new StreamCreate();
    private static StreamIntermediate streamIntermediate = new StreamIntermediate();
    private static StreamTerminal streamTerminal = new StreamTerminal();

    public static void main(String[] args) {
        streamCreate.createStream();
        streamIntermediate.intermediate();
        streamTerminal.terminal();
    }
}
