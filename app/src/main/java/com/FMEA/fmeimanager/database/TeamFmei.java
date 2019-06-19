package com.FMEA.fmeimanager.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class TeamFmei {

    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "team_fmei_id") private long mTeamFmeiId;
    @ColumnInfo(name = "team_fmei_fmei_id") private long mFmeiId;
    @ColumnInfo(name = "team_fmei_participant_id") private long mParticipantId;

    public TeamFmei(long fmeiId, long participantId) {
        mFmeiId = fmeiId;
        mParticipantId = participantId;
    }

    // GETTER
    public long getTeamFmeiId() {return mTeamFmeiId;}
    public long getParticipantId() {return mParticipantId;}
    public long getFmeiId() {return mFmeiId;}

    // SETTER
    public void setTeamFmeiId(long teamFmeiId) {mTeamFmeiId = teamFmeiId;}
    public void setFmeiId(long fmeiId) {mFmeiId = fmeiId;}
    public void setParticipantId(long participantId) {mParticipantId = participantId;}
}
