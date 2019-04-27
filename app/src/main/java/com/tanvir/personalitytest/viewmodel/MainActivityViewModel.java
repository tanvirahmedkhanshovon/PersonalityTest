package com.tanvir.personalitytest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tanvir.personalitytest.model.PersonalityFormRepository;
import com.tanvir.personalitytest.model.Question;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private PersonalityFormRepository repository;
    public MainActivityViewModel(@NonNull Application application) {

        super(application);
        repository = new PersonalityFormRepository(application);



    }

    public LiveData<List<Question>> getAllQuestions(){


        return repository.getQuestionLiveData();
    }
}
