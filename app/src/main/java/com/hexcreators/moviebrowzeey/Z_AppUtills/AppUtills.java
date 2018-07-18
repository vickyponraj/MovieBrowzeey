package com.hexcreators.moviebrowzeey.Z_AppUtills;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.hexcreators.moviebrowzeey.Data.Local.MovieLocalDataSource;
import com.hexcreators.moviebrowzeey.Data.MovieRepository;
import com.hexcreators.moviebrowzeey.Data.Remote.MovieRemoteDataSource;

public class AppUtills {

    public static final String ClickedData_TAG = "CLICKEDDATA_TAG";

    public static MovieRepository prepareDataSource(MovieRemoteDataSource instance_MovieRemoteDataSource, MovieLocalDataSource instance_MovieLocalDataSource) {
        return MovieRepository.getINSTANCE(instance_MovieRemoteDataSource, instance_MovieLocalDataSource);
    }

    public static void showSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);

        snackbar.show();
    }

}
