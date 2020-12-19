package com.septianen.weatherapp.ui.city.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.septianen.weatherapp.R;
import com.septianen.weatherapp.data.AppError;
import com.septianen.weatherapp.data.model.Forecast;
import com.septianen.weatherapp.data.network.ApiRepository;
import com.septianen.weatherapp.ui.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailCityActivity extends BaseActivity implements DetailCityMvp.View {

    @BindView(R.id.city_rv_forecast)
    RecyclerView rvForecast;

    @BindView(R.id.city_tv_name)
    TextView tvName;

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

        rvForecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvForecast.setAdapter(new ForecastListAdapter(forecasts));
    }

    @Override
    public void onFailed(AppError appError) {

        hideLoading();
        showErrorMessage(appError.getErrorMessage());
    }
}