package com.FMEA.fmeimanager.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Fmei.class, parentColumns = "fmei_id", childColumns = "processus_fmeiId"))
public class Processus {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "processus_id") private long mId;
    private int mStep;
    private String mName;
    private boolean mVisible;
    @ColumnInfo(name = "processus_fmeiId") private long mFmeiId;

    public Processus(String name, long fmeiId, int step) {
        mName = name;
        mFmeiId = fmeiId;
        mStep = step;
        mVisible = true;
    }

    //GETTER
    public long getId() {return mId;}
    public String getName() {return mName;}
    public long getFmeiId() {return mFmeiId;}
    public int getStep() {return mStep;}
    public boolean isVisible() {return mVisible;}

    //SETTER
    public void setId(long id) {mId = id;}
    public void setName(String name) {mName = name;}
    public void setFmeiId(long fmeiId) {mFmeiId = fmeiId;}
    public void setStep(int step) {mStep = step;}
    public void setVisible(boolean visible) {mVisible = visible;}
}
