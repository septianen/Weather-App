package com.septianen.weatherapp.ui.base;

/**
 * List of function for Base Activity
 *
 */

public interface BaseContract {
    interface View {
        void showMessage(String message);

        void showErrorMessage(String errorMessage);

        void showErrorDialog();

        void showLoading();

        void hideLoading();

        void hideKeyboard();
    }
}
