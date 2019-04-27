
package com.tanvir.personalitytest.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonalityForm implements Parcelable
{

    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;
    public final static Creator<PersonalityForm> CREATOR = new Creator<PersonalityForm>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PersonalityForm createFromParcel(Parcel in) {
            return new PersonalityForm(in);
        }

        public PersonalityForm[] newArray(int size) {
            return (new PersonalityForm[size]);
        }

    }
    ;

    protected PersonalityForm(Parcel in) {
        in.readList(this.categories, (String.class.getClassLoader()));
        in.readList(this.questions, (com.tanvir.personalitytest.model.Question.class.getClassLoader()));
    }

    public PersonalityForm() {
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(categories);
        dest.writeList(questions);
    }

    public int describeContents() {
        return  0;
    }

}
