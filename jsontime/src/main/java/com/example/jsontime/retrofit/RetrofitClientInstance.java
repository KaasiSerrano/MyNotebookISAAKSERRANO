package com.example.jsontime.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {


    private static final String Base_URL = "http://shibe.online/api/";
    private static Retrofit retrofit;
    //THIS IS SINGLETON. MUST ALWAYS BE STATIC
    public static Retrofit getRetrofit()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
