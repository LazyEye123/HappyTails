package com.genuinecoder.springclient.reotrfit;

import com.genuinecoder.springclient.employee_list_activity.dto.CustomerDTO;
import com.genuinecoder.springclient.employee_list_activity.dto.EmployeeDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegistrationApi
{
    @POST("/api/v1.0/registration/employee")
    public Call<Void> createEmployee(@Body EmployeeDTO employee);

    @POST("/api/v1.0/registration/customer")
    public Call<CustomerDTO> createCustomer(@Body CustomerDTO customer);

    @POST("/api/v1.0/authorization")
    public Call<CustomerDTO> getCityMessage(@Body CustomerDTO customer);

    @GET("/")
    public Call<String> getIp();
}
