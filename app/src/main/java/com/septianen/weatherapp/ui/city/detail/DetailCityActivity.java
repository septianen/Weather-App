package com.septianen.weatherapp.ui.city.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.septianen.weatherapp.R;
import com.septianen.weatherapp.data.AppError;
import com.septianen.weatherapp.data.model.Forecast;
import com.septianen.weatherapp.data.network.ApiRepository;
import com.septianen.weatherapp.ui.base.BaseActivity;
import com.septianen.weatherapp.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailCityActivity extends BaseActivity implements DetailCityMvp.View {

    @BindView(R.id.city_rv_forecast)
    RecyclerView rvForecast;

    @BindView(R.id.city_tv_name)
    TextView tvName;

    @BindView(R.id.city_tv_temp)
    TextView tvTemp;

    @BindView(R.id.city_tv_temp_feels)
    TextView tvFeels;

    @BindView(R.id.city_tv_desc)
    TextView tvDesc;

    @BindView(R.id.city_iv_weather)
    ImageView ivWeather;

    private DetailCityPresenter presenter;

    private String name;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_city);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("id");
            name = bundle.getString("name");

            setTvName();

            presenter = new DetailCityPresenter(this, new ApiRepository(this));
            presenter.getForecasts(id);
        }

    }
    
    private void setTvName() {
        tvName.setText(name);
    }

    @Override
    public void onProgress() {
        showLoading();
    }

    @Override
    public void onSuccess(List<Forecast> forecasts) {
        hideLoading();

        setCardData(forecasts.get(0));

        rvForecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvForecast.setAdapter(new ForecastListAdapter(forecasts));
    }

    @Override
    public void onFailed(AppError appError) {

        hideLoading();
        showErrorMessage(appError.getErrorMessage());
    }

    private void setCardData(Forecast forecast) {

        String temp = CommonUtils.celciusGenerator(forecast.getInfo().getTemp());
        String imageUrl = CommonUtils.getIconImageUrl(
                forecast.getWeathers().get(0).getIcon()
        );

        tvDesc.setText(
                forecast.getWeathers().get(0).getDescription()
        );

        tvTemp.setText(temp);

        tvFeels.setText("Feels like " + temp);

        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .apply(new RequestOptions().centerCrop())
                .into(ivWeather);
    }
}