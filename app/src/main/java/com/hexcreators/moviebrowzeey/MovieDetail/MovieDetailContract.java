package com.hexcreators.moviebrowzeey.MovieDetail;

import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.Model.Results;
import com.hexcreators.moviebrowzeey.Z_AppUtills.BasePresenter;
import com.hexcreators.moviebrowzeey.Z_AppUtills.BaseView;

import java.util.List;

public interface MovieDetailContract {

    interface View extends BaseView {

        void showMovie(Movie movie);

        void showSimilarProcessing();

        void setSimilarMovies(List<Results> movies);

        void hideSimilarProcessing();
    }

    interface Presenter extends BasePresenter {

        void loadSimilar(Integer id);
    }

}
