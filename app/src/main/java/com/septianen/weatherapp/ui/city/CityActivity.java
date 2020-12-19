package com.septianen.weatherapp.ui.city;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.septianen.weatherapp.R;
import com.septianen.weatherapp.data.AppError;
import com.septianen.weatherapp.data.model.City;
import com.septianen.weatherapp.data.network.ApiRepository;
import com.septianen.weatherapp.ui.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityActivity extends BaseActivity implements CityMvp.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.city_coordcinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.city_swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.city_rv_list)
    RecyclerView rvCity;

    private Skeleton skeleton;

    private CityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        ButterKnife.bind(this);

        rvCity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        swipeRefreshLayout.setOnRefreshListener(this::onRefresh);

        // retrieve city's data for first time
        getCities();

    }

    // swipe refresh
    @Override
    public void onRefresh() {
        getCities();
    }

    @Override
    public void onProgress() {
        skeleton = SkeletonLayoutUtils.applySkeleton(rvCity, R.layout.list_city, 3);
        skeleton.showSkeleton();
    }

    @Override
    public void onSuccess(List<City> cities) {
        skeleton.showOriginal();
        swipeRefreshLayout.setRefreshing(false);

        rvCity.setAdapter(new CityListAdapter(cities));
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

    private void getCities() {
        presenter = new CityPresenter(this, new ApiRepository(this));
        presenter.getCities();
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