package com.fyyzi.test.string;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) {
        Person person = new Person();
        Class<? extends Person> personClass = person.getClass();

        Field[] fields = personClass.getDeclaredFields();
        for (Field field:fields){
            System.out.println(field);
        }

//        Method[] declaredMethods = personClass.getDeclaredMethods();
//        for (Method:method:          declaredMethods              ) {
//            System.out.println();
//        }


    }

}
class Person{

    private Integer personId;

    protected String personName;

    String sex;

    public String personAge;


}
