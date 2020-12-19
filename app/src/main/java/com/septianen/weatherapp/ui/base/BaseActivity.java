package com.septianen.weatherapp.ui.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.septianen.weatherapp.utils.CommonUtils;

/**
 * Base ACtivaty ...
 * ... to handle some function ...
 * ... that will use for several times
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {

    private ProgressDialog mProgressDialog;
    private Dialog dialog;

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
     * Display error dialog ...
     * ... To give more specific information ...
     * ... than a toast message
     */
    @Override
    public void showErrorDialog() {
        hideLoading();
        dialog = CommonUtils.showErrorDialog(this);
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
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
