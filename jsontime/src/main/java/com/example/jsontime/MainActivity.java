package com.example.jsontime;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsontime.retrofit.RetrofitClientInstance;
import com.example.jsontime.retrofit.ShibeService;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonvolley,buttonNative,buttonRetrofit;
    TextView  tv_numbers;
    ImageFragment imageFragment;
    String TAG = "THIS_IS_A_THING_IDK_DUDE";
    ImageView imageView;
    int num1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_numbers = findViewById(R.id.editText);


        buttonNative = findViewById(R.id.buttonNative);
        buttonNative.setOnClickListener(this);

        buttonRetrofit= findViewById(R.id.buttonRetrofit);
        buttonRetrofit.setOnClickListener(this);



        buttonvolley = findViewById(R.id.buttonvolley);
        buttonvolley.setOnClickListener(this);
        Log.d(TAG, "on Error Response: ");

    }

    @Override
    public void onClick(View v) {


        if(tv_numbers.getText().toString().matches("")||Integer.parseInt(tv_numbers.getText().toString()) > 100)
        {
            Toast.makeText(this, "You must input a number or one 100 or lower", Toast.LENGTH_SHORT).show();
        }

        else
        {
            num1 = Integer.parseInt(tv_numbers.getText().toString());
            switch (v.getId()) {

                case R.id.buttonvolley: {
                    Log.d(TAG, "Button volley was clicked ");
                    retrofitRequest(num1);
                    //volleyRequest();
                }break;

                case R.id.buttonRetrofit :{
                    Log.d(TAG, "Button retrofit was clicked");
                    retrofitRequest(num1);

                }break;
                case R.id.buttonNative: {
                    Log.d(TAG, "Button native was clicked");
                    retrofitRequest(num1);
                    //new ImageDownloadedrAsyncTask(MainActivity.this).execute();

                }break;
            }

        }







        //Toast.makeText(this, "THIS IS A BUTTON", Toast.LENGTH_SHORT).show();
    }

    private void retrofitRequest(int number) {
        ShibeService shibeService =
                RetrofitClientInstance
                        .getRetrofit()
                        .create(ShibeService.class);

        Call<List<String>> call = shibeService.loadShibe((number));


        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, retrofit2.Response<List<String>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d(TAG, "onResponse000000 " + response.body().get(0));


                    EventBus.getDefault().post(new ImageEvent(response.body().get(0), response.body()));

                } else {
                    assert response.errorBody() != null;
                    Log.d(TAG, "onResponse1" + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getLocalizedMessage());

            }
        });

    }

    private class ImageDownloadedrAsyncTask extends AsyncTask<Void, Void, Void> {

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

            try {//create URL object passing the url string in the constructor
                URL url = new URL(baseURL + query);
                //we use the url object to create a new open connection(), then cast it as a HTTP URL connection
                httpURLConnection = (HttpURLConnection) url.openConnection();

                //create a imput stream instance and initalize it with a BufferedInputStream
                //passing the stream from the httpURLconnection object instance

                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());


                //create an ImputStreamReader Object instance and initalize it with the inputstream
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                //we create a BufferReader Object instance and initalize it with our inputStreamReader
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String line;

                // Using the bufferReader we read each line of the response and append it into our
                // string builder object instance

                while ((line = reader.readLine()) != null) {
                    result.append(line);


                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //Important to disconnect our HttpURL connection when we are done
                httpURLConnection.disconnect();
            }
            //used eventbus as our data transfer.

            Log.d(TAG, "doInBackground:412321 "+result.substring(2,result.length()-2));


           // EventBus.getDefault().post(new ImageEvent(response.body().get(0), response.body()));

            EventBus.getDefault().post(new ImageEvent(result.substring(2,result.length()-2), new ArrayList<String>()));

            return null;
        }
    }

    private void volleyRequest() {
        Log.d(TAG, "on Enter volleyRequest :");
//create url
        String baseUrl = "http://shibe.online/api/shibes?";
        int querynumber;
        String query = "count=1";
        final String url = baseUrl + query;
//create a RequestQueue instance object instance and initalize it with the VOlly class
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        //JsonArrayRequest meaining [] array
        //or
        //JsonObjectRequest () object
        //depending on what you're expecting to get back

        //in this case we're using JsonArrayRequest
        Log.d(TAG, "on JustBefore jsonArrayRequest :");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,


                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d(TAG, "on Response: " + response.get(0).toString());

                            EventBus.getDefault().post(new ImageEvent(response.get(0).toString(),new ArrayList<String>()));
                            // EventBus.getDefault().post(new ImageEvent(response,response.body);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "on Error Response: " + error.toString());
            }
        });

        requestQueue.add(jsonArrayRequest);


    }


}
