package com.genuinecoder.springclient.model;

import lombok.Data;

@Data
public class Cat {

    String name;
    int age;
    Boolean[] needs;
    public void print()
    {
        System.out.println("Кличка: " + name);
        System.out.println("Возраст: " + age);
        for (Boolean need : needs) {
            System.out.println(need);
        }
    }
}
