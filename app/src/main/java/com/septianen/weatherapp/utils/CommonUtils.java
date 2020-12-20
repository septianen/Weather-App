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
     * to generate celcius temperature ...
     * ... subtract by 273 to convert kelvin to celcius ...
     * ... add °C for celcius indicator
     *
     * @param temp
     * @return
     */
    public static String celciusGenerator(float temp) {

        int celcius = (int) temp - 273;

        return celcius + "°C";
    }

    /**
     * to generate image url of weather icon
     *
     * @param icon
     * @return
     */
    public static String getIconImageUrl(String icon) {
        return "https://openweathermap.org/img/wn/" + icon + "@2x.png";
    }

    /**
     * to split date from open weather API ...
     * ... change month format to MMM
     *
     * @param date
     * @return
     */
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

    /**
     * to split time from open weather API
     *
     * @param date
     * @return
     */
    public static String getTime (String date) {
        String split[] = date.split(" ");

        return split[1];
    }


}
