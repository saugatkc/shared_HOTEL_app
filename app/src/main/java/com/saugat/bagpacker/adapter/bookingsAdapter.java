package com.saugat.bagpacker.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saugat.bagpacker.Activity.user.hotelDetails;
import com.saugat.bagpacker.R;
import com.saugat.bagpacker.model.Booking;
import com.saugat.bagpacker.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

public class bookingsAdapter extends RecyclerView.Adapter<bookingsAdapter.bookingViewholder> {
    private List<Booking> bookingList;
    private Context mcontext;


    public bookingsAdapter( Context mcontext, List<Booking> bookingList){

        this.bookingList = bookingList;
        this.mcontext=mcontext;

    }


    @NonNull
    @Override
    public bookingViewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookingstatus, parent, false);
        return new bookingViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bookingViewholder holder, int i) {

        final Booking bookings = bookingList.get (i);
        holder.tvHotelName.setText(bookings.hotel.getHotelname());
        holder.tvCity.setText(bookings.hotel.getAddressCity());
        holder.tvDistrict.setText(bookings.hotel.getAddressDistrict());
        holder.tvAvailable.setText(bookings.hotel.getAvailable());
        holder.tvBookeddate.setText((bookings.getCreatedAt()));
        String hotelPath = Url.hotelPath +  bookings.hotel.getProfileimage();


        Picasso.get().load(hotelPath).into(holder.imghotelimg);
        Picasso.get().load(hotelPath).into(holder.imghotel);

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent notify = new Intent(mcontext, hotelDetails.class);
                notify.putExtra("id", bookings.hotel.getId());
                mcontext.startActivity(notify);

            }

        });

    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class bookingViewholder extends RecyclerView.ViewHolder{

        TextView tvHotelName, tvCity, tvDistrict, tvAvailable, tvBookeddate;
        ImageView imghotelimg, imghotel;
        Button btnView;


        public bookingViewholder (@NonNull View itemView){
            super (itemView);

            tvHotelName = itemView.findViewById(R.id.tvHotelName);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvDistrict = itemView.findViewById(R.id.tvDistrict);
//            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvAvailable = itemView.findViewById(R.id.tvAvailable);
            imghotel=itemView.findViewById(R.id.imghotel);
            imghotelimg=itemView.findViewById(R.id.imgHotelImg);
            btnView=itemView.findViewById(R.id.btnView);
            tvBookeddate=itemView.findViewById(R.id.tvBookeddate);
        }
    }

}

