package com.fyyzi.java8.domain;

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
    private long weight;

    public Apple() {
    }

    public Apple(String collor, long weight) {
        this.collor = collor;
        this.weight = weight;
    }

    public String getCollor() {
        return collor;
    }

    public void setCollor(String collor) {
        this.collor = collor;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "collor='" + collor + '\'' +
                ", weight=" + weight +
                '}';
    }
}
