package com.saugat.bagpacker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saugat.bagpacker.R;
import com.saugat.bagpacker.model.Feature;
import com.saugat.bagpacker.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class featureAdapter extends RecyclerView.Adapter<featureAdapter.hotelViewHolder> {
    private List<Feature> featureList;
    private Context mcontext;


    public featureAdapter( Context mcontext, List<Feature> featureList){

        this.featureList = featureList;
        this.mcontext=mcontext;

    }


    @NonNull
    @Override
    public hotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feature, parent, false);
        return new hotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hotelViewHolder holder, int i) {

        final Feature features = featureList.get (i);
        holder.tv1.setText(features.getFeature());

    }

    @Override
    public int getItemCount() {
        return featureList.size();
    }

    public class hotelViewHolder extends RecyclerView.ViewHolder{

        TextView tv1;


        public hotelViewHolder (@NonNull View itemView){
            super (itemView);

            tv1 = itemView.findViewById(R.id.lable);

        }
    }

}
