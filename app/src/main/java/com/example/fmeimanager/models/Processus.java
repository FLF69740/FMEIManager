package com.example.fmeimanager.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Fmei.class, parentColumns = "fmei_id", childColumns = "processus_fmeiId"))
public class Processus {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "processus_id") private long mId;
    private String mName;
    @ColumnInfo(name = "processus_fmeiId") private long mFmeiId;

    public Processus(String name, long fmeiId) {
        mName = name;
        mFmeiId = fmeiId;
    }

    //GETTER
    public long getId() {return mId;}
    public String getName() {return mName;}
    public long getFmeiId() {return mFmeiId;}

    //SETTER
    public void setId(long id) {mId = id;}
    public void setName(String name) {mName = name;}
    public void setFmeiId(long fmeiId) {mFmeiId = fmeiId;}
}
