package com.example.fmeimanager.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import com.example.fmeimanager.utils.Utils;

import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(entity = Risk.class, parentColumns = "risk_id", childColumns = "corrective_riskId", onDelete = CASCADE),
        @ForeignKey(entity = Participant.class, parentColumns = "participant_id", childColumns = "corrective_participantId")
})
public class CorrectiveAction {

    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "corrective_id") private long mId;
    private String mCorrectiveAction;
    private String mCreationDate;
    private String mParts;
    private String mIdentification;
    private String mCorrectiveDescription;
    private String mDeadLineDate;
    private String mRealisationDate;
    private String mUrlPictures;
    private int mNewGravity;
    private int mNewFrequencies;
    private int mNewDetectability;
    private boolean mApprobationTL;
    @ColumnInfo (name = "corrective_riskId") private long mRiskId;
    @ColumnInfo (name = "corrective_participantId") private long mParticipantId;

    public CorrectiveAction() {}

    public CorrectiveAction(String correctiveAction, String creationDate, String parts, String identification, String deadLineDate, long riskId, long participantId) {
        mCorrectiveAction = correctiveAction;
        mCreationDate = creationDate;
        mParts = parts;
        mIdentification = identification;
        mCorrectiveDescription = Utils.EMPTY;
        mDeadLineDate = deadLineDate;
        mRealisationDate = Utils.EMPTY;
        mNewGravity = 10;
        mNewFrequencies = 10;
        mNewDetectability = 10;
        mApprobationTL = false;
        mRiskId = riskId;
        mParticipantId = participantId;
    }

    // GETTER
    public long getId() {return mId;}
    public String getCorrectiveAction() {return mCorrectiveAction;}
    public String getCreationDate() {return mCreationDate;}
    public String getParts() {return mParts;}
    public String getIdentification() {return mIdentification;}
    public String getCorrectiveDescription() {return mCorrectiveDescription; }
    public String getDeadLineDate() {return mDeadLineDate;}
    public String getRealisationDate() {return mRealisationDate;}
    public String getUrlPictures() {return mUrlPictures;}
    public int getNewGravity() {return mNewGravity;}
    public int getNewFrequencies() {return mNewFrequencies;}
    public int getNewDetectability() {return mNewDetectability;}
    public boolean isApprobationTL() {return mApprobationTL;}
    public long getRiskId() {return mRiskId;}
    public long getParticipantId() {return mParticipantId;}


    // SETTER
    public void setId(long id) {mId = id;}
    public void setCorrectiveAction(String correctiveAction) {mCorrectiveAction = correctiveAction;}
    public void setCreationDate(String creationDate) {mCreationDate = creationDate;}
    public void setParts(String parts) {mParts = parts;}
    public void setIdentification(String identification) {mIdentification = identification;}
    public void setCorrectiveDescription(String correctiveDescription) {mCorrectiveDescription = correctiveDescription;}
    public void setDeadLineDate(String deadLineDate) {mDeadLineDate = deadLineDate;}
    public void setRealisationDate(String realisationDate) {mRealisationDate = realisationDate;}
    public void setUrlPictures(String urlPictures) {mUrlPictures = urlPictures;}
    public void setNewGravity(int newGravity) {mNewGravity = newGravity;}
    public void setNewFrequencies(int newFrequencies) {mNewFrequencies = newFrequencies;}
    public void setNewDetectability(int newDetectability) {mNewDetectability = newDetectability;}
    public void setApprobationTL(boolean approbationTL) {mApprobationTL = approbationTL;}
    public void setRiskId(long riskId) {mRiskId = riskId;}
    public void setParticipantId(long participantId) {mParticipantId = participantId;}
}
