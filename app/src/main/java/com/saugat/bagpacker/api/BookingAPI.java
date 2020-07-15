package com.saugat.bagpacker.api;

import com.saugat.bagpacker.model.Booking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookingAPI {

//    @POST("bookings/booking")
//    Call<Booking> createBooking(@Body Booking booking);
    @FormUrlEncoded
    @POST("bookings/booking")
    Call<Booking> createBooking(@Header("Authorization")String token, @Field("checkin") String checkin, @Field("checkout") String checkout, @Field("guest") String guest, @Field("hotel") String hotel);
    
    @GET("bookings/{id}/booking")
    Call<List<Booking>> currentBooking (@Path("id") String id);

    @GET("bookings/{id}/completed")
    Call<List<Booking>> completedBooking (@Path("id") String id);

    @GET("bookings/{id}/canceled")
    Call<List<Booking>> canceledBooking (@Path("id") String id);

    @PUT("bookings/{id}")
    Call<List<Booking>> makeCancelation (@Path("id") String id, @Body Booking booking);

}
