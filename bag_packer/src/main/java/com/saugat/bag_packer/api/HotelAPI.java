package com.saugat.bag_packer.api;

import com.saugat.bag_packer.model.Hotel;
import com.saugat.bag_packer.model.Hotel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface HotelAPI {


    @GET("hotels/hoteldetails")
    Call<List<Hotel>> hotelsDetails (@Header("Authorization") String token );


}

