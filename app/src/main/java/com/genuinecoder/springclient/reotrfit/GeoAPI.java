package com.genuinecoder.springclient.reotrfit;

import androidx.annotation.NonNull;

import java.io.IOException;

import lombok.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Data
public class GeoAPI
{
    private String ip;
    private String cityMessage;

    public String receiveIp()
    {
        try {
            requestIp();
        }
        catch(IOException e) {
            System.out.println("Ошибка ввода!");
        }
        System.out.println("Перед возвратом: " + ip);
        return ip;
    }

    private void requestIp() throws IOException {

        RetrofitService retrofitService = new RetrofitService("http://checkip.amazonaws.com");
        RegistrationApi registrationApi = retrofitService.getRetrofit().create(RegistrationApi.class);

        registrationApi.getIp()
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                        if(response.isSuccessful())
                        {
                            String ip = response.body().toString();
                            System.out.println("Реквест ip: " + ip);
                            setIp(ip);
                        }
                        else
                        {
                            ip = "Ошибка авторизации!";
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, Throwable t) {}
                });
    }

}
