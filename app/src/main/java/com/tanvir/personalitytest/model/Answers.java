package com.tanvir.personalitytest.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.tanvir.personalitytest.BR;

public class Answers  {
    @Expose
    private String question;
    @Expose
    private String answer;
    @Expose
    private int ageMin;
    @Expose
    private int ageMax;
    public Answers() {

    }
    public Answers(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }


    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;

    }


    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;

    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;

    }
}
