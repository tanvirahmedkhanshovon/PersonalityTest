package com.tanvir.personalitytest.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.tanvir.personalitytest.service.PersonalityTestService;
import com.tanvir.personalitytest.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonalityFormRepository {
    private static final String TAG = "Repository";
    private Application application;
    private ArrayList<String> categories = new ArrayList<>();
    private ArrayList<Question> questionList = new ArrayList<Question>();
    private MutableLiveData<List<Question>> questionsLiveData = new MutableLiveData<List<Question>>();
    private MutableLiveData<List<String>> categoriesLiveData = new MutableLiveData<List<String>>();


    public PersonalityFormRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Question>> getQuestionLiveData() {




        return questionsLiveData;
    }


    public MutableLiveData<List<String>> getCategoriesLiveData() {
        PersonalityTestService service = RetrofitInstance.getService();

        Call<PersonalityForm> personalityForm = service.getForm();
        personalityForm.enqueue(new Callback<PersonalityForm>() {
            @Override
            public void onResponse(Call<PersonalityForm> call, Response<PersonalityForm> response) {


                categories = (ArrayList<String>) response.body().getCategories();
                categoriesLiveData.setValue(categories);
                Log.i(TAG, response.body().getQuestions().get(0).getQuestion());
                questionList = (ArrayList<Question>) response.body().getQuestions();
                questionsLiveData.setValue(questionList);
                getQuestionLiveData();


            }

            @Override
            public void onFailure(Call<PersonalityForm> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });

        return categoriesLiveData;


    }

}
