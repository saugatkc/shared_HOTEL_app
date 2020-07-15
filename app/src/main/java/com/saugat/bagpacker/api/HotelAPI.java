package com.saugat.bagpacker.api;

import com.saugat.bagpacker.model.Feature;
import com.saugat.bagpacker.model.Gallery;
import com.saugat.bagpacker.model.Hotel;
import com.saugat.bagpacker.serverresponse.ImageResponse;
import com.saugat.bagpacker.serverresponse.SignUpResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface HotelAPI {


    @GET("hotels/hoteldetails")
    Call<List<Hotel>> hotelsDetails (@Header("Authorization") String token );

    @GET("hotels/{id}")
    Call<Hotel> fetchHotelDetail(@Path("id") String id);

    @GET("hotels/{id}/features")
    Call<List<Feature>> fetchFeatures(@Path("id") String id);

    @GET("hotels/{id}/gallery")
    Call<List<Gallery>> fetchGallery(@Path("id") String id);

}

