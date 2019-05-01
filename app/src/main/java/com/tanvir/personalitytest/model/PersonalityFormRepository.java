package com.tanvir.personalitytest.model;

import android.app.Application;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.tanvir.personalitytest.receiver.InternetReceiver;
import com.tanvir.personalitytest.service.PersonalityTestService;
import com.tanvir.personalitytest.service.RetrofitInstance;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonalityFormRepository {
    private static final String TAG = "Repository";
    private BroadcastReceiver brReceiver = null;
    private Application application;
    private ArrayList<String> categories = new ArrayList<>();
    private ArrayList<Question> questionList = new ArrayList<Question>();
    private MutableLiveData<List<Question>> questionsLiveData = new MutableLiveData<List<Question>>();
    private MutableLiveData<List<String>> categoriesLiveData = new MutableLiveData<List<String>>();
    private MutableLiveData<String> answersLiveData = new MutableLiveData<String>();
    private MutableLiveData<String> manyRequestMessge = new MutableLiveData<String>();
    public static MutableLiveData<String> internetState = new MutableLiveData<String>();


    public PersonalityFormRepository(Application application) {
        this.application = application;
        brReceiver = new InternetReceiver();
        broadcastIntent();
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


                try {
                    categories = (ArrayList<String>) response.body().getCategories();
                    categoriesLiveData.setValue(categories);
                    Log.i(TAG, response.body().getQuestions().get(0).getQuestion());
                    questionList = (ArrayList<Question>) response.body().getQuestions();
                    questionsLiveData.setValue(questionList);
                    getQuestionLiveData();
                } catch (Exception e) {

                    Log.i(TAG, response.raw().message());
                    manyRequestMessge.setValue(response.raw().message());

                }

            }

            @Override
            public void onFailure(Call<PersonalityForm> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });

        return categoriesLiveData;


    }

    public MutableLiveData<String> publishAnswers(final ArrayList<Answers> answers) {
        PersonalityTestService service = RetrofitInstance.getService();
        Call<PublishResult> call = service.publishResut(answers);

        call.enqueue(new Callback<PublishResult>() {
            @Override
            public void onResponse(Call<PublishResult> call, Response<PublishResult> response) {


                try {


                    PublishResult value = response.body();
                    answersLiveData.setValue(value.getStatus());
                    Log.i(TAG, value.getStatus());
                } catch (Exception e) {

                    manyRequestMessge.setValue(response.raw().message());
                }

            }

            @Override
            public void onFailure(Call<PublishResult> call, Throwable t) {
                Log.i(TAG, t.getMessage());

            }
        });


        return answersLiveData;
    }

    public MutableLiveData<String> showManyRequest() {


        return manyRequestMessge;
    }

    public MutableLiveData<String> getInternetInfo() {


        return internetState;
    }

    public void broadcastIntent() {
        application.registerReceiver(brReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


}
