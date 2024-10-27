package com.genuinecoder.springclient.reotrfit;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitService {

  @Getter
  private Retrofit retrofit;
  @Setter
  private static String login = "";
  @Setter
  private static String password = "";

  OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
    @NonNull
    @Override
    public okhttp3.Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
      Request originalRequest = chain.request();
      if(originalRequest.url().toString().equals("http://192.168.180.131:9000/api/v1.0/registration/customer"))
        return chain.proceed(originalRequest);

      Request.Builder builder = originalRequest.newBuilder().header("Authorization",
              Credentials.basic(login, password));

      Request newRequest = builder.build();
      return chain.proceed(newRequest);
    }
  }).build();


  public RetrofitService() {
    initializeRetrofit();

  }

  public RetrofitService(String url) {
    initializeRetrofit(url);
  }

  private void initializeRetrofit() {
    retrofit = new Retrofit.Builder()
        .baseUrl("http://192.168.31.222:9000")
        .addConverterFactory(GsonConverterFactory.create(new Gson()))
        .client(okHttpClient)
        .build();
  }

  private void initializeRetrofit(String url) {
    retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();
  }

}
