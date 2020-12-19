package com.septianen.weatherapp.ui.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.septianen.weatherapp.R;
import com.septianen.weatherapp.ui.city.CityActivity;
import com.septianen.weatherapp.utils.CommonUtils;

/**
 * Base ACtivaty ...
 * ... to handle some function ...
 * ... that will use for several times
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {

    private ProgressDialog mProgressDialog;

    /**
     * Display toast message ...
     * @param message
     */
    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Display toast message ...
     * ... same as showMessage() ...
     * ... but in different name to make it more readable
     * @param errorMessage
     */
    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }


    /**
     * Display progress bar ...
     * ... call it when need to hit an api ...
     */
    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    /**
     * Hide progress bar ...
     * ... after the process finished
     */
    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void returnToMainScreen(Context context) {
        Intent intent = new Intent(context, CityActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    public void showSnackBar(CoordinatorLayout coordinatorLayout, View.OnClickListener view) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, R.string.connection_error, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.retry, view);
        snackbar.setActionTextColor(getResources().getColor(R.color.accent));

        snackbar.show();
    }
}
