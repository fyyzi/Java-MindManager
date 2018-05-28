package com.fyyzi.mybatis.generator.comment.type;

import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * mybatis generator 自定义comment生成器
 * 基于<artifactId>mybatis-generator-core</artifactId>  1.3.5
 *
 * @author XiYang 2018-5-25日
 *
 * TODO 没成功，问题描述： 在generatorConfig.xml 的 commentGenerator标签 tyep = "com.fyyzi.mybatis.generator.comment.type.MyCommentGenerato"  提示ClassNotFoundException
 */
public class MyCommentGenerator extends DefaultCommentGenerator {

    public MyCommentGenerator() {
        System.out.println(111);
    }
}
