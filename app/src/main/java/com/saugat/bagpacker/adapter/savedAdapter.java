package com.saugat.bagpacker.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saugat.bagpacker.Activity.user.hotelDetails;
import com.saugat.bagpacker.R;
import com.saugat.bagpacker.api.UsersAPI;
import com.saugat.bagpacker.model.Hotel;
import com.saugat.bagpacker.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class savedAdapter extends RecyclerView.Adapter<savedAdapter.hotelViewHolder> {
    private List<Hotel> savedList;
    private String hotelid,hotelname ,addresscity, addressdistrict, available, profileimage;
    private Context mcontext;
    String id ;




    public savedAdapter( Context mcontext, List<Hotel> savedList){

        this.savedList = savedList;
        this.mcontext=mcontext;

    }


    @NonNull
    @Override
    public hotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_hotel, parent, false);
        return new hotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hotelViewHolder holder, int i) {

        final Hotel saveds = savedList.get (i);

        holder.tvHotelName.setText(saveds.getHotelname());
        holder.tvCity.setText(saveds.getAddressCity());
        holder.tvDistrict.setText(saveds.getAddressDistrict());
        holder.tvAvailable.setText(saveds.getAvailable());
        String hotelPath = Url.hotelPath +  saveds.getProfileimage();
        id = saveds.getId();

        Picasso.get().load(hotelPath).into(holder.imghotelimg);
        Picasso.get().load(hotelPath).into(holder.imghotel);

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent notify = new Intent(mcontext, hotelDetails.class);
                notify.putExtra("id", saveds.getId());
                mcontext.startActivity(notify);

            }

        });
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();

            }
        });
    }

    private void remove() {
            {
                String hotel = id;

                UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
                Call<Void> hotelCall = usersAPI.removeSavedHotel(Url.id,hotel);

                hotelCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(mcontext, "Errror", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(mcontext, "Removed", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
    }


    @Override
    public int getItemCount() {
        return savedList.size();
    }

    public class hotelViewHolder extends RecyclerView.ViewHolder{

        TextView tvHotelName, tvCity, tvDistrict, tvAvailable;
        ImageView imghotelimg, imghotel;
        Button btnView,btnRemove;


        public hotelViewHolder (@NonNull View itemView){
            super (itemView);

            tvHotelName = itemView.findViewById(R.id.tvHotelName);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvDistrict = itemView.findViewById(R.id.tvDistrict);
//            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvAvailable = itemView.findViewById(R.id.tvAvailable);
            imghotel=itemView.findViewById(R.id.imghotel);
            imghotelimg=itemView.findViewById(R.id.imgHotelImg);
            btnView=itemView.findViewById(R.id.btnView);
            btnRemove=itemView.findViewById(R.id.btnRemove);
        }
    }

}
