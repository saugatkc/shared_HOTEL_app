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
import com.saugat.bagpacker.adapter.savedAdapter;
import com.saugat.bagpacker.api.UsersAPI;
import com.saugat.bagpacker.model.Hotel;
import com.saugat.bagpacker.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class savedFragment extends Fragment {
    public  savedFragment(){
        //Requires empty public constructor
    }

    private RecyclerView rvSaved;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the layout for this fragment
        View view = inflater.inflate(R.layout.saved, container, false);

        rvSaved=view.findViewById(R.id.rvSaved);
        savedhotels();

        return view;

    }

    private void savedhotels() {

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<List<Hotel>> hotelCall = usersAPI.getSaved(Url.id);

        hotelCall.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Errror", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Hotel> savedList = response.body();
                for (Hotel saveds : savedList){
                    savedAdapter  ha = new savedAdapter(getContext(), savedList);
                    rvSaved.setAdapter(ha);
                    rvSaved.setLayoutManager(new LinearLayoutManager(getContext()));

                }

            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {

            }
        });

    }
}
