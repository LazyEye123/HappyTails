package com.genuinecoder.springclient.reotrfit;

import com.genuinecoder.springclient.employee_list_activity.dto.EmployeeDTO;
import com.genuinecoder.springclient.employee_list_activity.model.EmployeeCard;
import com.genuinecoder.springclient.employee_list_activity.model.EmployeeFilter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EmployeeApi {

  @POST("/api/v1.0/employee/filter")
  Call<List<EmployeeDTO>> getEmployeesByFilter(@Body EmployeeFilter employeeFilter);

  @GET("/api/v1.0/employee")
  Call<List<EmployeeCard>> getAllEmployees();



}
