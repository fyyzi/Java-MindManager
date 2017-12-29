package com.fyyzi.idealearning.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author 息阳
 * 2017/12/29 11:36
 * @version 1.0
 */
@Entity
public class Girl {

    @Id
    @GeneratedValue
    private Integer id;
    private String cupSize;

    @Min(value = 18, message = "未成年禁止入内")
    private Integer age;

    /**
     * 必须有一个无参构造器
     */
    public Girl() {
    }

    public Girl(Integer id, String cupSize, Integer age) {
        this.id = id;
        this.cupSize = cupSize;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
