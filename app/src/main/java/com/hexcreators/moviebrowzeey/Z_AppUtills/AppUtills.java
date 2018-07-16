package com.hexcreators.moviebrowzeey.Z_AppUtills;

import com.hexcreators.moviebrowzeey.Data.Local.MovieLocalDataSource;
import com.hexcreators.moviebrowzeey.Data.MovieRepository;
import com.hexcreators.moviebrowzeey.Data.Remote.MovieRemoteDataSource;

public class AppUtills {

    public static final String ClickedData_TAG = "CLICKEDDATA_TAG";


    public static MovieRepository prepareDataSource(MovieRemoteDataSource instance_MovieRemoteDataSource, MovieLocalDataSource instance_MovieLocalDataSource) {
        return MovieRepository.getINSTANCE(instance_MovieRemoteDataSource, instance_MovieLocalDataSource);
    }

}
