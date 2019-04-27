
package com.tanvir.personalitytest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Range implements Parcelable
{

    @SerializedName("from")
    @Expose
    private Integer from;
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

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(from);
        dest.writeValue(to);
    }

    public int describeContents() {
        return  0;
    }

}
