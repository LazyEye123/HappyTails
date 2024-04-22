package com.happytails.springserver.filter;

import com.happytails.springserver.enums.Priority;
import lombok.Data;
@Data
public class EmployeeFilter {

    Boolean[] orderTypes;
    Priority priority;
    String city;
    Boolean cat;
    Boolean dog;
}
