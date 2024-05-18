package com.genuinecoder.springclient.reotrfit;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import lombok.Getter;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

  @Getter
  private Retrofit retrofit;
  OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
    @NonNull
    @Override
    public okhttp3.Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
      Request originalRequest = chain.request();

      Request.Builder builder = originalRequest.newBuilder().header("Authorization",
              Credentials.basic("test", "7632112367"));

      Request newRequest = builder.build();
      return chain.proceed(newRequest);
    }
  }).build();


  public RetrofitService() {
    initializeRetrofit();
  }

  private void initializeRetrofit() {
    retrofit = new Retrofit.Builder()
        .baseUrl("http://192.168.43.51:9000")
        .addConverterFactory(GsonConverterFactory.create(new Gson()))
        .client(okHttpClient)
        .build();
  }

}
