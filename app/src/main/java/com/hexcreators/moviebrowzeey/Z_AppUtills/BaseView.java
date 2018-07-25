package com.hexcreators.moviebrowzeey.Z_AppUtills;

public interface BaseView {

    void showProcessing();

    void hideProcessing();

    void showMessage(String message);

    void showMessage(int message);

    void showErrorMessage(String message);

    void showErrorMessage(int message);

}
