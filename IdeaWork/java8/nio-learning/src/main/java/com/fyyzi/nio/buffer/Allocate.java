package com.fyyzi.nio.buffer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

/**
 * 非直接缓冲区    allocate 将缓冲区建立在JVM 的内存中
 * @author 息阳
 * 2018/1.jpg/7 9:23
 * @version 1.jpg.0
 */
public class Allocate {

    private static final Logger logger = LoggerFactory.getLogger(Allocate.class);

    @Test
    public void testAllocate(){
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        logger.info("采用allocate方法配置的缓冲区是非直接缓冲区,缓冲区建立在JVM内存中,可采用isDirect()方法获取：{}",allocate.isDirect());

        ByteBuffer buffer = ByteBuffer.allocateDirect(2014);
        logger.info("直接缓冲区的创建方法是allocateDirect()方法：{}",buffer.isDirect());
    }
}
