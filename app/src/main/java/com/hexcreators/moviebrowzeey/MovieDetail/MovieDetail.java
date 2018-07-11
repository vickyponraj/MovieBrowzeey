package com.hexcreators.moviebrowzeey.MovieDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hexcreators.moviebrowzeey.R;

public class MovieDetail extends AppCompatActivity implements MovieDetailContract.View{

    private MovieDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


       // presenter   =   new MovieDetailPresenter(this);



    }

    @Override
    public void showProcessing() {

    }

    @Override
    public void hideProcessing() {

    }
}
