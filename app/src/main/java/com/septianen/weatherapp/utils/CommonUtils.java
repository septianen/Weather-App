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

    public static String targetDateGenerator(int additionalYear) {
        Date date = Calendar.getInstance().getTime();

        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        int countYear = Integer.parseInt(year.format(date)) + additionalYear;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-");
        String newDate = simpleDateFormat.format(date);

        return newDate+countYear;
    }

    public static int getYearFromDate (String date) {
        String split[] = date.split(" ");
        int targetYear = Integer.parseInt(split[2]);

        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String year = simpleDateFormat.format(currentDate);
        int currentYear = Integer.parseInt(year);

        int goalYear = targetYear - currentYear;

        return goalYear;
    }


}
