package com.example.jsontime;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.jsontime.retrofit.RetrofitClientInstance;
import com.example.jsontime.retrofit.ShibeService;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button LoadButton;
    ImageFragment imageFragment;
    String TAG = "THIS_IS_A_THING_IDK_DUDE";
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadButton = findViewById(R.id.button);
        LoadButton.setOnClickListener(this);
        Log.d(TAG ,"on Error Response: ");

    }

    @Override
    public void onClick(View v) {

        //volleyRequest();
        retrofitRequest();
        Log.d(TAG ,"on Click Response: ");

        //new ImageDownloadedrAsyncTask(MainActivity.this).execute();
        //Toast.makeText(this, "THIS IS A BUTTON", Toast.LENGTH_SHORT).show();
    }

    private void retrofitRequest() {
        ShibeService shibeService =
                RetrofitClientInstance
                .getRetrofit()
                .create(ShibeService.class);
        Call<List<String>> call = shibeService.loadShibe((20));
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, retrofit2.Response<List<String>> response) {
               if(response.isSuccessful())
               {
                   assert response.body() != null;
                   Log.d(TAG,"onResponse" +response.body().toString());
                   EventBus.getDefault().post(new ImageEvent (response.body().get(0),response.body()));

               } else
               {
                   assert response.errorBody() != null;
                   Log.d(TAG,"onResponse" +response.errorBody().toString());
               }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d(TAG,"onFailure"+ t.getLocalizedMessage());

            }
        });

    }

    private class ImageDownloadedrAsyncTask extends AsyncTask<Void,Void,Void>{

        WeakReference<MainActivity> activityWeakReference;
        HttpURLConnection httpURLConnection;

        public ImageDownloadedrAsyncTask(MainActivity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String baseURL = "http://shibe.online/api/shibes?",
                    query = "count=1";

            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL(baseURL+query);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String line;
                while ((line = reader.readLine())!=null){
                    result.append(line);


                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                httpURLConnection.disconnect();
            }
           // EventBus.getDefault().post(new ImageEvent(result.toString()));
            return null;
        }
    }
    private void volleyRequest ()
    {
        Log.d(TAG ,"on Enter volleyRequest :");

        String baseUrl = "http://shibe.online/api/shibes?";
        String query = "count=1";
        final String url = baseUrl+query;

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        //JsonArrayRequest meaining [] array
        //or
        //JsonObjectRequest () object
        //depending on what you're expecting to get back

        //in this case we're using JsonArrayRequest
        Log.d(TAG ,"on JustBefore jsonArrayRequest :");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,



                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d(TAG ,"on Response: "+ response.get(0));


                         //   EventBus.getDefault().post(new ImageEvent(response.get(0).toString()));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG ,"on Error Response: "+ error.toString());
            }
        });

        requestQueue.add(jsonArrayRequest);





    }


}
