package com.example.catapi.retrofit;

import com.example.catapi.Constants;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class myHttpClient implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        //Intercepts our request so we can add some data to it before it
        //reaches the server
        Request.Builder onGoingRequest = chain.request().newBuilder();
        onGoingRequest.addHeader("x-api-key", Constants.API_KEY);
        return chain.proceed(onGoingRequest.build());
    }
}
