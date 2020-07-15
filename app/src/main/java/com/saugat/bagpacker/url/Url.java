package com.saugat.bagpacker.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {

//   public static final String BASE_URL = "http://10.0.2.2:3000/";
        public static final String BASE_URL = "http://192.168.1.135:3001/";
        public static String token = "Bearer ";
        public static String imagePath = BASE_URL + "uploads/" ;
        public static String galleryPath = BASE_URL + "gallery/" ;
        public static String hotelPath = BASE_URL + "hotels/" ;
        public static String id="";


        public static Retrofit getInstance(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Url.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return  retrofit;

        }

    }

