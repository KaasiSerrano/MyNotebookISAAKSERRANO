package com.example.jsontime.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShibeService {


    @GET("shibes")
    Call <List<String>> loadShibe(@Query("count")int count);

}
