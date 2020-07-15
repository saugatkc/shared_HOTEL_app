package com.saugat.bagpacker.api;


import com.saugat.bagpacker.model.Hotel;
import com.saugat.bagpacker.model.User;
import com.saugat.bagpacker.serverresponse.ImageResponse;
import com.saugat.bagpacker.serverresponse.SignUpResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UsersAPI {
    @POST("users/signup")
    Call<SignUpResponse> registerUser(@Body User users);

    @FormUrlEncoded
    @POST("users/login")
    Call<SignUpResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<User> getUserDetails(@Header("Authorization")String token);

    @PUT("users/me")
    Call<User> updateUser(@Header("Authorization")String token,@Body User users);

    @POST("users/{id}/saved/{hid}")
    Call<Void> saveHotel(@Path("id") String id,@Path("hid") String hotel);

    @DELETE("users/{id}/saved/{hid}")
    Call<Void> removeSavedHotel(@Path("id") String id,@Path("hid") String hotel);

    @GET("users/{id}/saved")
    Call<List<Hotel>> getSaved(@Path("id") String id);
}
