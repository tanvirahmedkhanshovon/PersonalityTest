
package com.tanvir.personalitytest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tanvir.personalitytest.BR;

public class QuestionType_ extends BaseObservable implements Parcelable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("range")
    @Expose
    private Range range;
    public final static Creator<QuestionType_> CREATOR = new Creator<QuestionType_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public QuestionType_ createFromParcel(Parcel in) {
            return new QuestionType_(in);
        }

        public QuestionType_[] newArray(int size) {
            return (new QuestionType_[size]);
        }

    }
    ;

    protected QuestionType_(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.range = ((Range) in.readValue((Range.class.getClassLoader())));
    }

    public QuestionType_() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Bindable
    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
        notifyPropertyChanged(BR.range);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(range);
    }

    public int describeContents() {
        return  0;
    }

}
