package com.fyyzi.nio.selector;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.Iterator;

/*
 *一、 使用NIO完成网络通信的三个核心：
 *	1. 通道(Channel) ： 负责链接
 *		java.nio.channels.channel接口：
 *			|-- SelectableChannel
 *				|-- SocketChannel
 *				|-- ServerSocketCHannel		TCP
 *				|-- DatagramChannel			UDP
 *
 *				|-- Pipe.SinkCHannel
 *				|-- Pipe.SourceChannel
 *
 *			PS: FileChannel 没有非阻塞模式
 *
 *	2. 缓冲区(Buffer)： 负责数据的存储
 *	3. 选择权(Selector):是SelectableChannel的多路复用器。用于监控SelectorChannel的IO状况。
 *
 * @author xiyang
 * @date 2018年1月20日
 */
public class Selector {

    private static BlockingNioTest blockingNioTest = new BlockingNioTest();
    private static NullBlockingNioTest nullBlockingNioTest = new NullBlockingNioTest();
    private static final Logger logger = LoggerFactory.getLogger(Selector.class);

    @Test
    public void test01() {
        new Thread(() -> {
            blockingNioTest.server();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                blockingNioTest.cliend();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(111);
        }).start();

        try {
            logger.info("JUnit睡眠两秒等待其他线程结束运行。");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class NullBlockingNioTest {

    @Test
    public void cliend() {
        // 1. 获取通道
        // 2. 切换成非阻塞模式
        // 3. 分配指定大小的缓冲区
        // 4. 读取并发送到服务端
        // 5. 关闭通道

        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            byteBuffer.put(Instant.now().toString().getBytes());
            byteBuffer.flip();

            socketChannel.write(byteBuffer);

            byteBuffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void server() {
        // 1. 获取通道
        // 2. 切换到非阻塞模式
        // 3. 绑定链接
        // 4. 获取一个选择器
        // 5. 将通道注册到选择器上，并指定接收事件
        // 6. 轮询式的去监听选择器上的准备就绪事件
        // 7. 获取当前选择器中所有注册的选择键（已注册的监听事件）
        // 8. 迭代获取“准备就绪”的事件
        // 9. 判断具体是什么事件准备就绪
        // 10.若接收就绪就获取客户端的链接
        // 11.切换非阻塞模式
        // 12.将钙通道注册到选择器上
        // 13. 获取当前选择器上  读就绪 状态的通道
        // 14. 读取数据
        // 15. 取消选择键

        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(9898));
            java.nio.channels.Selector selector = java.nio.channels.Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator1 = selector.selectedKeys().iterator();
                while (iterator1.hasNext()) {
                    SelectionKey selectionKey = iterator1.next();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel accept = serverSocketChannel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {

                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int len = 0;
                        while ((len = socketChannel.read(byteBuffer)) > 0) {
                            byteBuffer.flip();
                            String string = new String(byteBuffer.array(), 0, len);
                            byteBuffer.clear();
                        }
                    }
                    iterator1.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocketChannel != null) {
                try {
                    serverSocketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

class BlockingNioTest {

    private static final Logger logger = LoggerFactory.getLogger(BlockingNioTest.class);

    public void cliend() {
        logger.info("客户端启动");
        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {
            // 1. 获取通道
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

            // 2. 分配指定大小的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 3. 读取本地文件并发送到服务端
//            fileChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
            fileChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭通道
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info("客户端正常停止");
    }

    public void server() {
        logger.info("服务端启动");
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {
            // 1. 获取网络传输通道
            serverSocketChannel = ServerSocketChannel.open();

            // 2. 绑定端口号
            serverSocketChannel.bind(new InetSocketAddress(9898));

            // 3. 获取客户端连接的通道
            socketChannel = serverSocketChannel.accept();

            // 4. 接收客户端发来的数据 并保存到本地
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fileChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            // 5. 通过套接字循环的接收
            while (socketChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                fileChannel.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (serverSocketChannel != null) {
                try {
                    serverSocketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        logger.info("服务端正常停止");

    }
}
