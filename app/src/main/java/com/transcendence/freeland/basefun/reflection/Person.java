package com.transcendence.freeland.basefun.reflection;

/**
 * @author asus
 */
public class Person {
    private String name;
    private int age;

    // 公有构造函数
    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }

    // 私有构造函数
    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 公有方法
    public void sayHello() {
        System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
    }

    // 私有方法
    private void privateMethod() {
        System.out.println("This is a private method.");
    }

    // Getter 和 Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}