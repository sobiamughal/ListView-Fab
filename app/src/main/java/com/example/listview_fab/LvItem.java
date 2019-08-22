package com.example.listview_fab;

import android.os.Parcel;
import android.os.Parcelable;

public class LvItem implements Parcelable {
    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTE = "note";
    private String name;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOTE + " TEXT"
                    + ")";

    protected LvItem(Parcel in) {
        name = in.readString();
    }

    public static final Creator<LvItem> CREATOR = new Creator<LvItem>() {
        @Override
        public LvItem createFromParcel(Parcel in) {
            return new LvItem(in);
        }

        @Override
        public LvItem[] newArray(int size) {
            return new LvItem[size];
        }
    };

    public LvItem(String name) {
        name = name;
    }

    public LvItem() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
