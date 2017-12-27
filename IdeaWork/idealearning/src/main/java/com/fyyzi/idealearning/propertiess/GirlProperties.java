package com.fyyzi.idealearning.propertiess;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 息阳
 * 2017/12/27 12:59
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "girl")
public class GirlProperties {

    private Integer age;

    private String cupSize;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {

        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
