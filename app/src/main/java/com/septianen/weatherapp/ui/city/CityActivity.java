package com.septianen.weatherapp.ui.city;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;
import com.septianen.weatherapp.R;
import com.septianen.weatherapp.data.AppError;
import com.septianen.weatherapp.data.model.City;
import com.septianen.weatherapp.data.network.ApiRepository;
import com.septianen.weatherapp.ui.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityActivity extends BaseActivity implements CityMvp.View {

    @BindView(R.id.city_coordcinator_layout)
    CoordinatorLayout coordinatorLayout;

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

        rvCity.setAdapter(new CityListAdapter(cities));
    }

    @Override
    public void onFailed(AppError appError) {
        skeleton.showOriginal();

        showSnackBar(coordinatorLayout, getOnClickListener());
    }

    private void getCities() {
        presenter = new CityPresenter(this, new ApiRepository(this));
        presenter.getCities();
    }

    private View.OnClickListener getOnClickListener(){

        View.OnClickListener v = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCities();
            }
        };

        return v;
    }
}