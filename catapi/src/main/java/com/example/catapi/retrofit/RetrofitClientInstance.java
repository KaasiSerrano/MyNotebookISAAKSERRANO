package com.example.catapi.retrofit;

import com.example.catapi.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static OkHttpClient client;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(myOkhttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient myOkhttpClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(new myHttpClient())
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return client;
    }
}


