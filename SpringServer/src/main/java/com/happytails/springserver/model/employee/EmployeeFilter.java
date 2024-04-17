package com.happytails.springserver.model.employee;

import lombok.Data;
@Data
public class EmployeeFilter {

    enum Priority {Price, Review, Rating}

    boolean[] orderTypes = new boolean[3];
    Priority priority;
    String city;
    boolean cat;
    boolean dog;
}
