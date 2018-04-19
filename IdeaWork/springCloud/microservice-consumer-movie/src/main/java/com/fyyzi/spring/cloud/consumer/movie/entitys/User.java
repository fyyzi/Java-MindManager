package com.fyyzi.spring.cloud.consumer.movie.entitys;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable {

    private static final long serialVersionUID = -8313337229471081269L;
    private Long id;

    private String username;

    private String name;

    private Short age;

    private BigDecimal balance;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
