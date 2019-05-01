package com.tanvir.personalitytest.service;

import com.tanvir.personalitytest.service.PersonalityTestService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;

   // private static String BASE_URL = "https://testmenow.free.beeceptor.com/";
   // private static String BASE_URL = "https://personalityform.free.beeceptor.com";
    private static String BASE_URL = "https://tanvirapp.free.beeceptor.com";
//    private static String BASE_URL = "https://personality.free.beeceptor.com/";

    public static PersonalityTestService getService() {


        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }


        return retrofit.create(PersonalityTestService.class);
    }

}