
package com.tanvir.personalitytest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IfPositive extends BaseObservable implements Parcelable
{

    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("question_type")
    @Expose
    private QuestionType_ questionType;
    public final static Creator<IfPositive> CREATOR = new Creator<IfPositive>() {


        @SuppressWarnings({
            "unchecked"
        })
        public IfPositive createFromParcel(Parcel in) {
            return new IfPositive(in);
        }

        public IfPositive[] newArray(int size) {
            return (new IfPositive[size]);
        }

    }
    ;

    protected IfPositive(Parcel in) {
        this.question = ((String) in.readValue((String.class.getClassLoader())));
        this.category = ((String) in.readValue((String.class.getClassLoader())));
        this.questionType = ((QuestionType_) in.readValue((QuestionType_.class.getClassLoader())));
    }

    public IfPositive() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public QuestionType_ getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType_ questionType) {
        this.questionType = questionType;
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
