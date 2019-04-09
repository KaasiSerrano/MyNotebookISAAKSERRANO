package com.example.catapi;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catapi.adapter.BreedAdapter;
import com.example.catapi.model.Breed;
import com.example.catapi.retrofit.CatService;
import com.example.catapi.retrofit.RetrofitClientInstance;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonvolley,buttonNative,buttonRetrofit;
    TextView  tv_numbers;
    String TAG = "THIS_IS_A_THING_IDK_DUDE";

    CatService catService;
    ImageView imageView;

    RecyclerView recyclerView;

    BreedAdapter breedAdapter;

    int num1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvBreeds);


        breedAdapter = new BreedAdapter(this,new ArrayList<Breed>());

        //declare and init recyclerview

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(breedAdapter);


        retrofitRequest();

        Log.d(TAG, "on Error Response: ");

    }

    @Override
    public void onClick(View v) {


    }
    private void retrofitRequest() {
        CatService catService =
                RetrofitClientInstance
                        .getRetrofit()
                        .create(CatService.class);


        Call<List<Breed>> call = catService.loadBreeds(10);


        call.enqueue(new Callback<List<Breed>>() {
            @Override
            public void onResponse(Call<List<Breed>> call, retrofit2.Response<List<Breed>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d(TAG, "onResponse" + response.body());
                    breedAdapter.setData(response.body());


                } else {
                    assert response.errorBody() != null;
                    Log.d(TAG, "onResponseError" + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Breed>> call, Throwable t) {

            }
        });

    }
}


