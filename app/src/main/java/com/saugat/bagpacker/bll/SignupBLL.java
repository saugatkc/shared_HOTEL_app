package com.saugat.bagpacker.bll;

import com.saugat.bagpacker.api.UsersAPI;
import com.saugat.bagpacker.model.User;
import com.saugat.bagpacker.serverresponse.SignUpResponse;
import com.saugat.bagpacker.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SignupBLL {
    boolean isSuccess = false;

    public boolean addUser(String fullname, String username, String password, String phone,String email, String imageName) {


        User users = new User(fullname, username, password, phone, email, imageName);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> signUpCall = usersAPI.registerUser(users);

        try {
            Response<SignUpResponse> loginResponse = signUpCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Signup success!")) {

                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

}
