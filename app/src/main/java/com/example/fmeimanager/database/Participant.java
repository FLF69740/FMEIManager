package com.example.fmeimanager.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.example.fmeimanager.utils.Utils;

@Entity
public class Participant {

    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "participant_id") private long mId;
    private String mName;
    private String mForname;
    private String mFunction;
    private String mUrlPicture;
    private String mMail;
    private String mTel;
    private Boolean mIsATeamLeader;
    private String mLeadList;

    public Participant() {}

    public Participant(String name, String forname) {
        mName = name;
        mForname = forname;
        mFunction = Utils.EMPTY;
        mUrlPicture = Utils.EMPTY;
        mMail = Utils.EMPTY;
        mTel = Utils.EMPTY;
        mIsATeamLeader = false;
        mLeadList = Utils.EMPTY;
    }

    // GETTER
    public long getId() {return mId;}
    public String getName() {return mName;}
    public String getForname() {return mForname;}
    public String getFunction() {return mFunction;}
    public String getUrlPicture() {return mUrlPicture;}
    public String getMail() {return mMail;}
    public String getTel() {return mTel;}
    public Boolean isATeamLeader() {return mIsATeamLeader;}
    public String getLeadList() {return mLeadList;}

    // SETTER
    public void setId(long id) {mId = id;}
    public void setName(String name) {mName = name;}
    public void setForname(String forname) {mForname = forname;}
    public void setFunction(String function) {mFunction = function;}
    public void setUrlPicture(String urlPicture) {mUrlPicture = urlPicture;}
    public void setMail(String mail) {mMail = mail;}
    public void setTel(String tel) {mTel = tel;}
    public void setIsATeamLeader(Boolean ATeamLeader) {mIsATeamLeader = ATeamLeader;}
    public void setLeadList(String leadList) {mLeadList = leadList;}
}
