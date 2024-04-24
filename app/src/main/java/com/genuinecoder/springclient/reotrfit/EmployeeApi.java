package com.genuinecoder.springclient.reotrfit;

import com.genuinecoder.springclient.model.Cat;
import com.genuinecoder.springclient.model.Employee;
import com.genuinecoder.springclient.model.EmployeeFilter;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EmployeeApi {

  @POST("/api/v1.0/employee/filter")
  Call<List<Employee>> getEmployeesByFilter(@Body EmployeeFilter employeeFilter);

  @GET("/api/v1.0/employee")
  Call<List<Employee>> getAllEmployees();

  @GET("/api/v1.0/employee/test")
  Call<Employee> getOneEmployee();


  @POST("/employee/save")
  Call<Employee> save(@Body Employee employee);

  @POST("/api/v1.0/cat/print")
  Call<Cat> print(@Body Cat cat);
}
