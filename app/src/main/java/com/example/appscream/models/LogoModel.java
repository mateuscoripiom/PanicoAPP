package com.example.appscream.models;

import android.os.Parcel;
import android.os.Parcelable;

public class LogoModel implements Parcelable {
    private String file_path;

    public LogoModel(String file_path) {
        this.file_path = file_path;
    }

    protected LogoModel(Parcel in) {
        file_path = in.readString();
    }

    public static final Creator<LogoModel> CREATOR = new Creator<LogoModel>() {
        @Override
        public LogoModel createFromParcel(Parcel in) {
            return new LogoModel(in);
        }

        @Override
        public LogoModel[] newArray(int size) {
            return new LogoModel[size];
        }
    };

    public String getFile_path() {return file_path;}

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(file_path);
    }
}
