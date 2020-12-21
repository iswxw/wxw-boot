package com.wxw.domain;

/**
 * @author ：wxw.
 * @date ： 11:43 2020/12/21
 * @description：人的类
 * @link:
 * @version: v_0.0.1
 */
public class Person {

    private String id;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
