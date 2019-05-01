package com.tanvir.personalitytest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublishResult implements Parcelable
{

    @SerializedName("status")
    @Expose
    private String status;
    public final static Parcelable.Creator<PublishResult> CREATOR = new Creator<PublishResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PublishResult createFromParcel(Parcel in) {
            return new PublishResult(in);
        }

        public PublishResult[] newArray(int size) {
            return (new PublishResult[size]);
        }

    }
            ;

    protected PublishResult(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PublishResult() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}