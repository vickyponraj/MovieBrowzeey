package com.hexcreators.moviebrowzeey.MovieList;

import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.Model.Results;
import com.hexcreators.moviebrowzeey.Z_AppUtills.BasePresenter;
import com.hexcreators.moviebrowzeey.Z_AppUtills.BaseView;

import java.util.List;

public interface MovieListContract {

    interface View extends BaseView {

        void setMovies(List<Results> movies);

        void setLocalMovies(List<Movie> movies);
    }

    interface Presenter extends BasePresenter {

        void loadLocalMovies();

    }

}
