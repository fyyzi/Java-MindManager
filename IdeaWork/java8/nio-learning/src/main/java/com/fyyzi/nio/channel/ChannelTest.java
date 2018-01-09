package com.fyyzi.nio.channel;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

/**
 * 1. 利用通道完成文件的复制(非直接缓冲区）
 * 2. 利用内存映射文件的方式复制（直接缓冲区）
 * 3. 通道中间的数据传输（直接缓冲区）；
 * 4. 分散和聚集
 *
 * @author 息阳
 * 2018/1.jpg/7 10:07
 * @version 1.jpg.0
 */
public class ChannelTest {

    private static final Logger logger = LoggerFactory.getLogger(ChannelTest.class);

    @Test
    public void test04(){
        FileChannel randomAccessFileChannel = null;
        FileChannel channel = null;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("1.hhc","rw");
            // 获取通道
            randomAccessFileChannel = randomAccessFile.getChannel();
            // 分配指定大小的缓冲区
            ByteBuffer allocate1 = ByteBuffer.allocate(100);
            ByteBuffer allocate2 = ByteBuffer.allocate(1024);

            // 分散读取
            ByteBuffer[] byteBuffers = {allocate1,allocate2};
                randomAccessFileChannel.read(byteBuffers);
            for (ByteBuffer b:byteBuffers) {
                b.flip();
            }
            logger.info("前100个"+new String(byteBuffers[0].array(),0,byteBuffers[0].limit()));
            logger.info("后1024各"+new String(byteBuffers[1].array(),0,byteBuffers[1].limit()));

            // 聚集写入
            RandomAccessFile randomAccessFile2 = new RandomAccessFile("1.txt","rw");
            channel = randomAccessFile2.getChannel();
            channel.write(byteBuffers);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (randomAccessFileChannel!=null){
                try {
                    randomAccessFileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (channel != null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test03(){
        Instant start = Instant.now();

        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("4.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

            /*inChannel.transferTo(0,inChannel.size(),outChannel);*/
            inChannel.transferFrom(inChannel,0,inChannel.size());

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outChannel != null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Instant end = Instant.now();
        logger.info(""+java.time.Duration.between(start,end).toMillis());
    }


    @Test
    public void test02() throws IOException {
        Instant start = Instant.now();

        // 获取通道
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        // 内存映射文件
        MappedByteBuffer inMappedBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        byte[] bytes = new byte[inMappedBuffer.limit()];
        inMappedBuffer.get(bytes);
        outMappedBuffer.put(bytes);

        outChannel.close();
        inChannel.close();

        Instant end = Instant.now();

        logger.info(""+java.time.Duration.between(start,end).toMillis());
    }

    @Test
    public void test01(){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileInputStreamChannel = null;
        FileChannel fileOutputStreamChannel = null;
        try {
            fileInputStream = new FileInputStream("1.jpg.jpg");
            fileOutputStream = new FileOutputStream("2.jpg.jpg");

            // 获取通道
            fileInputStreamChannel = fileInputStream.getChannel();
            fileOutputStreamChannel = fileOutputStream.getChannel();

            // 分配指定大小的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 将通道中的数据写入缓冲区
            while (fileInputStreamChannel.read(byteBuffer)!=-1){
                // 将缓冲区中的数据写入通道
                byteBuffer.flip();
                fileOutputStreamChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fileInputStreamChannel!=null) {
                try {
                    fileOutputStreamChannel.close();
                } catch (IOException e) {
                }
            }
            if (fileInputStreamChannel!=null) {
                try {
                    fileInputStreamChannel.close();
                } catch (IOException e) {
                }
            }

            if (fileOutputStream!=null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                }
            }
            if (fileInputStream!=null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                }
            }
        }






    }
}
