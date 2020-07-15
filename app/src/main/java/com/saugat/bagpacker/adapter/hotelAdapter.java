package com.saugat.bagpacker.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saugat.bagpacker.Activity.user.hotelDetails;
import com.saugat.bagpacker.R;
import com.saugat.bagpacker.model.Hotel;
import com.saugat.bagpacker.url.Url;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class hotelAdapter extends RecyclerView.Adapter<hotelAdapter.hotelViewHolder> implements Filterable {
    private List<Hotel> hotelList;
    private List<Hotel> filterhotelList;
    private Context mcontext;




    public hotelAdapter( Context mcontext, List<Hotel> hotelList){

        this.hotelList = hotelList;
        filterhotelList = new ArrayList<>(hotelList);
        this.mcontext=mcontext;

    }


    @NonNull
    @Override
    public hotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel, parent, false);
        return new hotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hotelViewHolder holder, int i) {

        final Hotel hotels = hotelList.get (i);
        holder.tvHotelName.setText(hotels.getHotelname());
        holder.tvCity.setText(hotels.getAddressCity());
        holder.tvDistrict.setText(hotels.getAddressDistrict());
        holder.tvAvailable.setText(hotels.getAvailable());
        String hotelPath = Url.hotelPath +  hotels.getProfileimage();


        Picasso.get().load(hotelPath).into(holder.imghotelimg);
        Picasso.get().load(hotelPath).into(holder.imghotel);

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent notify = new Intent(mcontext, hotelDetails.class);
                notify.putExtra("id", hotels.getId());
                mcontext.startActivity(notify);

            }

        });
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    @Override
    public Filter getFilter() {
        return hotelfilter;
    }

    private Filter hotelfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Hotel> filteredList= new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filterhotelList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Hotel hotel : filterhotelList){
                    if(hotel.getAddressCity().toLowerCase().contains(filterPattern)){
                        filteredList.add(hotel);
                     }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            hotelList.clear();
            hotelList.addAll((List)results.values);
            notifyDataSetChanged();


        }
    };

    public class hotelViewHolder extends RecyclerView.ViewHolder{

        TextView tvHotelName, tvCity, tvDistrict, tvAvailable;
        ImageView imghotelimg, imghotel;
        Button btnView;


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
        }
    }

}
