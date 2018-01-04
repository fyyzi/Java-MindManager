package com.fyyzi.java8.stream;

import com.fyyzi.java8.domain.Apple;
import com.fyyzi.java8.domain.AppleFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 终端操作
 *
 * @author 息阳
 * 2018/1/4 10:46
 * @version 1.0
 */
public class StreamTerminal {
    protected static StreamTerminal streamTerminal = new StreamTerminal();
    private static Logger logger = LoggerFactory.getLogger(StreamTerminal.class);
    List<Apple> apples = AppleFactory.getList();

    public void terminal(){
        
    }

}
