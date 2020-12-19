package com.septianen.weatherapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.septianen.weatherapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class CommonUtils {

    private static final String TAG = "CommonUtils";

    private CommonUtils() {
        // private constructor
        // not for called
    }

    /**
     * To hide and display keyboard ...
     * ... call once to hide keyboard ...
     * ... call twice to show keyboard again
     **/
    public static void showKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * To show progress dialog ...
     * ... send context to handle specific android context
     * @param context
     * @return
     */
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();

        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        progressDialog.setContentView(R.layout.dialog_loading);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }

    /**
     * To hide progress dialog ...
     * ... send context to handle specific android context
     * @param context
     * @return
     */
    public static Dialog showErrorDialog(Context context) {
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat);
        dialog.show();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        }

        dialog.setContentView(R.layout.dialog_error);
        dialog.setCanceledOnTouchOutside(false);


        TextView tvError = dialog.findViewById(R.id.error_tv);

        return dialog;
    }

    public static String celciusGenerator(float temp) {

        int celcius = (int) temp - 273;

        return celcius + "°C";
    }

    public static String getIconImageUrl(String icon) {
        return "https://openweathermap.org/img/wn/" + icon + "@2x.png";
    }

    public static String getDate (String date) {

        Date newDate = null;
        try {
            newDate = new SimpleDateFormat("dd-MM-yy hh:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        String resultDate = simpleDateFormat.format(newDate);

        return resultDate;
    }

    public static String getTime (String date) {
        String split[] = date.split(" ");

        return split[1];
    }


}
