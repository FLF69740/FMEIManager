package com.example.fmeimanager.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Fmei {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "fmei_id") private long mId;
    private String mName;
    private long mTeamLeader;

    public Fmei(){}

    // GETTER
    public long getId() {return mId;}
    public String getName() {return mName;}
    public long getTeamLeader() {return mTeamLeader;}

    // SETTER
    public void setId(long id) {mId = id;}
    public void setName(String name) {mName = name;}
    public void setTeamLeader(long teamLeader) {mTeamLeader = teamLeader;}
}
