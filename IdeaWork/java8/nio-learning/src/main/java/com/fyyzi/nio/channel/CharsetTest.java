package com.fyyzi.nio.channel;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.Set;

/**
 * 1. 显示所支持的字符集
 * 2.
 *
 * @author 息阳
 * 2018/1/9 10:46
 * @version 1.0
 */
public class CharsetTest {

    @Test
    public void test02() throws CharacterCodingException {
        Charset gbk = Charset.forName("GBK");

        // 获取编码器
        CharsetEncoder charsetEncoder = gbk.newEncoder();

        // 获取解码器
        CharsetDecoder charsetDecoder = gbk.newDecoder();

        CharBuffer allocate = CharBuffer.allocate(1024);
        allocate.put("字符串威武！");

        // 编码
        allocate.flip();
        ByteBuffer byteBuffer = charsetEncoder.encode(allocate);
        for(int i = 0 ; i < 12;i++){
            System.out.println(byteBuffer.get());
        }

        // 解码
        byteBuffer.flip();
        CharBuffer decode = charsetDecoder.decode(byteBuffer);
        System.out.println(decode.toString());

        // 其他格式解码
        Charset utf8 = Charset.forName("UTF-8");
        byteBuffer.flip();
        CharBuffer decode1 = utf8.decode(byteBuffer);
        System.out.println(decode1);

    }


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

