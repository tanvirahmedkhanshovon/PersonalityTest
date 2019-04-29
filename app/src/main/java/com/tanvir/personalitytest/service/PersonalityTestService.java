package com.tanvir.personalitytest.service;


import com.tanvir.personalitytest.model.PersonalityForm;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonalityTestService {

    @GET("personalitytest")
    Call<PersonalityForm> getForm();

}
