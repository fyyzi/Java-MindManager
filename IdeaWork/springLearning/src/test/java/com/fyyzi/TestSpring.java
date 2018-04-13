package com.fyyzi;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        Object person = context.getBean("person01");
        System.out.println(person);
    }

}
