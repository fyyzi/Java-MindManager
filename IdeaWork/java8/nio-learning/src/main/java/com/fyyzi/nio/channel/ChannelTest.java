package com.fyyzi.nio.channel;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * 1. 利用通道完成文件的复制(非直接缓冲区）
 * 2. 利用内存映射（直接缓冲区）
 *
 * @author 息阳
 * 2018/1/7 10:07
 * @version 1.0
 */
public class ChannelTest {

    private static final Logger logger = LoggerFactory.getLogger(ChannelTest.class);


    @Test
    public void test02() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1,jog"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2,jog"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        MappedByteBuffer mappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());




    }

    @Test
    public void test01(){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileInputStreamChannel = null;
        FileChannel fileOutputStreamChannel = null;
        try {
            fileInputStream = new FileInputStream("1.jpg");
            fileOutputStream = new FileOutputStream("2.jpg");

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
