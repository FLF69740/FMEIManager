package com.example.fmeimanager.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Fmei {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "fmei_id") private long mId;
    private String mName;

    public Fmei(){}

    public Fmei(String name) {
        mName = name;
    }

    // GETTER
    public long getId() {return mId;}
    public String getName() {return mName;}

    // SETTER
    public void setId(long id) {mId = id;}
    public void setName(String name) {mName = name;}
}
