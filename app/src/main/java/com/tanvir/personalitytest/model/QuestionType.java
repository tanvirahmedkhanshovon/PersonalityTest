
package com.tanvir.personalitytest.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionType implements Parcelable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("options")
    @Expose
    private List<String> options = null;
    @SerializedName("condition")
    @Expose
    private Condition condition;
    public final static Creator<QuestionType> CREATOR = new Creator<QuestionType>() {


        @SuppressWarnings({
            "unchecked"
        })
        public QuestionType createFromParcel(Parcel in) {
            return new QuestionType(in);
        }

        public QuestionType[] newArray(int size) {
            return (new QuestionType[size]);
        }

    }
    ;

    protected QuestionType(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.options, (String.class.getClassLoader()));
        this.condition = ((Condition) in.readValue((Condition.class.getClassLoader())));
    }

    public QuestionType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeList(options);
        dest.writeValue(condition);
    }

    public int describeContents() {
        return  0;
    }

}
