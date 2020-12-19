package com.septianen.weatherapp.ui.city.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.septianen.weatherapp.R;
import com.septianen.weatherapp.data.model.Forecast;
import com.septianen.weatherapp.data.model.Info;
import com.septianen.weatherapp.data.model.Weather;
import com.septianen.weatherapp.utils.CommonUtils;

import java.util.List;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ViewHolder> {

    private Context context;
    private List<Forecast> forecasts;

    public ForecastListAdapter(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.list_forecast, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Weather weather = forecasts.get(position).getWeathers().get(0);
        Info info = forecasts.get(position).getInfo();

        String date = forecasts.get(position).getDate();

        holder.setData(
                CommonUtils.celciusGenerator(info.getTemp()),
                weather.getName(),
                CommonUtils.getDate(date),
                CommonUtils.getTime(date),
                CommonUtils.getIconImageUrl(weather.getIcon()),
                String.valueOf(info.getPressure()),
                String.valueOf(info.getSea_level()),
                CommonUtils.celciusGenerator(info.getTemp_max()),
                CommonUtils.celciusGenerator(info.getTemp_min())
        );
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTemperatur, tvWeather, tvDate, tvTime, tvPressure, tvSeaLevel, tvMaxTemp, tvMinTemp;
        private ImageView ivWeather;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.forecast_card);

            ivWeather = itemView.findViewById(R.id.forecast_iv_weather);

            tvTemperatur = itemView.findViewById(R.id.forecast_tv_temp);
            tvDate = itemView.findViewById(R.id.forecast_tv_date);
            tvTime = itemView.findViewById(R.id.forecast_tv_time);
            tvWeather = itemView.findViewById(R.id.forecast_tv_weather);

            tvPressure = itemView.findViewById(R.id.forecast_tv_pressure);
            tvSeaLevel = itemView.findViewById(R.id.forecast_tv_sea_lvl);
            tvMaxTemp = itemView.findViewById(R.id.forecast_tv_max_temp);
            tvMinTemp = itemView.findViewById(R.id.forecast_tv_min_temp);
        }

        private void setData(String temp, String weather, String date, String time, String imageUrl, String pressure, String seaLevel, String maxTemp, String minTemp) {
            tvTemperatur.setText(temp);
            tvWeather.setText(weather);
            tvDate.setText(date);
            tvTime.setText(time);

            tvPressure.setText(pressure);
            tvSeaLevel.setText(seaLevel);
            tvMaxTemp.setText(maxTemp);
            tvMinTemp.setText(minTemp);

            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .apply(new RequestOptions().centerCrop())
                    .into(ivWeather);
        }
    }
}
