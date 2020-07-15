package com.saugat.bagpacker.Activity.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saugat.bagpacker.Activity.MapsActivity;
import com.saugat.bagpacker.R;
import com.saugat.bagpacker.adapter.featureAdapter;
import com.saugat.bagpacker.adapter.galleryAdapter;
import com.saugat.bagpacker.api.BookingAPI;
import com.saugat.bagpacker.api.HotelAPI;
import com.saugat.bagpacker.api.UsersAPI;
import com.saugat.bagpacker.model.Booking;
import com.saugat.bagpacker.model.Feature;
import com.saugat.bagpacker.model.Gallery;
import com.saugat.bagpacker.model.Hotel;
import com.saugat.bagpacker.url.Url;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class hotelDetails extends AppCompatActivity {
    String id ;
    RecyclerView rvHotelGallery,rvHotelFeatures;
    Button btnBook,btnSave;
    TextView tvhdHotelname,tvhdCity,tvhdDistrict,tvhdPhone,tvhdEmail,tvhdPrice;
    EditText etchekin, etchekout;
    ImageView imgmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotelinfo);

        tvhdHotelname=findViewById(R.id.tvhdHotelname);
        tvhdCity=findViewById(R.id.tvhdCity);
        tvhdDistrict=findViewById(R.id.tvhdDistrict);
        tvhdPhone=findViewById(R.id.tvhdPhone);
        tvhdEmail=findViewById(R.id.tvhdEmail);
        tvhdPrice=findViewById(R.id.tvhdPrice);
        rvHotelGallery=findViewById(R.id.rvHotelGallery);
        rvHotelFeatures = findViewById(R.id.rvHotelFeatures);
        etchekin = findViewById(R.id.etcheckin);
        etchekout = findViewById(R.id.etcheckout);
        btnBook=findViewById(R.id.btnBook);
        btnSave=findViewById(R.id.btnSave);
        imgmap=findViewById(R.id.imghotelMap);

        imgmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hotelDetails.this, MapsActivity.class);
                startActivity(intent);
            }
        });



        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createbooking();
            }
        });


        //create a date string.
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        //get hold of textview.
        //set it as current date.
        etchekin.setText(date_n);
        etchekout.setText(date_n);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id= bundle.getString("id");
        }
        else{
            Toast.makeText(this, "No Message", Toast.LENGTH_LONG).show();
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        loadhoteldetails();
    }

    private void save() {
        {
            String hotel = id;

            UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
            Call<Void> hotelCall = usersAPI.saveHotel(Url.id,hotel);

            hotelCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Errror", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });

        }
    }


    private void createbooking(){
        String checkin = etchekin.getText().toString();
        String checkout = etchekout.getText().toString();
        String guest = Url.id;
        String hotel=id;

//        Booking booking = new Booking(checkin, checkout, guest ,hotel);
        BookingAPI bookingAPI =Url.getInstance().create(BookingAPI.class);
        Call<Booking> bookingcall = bookingAPI.createBooking(Url.token,checkin, checkout, guest ,hotel);

        bookingcall.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(hotelDetails.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(hotelDetails.this, "Booked", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                Toast.makeText(hotelDetails.this, "bookedsucesfully" , Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void loadhoteldetails() {

        HotelAPI hotelAPI = Url.getInstance().create(HotelAPI.class);
        Call<Hotel> hotelCall = hotelAPI.fetchHotelDetail(id);

        hotelCall.enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(hotelDetails.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                tvhdHotelname.setText(response.body().getHotelname());
                tvhdCity.setText(response.body().getAddressCity());
                tvhdDistrict.setText(response.body().getAddressDistrict());
                tvhdPhone.setText(response.body().getPhone());
                tvhdEmail.setText(response.body().getEmail());
                tvhdPrice.setText(response.body().getPrice());

                //fetching features
                HotelAPI feature = Url.getInstance().create(HotelAPI.class);
                Call<List<Feature>> featurecall = feature.fetchFeatures(id);

                featurecall.enqueue(new Callback<List<Feature>>() {
                    @Override
                    public void onResponse(Call<List<Feature>> call, Response<List<Feature>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Errror", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        List<Feature> hotelList = response.body();
                        for (Feature hotels : hotelList){
                            featureAdapter  ha = new featureAdapter (getApplicationContext(), hotelList);
                            rvHotelFeatures.setAdapter(ha);
                            rvHotelFeatures.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Feature>> call, Throwable t) {
                    }
                });

                //fetching images in gallery
                HotelAPI gallery = Url.getInstance().create(HotelAPI.class);
                Call<List<Gallery>> gallerycall = gallery.fetchGallery(id);

                gallerycall.enqueue(new Callback<List<Gallery>>() {
                    @Override
                    public void onResponse(Call<List<Gallery>> call, Response<List<Gallery>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Errror", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        List<Gallery> imageList = response.body();
                        for (Gallery images : imageList){
                            galleryAdapter ha = new galleryAdapter (getApplicationContext(), imageList);
                            rvHotelGallery.setAdapter(ha);
                            rvHotelGallery.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Gallery>> call, Throwable t) {
                    }
                });

            }

            @Override
            public void onFailure(Call<Hotel> call, Throwable t) {

            }
        });



    }
}
