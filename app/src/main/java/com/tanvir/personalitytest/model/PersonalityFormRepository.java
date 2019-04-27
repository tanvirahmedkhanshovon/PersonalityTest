package com.tanvir.personalitytest.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.tanvir.personalitytest.service.PersonalityTestService;
import com.tanvir.personalitytest.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonalityFormRepository {
    private static final String TAG = "Repository";
    private Application application;
    private ArrayList<String> categories= new ArrayList<>();
    private ArrayList<Question> questionList= new ArrayList<Question>();
    private MutableLiveData<List<Question>> questionLiveData = new MutableLiveData<List<Question>>();
    private MutableLiveData<List<String>> categoryLiveData = new MutableLiveData<>();

    public PersonalityFormRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Question>> getQuestionLiveData(){

        PersonalityTestService service = RetrofitInstance.getService();

        Call<PersonalityForm> personalityForm = service.getForm();
        personalityForm.enqueue(new Callback<PersonalityForm>() {
            @Override
            public void onResponse(Call<PersonalityForm> call, Response<PersonalityForm> response) {


                categories =(ArrayList<String>) response.body().getCategories();

                Log.i(TAG,response.body().getQuestions().get(0).getQuestion());
                questionList = (ArrayList<Question>) response.body().getQuestions();

                categoryLiveData.setValue(categories);
                questionLiveData.setValue(questionList);
            }

            @Override
            public void onFailure(Call<PersonalityForm> call, Throwable t) {
                Log.i(TAG,t.getMessage());
            }
        });

return questionLiveData;
    }


}
