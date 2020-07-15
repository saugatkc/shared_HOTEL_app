package com.saugat.bag_packer.api;


import com.saugat.bag_packer.model.User;
import com.saugat.bag_packer.model.User;
import com.saugat.bag_packer.serverresponse.SignUpResponse;

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
    @FormUrlEncoded
    @POST("users/login")
    Call<SignUpResponse> checkUser(@Field("username") String username, @Field("password") String password);

}
