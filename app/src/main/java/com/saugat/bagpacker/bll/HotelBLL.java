package com.saugat.bagpacker.bll;

import com.saugat.bagpacker.api.HotelAPI;
import com.saugat.bagpacker.model.Hotel;
import com.saugat.bagpacker.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class HotelBLL {
    boolean isSuccess = false;

    public boolean getHoteldetails(String id) {

        HotelAPI hotelAPI = Url.getInstance().create(HotelAPI.class);
        Call<Hotel> hotelCall = hotelAPI.fetchHotelDetail(id);

        try {
            Response<Hotel> hotelResponse = hotelCall.execute();
            if (hotelResponse.isSuccessful()) {

                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

}

