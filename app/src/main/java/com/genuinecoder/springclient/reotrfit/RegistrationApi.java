package com.genuinecoder.springclient.reotrfit;

import com.genuinecoder.springclient.employee_list_activity.dto.CustomerDTO;
import com.genuinecoder.springclient.employee_list_activity.dto.EmployeeDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistrationApi
{
    @POST("/api/v1.0/registration/employee")
    public Call<Void> createEmployee(@Body EmployeeDTO employee);

    @POST("/api/v1.0/registration/customer")
    public Call<Void> createCustomer(@Body CustomerDTO customer);
}
