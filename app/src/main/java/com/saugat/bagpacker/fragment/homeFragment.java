package com.saugat.bagpacker.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saugat.bagpacker.R;
import com.saugat.bagpacker.adapter.hotelAdapter;
import com.saugat.bagpacker.api.HotelAPI;
import com.saugat.bagpacker.model.Hotel;
import com.saugat.bagpacker.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homeFragment extends Fragment {


    private RecyclerView rvHotels;
    hotelAdapter  ha;
    private SearchView searchhotel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.home, container, false);

        rvHotels =view.findViewById(R.id.rvHotels);
        searchhotel=view.findViewById(R.id.svHotel);
        loadhotels();

        searchhotel.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ha.getFilter().filter(newText);
                return false;
            }
        });





        return view;
    }

    public void loadhotels() {

        HotelAPI hotelAPI = Url.getInstance().create(HotelAPI.class);
        Call<List<Hotel>> hotelCall = hotelAPI.hotelsDetails(Url.token);

        hotelCall.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Errror", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Hotel> hotelList = response.body();
                for (Hotel hotels : hotelList){

                    ha = new hotelAdapter (getContext(), hotelList);
                    rvHotels.setAdapter(ha);
                    rvHotels.setLayoutManager(new LinearLayoutManager(getContext()));

                }

            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {

            }
        });

    }
}