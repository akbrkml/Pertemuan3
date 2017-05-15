package com.akbar.dev.pertemuan2volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.akbar.dev.pertemuan2volley.model.MovieData;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {

    private Bundle extras;
    private TextView mTextMovieTitle, mTextSinopsis;
    private String mJson;

    private MovieData.Result movieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTextMovieTitle = (TextView) findViewById(R.id.textMovieTitle);
        mTextSinopsis = (TextView) findViewById(R.id.txtSinopsis);

        extras = getIntent().getExtras();
        if (extras != null){
            mJson = extras.getString("json");
        }

        movieData = new Gson().fromJson(mJson, MovieData.Result.class);

        mTextMovieTitle.setText(movieData.original_title);
        mTextSinopsis.setText(movieData.overview);
    }
}
