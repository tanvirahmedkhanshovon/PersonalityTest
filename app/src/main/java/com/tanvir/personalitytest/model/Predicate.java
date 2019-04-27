
package com.tanvir.personalitytest.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Predicate implements Parcelable
{

    @SerializedName("exactEquals")
    @Expose
    private List<String> exactEquals = null;
    public final static Creator<Predicate> CREATOR = new Creator<Predicate>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Predicate createFromParcel(Parcel in) {
            return new Predicate(in);
        }

        public Predicate[] newArray(int size) {
            return (new Predicate[size]);
        }

    }
    ;

    protected Predicate(Parcel in) {
        in.readList(this.exactEquals, (String.class.getClassLoader()));
    }

    public Predicate() {
    }

    public List<String> getExactEquals() {
        return exactEquals;
    }

    public void setExactEquals(List<String> exactEquals) {
        this.exactEquals = exactEquals;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(exactEquals);
    }

    public int describeContents() {
        return  0;
    }

}
