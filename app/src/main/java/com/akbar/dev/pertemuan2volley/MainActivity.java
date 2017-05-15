package com.akbar.dev.pertemuan2volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.akbar.dev.pertemuan2volley.adapter.AdapterMovies;
import com.akbar.dev.pertemuan2volley.model.MovieData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MovieData mMovieData;
    private AdapterMovies mAdapterMovies;
    private String urlMovie;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvListMovie);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        urlMovie = AppConstant.MOVIE_URL + "popular" + "?api_key=" + AppConstant.API_KEY;
        jsonParser(urlMovie);
    }

    private void jsonParser(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {;
            @Override
            public void onResponse(String response) {
                Log.d("Main Activity", "Response " + response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                mMovieData = gson.fromJson(response, MovieData.class);
                mAdapterMovies = new AdapterMovies(MainActivity.this, mMovieData.results);
                mRecyclerView.setAdapter(mAdapterMovies);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(), "Error while connecting to the server.", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplication(), "Please check your connection.", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}
