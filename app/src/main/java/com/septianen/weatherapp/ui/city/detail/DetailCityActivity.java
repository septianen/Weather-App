package com.septianen.weatherapp.ui.city.detail;

import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.septianen.weatherapp.R;
import com.septianen.weatherapp.data.AppError;
import com.septianen.weatherapp.data.model.Forecast;
import com.septianen.weatherapp.data.network.ApiRepository;
import com.septianen.weatherapp.ui.base.BaseActivity;
import com.septianen.weatherapp.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailCityActivity extends BaseActivity implements DetailCityMvp.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.detail_city_coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.detail_city_swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.detail_city_card)
    CardView cardView;

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

    private Skeleton skeleton;

    private String name;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_city);

        ButterKnife.bind(this);

        swipeRefreshLayout.setOnRefreshListener(this::onRefresh);

        cardView.setVisibility(View.GONE);

        rvForecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("id");
            name = bundle.getString("name");

            // retrieve forecast data for first time
            getForecasts();
        } else {
            // return to main screen if bundle is null
            returnToMainScreen(DetailCityActivity.this);
        }

    }
    
    private void setTvName() {
        tvName.setText(name);
    }

    // swipe refresh
    @Override
    public void onRefresh() {
        getForecasts();
    }

    @Override
    public void onProgress() {
        skeleton = SkeletonLayoutUtils.applySkeleton(rvForecast, R.layout.activity_detail_city, 3);
        skeleton.showSkeleton();
    }

    @Override
    public void onSuccess(List<Forecast> forecasts) {
        skeleton.showOriginal();
        swipeRefreshLayout.setRefreshing(false);

        cardView.setVisibility(View.VISIBLE);
        rvForecast.setAdapter(new ForecastListAdapter(forecasts));

        setTvName();
        setCardData(forecasts.get(0));

    }

    @Override
    public void onFailed(AppError appError) {
        skeleton.showOriginal();
        swipeRefreshLayout.setRefreshing(false);

        // call show snack bar
        // send coordinator layout as parent layout
        // send OnClickListener
        showSnackBar(coordinatorLayout, getOnClickListener());
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
                .placeholder(R.drawable.base_image_view)
                .apply(new RequestOptions().centerCrop())
                .into(ivWeather);
    }

    private void getForecasts() {
        presenter = new DetailCityPresenter(this, new ApiRepository(this));
        presenter.getForecasts(id);
    }

    /**
     * On click listener ...
     * ... to handle snackbar action button ...
     * ... retry to get forecasts data when connection error
     *
     * @return
     */
    private View.OnClickListener getOnClickListener(){

        View.OnClickListener v = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // refresh
                onRefresh();
            }
        };

        return v;
    }
}