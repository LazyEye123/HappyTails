package com.genuinecoder.springclient.error;

import com.genuinecoder.springclient.reotrfit.RetrofitService;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static ValidationErrorResponse parseValidationError(Response<?> response) {
        RetrofitService retrofitService = new RetrofitService();
        Converter<ResponseBody, ValidationErrorResponse> converter =
                retrofitService.getRetrofit()
                        .responseBodyConverter(ValidationErrorResponse.class, new Annotation[0]);

        ValidationErrorResponse error = new ValidationErrorResponse();

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            System.out.println("Ошибка конвертации!");
        }

        return error;
    }
}
