
package com.tanvir.personalitytest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Condition implements Parcelable
{

    @SerializedName("predicate")
    @Expose
    private Predicate predicate;
    @SerializedName("if_positive")
    @Expose
    private IfPositive ifPositive;
    public final static Creator<Condition> CREATOR = new Creator<Condition>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Condition createFromParcel(Parcel in) {
            return new Condition(in);
        }

        public Condition[] newArray(int size) {
            return (new Condition[size]);
        }

    }
    ;

    protected Condition(Parcel in) {
        this.predicate = ((Predicate) in.readValue((Predicate.class.getClassLoader())));
        this.ifPositive = ((IfPositive) in.readValue((IfPositive.class.getClassLoader())));
    }

    public Condition() {
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public IfPositive getIfPositive() {
        return ifPositive;
    }

    public void setIfPositive(IfPositive ifPositive) {
        this.ifPositive = ifPositive;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(predicate);
        dest.writeValue(ifPositive);
    }

    public int describeContents() {
        return  0;
    }

}
