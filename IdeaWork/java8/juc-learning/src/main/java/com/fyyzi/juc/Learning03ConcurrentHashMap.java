package com.fyyzi.juc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 *  CopyOnWriteArrayList/CopyOnWriteArraySet ： “写入并复制”
 *      注意： 添加操作多时，效率低，因为每次添加时都会进行复制操作，开销非常大。  并发迭代操作多时候可以选择。
 *
 * @author 息阳
 * 2018/1/24 11:50
 * @version 1.0
 */
public class Learning03ConcurrentHashMap {

    CopyOnWriteArrayListTest copyOnWriteArrayListTest = new CopyOnWriteArrayListTest();

    public void test() {
        copyOnWriteArrayListTest.copyOnWriteTest();
    }
}

class CopyOnWriteArrayListTest {

    @Test
    public void copyOnWriteTest() {
        // 用于演示并发修改异常
        boolean isCopyOnWrite = false;
        if (isCopyOnWrite) {
            // CopyOnWriteList 在并发修改时候会正常运行
            List<String> copyOnWriteArrayList = getCopyOnWriteArrayList();
            for (int i = 0; i < 10; i++) {
                HelloThread(copyOnWriteArrayList);
            }
        } else {
            // ArrayList在并发修改的时候会抛出异常 ConcurrentModificationException
            List<String> arrayList = getArrayList();
            for (int i = 0; i < 10; i++) {
                HelloThread(arrayList);
            }

        }
    }

    /**
     * 启动一个新线程用于测试什么样的List集合可以进行并发修改
     *
     * @param arrayList
     */
    private void HelloThread(List<String> arrayList) {
        new Thread(
                () -> {
                    Iterator<String> iterator = arrayList.iterator();
                    while (iterator.hasNext()) {
                        System.out.println(iterator.next());
                        arrayList.add("AA");
                    }
                }
        ).start();
    }

    /**
     * 获取一个CopyOnWriteArrayList集合
     *
     * @return
     */
    private List<String> getCopyOnWriteArrayList() {
        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        addStringToList(copyOnWriteArrayList);
        return copyOnWriteArrayList;
    }

    /**
     * 获取传统的ArrayList集合
     *
     * @return
     */
    private List<String> getArrayList() {
        List<String> list = new ArrayList<>();
        addStringToList(list);
        return list;
    }

    /**
     * 向集合中添加数据
     *
     * @param list
     */
    private void addStringToList(List<String> list) {
        list.add("aa");
        list.add("bb");
        list.add("cc");
    }
}
