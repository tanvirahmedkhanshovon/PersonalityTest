
package com.tanvir.personalitytest.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tanvir.personalitytest.BR;

public class Range extends BaseObservable implements Parcelable
{

    @SerializedName("from")
    @Expose
    private int from;
    @SerializedName("to")
    @Expose
    private Integer to;
    public final static Creator<Range> CREATOR = new Creator<Range>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Range createFromParcel(Parcel in) {
            return new Range(in);
        }

        public Range[] newArray(int size) {
            return (new Range[size]);
        }

    }
    ;

    protected Range(Parcel in) {
        this.from = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.to = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Range() {
    }

    @Bindable
    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
        notifyPropertyChanged(BR.from);
    }
    @Bindable
    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
        notifyPropertyChanged(BR.to);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(from);
        dest.writeValue(to);
    }

    public int describeContents() {
        return  0;
    }

}
