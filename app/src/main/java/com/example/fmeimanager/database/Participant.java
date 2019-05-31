package com.example.fmeimanager.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.fmeimanager.utils.Utils;

@Entity
public class Participant implements Parcelable {

    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "participant_id") private long mId;
    private String mName;
    private String mForname;
    private String mFunction;
    private String mMail;
    private String mTel;
    private boolean mActivated;

    public Participant() {
        mFunction = Utils.EMPTY;
        mMail = Utils.EMPTY;
        mTel = Utils.EMPTY;
        mActivated = true;
    }

    // GETTER
    public long getId() {return mId;}
    public String getName() {return mName;}
    public String getForname() {return mForname;}
    public String getFunction() {return mFunction;}
    public String getMail() {return mMail;}
    public String getTel() {return mTel;}
    public boolean isActivated() {return mActivated;}

    // SETTER
    public void setId(long id) {mId = id;}
    public void setName(String name) {mName = name;}
    public void setForname(String forname) {mForname = forname;}
    public void setFunction(String function) {mFunction = function;}
    public void setMail(String mail) {mMail = mail;}
    public void setTel(String tel) {mTel = tel;}
    public void setActivated(boolean activated) {mActivated = activated;}

    protected Participant(Parcel in) {
        mName = in.readString();
        mForname = in.readString();
        mFunction = in.readString();
        mMail = in.readString();
        mTel = in.readString();
        mActivated = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mForname);
        dest.writeString(mFunction);
        dest.writeString(mMail);
        dest.writeString(mTel);
        dest.writeByte((byte) (mActivated ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Participant> CREATOR = new Parcelable.Creator<Participant>() {
        @Override
        public Participant createFromParcel(Parcel in) {
            return new Participant(in);
        }

        @Override
        public Participant[] newArray(int size) {
            return new Participant[size];
        }
    };
}
