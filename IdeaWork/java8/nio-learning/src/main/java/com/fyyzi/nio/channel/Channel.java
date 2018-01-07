package com.fyyzi.nio.channel;

/**
 * 一、通道：用于连接点与目标节点耳朵连接，在NIO 中负责与操作系统Channoo();Channel本身是不存储任何数据的，因此需要配合Buffer来使用。
 * 二、通道的主要实现类：<br>
 *     java.nio.channel.Channel接口：<br>
 *         |--FileChannel
 *         |--SocketChannel
 *         |--ServerSocketChannel
 *         |--DatagramChannel
 * 三、获取通道的三种方式：<br>
 *     1. Java针对支持通道的类提供了getChannel() 方法<br>
 *         本地IO：<br>
 *             FileInputStream/FileOutputStream
 *             RandomAccessFile
 *         网络IO：<br>
 *             Socket
 *             ServerSocket
 *             DatagramSocket
 *     2. JDK1.7中的NIO.2中针对各个通道提供了静态方法open()方法
 *     3. JDK1.7中的NIO.2的Files工具类提供了newByteChannel()方法
 *
 * @author 息阳
 * Create in 4:03 2018/1/8
 * @version 1.0
 */
public class Channel {
}
