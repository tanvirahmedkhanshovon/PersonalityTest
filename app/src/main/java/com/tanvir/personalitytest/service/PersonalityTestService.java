package com.tanvir.personalitytest.service;


import com.tanvir.personalitytest.model.Answers;
import com.tanvir.personalitytest.model.PersonalityForm;
import com.tanvir.personalitytest.model.PublishResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PersonalityTestService {

    @GET("personalitytest")
    Call<PersonalityForm> getForm();
    @POST("publishResult")
    Call<PublishResult> publishResut(@Body ArrayList<Answers> answers);

}
