package com.septianen.weatherapp.ui.city;

import android.content.Context;
import android.content.Intent;
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
import com.septianen.weatherapp.data.ConstData;
import com.septianen.weatherapp.data.model.City;
import com.septianen.weatherapp.ui.city.detail.DetailCityActivity;
import com.septianen.weatherapp.utils.CommonUtils;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {

    private List<City> cities;
    private Context context;

    public CityListAdapter(List<City> cities) {
        this.cities = cities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.list_city, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String cityName = cities.get(position).getName();
        String imageUrl = CommonUtils.getIconImageUrl(cities.get(position).getWeathers().get(0).getIcon());


        holder.setData(
                cityName,
                imageUrl
        );

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailCityActivity.class);
                intent.putExtra("id", cities.get(position).getId());
                intent.putExtra("name", cities.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView tvName;
        private ImageView ivIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.city_card);
            tvName = itemView.findViewById(R.id.city_tv_name);
            ivIcon = itemView.findViewById(R.id.city_iv_weather);
        }

        private void setData(String cityName, String imageUrl) {
            tvName.setText(cityName);

            Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.base_image_view)
                .apply(new RequestOptions().centerCrop())
                .into(ivIcon);
        }
    }
}
