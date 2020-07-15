package com.saugat.bag_packer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.saugat.bag_packer.adapter.hotelAdapter;
import com.saugat.bag_packer.api.HotelAPI;
import com.saugat.bag_packer.model.Hotel;
import com.saugat.bag_packer.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dashboard extends AppCompatActivity {

    private RecyclerView rvHotels;
    hotelAdapter  ha;
    private SearchView searchhotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        rvHotels =findViewById(R.id.rvHotels);
        loadhotels();


    }

    private void loadhotels() {
        HotelAPI hotelAPI = Url.getInstance().create(HotelAPI.class);
        Call<List<Hotel>> hotelCall = hotelAPI.hotelsDetails(Url.token);

        hotelCall.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Errror", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Hotel> hotelList = response.body();
                for (Hotel hotels : hotelList){

                    ha = new hotelAdapter(getApplicationContext(), hotelList);
                    rvHotels.setAdapter(ha);
                    rvHotels.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                }

            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {

            }
        });

    }
}