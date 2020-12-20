package com.septianen.weatherapp.ui.base;

import android.content.Context;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * List of function for Base Activity
 *
 */

public interface BaseContract {
    interface View {
        void showMessage(String message);

        void showErrorMessage(String errorMessage);

        void showLoading();

        void hideLoading();

        void returnToMainScreen(Context context);

        void showSnackBar(CoordinatorLayout coordinatorLayout, android.view.View.OnClickListener view);
    }
}
