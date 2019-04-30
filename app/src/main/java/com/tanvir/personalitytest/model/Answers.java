package com.tanvir.personalitytest.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.tanvir.personalitytest.BR;

public class Answers extends BaseObservable {
    private String question;
    private String answer;
    private int ageMin;
    private int ageMax;
    public Answers() {

    }
    public Answers(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Bindable
    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
        notifyPropertyChanged(BR.ageMin);
    }

    @Bindable
    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
        notifyPropertyChanged(BR.ageMax);
    }

    @Bindable
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
        notifyPropertyChanged(BR.question);
    }

    @Bindable
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        notifyPropertyChanged(BR.answer);
    }
}
