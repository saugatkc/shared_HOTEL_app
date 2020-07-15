package com.saugat.bagpacker.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saugat.bagpacker.R;
import com.saugat.bagpacker.api.BookingAPI;
import com.saugat.bagpacker.adapter.bookingsAdapter;
import com.saugat.bagpacker.model.Booking;
import com.saugat.bagpacker.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelFragment extends Fragment {
    public  CancelFragment(){
        //Requires empty public constructor
    }

    private RecyclerView rvBookings;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragement_bookings, container, false);

        rvBookings=view.findViewById(R.id.rvBookings);
        loadbooking();

        return view;

    }

    private void loadbooking() {

        BookingAPI bookingAPI = Url.getInstance().create(BookingAPI.class);
        Call<List<Booking>> hotelCall = bookingAPI.canceledBooking(Url.id);

        hotelCall.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Errror", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Booking> bookinglist = response.body();
                for (Booking bookings : bookinglist){
                    bookingsAdapter  ha = new bookingsAdapter (getContext(), bookinglist);
                    rvBookings.setAdapter(ha);
                    rvBookings.setLayoutManager(new LinearLayoutManager(getContext()));

                }

            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {

            }
        });

    }
}
