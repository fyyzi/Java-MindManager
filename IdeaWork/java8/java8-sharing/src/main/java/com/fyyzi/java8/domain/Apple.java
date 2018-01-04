package com.fyyzi.java8.domain;

import java.util.Objects;

/**
 * @author 息阳
 * Create in 1:53 2018/1/1
 * @version 1.0
 */
public class Apple {

    /**
     * 颜色
     */
    private String collor;

    /**
     * 重量
     */
    private Integer weight;

    /**
     * 价格
     */
    private Double pirce;

    /**
     *  大小
     */
    private Size size;

    public Apple() {
    }

    public Apple(String collor, Integer weight) {
        this.collor = collor;
        this.weight = weight;
    }

    public Apple(String collor, Integer weight, Double pirce, Size size) {
        this.collor = collor;
        this.weight = weight;
        this.pirce = pirce;
        this.size = size;
    }

    public String getCollor() {
        return collor;
    }

    public void setCollor(String collor) {
        this.collor = collor;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getPirce() {
        return pirce;
    }

    public void setPirce(Double pirce) {
        this.pirce = pirce;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "collor='" + collor + '\'' +
                ", weight=" + weight +
                ", pirce=" + pirce +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Apple apple = (Apple) o;
        return Objects.equals(getCollor(), apple.getCollor()) &&
                Objects.equals(getWeight(), apple.getWeight()) &&
                Objects.equals(pirce, apple.pirce) &&
                size == apple.size;
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCollor(), getWeight(), pirce, size);
    }

    public enum Size {
        SMALL,BIG,BIGGER,NONE;
    }
}
