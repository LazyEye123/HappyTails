package com.genuinecoder.springclient;

import android.app.Application;

import com.genuinecoder.springclient.employee_list_activity.dto.CustomerDTO;

import lombok.Data;

@Data
public class MyApplication extends Application {
    private CustomerDTO user;
}
