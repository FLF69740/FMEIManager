package com.example.fmeimanager.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.fmeimanager.utils.Utils;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(foreignKeys = {
        @ForeignKey(entity = Processus.class, parentColumns = "processus_id", childColumns = "risk_processusId", onDelete = CASCADE),
        @ForeignKey(entity = Participant.class, parentColumns = "participant_id", childColumns = "risk_participantId")
})
public class Risk {

    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "risk_id") private long mId;
    private String mCreationDate;
    private String mParts;
    private String mIdentification;
    private String mRisk;
    private String mRiskEffect;
    private String mVerification;
    private String mPotentialCause;
    private String mUrlPictures;
    private int mGravity;
    private int mFrequencies;
    private int mDetectability;
    @ColumnInfo (name = "risk_participantId") private long mParticipantId;
    @ColumnInfo (name = "risk_processusId") private long mProcessusId;

    public Risk() {}

    public Risk(String creationDate, String parts, String identification, String risk, long processusId, long participantId) {
        mCreationDate = creationDate;
        mParts = parts;
        mIdentification = identification;
        mRisk = risk;
        mRiskEffect = Utils.EMPTY;
        mVerification = Utils.EMPTY;
        mPotentialCause = Utils.EMPTY;
        mUrlPictures = Utils.EMPTY;
        mGravity = 10;
        mFrequencies = 10;
        mDetectability = 10;
        mParticipantId = participantId;
        mProcessusId = processusId;
    }

    // GETTER
    public long getId() {return mId;}
    public String getCreationDate() {return mCreationDate;}
    public String getParts() {return mParts;}
    public String getIdentification() {return mIdentification;}
    public String getRisk() {return mRisk;}
    public String getRiskEffect() {return mRiskEffect;}
    public String getVerification() {return mVerification;}
    public String getPotentialCause() {return mPotentialCause;}
    public String getUrlPictures() {return mUrlPictures;}
    public int getGravity() {return mGravity;}
    public int getFrequencies() {return mFrequencies;}
    public int getDetectability() {return mDetectability;}
    public long getParticipantId() {return mParticipantId;}
    public long getProcessusId() {return mProcessusId;}

    // SETTER
    public void setId(long id) {mId = id;}
    public void setCreationDate(String creationDate) {mCreationDate = creationDate;}
    public void setParts(String parts) {mParts = parts;}
    public void setIdentification(String identification) {mIdentification = identification;}
    public void setRisk(String risk) {mRisk = risk;}
    public void setRiskEffect(String riskEffect) {mRiskEffect = riskEffect;}
    public void setVerification(String verification) {mVerification = verification;}
    public void setPotentialCause(String potentialCause) {mPotentialCause = potentialCause;}
    public void setUrlPictures(String urlPictures) {mUrlPictures = urlPictures;}
    public void setGravity(int gravity) {mGravity = gravity;}
    public void setFrequencies(int frequencies) {mFrequencies = frequencies;}
    public void setDetectability(int detectability) {mDetectability = detectability;}
    public void setParticipantId(long participantId) {mParticipantId = participantId;}
    public void setProcessusId(long processusId) {mProcessusId = processusId;}
}