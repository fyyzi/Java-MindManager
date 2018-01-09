package com.fyyzi.nio.channel;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 * 字符集
 *
 * @author 息阳
 * 2018/1/9 10:46
 * @version 1.0
 */
public class CharsetTest {

    /**
     * 查看支持多少种字符集
     */
    @Test
    public void test01() {
        Map<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = map.entrySet();

        for (Map.Entry<String, Charset> entry : entries) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}

