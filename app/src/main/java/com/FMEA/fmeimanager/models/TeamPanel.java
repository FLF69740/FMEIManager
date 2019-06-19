package com.FMEA.fmeimanager.models;

import com.FMEA.fmeimanager.database.Participant;

import java.util.List;

public class TeamPanel {

    private long mFmeaId;
    private List<Participant> mParticipantList;
    private long mTeamLeaderId;

    public TeamPanel(long fmeiad, long teamLeaderId) {
        mFmeaId = fmeiad;
        mTeamLeaderId = teamLeaderId;
    }

    //GETTER
    public long getFmeaId() {return mFmeaId;}
    public List<Participant> getParticipantList() {return mParticipantList;}
    public long getTeamLeaderId() {return mTeamLeaderId;}

    //SETTER
    public void setParticipantList(List<Participant> participantList) {mParticipantList = participantList;}
}
