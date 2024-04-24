package com.genuinecoder.springclient.model;

import lombok.Data;

@Data
public class EmployeeFilter {

    public EmployeeFilter(Boolean[] orderTypes, Priority priority, String city, Boolean cat, Boolean dog)
    {
        this.orderTypes = orderTypes;
        this.priority = priority;
        this.city = city;
        this.cat = cat;
        this.dog = dog;
    }

    public EmployeeFilter(Boolean[] orderTypes)
    {
        this.orderTypes = orderTypes;
    }

    String city;
    Boolean[] orderTypes;
    Priority priority;
    Boolean cat;
    Boolean dog;
}
