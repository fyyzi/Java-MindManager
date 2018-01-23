package com.fyyzi.nio.buffer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

/**
 * 缓冲区：在Java NIO 中负责存取。缓冲区就是用于存储不同数据类型的数据数组<br>
 *     一、根据数据类型的不同提供了相应的缓冲区（boolean除外）：<br>
 *              ByteBuffer
 *              ShortBuffer
 *              CharaBuffer
 *              IntBuffer
 *              LongBuffer
 *              FloutBuffer
 *              DoubleBuffer
 *          上述缓冲区的获取方式几乎一致，通过allocate()获取缓冲区
 *      二、缓冲区存储数据的核心方法：<br>
 *          put()：存入数据到缓冲区
 *          get()：获取缓冲区中过的数据
 *
 *      四、缓冲区的四个核心属性：<br>
 *          capacity    容量：缓冲区的最大存储数据的容量，一旦生命不能改变；
 *          limit       界限：表示缓冲区中可以操作数据的大小，limit后面的数据是不能进行读写的；
 *          position    位置：表示缓冲区中正在操作的数据的位置；
 *          mark        标记：用于记录当前position的位置，然后可以通过reset()方法回复到刚才mark的位置
 *
 *          大小规则：0 <= mark <= position <= limit <= capacity
 *      五、直接缓冲区与非直接缓冲区allocate / AllocateDirect<br>
 *          直接缓冲区    通过allocateDirect()方法分配直接缓冲区，缓冲区建立在操作系统的物理内存中。某种情况下可以提高效率。
 *          非直接缓冲区    allocate 将缓冲区建立在JVM 的内存中
 *
 * @author 息阳
 * 2018/1.jpg/6 14:36
 * @version 1.0
 */
public class Buffer {

    private static final Logger logger = LoggerFactory.getLogger(Buffer.class);
    private String str = "abcde";

    @Test
    public void byteBuffer(){
        byteBufferBySize();
        byteBufferByMark();
        byteBufferHasRemaining();
    }

    /**
     * capacity() limit() position() get() rewind() clear()
     * 缓冲区指针变化
     */
    private void byteBufferBySize(){
        // 1.jpg. 分片一个指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        logger.info("allocate初始后：总容量：{}",byteBuffer.capacity());
        logger.info("allocate初始后：一共能操作多少：{}",byteBuffer.limit());
        logger.info("allocate初始后：正在操作数据的位置：{}\n" ,byteBuffer.position());

        byteBuffer.put(str.getBytes());
        logger.info("put 存储数据后：总容量：{}",byteBuffer.capacity());
        logger.info("put 存储数据后：一共能操作多少：{}",byteBuffer.limit());
        logger.info("put 存储数据后：正在操作数据的位置：{}\n" ,byteBuffer.position());

        byteBuffer.flip();
        logger.info("flip切换成读模式：总容量：{}",byteBuffer.capacity());
        logger.info("flip切换成读模式：一共能操作多少：{}",byteBuffer.limit());
        logger.info("flip切换成读模式：正在操作数据的位置：{}\n" ,byteBuffer.position());
        
        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst);
        System.out.println(new String(dst,0,dst.length));

        logger.info("get后：总容量：{}",byteBuffer.capacity());
        logger.info("get后：一共能操作多少：{}",byteBuffer.limit());
        logger.info("get后：正在操作数据的位置：{}\n" ,byteBuffer.position());

        byteBuffer.rewind();
        logger.info("rewind 重读后：总容量：{}",byteBuffer.capacity());
        logger.info("rewind 重读后：一共能操作多少：{}",byteBuffer.limit());
        logger.info("rewind 重读后：正在操作数据的位置：{}\n" ,byteBuffer.position());

        byteBuffer.clear();
        logger.info("clear只是对指针进行了偏移,里面的数据没有做清除");
        logger.info("clear清空缓冲区后：总容量：{}",byteBuffer.capacity());
        logger.info("clear清空缓冲区后：一共能操作多少：{}",byteBuffer.limit());
        logger.info("clear清空缓冲区后：正在操作数据的位置：{}\n" ,byteBuffer.position());

    }


    /**
     * mark() reset()
     * mark()记录指针位置，reset()恢复指针位置
     */
    private void byteBufferByMark(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();

        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst,0,2);
        System.out.println(new String(dst));
        logger.info("读取后position位置为：{}",byteBuffer.position());
        byteBuffer.mark();
        byteBuffer.get(dst,2,2);
        System.out.println(new String(dst,2,2));
        logger.info("再次读取后position位置为：{}",byteBuffer.position());
        byteBuffer.reset();
        logger.info("通过reset()恢复到mark的位置后position位置为：{}",byteBuffer.position());
    }

    /**
     * 其他方法: 时候含有剩余数据
     */
    private void byteBufferHasRemaining(){
        // 1.jpg.分配一个指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 获取缓冲区中是否还有剩余数据
        boolean flag = buffer.hasRemaining();
        int remaining = 0;
        if (flag){
            remaining = buffer.remaining();
        }
        logger.info("缓冲区中是否含有剩余数据：{}，剩余量为：{},{}",flag,remaining);

    }
}
