package com.hexcreators.moviebrowzeey.MovieList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hexcreators.moviebrowzeey.Data.Local.MovieLocalDataSource;
import com.hexcreators.moviebrowzeey.Data.Model.Results;
import com.hexcreators.moviebrowzeey.Data.MovieRepository;
import com.hexcreators.moviebrowzeey.Data.Remote.MovieRemoteDataSource;
import com.hexcreators.moviebrowzeey.R;
import com.hexcreators.moviebrowzeey.Z_AppUtills.ItemDecorationGrid;

import java.util.List;

import static com.hexcreators.moviebrowzeey.Z_AppUtills.AppUtills.prepareDataSource;

public class MovieList extends AppCompatActivity implements MovieListContract.View {

    private MovieListPresenter presenter;
    private RecyclerView rv_list;
    private MovieListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        presenter = new MovieListPresenter(prepareDataSource(MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getINSTANCE()), this);

        initView();

        initControllers();

    }

    private void initControllers() {
        adapter  =  new MovieListAdapter(callbacks);
        rv_list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rv_list.addItemDecoration(new ItemDecorationGrid(0, 2));
        rv_list.setAdapter(adapter);
    }

    private void initView() {
        rv_list = findViewById(R.id.rv_list);
    }


    @Override
    public void showProcessing() {

    }

    @Override
    public void hideProcessing() {

    }

    @Override
    public void setMovies(List<Results> movies) {
        Toast.makeText(this, "Size : " + movies.size(), Toast.LENGTH_SHORT).show();
        adapter.setMovies(movies);
    }

    private MovieListAdapter.Callbacks callbacks = new MovieListAdapter.Callbacks() {
        @Override
        public void onItemClick(Results result) {
            openMovie(result);
        }
    };

    private void openMovie(Results result) {
        String movieJSON = new Gson().toJson(result);

    }
}
