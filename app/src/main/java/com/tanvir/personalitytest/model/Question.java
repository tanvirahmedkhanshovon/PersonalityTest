
package com.tanvir.personalitytest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tanvir.personalitytest.BR;

public class Question extends BaseObservable implements Parcelable
{

    @SerializedName("question")
    @Expose
    private String question;
    @Expose(serialize = false,deserialize = false)
    private String answer;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("question_type")
    @Expose
    private QuestionType questionType;

    public final static Creator<Question> CREATOR = new Creator<Question>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return (new Question[size]);
        }

    }
    ;

    protected Question(Parcel in) {
        this.question = ((String) in.readValue((String.class.getClassLoader())));
        this.category = ((String) in.readValue((String.class.getClassLoader())));
        this.questionType = ((QuestionType) in.readValue((QuestionType.class.getClassLoader())));
    }

    public Question() {
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
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        notifyPropertyChanged(BR.category);
    }

    @Bindable
    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
        notifyPropertyChanged(BR.questionType);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(question);
        dest.writeValue(category);
        dest.writeValue(questionType);
    }

    public int describeContents() {
        return  0;
    }


}
