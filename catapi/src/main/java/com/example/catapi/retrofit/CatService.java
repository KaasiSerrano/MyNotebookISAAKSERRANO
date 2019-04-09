package com.example.catapi.retrofit;

import com.example.catapi.Constants;
import com.example.catapi.model.Breed;
import com.example.catapi.model.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatService {
    @GET(Constants.BREEDS_API)
    Call<List<Breed>> loadBreeds(@Query("limit") int limit);


    @GET(Constants.IMAGES_SEARCH)
    Call<List<Image>> getBreedImage(@Query("breed_id") String breedId, @Query("limit") int limit);


}
