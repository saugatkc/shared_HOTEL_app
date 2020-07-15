package com.saugat.bagpacker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saugat.bagpacker.R;
import com.saugat.bagpacker.model.Gallery;
import com.saugat.bagpacker.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

public class galleryAdapter extends RecyclerView.Adapter<galleryAdapter.hotelViewHolder> {
    private List<Gallery> imageList;
    private Context mcontext;

        public galleryAdapter( Context mcontext, List<Gallery> imageList){

            this.imageList = imageList;
            this.mcontext=mcontext;

        }


        @NonNull
        @Override
        public hotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.gallery, parent, false);
            return new hotelViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull hotelViewHolder holder, int i) {

            final Gallery image = imageList.get (i);
//            holder.tv1.setText(features.getImage());
            String hotelPath = Url.galleryPath +  image.getImage();


            Picasso.get().load(hotelPath).into(holder.iv1);

        }

        @Override
        public int getItemCount() {
            return imageList.size();
        }

        public class hotelViewHolder extends RecyclerView.ViewHolder{

            ImageView iv1;


            public hotelViewHolder (@NonNull View itemView){
                super (itemView);

                iv1 = itemView.findViewById(R.id.ivImg);

            }
        }

}
